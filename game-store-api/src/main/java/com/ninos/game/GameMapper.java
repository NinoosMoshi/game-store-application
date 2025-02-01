package com.ninos.game;

import com.ninos.category.Category;
import org.springframework.stereotype.Service;

@Service
public class GameMapper {

    public Game toGame(GameRequest gameRequest) {
        return Game.builder()
                .title(gameRequest.title())
                .category(Category.builder().id(gameRequest.categoryId()).build())
                .build();
    }

    /*
      public Game toGame(GameRequest gameRequest) {
        Game game = new Game();
        BeanUtils.copyProperties(gameRequest, game);

        // Set the category if categoryId is provided
        if (gameRequest.categoryId() != null) {
            Category category = new Category();
            category.setId(gameRequest.categoryId());
            game.setCategory(category);
        }

        return game;
    }
    * */

}
