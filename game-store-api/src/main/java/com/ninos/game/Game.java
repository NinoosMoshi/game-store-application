package com.ninos.game;

import com.ninos.category.Category;
import com.ninos.comment.Comment;
import com.ninos.common.BaseEntity;
import com.ninos.platform.Console;
import com.ninos.platform.Platform;
import com.ninos.wishlist.WishList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Game extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String title;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    private List<Platform> platforms;

    private String coverPicture;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // orphanRemoval: mean if you delete a game it will delete all the comments that related to this game
    @OneToMany(mappedBy = "game", orphanRemoval = true)
//    @OrderBy(value = "content DESC")   // when we get a game we will get a sorted comment desc always
    @OrderBy(value = "content")   // when we get a game we will get a sorted comment asc always
    private List<Comment> comments;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "game_wishlist",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "wishlist_id")}
    )
    private List<WishList> wishlists;

    public void addWishlist(WishList wishlist) {
        this.wishlists.add(wishlist);
        wishlist.getGames().add(this);
    }

    public void removeWishlist(WishList wishlist) {
        this.wishlists.remove(wishlist);
        wishlist.getGames().remove(this);
    }


    public void addPlatform(Platform platform) {
        this.platforms.add(platform);
        platform.getGames().add(this);
    }

    public void removePlatform(Platform platform) {
        this.platforms.remove(platform);
        platform.getGames().remove(this);
    }

}
