package com.qa.gamelibrary;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.gamelibrary.rest.GameController;

@SpringBootTest
class GameLibraryApplicationTests {

	@Autowired
	private GameController controller;

	//Smoke Test
	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
