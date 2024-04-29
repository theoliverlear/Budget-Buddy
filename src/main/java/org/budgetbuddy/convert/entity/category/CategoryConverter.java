package org.budgetbuddy.convert.entity.category;
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
        // Convert the Category to a string using its title.
        return category.getTitle();
    }
    //--------------------Convert-From-Database-Column------------------------
    @Override
    public Category convertToEntityAttribute(String categoryTitle) {
        // Convert the string title to a Category object.
        return new Category(categoryTitle);
    }
}
