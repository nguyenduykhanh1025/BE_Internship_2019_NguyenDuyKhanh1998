package com.kunlez.bookstore.services;

import com.kunlez.bookstore.DTO.AuthorDTO;
import com.kunlez.bookstore.DTO.BookDTO;
import com.kunlez.bookstore.DTO.CategoriesDTO;
import com.kunlez.bookstore.common.CommonMethot;
import com.kunlez.bookstore.configurations.TokenProvider;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.BookEntity;
import com.kunlez.bookstore.entity.CategoriesEntity;
import com.kunlez.bookstore.entity.UserEntity;
import com.kunlez.bookstore.exception.bookException.BookNotFound;
import com.kunlez.bookstore.exception.bookException.BookNotOfUser;
import com.kunlez.bookstore.repository.BookRepository;
import com.kunlez.bookstore.repository.CategoriesRepository;
import com.kunlez.bookstore.repository.UserRepository;
import com.kunlez.bookstore.sortCollection.sortItemFollowCreateUpdate;
import com.kunlez.bookstore.sortCollection.sortItemFollowDateUpdate;
import com.kunlez.bookstore.sortCollection.sortItemFollowName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

import static com.kunlez.bookstore.common.ConstantsCommon.*;

@Service
public class BookServices {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private Converter<BookEntity, BookDTO> bookEntityToBookDTOConverter;

    @Autowired
    private Converter<BookDTO, BookEntity> bookDTOToBookEntityConverter;

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TokenProvider jwtTokenUtil;


    public List<BookDTO> get(int numberItem, int indexPage, int idCategories,int valueSort){
        List<BookDTO> bookDTOS = new ArrayList<>();

        Set<BookEntity> bookEntityList = new HashSet<>();

        if(idCategories == ID_CATEGORIES_NONE){
            if(SORT_ITEM_FOLLOW_DATE_UPDATE == valueSort){
                bookEntityList =  bookRepository.findAllByOrderByUpdatedAtDesc();
            }else if(SORT_ITEM_FOLLOW_CREATE_AT == valueSort){
                bookEntityList = bookRepository.findAllByOrderByCreatedAtDesc();
            }else{
                bookEntityList = bookRepository.findAllByOrderByTitleAsc();
            }
        }
        else {
            CategoriesEntity categoriesEntity = categoriesRepository.findById(idCategories).get();

            if(SORT_ITEM_FOLLOW_DATE_UPDATE == valueSort){
                bookEntityList =  bookRepository.findAllByCategoriesEntitiesOrderByUpdatedAtDesc(categoriesEntity);
            }else if(SORT_ITEM_FOLLOW_CREATE_AT == valueSort){
                bookEntityList =  bookRepository.findAllByCategoriesEntitiesOrderByCreatedAtDesc(categoriesEntity);
            }else{
                bookEntityList =  bookRepository.findAllByCategoriesEntitiesOrderByTitleAsc(categoriesEntity);
            }
        }

        if(bookEntityList.size() != 0){
            int start =  (indexPage - 1 ) * numberItem;
            int stop = indexPage * numberItem > bookEntityList.size() ? bookEntityList.size() : indexPage * numberItem;

            for(BookEntity bookEntity : (new LinkedList<BookEntity>(bookEntityList)).subList(start,stop)){
                if(bookEntity.isEnable()){
                    bookDTOS.add(bookEntityToBookDTOConverter.convert(bookEntity));
                }
            }
        }

        return bookDTOS;
    }

    public List<BookDTO> getBookPagination(int indexPage, int numberItem){
        List<BookDTO> bookDTOList = new ArrayList<>();

        Set<BookEntity> bookEntities =  bookRepository.findAllIsEnableOrderByUpdateAt();

        int start = (indexPage - 1 ) * numberItem;
        int stop = indexPage * numberItem > bookEntities.size() ? bookEntities.size() : indexPage * numberItem;
        List<BookEntity> listBookLimit  = (new LinkedList<BookEntity>(bookEntities)).subList(start,stop);

        for(BookEntity bookEntity : listBookLimit){
            bookDTOList.add(bookEntityToBookDTOConverter.convert(bookEntity));
        }

        Collections.sort(bookDTOList, new sortItemFollowDateUpdate());

        return bookDTOList;
    }

