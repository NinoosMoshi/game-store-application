package com.ninos.category;

import com.ninos.common.BaseEntity;
import com.ninos.game.Game;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQuery(name="Category.namedQueryFindByName",
        query = "SELECT c FROM Category c " +
                "WHERE c.name LIKE lower(:catName) " +
                "ORDER BY c.name ASC ")
public class Category extends BaseEntity {

    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Game> games;


}
