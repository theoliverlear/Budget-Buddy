package org.budgetbuddy.convert.entity;
//=================================-Imports-==================================

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.budgetbuddy.entity.category.Category;

@Converter(autoApply = true)
public class CategoryConverter implements AttributeConverter<Category, String> {
    //============================-Variables-=================================
    ObjectMapper objectMapper;
    //===========================-Constructors-===============================
    public CategoryConverter() {
        this.objectMapper = new ObjectMapper();
    }
    //============================-Methods-===================================

    //---------------------Convert-To-Database-Column-------------------------
    @Override
    public String convertToDatabaseColumn(Category category) {
        return category.getTitle();
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Category convertToEntityAttribute(String categoryTitle) {
        return new Category(categoryTitle);
    }
}