    public BookDTO getBookFollowId(int id){
        return bookEntityToBookDTOConverter.convert(bookRepository.findById(id).get());
    }


    public List<BookDTO> getAllBookFollowCategories(int idCategories){
        List<BookDTO> bookDTOList = new ArrayList<>();

        CategoriesEntity categoriesEntity = categoriesRepository.findById(idCategories).get();

        for(BookEntity bookEntity : bookRepository.findAllByCategoriesEntities(categoriesEntity)){
            if(bookEntity.isEnable()){
                bookDTOList.add(bookEntityToBookDTOConverter.convert(bookEntity));
            }
        }

        Collections.sort(bookDTOList, new sortItemFollowDateUpdate());
        return bookDTOList;
    }

    public List<BookDTO> getAllBookFollowUser(String token){

        String username = CommonMethot.getUserName(token, jwtTokenUtil);

        List<BookDTO> bookDTOList = new ArrayList<>();

        // is user admin
        if(CommonMethot.getAllRole().contains("ROLE_ADMIN")){
            List<BookDTO> bookDTOS = new ArrayList<>();
            for(BookEntity bookEntity : bookRepository.findAll()){
                bookDTOS.add(bookEntityToBookDTOConverter.convert(bookEntity));
            }
            Collections.sort(bookDTOS, new sortItemFollowDateUpdate());
            return bookDTOS;
        }

        UserEntity userEntity = userRepository.findByEmail(username);

        for(BookEntity bookEntity : userEntity.getBookEntities()){
            bookDTOList.add(bookEntityToBookDTOConverter.convert(bookEntity));
        }

        Collections.sort(bookDTOList, new sortItemFollowDateUpdate());


        return bookDTOList;
    }


