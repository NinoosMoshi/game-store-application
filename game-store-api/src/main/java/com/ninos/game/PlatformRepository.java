package com.ninos.game;

import com.ninos.platform.Console;
import com.ninos.platform.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlatformRepository extends JpaRepository<Platform, String> {

    @Query("SELECT p FROM Platform p WHERE p.console IN :consoles")
    List<Platform> findAllByConsoleIn(@Param("consoles") List<Console> selectedConsoles);
}
