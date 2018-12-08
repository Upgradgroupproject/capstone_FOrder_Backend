package org.upgrad.services;


public interface CategoryService {
    List<Category> getAllCategory();
    List<Category> getAllCategoryByCategoryName(CategoryName);   
}
