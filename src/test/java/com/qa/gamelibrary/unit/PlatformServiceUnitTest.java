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
import com.qa.gamelibrary.domain.Platform;
import com.qa.gamelibrary.dto.PlatformDTO;
import com.qa.gamelibrary.repo.PlatformRepo;
import com.qa.gamelibrary.service.PlatformService;

@SpringBootTest
@ActiveProfiles("test")
public class PlatformServiceUnitTest {

	@Autowired
	private PlatformService service;

	@MockBean
	private PlatformRepo repo;

	@Test
	void testCreate() {
		// GIVEN
		Platform testData = new Platform("GoG");
		Platform created = new Platform(2, "GoG");
		PlatformDTO createdDTO = new PlatformDTO(2, "GoG");

		// WHEN
		Mockito.when(this.repo.save(testData)).thenReturn(created);

		// THEN
		assertThat(this.service.createPlatform(testData)).isEqualTo(createdDTO);

		Mockito.verify(this.repo, Mockito.times(1)).save(testData);
	}

	@Test
	void testGetPlatforms() {
		// GIVEN
		Platform existing = new Platform(1, "Steam");
		List<Platform> games = List.of(existing);

		PlatformDTO existingDTO = new PlatformDTO(1, "Steam");
		List<PlatformDTO> dtos = List.of(existingDTO);

		// WHEN
		Mockito.when(this.repo.findAll()).thenReturn(games);

		// THEN
		assertThat(this.service.getPlatforms()).isEqualTo(dtos);

	}

	@Test
	void testFindPlatform() {
		// GIVEN
		Integer testId = 1;
		Platform existing = new Platform(1, "Steam");
		PlatformDTO existingDTO = new PlatformDTO(1, "Steam");

		// WHEN
		Mockito.when(this.repo.findById(testId)).thenReturn(Optional.of(existing));

		// THEN
		assertThat(this.service.findPlatform(testId)).isEqualTo(existingDTO);
	}

	@Test
	void testUpdate() {
		// GIVEN
		Integer testId = 1;
		Platform testData = new Platform("Origin");
		Platform existing = new Platform(1, "Steam");
		Platform updatedPlatform = new Platform(testId, "Origin");
		PlatformDTO updatedPlatformDTO = new PlatformDTO(testId, "Origin");

		// WHEN
		Mockito.when(this.repo.findById(testId)).thenReturn(Optional.of(existing));
		Mockito.when(this.repo.save(updatedPlatform)).thenReturn(updatedPlatform);

		// THEN
		assertThat(this.service.updatePlatform(testId, testData)).isEqualTo(updatedPlatformDTO);

		Mockito.verify(this.repo, Mockito.times(1)).findById(testId);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedPlatform);
	}

	@Test
	void testDelete() {
		// GIVEN
		Integer testId = 1;
		boolean exists = false;
		// WHEN
		Mockito.when(this.repo.existsById(testId)).thenReturn(exists);

		// THEN
		assertThat(this.service.removePlatform(testId)).isEqualTo(!exists);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(testId);
	}

}
