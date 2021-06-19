package com.qa.gamelibrary.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.gamelibrary.domain.Platform;
import com.qa.gamelibrary.dto.PlatformDTO;
import com.qa.gamelibrary.rest.PlatformController;
import com.qa.gamelibrary.service.PlatformService;

@SpringBootTest
@ActiveProfiles("test")
public class PlatformControllerUnitTest {

	@Autowired
	private PlatformController controller;

	@MockBean
	private PlatformService service;

	@Test
	void testCreatePlatform() {
		// GIVEN
		Platform testData = new Platform("Origin");
		PlatformDTO createdDTO = new PlatformDTO(2, "Origin");

		// WHEN
		Mockito.when(service.createPlatform(testData)).thenReturn(createdDTO);

		// THEN
		assertThat(this.controller.createPlatform(testData)).isEqualTo(createdDTO);

		Mockito.verify(this.service, Mockito.times(1)).createPlatform(testData);
	}

	@Test
	void testGetPlatforms() {
		// GIVEN	
		PlatformDTO existingDTO = new PlatformDTO(1, "Steam");
		List<PlatformDTO> dtos = List.of(existingDTO);

		// WHEN
		Mockito.when(this.service.getPlatforms()).thenReturn(dtos);

		// THEN
		assertThat(this.controller.getPlatforms()).isEqualTo(dtos);
		
		Mockito.verify(this.service, Mockito.times(1)).getPlatforms();

	}

	@Test
	void testFindPlatform() {
		// GIVEN
		Integer testId = 1;
		PlatformDTO existingDTO = new PlatformDTO(1, "Steam");

		// WHEN
		Mockito.when(this.service.findPlatform(testId)).thenReturn(existingDTO);

		// THEN
		assertThat(this.controller.findPlatform(testId)).isEqualTo(existingDTO);
	}

	@Test
	void testUpdatePlatform() {
		// GIVEN
		Integer testId = 1;
		Platform testData = new Platform(1, "Origin");
		Platform updatedPlatform = new Platform(testId, "Origin");
		PlatformDTO updatedPlatformDTO = new PlatformDTO(testId, "Origin");

		// WHEN
		Mockito.when(this.service.updatePlatform(testId, testData)).thenReturn(updatedPlatformDTO);

		// THEN
		assertThat(this.controller.updatePlatform(testData, testId)).isEqualTo(updatedPlatformDTO);

		Mockito.verify(this.service, Mockito.times(1)).updatePlatform(testId, updatedPlatform);
	}

	@Test
	void testDelete() {
		// GIVEN
		Integer testId = 1;
		// WHEN
		Mockito.when(this.service.removePlatform(testId)).thenReturn(true);

		// THEN
		assertThat(this.controller.deletePlatform(testId)).isTrue();

		Mockito.verify(this.service, Mockito.times(1)).removePlatform(testId);
	}

}
