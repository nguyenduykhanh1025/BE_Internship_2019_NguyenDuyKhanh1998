package com.kunlez.bookstore.converters.categoriesConverter;

import com.kunlez.bookstore.DTO.CategoriesDTO;
import com.kunlez.bookstore.converters.base.Converter;
import com.kunlez.bookstore.entity.CategoriesEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriesEntityToCategoriesDTOConverter extends Converter<CategoriesEntity, CategoriesDTO> {
    @Override
    public CategoriesDTO convert(CategoriesEntity source) {
        CategoriesDTO categoriesDTO = new CategoriesDTO();

        categoriesDTO.setId(source.getId());
        categoriesDTO.setName(source.getName());
        return categoriesDTO;
    }
}
