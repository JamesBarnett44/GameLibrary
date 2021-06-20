package com.qa.gamelibrary.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.gamelibrary.domain.Game;
import com.qa.gamelibrary.dto.GameDTO;
import com.qa.gamelibrary.repo.GameRepo;
import com.qa.gamelibrary.service.GameService;

@SpringBootTest
@ActiveProfiles("test")
public class GameServiceUnitTest {

	@Autowired
	private GameService service;

	@MockBean
	private GameRepo repo;

	@Test
	void testCreate() {
		// GIVEN
		Game testData = new Game("Subnautica", "Survival", "Completed");
		Game created = new Game(2, "Subnautica", "Survival", "Completed");
		GameDTO createdDTO = new GameDTO(2, "Subnautica", "Survival", "Completed");

		// WHEN
		Mockito.when(this.repo.save(testData)).thenReturn(created);

		// THEN
		assertThat(this.service.createGame(testData)).isEqualTo(createdDTO);

		Mockito.verify(this.repo, Mockito.times(1)).save(testData);
	}

	@Test
	void testGetGames() {
		// GIVEN
		Game existing = new Game(1, "Bloodborne", "RPG", "Completed");
		List<Game> games = List.of(existing);

		GameDTO existingDTO = new GameDTO(1, "Bloodborne", "RPG", "Completed");
		List<GameDTO> dtos = List.of(existingDTO);

		// WHEN
		Mockito.when(this.repo.findAll()).thenReturn(games);

		// THEN
		assertThat(this.service.getGames()).isEqualTo(dtos);

	}

	@Test
	void testFindGame() {
		// GIVEN
		Integer testId = 1;
		Game existing = new Game(1, "Bloodborne", "RPG", "Completed");
		GameDTO existingDTO = new GameDTO(1, "Bloodborne", "RPG", "Completed");

		// WHEN
		Mockito.when(this.repo.findById(testId)).thenReturn(Optional.of(existing));

		// THEN
		assertThat(this.service.findGame(testId)).isEqualTo(existingDTO);
	}

	@Test
	void testUpdate() {
		// GIVEN
		Integer testId = 1;
		Game testData = new Game("Control", "Action", "Completed");
		Game existing = new Game(1, "Bloodborne", "RPG", "Completed");
		Game updatedGame = new Game(testId, "Control", "Action", "Completed");
		GameDTO updatedGameDTO = new GameDTO(testId, "Control", "Action", "Completed");

		// WHEN
		Mockito.when(this.repo.findById(testId)).thenReturn(Optional.of(existing));
		Mockito.when(this.repo.save(updatedGame)).thenReturn(updatedGame);

		// THEN
		assertThat(this.service.updateGame(testId, testData)).isEqualTo(updatedGameDTO);

		Mockito.verify(this.repo, Mockito.times(1)).findById(testId);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedGame);
	}

	@Test
	void testDelete() {
		// GIVEN
		Integer testId = 1;
		boolean exists = false;
		// WHEN
		Mockito.when(this.repo.existsById(testId)).thenReturn(exists);

		// THEN
		assertThat(this.service.removeGame(testId)).isEqualTo(!exists);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(testId);
	}

}