    public ResponseEntity<?> deleteBook(int id, String token){

        if(bookRepository.findById(id) == null){
            throw new BookNotFound();
        }
        BookEntity bookEntity = bookRepository.findById(id).get();

        if(CommonMethot.getAllRole().contains("ROLE_ADMIN")){
            bookRepository.delete(bookEntity);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        if(!bookEntity.getUserEntity().getEmail().equals(CommonMethot.getUserName(token, jwtTokenUtil))){
            throw new BookNotOfUser();
        }

        bookRepository.delete(bookEntity);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    public BookDTO post(BookDTO bookDTO){
        // client send title & description & nameCategories & linkImage & nameAuthor
        // set categories
        CategoriesDTO[] categoriesDTOS = new CategoriesDTO[bookDTO.getNameCategories().length];
        System.out.println(categoriesDTOS.length);
        String[] nameCategories = bookDTO.getNameCategories();
        for(int i = 0; i< bookDTO.getNameCategories().length; ++i){
            CategoriesDTO categoriesDTO = new CategoriesDTO();
            categoriesDTO.setName(nameCategories[i]);
            categoriesDTOS[i] = categoriesDTO;

        }
        bookDTO.setCategoriesDTOS(categoriesDTOS);

        // set author
        AuthorDTO[] authorDTOS = new AuthorDTO[bookDTO.getNameAuthor().length];
        for(int i = 0; i< bookDTO.getNameAuthor().length; ++i){

            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setName(bookDTO.getNameAuthor()[i]);
            authorDTOS[i] = authorDTO;
        }
        bookDTO.setAuthorDTOS(authorDTOS);

        // set create at
        bookDTO.setCreatedAt(new Date());

        // set update at
        bookDTO.setUpdateAt(new Date());

        BookEntity bookEntity = bookDTOToBookEntityConverter.convert(bookDTO);
        bookRepository.save(bookDTOToBookEntityConverter.convert(bookDTO));
        return bookDTO;

    }

    public ResponseEntity<?> update(BookDTO bookDTO, String token){
        // client send title & description & nameCategories & linkImage & nameAuthor
        // set categories

        CategoriesDTO[] categoriesDTOS = new CategoriesDTO[bookDTO.getNameCategories().length];

        String[] nameCategories = bookDTO.getNameCategories();
        for(int i = 0; i< bookDTO.getNameCategories().length; ++i){
            CategoriesDTO categoriesDTO = new CategoriesDTO();
            categoriesDTO.setName(nameCategories[i]);
            categoriesDTOS[i] = categoriesDTO;

        }
        bookDTO.setCategoriesDTOS(categoriesDTOS);

        // set author
        AuthorDTO[] authorDTOS = new AuthorDTO[bookDTO.getNameAuthor().length];
        for(int i = 0; i< bookDTO.getNameAuthor().length; ++i){

            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setName(bookDTO.getNameAuthor()[i]);
            authorDTOS[i] = authorDTO;
        }
        bookDTO.setAuthorDTOS(authorDTOS);

        // set update at
        bookDTO.setUpdateAt(new Date());

        bookDTO.setCreatedAt(bookRepository.findById(bookDTO.getId()).get().getCreatedAt());

        BookEntity bookEntity = bookDTOToBookEntityConverter.convert(bookDTO);

        bookEntity.setId(bookDTO.getId());

        bookRepository.save(bookEntity);

        return ResponseEntity.ok(bookDTO);
    }

    public void enableID(int id){
        BookEntity bookEntity = bookRepository.findById(id).get();
        bookEntity.setEnable(true);
        bookRepository.save(bookEntity);
    }

    public List<BookDTO> getSortListBook(int valueSort){

        List<BookDTO> bookDTOS = new ArrayList<>();

        for(BookEntity bookEntity : bookRepository.findAll()){
            if(bookEntity.isEnable()){
                bookDTOS.add(bookEntityToBookDTOConverter.convert(bookEntity));
            }
        }

        System.out.println(valueSort);
        if(valueSort == SORT_ITEM_FOLLOW_DATE_UPDATE){
            Collections.sort(bookDTOS, new sortItemFollowDateUpdate());
        }
        else if(valueSort == SORT_ITEM_FOLLOW_CREATE_AT){
            Collections.sort(bookDTOS, new sortItemFollowCreateUpdate());
        }else {
            Collections.sort(bookDTOS, new sortItemFollowName());
        }

        return bookDTOS;
    }

    public List<BookDTO> getBookFollowNameAuthor(String nameAuthor){
        List<BookDTO> bookDTOList = new ArrayList<>();

        for(BookEntity bookEntity : bookRepository.findAllHaveNameAuthorIsEnableOrderlyUpdateDate(nameAuthor.toLowerCase())){
            bookDTOList.add(bookEntityToBookDTOConverter.convert(bookEntity));
        }
        return bookDTOList;
    }

    public List<BookDTO> getListBookFollowNamePoster(String namePoster){
        List<BookDTO> bookDTOList = new ArrayList<>();

        UserEntity userEntity = userRepository.findByEmail(namePoster);

        System.out.println(namePoster);

        for(BookEntity bookEntity : bookRepository.findAllByUserEntityOrderByUpdatedAtDesc(userEntity)){
            if(bookEntity.isEnable()){
                bookDTOList.add(bookEntityToBookDTOConverter.convert(bookEntity));
            }
        }

        return bookDTOList;
    }

    public List<BookDTO> getAllBookSearchByKeyword(String keyword){

        List<BookDTO> bookDTOList = new ArrayList<>();

        for(BookEntity bookEntity : bookRepository.findAllLikeTitleLikeDescriptionNameAuthorLikeNameAuthorOrderByUpdateDate(keyword)){
            bookDTOList.add(bookEntityToBookDTOConverter.convert(bookEntity));
        }

        return bookDTOList;
    }
}
