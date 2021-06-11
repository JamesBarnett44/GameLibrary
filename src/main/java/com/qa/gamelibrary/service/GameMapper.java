package com.qa.gamelibrary.service;

import org.springframework.stereotype.Service;

import com.qa.gamelibrary.domain.Game;
import com.qa.gamelibrary.dto.GameDTO;

@Service
public class GameMapper {
	
	public GameDTO mapToDTO(Game game) {
		GameDTO dto = new GameDTO();
		dto.setId(game.getId());
		dto.setName(game.getName());
		dto.setGenre(game.getGenre());
		dto.setProgress(game.getProgress());
		//dto.setPlatformId(game.getPlatformId());
		return dto;
	}

}
