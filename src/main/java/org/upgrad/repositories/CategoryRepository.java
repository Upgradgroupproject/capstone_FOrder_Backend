package org.upgrad.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.upgrad.models.Category; 
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends CrudRepository<Category,Integer> {

    @Query(nativeQuery = true, value="select * from category")
    List<Category> getAllCategory();



    @Query(nativeQuery = true,value="select * from question where user_id=?1")
    List<Category> getAllCategoryByCategoryName(String categoryName);
}
