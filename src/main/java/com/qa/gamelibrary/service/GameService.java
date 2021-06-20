package com.qa.gamelibrary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.gamelibrary.domain.Game;
import com.qa.gamelibrary.dto.GameDTO;
import com.qa.gamelibrary.repo.GameRepo;
import com.qa.gamelibrary.utils.GameMapper;

@Service
public class GameService {

	private GameRepo repo;

	private GameMapper mapper;

	public GameService(GameRepo repo, GameMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	// CREATE
	public GameDTO createGame(Game game) {
		Game saved = repo.save(game);
		return mapper.mapToDTO(saved);
	}

	// READ
	public List<GameDTO> getGames() {
		List<Game> games = this.repo.findAll();
		List<GameDTO> dtos = new ArrayList<>();
		for (Game game : games) {
			GameDTO dto = this.mapper.mapToDTO(game);
			dtos.add(dto);
		}
		return dtos;
	}

	public GameDTO findGame(Integer id) {
		Optional<Game> optionalGame = this.repo.findById(id);
		Game found = optionalGame.orElseThrow(() -> new EntityNotFoundException());
		return this.mapper.mapToDTO(found);
	}

	// UPDATE
	public GameDTO updateGame(Integer id, Game update) {
		Game existing = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		existing.setName(update.getName());
		existing.setGenre(update.getGenre());
		existing.setProgress(update.getProgress());
		Game updated = this.repo.save(existing);
		return this.mapper.mapToDTO(updated);
	}
	
	// DELETE
	public boolean removeGame(@PathVariable Integer id) {
    	this.repo.deleteById(id);
		return !this.repo.existsById(id);
    }

}
