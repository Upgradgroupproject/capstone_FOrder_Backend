package org.upgrad.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.upgrad.models.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {

    @Query(nativeQuery = true, value="select * from category")
    List<Category> getAllCategory();

    @Query(nativeQuery = true,value="select * from question where user_id=?1")
    List<Category> getAllCategoryByCategoryName(String categoryName);

    @Query(nativeQuery = true, value = "select * from category where category_name ILIKE %?1%")
    Category findCategoryByName(String name);

}


