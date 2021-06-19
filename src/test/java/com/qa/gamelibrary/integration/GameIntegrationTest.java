package com.qa.gamelibrary.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.gamelibrary.domain.Game;
import com.qa.gamelibrary.dto.GameDTO;
import com.qa.gamelibrary.repo.GameRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql",
		"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class GameIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private GameRepo repo;

	@Test
	void testCreate() throws Exception {
		Game testGame = new Game("Subnautica", "RPG", "Completed");
		String testGameAsJSON = this.mapper.writeValueAsString(testGame);

		GameDTO testSavedGame = new GameDTO(2, "Subnautica", "RPG", "Completed");
		String testSavedGameAsJSON = this.mapper.writeValueAsString(testSavedGame);
		System.out.println(testSavedGameAsJSON);

		this.mvc.perform(post("/games/create").content(testGameAsJSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(testSavedGameAsJSON));
	}

	@Test
	void testGetAll() throws Exception {
		GameDTO testGame = new GameDTO(1, "Bloodborne", "RPG", "Completed");
		List<GameDTO> testPeople = new ArrayList<>();
		testPeople.add(testGame);

		String testPeopleAsJSONArray = this.mapper.writeValueAsString(testPeople);

		this.mvc.perform(get("/games/")).andExpect(status().isOk()).andExpect(content().json(testPeopleAsJSONArray));
	}

	@Test
	void testUpdate() throws Exception {
		Game testGame = new Game(1, "Subnautica", "Survival", "In Progress");
		String testGameAsJSON = this.mapper.writeValueAsString(testGame);
		System.out.println(testGameAsJSON);

		GameDTO testGameDTO = new GameDTO(1, "Subnautica", "Survival", "In Progress");
		String testGameDTOAsJSON = this.mapper.writeValueAsString(testGameDTO);

		this.mvc.perform(put("/games/update/1").content(testGameAsJSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(testGameDTOAsJSON));
	}

	@Test
	void testDelete() throws Exception {
		assertThat(repo.existsById(1)).isTrue();
		this.mvc.perform(delete("/games/delete/1")).andExpect(status().isOk());
		assertThat(repo.existsById(1)).isFalse();
	}

}
