package com.ninos.notification;

import com.ninos.common.BaseEntity;
import com.ninos.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification extends BaseEntity {

    private String message;
    private String receiver;

    @Enumerated(EnumType.STRING)
    private NotificationLevel level;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
