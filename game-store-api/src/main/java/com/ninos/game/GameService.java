package com.ninos.game;

import ch.qos.logback.core.util.StringUtil;
import com.ninos.common.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    public PageResponse<Game> pageResult(final int page, final int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "title","createTime"));
        Page<Game> pagedResult = gameRepository.findAllByCategoryName("ninos", pageable);

        return PageResponse.<Game>builder()
                .content(pagedResult.getContent())
                .totalPages(pagedResult.getTotalPages())
                .totalElements(pagedResult.getNumberOfElements())
                .isFirst(pagedResult.isFirst())
                .isLast(pagedResult.isLast())
                .build();
    }


    public void queryByExampleCaseSensitive() {
        Game game = new Game();
        game.setTitle("roblox123"); // in my DB --> Roblox, it will not return anything because R is uppercase
        game.setSupportedPlatforms(SupportedPlatforms.PC);

        Example<Game> example = Example.of(game);

        Optional<Game> myGame = gameRepository.findOne(example);
    }


    public void queryByExampleInCaseSensitive() {
        Game game = new Game();
        game.setTitle("roblox123");
        game.setSupportedPlatforms(SupportedPlatforms.PC);

        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase();
        Example<Game> example = Example.of(game, matcher);

        Optional<Game> myGame = gameRepository.findOne(example);
    }


    public void queryByExampleCustomMatching() {
        Game game = new Game();
        game.setTitle("roblox");
        game.setSupportedPlatforms(SupportedPlatforms.PC);

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("supportedPlatforms", ExampleMatcher.GenericPropertyMatchers.exact());

        Example<Game> example = Example.of(game, matcher);

        List<Game> myGame = gameRepository.findAll(example);
    }

    public void queryByExampleWithIgnoringProperties() {
        Game game = new Game();
        game.setTitle("roblox");

    }


    public void specificationExample1() {
        Specification<Game> spec = buildSpecificationWithAndOperator("roblox", SupportedPlatforms.PS);
        List<Game> games = gameRepository.findAll(spec);
    }

    public void specificationExample2() {
        Specification<Game> spec = buildSpecificationWithOrOperator("roblox", SupportedPlatforms.PS);
        List<Game> games = gameRepository.findAll(spec);
    }

    private Specification<Game> buildSpecificationWithAndOperator(String title, SupportedPlatforms platform) {
        Specification<Game> spec = Specification.where(null);

        if(StringUtils.hasLength("title")) {
            spec = spec.and(GameSpecifications.byGameTitle(title));
        }
        if(platform != null) {
            spec = spec.and(GameSpecifications.bySupportedPlatforms(platform));
        }

        return spec;
    }


    private Specification<Game> buildSpecificationWithOrOperator(String title, SupportedPlatforms platform) {
        Specification<Game> spec = Specification.where(null);

        if(StringUtils.hasLength("title")) {
            spec = spec.and(GameSpecifications.byGameTitle(title));
        }
        if(platform != null) {
            spec = spec.or(GameSpecifications.bySupportedPlatforms(platform));
        }

        return spec;
    }



}
