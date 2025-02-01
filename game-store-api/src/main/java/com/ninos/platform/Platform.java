package com.ninos.platform;

import com.ninos.common.BaseEntity;
import com.ninos.game.Game;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
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
public class Platform extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Console console;

    @ManyToMany(mappedBy = "platforms")
    private List<Game> games;

}
