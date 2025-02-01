package com.ninos.game;

import com.ninos.category.CategoryRepository;
import com.ninos.common.PageResponse;
import com.ninos.platform.Console;
import com.ninos.platform.Platform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameService {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final PlatformRepository platformRepository;
    private final GameMapper gameMapper;
    private final LocaleResolver localeResolver;


    public String saveGame(final GameRequest gameRequest){

        // Check for Duplicate Game Title
        if(gameRepository.existsByTitle(gameRequest.title())){
            log.info("Game already exists: {}", gameRequest.title());
            // todo create a dedicated exception
            throw new RuntimeException("Game already exists");
        }


        // Validate Platforms
        final List<Console> selectedConsoles = gameRequest.platforms()
                .stream()
                .map(Console::valueOf) // .map(p -> Console.valueOf(p))
                .toList();

        // fetch platforms that match the provided console list
        final List<Platform> platforms = platformRepository.findAllByConsoleIn(selectedConsoles);

        // checks if all requested platforms exist in the database
        if (platforms.size() != selectedConsoles.size()) {
            log.warn("Received a non supported platforms. Received: {} - Stored: {}",selectedConsoles, platforms);
            // todo create a dedicated exception
            throw new RuntimeException("one or more platforms not supported");
        }

        if(!categoryRepository.existsById(gameRequest.categoryId())){
            log.warn("Category does not exist: {}", gameRequest.categoryId());
            // todo create a dedicated exception
            throw new RuntimeException("Category does not exist");
        }

        final Game game = gameMapper.toGame(gameRequest);

        // The retrieved platform list is assigned to the game entity.
        game.setPlatforms(platforms);
        final Game savedGame = gameRepository.save(game);
        // todo do we need to assign the game to the selectedPlatforms!?
        return savedGame.getId();
    }


    public void updateGame(String gameId, GameRequest gameRequest){

    }


    public String uploadGameImage(MultipartFile file, String gameId){
      return null;
    }

    // the result should be paginated
    public PageResponse<GameResponse> findAllGames(int page, int size){

        return null;
    }


    public void deleteGame(String gameId){

    }


}
