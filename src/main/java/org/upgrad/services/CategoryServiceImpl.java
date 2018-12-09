package org.upgrad.services;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {

        this.categoryRepository =categoryRepository;
    }
    
@Override
public List<Category> getAllCategory() {
    return categoryRepository.getAllCategory();
}

@Override
    public List<Category> getAllCategoryByCategoryName(CategoryName) {
        return categoryRepository.getAllCategoryByCategoryName(CategoryName);
    }
}
