package org.upgrad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.models.Category;
import org.upgrad.repositories.CategoryRepository;

import java.util.List;

/*
 * Services/ Methods to call Repository or SQL scripts, these services are called from Category controller
 *
 * */

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private  final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {

        this.categoryRepository =categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {

        return categoryRepository.getAllCategory ();
    }

    @Override
    public Category getCategory(String categoryName) {

        return categoryRepository.findCategoryByName (categoryName);
    }


}
