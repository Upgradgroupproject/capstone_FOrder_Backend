package org.upgrad.services;


import org.upgrad.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategory(String categoryName);
    
}
