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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qa.gamelibrary.domain.Platform;
import com.qa.gamelibrary.dto.PlatformDTO;
import com.qa.gamelibrary.service.PlatformService;

@RestController
@RequestMapping("/platforms")
public class PlatformController {

	private PlatformService service;

	@Autowired
	public PlatformController(PlatformService service) {
		super();
		this.service = service;
	}

	@GetMapping(path = "/test")
	public String test() {
		return "Hello World";
	}

	// CREATE
	@PostMapping("/create")
	public PlatformDTO createPlatform(@RequestBody Platform platform) {
		return this.service.createPlatform(platform);
	}

	// READ
	@GetMapping("/")
	public List<PlatformDTO> getPlatforms() {
		return this.service.getPlatforms();
	}
	
	@GetMapping("/{id}")
	public PlatformDTO findPlatform(@PathVariable Integer id) {
		return this.service.findPlatform(id);
	}
	
	// UPDATE
	@PutMapping("/update/{id}")
	public PlatformDTO updatePlatform(@RequestBody Platform platform, @PathVariable Integer id) {
		return this.service.updatePlatform(id, platform);
	}
	
	// DELETE
	@DeleteMapping("/delete/{id}")
	public boolean deletePlatform(@PathVariable Integer id) {
		return this.service.removePlatform(id);
	}

}
