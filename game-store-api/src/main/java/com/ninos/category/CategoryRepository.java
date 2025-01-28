package com.ninos.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category> findAllByNameStartingWithIgnoreCaseOrderByNameAsc(String name);

    // JPQL
    @Query("""
    SELECT c FROM Category c
    WHERE c.name LIKE lower(:catName) 
    ORDER BY c.name ASC    
    """)
    List<Category> findAllByName(@Param("catName") String categoryName);


    @Query(value = "select * from category where name like lower(:catName) order by name asc ",nativeQuery = true)
    List<Category> findAllByUsingNativeQuery(@Param("catName") String categoryName);

    @Query(name = "Category.namedQueryFindByName")
    List<Category> findAllByNameQuery(@Param("catName") String categoryName);

}
