package com.ninos.game;

import org.springframework.data.jpa.domain.Specification;

public class GameSpecifications {

    public static Specification<Game> byGameTitle(String gameTitle) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("title"), gameTitle);
    }

    public static Specification<Game> bySupportedPlatforms(SupportedPlatforms platforms) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("supportedPlatforms"), platforms);
    }

    public static Specification<Game> byCategoryName(String categoryName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category").get("name"), categoryName);
    }

}
