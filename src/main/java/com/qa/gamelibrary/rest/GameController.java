package com.qa.gamelibrary.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.gamelibrary.domain.Game;
import com.qa.gamelibrary.dto.GameDTO;
import com.qa.gamelibrary.service.GameService;

@RestController
@RequestMapping("/games")
public class GameController {

	private GameService service;

	@Autowired
	public GameController(GameService service) {
		super();
		this.service = service;
	}

	@GetMapping(path = "/test")
	public String test() {
		return "Hello World";
	}

	// CREATE
	@PostMapping("/create")
	public GameDTO createGame(@RequestBody Game game) {
		return this.service.createGame(game);
	}

	// READ
	@GetMapping("/")
	public List<GameDTO> getGames() {
		return this.service.getGames();
	}
	
	@GetMapping("/{id}")
	public GameDTO findGame(@PathVariable Integer id) {
		return this.service.findGame(id);
	}
	
	// UPDATE
	@PutMapping("/update/{id}")
	public GameDTO updateGame(@RequestBody Game game, @PathVariable Integer id) {
		return this.service.updateGame(id, game);
	}
	
	// DELETE
	@DeleteMapping("/delete/{id}")
	public boolean deleteGame(@PathVariable Integer id) {
		return this.service.removeGame(id);
	}

}
