package org.upgrad.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.upgrad.models.Category;
import org.upgrad.services.CategoryService;

@RestController
@RequestMapping("/category")


public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<> (categoryService.getAllCategories (), HttpStatus.OK);
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<?> getCategoriesByName(@PathVariable("categoryName") String categoryName) {

        Category category = categoryService.getCategory (categoryName);
        if (category != null) {
            return new ResponseEntity<> (category, HttpStatus.OK);
        } else {
            return new ResponseEntity<> ("No Category by this name!", HttpStatus.NOT_FOUND);
        }

    }

}

