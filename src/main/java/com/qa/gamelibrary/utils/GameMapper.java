package com.qa.gamelibrary.utils;

import org.springframework.stereotype.Service;

import com.qa.gamelibrary.domain.Game;
import com.qa.gamelibrary.dto.GameDTO;

@Service
public class GameMapper implements Mapper<Game, GameDTO> {

	@Override
	public GameDTO mapToDTO(Game game) {
		GameDTO dto = new GameDTO();
		dto.setId(game.getId());
		dto.setName(game.getName());
		dto.setGenre(game.getGenre());
		dto.setProgress(game.getProgress());
		return dto;
	}

}
