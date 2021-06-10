package com.qa.gamelibrary.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.gamelibrary.domain.Game;

@Repository
public interface GameRepo extends JpaRepository<Game, Integer>{

}
