package com.kunlez.bookstore.repository;

import com.kunlez.bookstore.entity.AuthorEntity;
import com.kunlez.bookstore.entity.BookEntity;
import com.kunlez.bookstore.entity.CategoriesEntity;
import com.kunlez.bookstore.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    Set<BookEntity> findAllByCategoriesEntities(CategoriesEntity categoriesEntity);

    @Query(
            value = "select b.* from book as b join book_author as b_a on b.id = b_a.book_id join author as a on b_a.author_id = a.id where a.name = :nameAuthor",
            nativeQuery = true
    )
    Set<BookEntity> findAllByNameAuthor(@Param("nameAuthor") String nameAuthor);

    @Query(
            value = "select b.* from book as b where b.enable = true order by b.updated_at desc",
            nativeQuery = true
    )
    Set<BookEntity> findAllIsEnableOrderByUpdateAt();




    @Query(
            value = "select b.* from book as b inner join book_author as b_a on b.id = b_a.book_id inner join author as a on b_a.author_id = a.id where b.enable = true and a.name = :nameAuthor order by b.updated_at desc",
            nativeQuery = true
    )
    Set<BookEntity> findAllHaveNameAuthorIsEnableOrderlyUpdateDate(@Param("nameAuthor") String nameAuthor);


    Set<BookEntity> findAllByUserEntityOrderByUpdatedAtDesc(UserEntity userEntity);

    @Query(
            value = "SELECT DISTINCT b.* FROM book as b \n" +
                    "inner join book_author as b_a on b.id = b_a.book_id \n" +
                    "inner join author as a on a.id = b_a.author_id \n" +
                    "inner join book_categories as b_c on b.id = b_c.book_id \n" +
                    "inner join categories as c on c.id = b_c.categories_id \n" +
                    "where b.title like CONCAT('%',:keyword,'%') \n" +
                    "or b.description like CONCAT('%',:keyword,'%') \n" +
                    "or a.name like CONCAT('%',:keyword,'%') \n" +
                    "or c.name like CONCAT('%',:keyword,'%') \n" +
                    "and b.enable = true \n" +
                    "order by b.updated_at desc",
            nativeQuery = true
    )
    Set<BookEntity> findAllLikeTitleLikeDescriptionNameAuthorLikeNameAuthorOrderByUpdateDate(@Param("keyword") String keyword);


    @Query(
            value = "SELECT DISTINCT b.* FROM book as b \n" +
                    "inner join book_author as b_a on b.id = b_a.book_id \n" +
                    "inner join author as a on a.id = b_a.author_id \n" +
                    "inner join book_categories as b_c on b.id = b_c.book_id \n" +
                    "inner join categories as c on c.id = b_c.categories_id \n" +
                    "where b.title like CONCAT('%',:keyword,'%') \n" +
                    "or b.description like CONCAT('%',:keyword,'%') \n" +
                    "or a.name like CONCAT('%',:keyword,'%') \n" +
                    "or c.name like CONCAT('%',:keyword,'%') \n" +
                    "and b.enable = true \n" +
                    "order by b.create_at desc",
            nativeQuery = true
    )
    Set<BookEntity> findAllLikeTitleLikeDescriptionNameAuthorLikeNameAuthorOrderByCreateDate(@Param("keyword") String keyword);


    @Query(
            value = "SELECT DISTINCT b.* FROM book as b \n" +
                    "inner join book_author as b_a on b.id = b_a.book_id \n" +
                    "inner join author as a on a.id = b_a.author_id \n" +
                    "inner join book_categories as b_c on b.id = b_c.book_id \n" +
                    "inner join categories as c on c.id = b_c.categories_id \n" +
                    "where b.title like CONCAT('%',:keyword,'%') \n" +
                    "or b.description like CONCAT('%',:keyword,'%') \n" +
                    "or a.name like CONCAT('%',:keyword,'%') \n" +
                    "or c.name like CONCAT('%',:keyword,'%') \n" +
                    "and b.enable = true \n" +
                    "order by b.title asc",
            nativeQuery = true
    )
    Set<BookEntity> findAllLikeTitleLikeDescriptionNameAuthorLikeNameAuthorOrderByTitleBook(@Param("keyword") String keyword);


    @Query(
            value = "SELECT DISTINCT b.* FROM book as b \n" +
                    "inner join book_author as b_a on b.id = b_a.book_id \n" +
                    "inner join author as a on a.id = b_a.author_id \n" +
                    "inner join book_categories as b_c on b.id = b_c.book_id \n" +
                    "inner join categories as c on c.id = b_c.categories_id \n" +
                    "where (b.title like CONCAT('%',:keyword,'%') \n" +
                    "or b.description like CONCAT('%',:keyword,'%') \n" +
                    "or a.name like CONCAT('%',:keyword,'%') \n" +
                    "or c.name like CONCAT('%',:keyword,'%')) \n" +
                    "and (b.enable = true \n" +
                    "and c.id = :idCategories) \n" +
                    "order by b.updated_at desc",
            nativeQuery = true
    )
    Set<BookEntity> findAllByIdCategoriesLikeTitleLikeDescriptionNameAuthorLikeNameAuthorOrderByUpdateDate(@Param("keyword") String keyword, @Param("idCategories") int idCategories);



    @Query(
            value = "SELECT DISTINCT b.* FROM book as b \n" +
                    "inner join book_author as b_a on b.id = b_a.book_id \n" +
                    "inner join author as a on a.id = b_a.author_id \n" +
                    "inner join book_categories as b_c on b.id = b_c.book_id \n" +
                    "inner join categories as c on c.id = b_c.categories_id \n" +
                    "where (b.title like CONCAT('%',:keyword,'%') \n" +
                    "or b.description like CONCAT('%',:keyword,'%') \n" +
                    "or a.name like CONCAT('%',:keyword,'%') \n" +
                    "or c.name like CONCAT('%',:keyword,'%')) \n" +
                    "and (b.enable = true \n" +
                    "and c.id = :idCategories) \n" +
                    "order by b.create_at desc",
            nativeQuery = true
    )
    Set<BookEntity> findAllByIdCategoriesLikeTitleLikeDescriptionNameAuthorLikeNameAuthorOrderByCreateDate(@Param("keyword") String keyword, @Param("idCategories") int idCategories);



    @Query(
            value = "SELECT DISTINCT b.* FROM book as b \n" +
                    "inner join book_author as b_a on b.id = b_a.book_id \n" +
                    "inner join author as a on a.id = b_a.author_id \n" +
                    "inner join book_categories as b_c on b.id = b_c.book_id \n" +
                    "inner join categories as c on c.id = b_c.categories_id \n" +
                    "where (b.title like CONCAT('%',:keyword,'%') \n" +
                    "or b.description like CONCAT('%',:keyword,'%') \n" +
                    "or a.name like CONCAT('%',:keyword,'%') \n" +
                    "or c.name like CONCAT('%',:keyword,'%')) \n" +
                    "and (b.enable = true \n" +
                    "and c.id = :idCategories) \n" +
                    "order by b.title asc",
            nativeQuery = true
    )
    Set<BookEntity> findAllByIdCategoriesLikeTitleLikeDescriptionNameAuthorLikeNameAuthorOrderByTitleBook(@Param("keyword") String keyword, @Param("idCategories") int idCategories);
}
