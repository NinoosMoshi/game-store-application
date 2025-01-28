package com.ninos.game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, String> {

    List<Game> findAllByCategoryName(String categoryName);

    // JPQL
    /*@Query("""
    SELECT g FROM Game g
    INNER JOIN Category c ON g.category.id = c.id
    WHERE c.name LIKE :catName       
    """)*/
    @Query("""
    SELECT g FROM Game g
    INNER JOIN g.category c
    WHERE c.name LIKE :catName       
    """)
    List<Game> findAllByCat(@Param("catName") String catName);


    @Query("update Game set title = upper(title) ")
    @Modifying  // when you have update, delete, insert you should to put this annotation
    void transformGamesTitleToUpperCase();


}
