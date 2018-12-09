package org.upgrad.repositories;

import org.omg.CORBA.INTERNAL;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.upgrad.models.Category;

public interface CategoryRepository extends CrudRepository<Category,Integer>{

    @Query(nativeQuery = true, value = "SELECT * FROM CATEGORY WHERE category_name = ?1")
    Category findCategoryByName(String name);
}
