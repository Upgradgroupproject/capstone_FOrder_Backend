package org.upgrad.services;

import org.springframework.stereotype.Service;
import org.upgrad.models.Category;
import org.upgrad.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    private  final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {

        this.categoryRepository =categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category getCategory(String categoryName) {
        return null;
    }
}
