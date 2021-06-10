package com.qa.gamelibrary.service;

import org.springframework.stereotype.Service;

import com.qa.gamelibrary.domain.Game;
import com.qa.gamelibrary.dto.GameDTO;
import com.qa.gamelibrary.repo.GameRepo;

@Service
public class GameService {
	
	private GameRepo repo;
	
	//private GameMapper mapper;
	
	public GameService(GameRepo repo) {
		super();
		this.repo = repo;
	}
	
	//CREATE
	public GameDTO createGame(Game game) {
		repo.save(game);
		GameDTO dto = new GameDTO();
		dto.setName(game.getName());
		dto.setGenre(game.getGenre());
		dto.setProgress(game.getProgress());
		dto.setPlatformId(game.getPlatformId());
		return dto;
	}
	
	//READ
	

}
