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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.gamelibrary.domain.Platform;
import com.qa.gamelibrary.dto.GameDTO;
import com.qa.gamelibrary.dto.PlatformDTO;
import com.qa.gamelibrary.repo.PlatformRepo;

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql",
"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class PlatformIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private PlatformRepo repo;

	@Test
	public void testCreate() throws Exception {
		Platform testPlatform = new Platform("GoG");
		String testPlatformAsJSON = this.mapper.writeValueAsString(testPlatform);

		Platform testSavedPlatform = new Platform(2, "GoG");
		String testSavedPlatformAsJSON = this.mapper.writeValueAsString(testSavedPlatform);

		this.mvc.perform(post("/platforms/create").content(testPlatformAsJSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(testSavedPlatformAsJSON));
	}
	
	@Test
	void testGetAll() throws Exception {
		GameDTO testgame = new GameDTO(1, "Bloodborne", "RPG", "Completed");		
		List<GameDTO> testlist = List.of(testgame);
		
		PlatformDTO testPlatform = new PlatformDTO(1, "Steam", testlist);		
		List<PlatformDTO> platformlist = List.of(testPlatform);
		
		String platformsAsJSONArray = this.mapper.writeValueAsString(platformlist);
		
		this.mvc.perform(get("/platforms/")).andExpect(status().isOk()).andExpect(content().json(platformsAsJSONArray));
	}	

	@Test
	void testUpdate() throws Exception {
		Platform testPlatform = new Platform(1, "Origin");
		String testPlatformAsJSON = this.mapper.writeValueAsString(testPlatform);

		this.mvc.perform(put("/platforms/update/1").content(testPlatformAsJSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().json(testPlatformAsJSON));
	}	
	
	@Test
	void testDelete() throws Exception {
		assertThat(repo.existsById(1));
		this.mvc.perform(delete("/games/delete/1")).andExpect(status().isOk());
		this.mvc.perform(delete("/platforms/delete/1")).andExpect(status().isOk());
		assertThat(!(repo.existsById(1)));		
	}

}
