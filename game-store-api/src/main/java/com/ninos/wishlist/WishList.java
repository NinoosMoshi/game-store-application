package com.ninos.wishlist;

import com.ninos.common.BaseEntity;
import com.ninos.game.Game;
import com.ninos.user.User;
import jakarta.persistence.*;
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
public class WishList extends BaseEntity {

    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToMany(mappedBy = "wishlists", fetch = FetchType.EAGER)
    private List<Game> games;


}
