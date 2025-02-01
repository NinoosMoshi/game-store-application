package com.ninos.user;

import com.ninos.common.BaseEntity;
import com.ninos.gamerequest.GameRequestEntity;
import com.ninos.notification.Notification;
import com.ninos.wishlist.WishList;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Table(name = "_user")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String profilePictureUrl;

    @OneToOne(mappedBy = "user")
    private WishList wishList;


    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user")
    private List<GameRequestEntity> gameRequests;

}
