package com.ninos.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {


    long countByGameId(String gameId);
}
