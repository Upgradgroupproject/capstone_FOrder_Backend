package org.upgrad.controllers;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
package org.upgrad.model.Category;
package org.upgrad.service.CategoryService;

@RestController
@RequestMapping("/Category")


public class CategoryController {
    @Autowired

    CategoryService categoryService;

    @GetMapping("/api/category")
    public ResponseEntity<?> getAllCategory(HttpSession session) {

        if (session.getAttribute("Category")!==null) {
        return new ResponseEntity<>(categoryService.getAllCategory(categoryService.getCategoryId ((String))), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/")
	public ModelAndView listCategory(ModelAndView model) throws IOException {
		List<Category> listCategory = CategoryService.getAllCategory();
		model.addObject("listCategory", listCategory);
		//model.setViewName("home");
		return model;
    }
    
    @GetMapping("/api/category/{categoryName}")
    public ResponseEntity<?> getAllCategoryByCategoryName(HttpSession session) {

        if (session.getAttribute("categoryName")==null) {
            return new ResponseEntity<>("No Category by this name!", HttpStatus.NOTFOUND);
        }

        else {
            return new ResponseEntity<>(categoryService.getAllCategoryByCategoryName(CategoryService.getCategoryId ((String) session.getAttribute("CategoryName"))), HttpStatus.OK);
        }
    }
}

