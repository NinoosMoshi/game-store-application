package com.ninos.game;

import com.ninos.category.Category;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GameMapper {

    public Game toGame(GameRequest gameRequest) {
        return Game.builder()
                .title(gameRequest.title())
                .category(Category.builder().id(gameRequest.categoryId()).build())
                .build();
    }

    public GameResponse toGameResponse(Game game) {
        return GameResponse.builder()
                .id(game.getId())
                .title(game.getTitle())
                // fixme set the CDN URL
                .imageUrl("Fix-ME")
                .platforms(
                        game.getPlatforms().stream()
                                .map(p -> p.getConsole().name()).collect(Collectors.toSet())
                )
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

    /*
    *  public GameResponse toGameResponse(Game game) {
        GameResponse gameResponse = new GameResponse();
        BeanUtils.copyProperties(game, gameResponse);

        // Fixme set the CDN URL
        gameResponse.setImageUrl("Fix-ME");

        gameResponse.setPlatforms(
                game.getPlatforms().stream()
                        .map(p -> p.getConsole().name())
                        .collect(Collectors.toSet())
        );

        return gameResponse;
    }
    * */

}
