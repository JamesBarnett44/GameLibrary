package com.qa.gamelibrary.utils;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.qa.gamelibrary.domain.Game;
import com.qa.gamelibrary.domain.Platform;
import com.qa.gamelibrary.dto.GameDTO;
import com.qa.gamelibrary.dto.PlatformDTO;

@Service
public class PlatformMapper implements Mapper<Platform, PlatformDTO>{
	
	private GameMapper gameMapper;
	
	public PlatformMapper(GameMapper gameMapper) {
		super();
		this.gameMapper = gameMapper;
	}
	
	@Override
	public PlatformDTO mapToDTO(Platform platform) {
		PlatformDTO dto = new PlatformDTO();
		dto.setId(platform.getId());
		dto.setName(platform.getName());
		List<GameDTO> games = new ArrayList<>();
		for (Game game : platform.getGames()) {
			games.add(this.gameMapper.mapToDTO(game));
		}
		dto.setGames(games);
		return dto;
	}
}
