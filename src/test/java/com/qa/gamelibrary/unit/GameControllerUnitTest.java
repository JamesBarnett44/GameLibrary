package com.qa.gamelibrary.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.gamelibrary.domain.Game;
import com.qa.gamelibrary.dto.GameDTO;
import com.qa.gamelibrary.rest.GameController;
import com.qa.gamelibrary.service.GameService;

@SpringBootTest
@ActiveProfiles("test")
public class GameControllerUnitTest {

	@Autowired
	private GameController controller;

	@MockBean
	private GameService service;

	@Test
	void testCreateGame() {
		// GIVEN
		Game testData = new Game("Subnautica", "Survival", "Completed");
		GameDTO createdDTO = new GameDTO(2, "Subnautica", "Survival", "Completed");

		// WHEN
		Mockito.when(service.createGame(testData)).thenReturn(createdDTO);

		// THEN
		assertThat(this.controller.createGame(testData)).isEqualTo(createdDTO);

		Mockito.verify(this.service, Mockito.times(1)).createGame(testData);
	}

	@Test
	void testGetGames() {
		// GIVEN
		GameDTO existingDTO = new GameDTO(1, "Bloodborne", "RPG", "Completed");
		List<GameDTO> dtos = List.of(existingDTO);

		// WHEN
		Mockito.when(this.service.getGames()).thenReturn(dtos);

		// THEN
		assertThat(this.controller.getGames()).isEqualTo(dtos);
		
		Mockito.verify(this.service, Mockito.times(1)).getGames();

	}

	@Test
	void testFindGame() {
		// GIVEN
		Integer testId = 1;
		GameDTO existingDTO = new GameDTO(1, "Bloodborne", "RPG", "Completed");

		// WHEN
		Mockito.when(this.service.findGame(testId)).thenReturn(existingDTO);

		// THEN
		assertThat(this.controller.findGame(testId)).isEqualTo(existingDTO);
	}

	@Test
	void testUpdateGame() {
		// GIVEN
		Integer testId = 1;
		Game testData = new Game(1, "Control", "Action", "Completed");
		Game updatedGame = new Game(testId, "Control", "Action", "Completed");
		GameDTO updatedGameDTO = new GameDTO(testId, "Control", "Action", "Completed");

		// WHEN
		Mockito.when(this.service.updateGame(testId, testData)).thenReturn(updatedGameDTO);

		// THEN
		assertThat(this.controller.updateGame(testData, testId)).isEqualTo(updatedGameDTO);

		Mockito.verify(this.service, Mockito.times(1)).updateGame(testId, updatedGame);
	}

	@Test
	void testDelete() {
		// GIVEN
		Integer testId = 1;
		// WHEN
		Mockito.when(this.service.removeGame(testId)).thenReturn(true);

		// THEN
		assertThat(this.controller.deleteGame(testId)).isTrue();

		Mockito.verify(this.service, Mockito.times(1)).removeGame(testId);
	}

}
