package com.qa.gamelibrary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.gamelibrary.domain.Platform;
import com.qa.gamelibrary.dto.PlatformDTO;
import com.qa.gamelibrary.repo.PlatformRepo;

@Service
public class PlatformService {

	private PlatformRepo repo;

	private PlatformMapper mapper;

	public PlatformService(PlatformRepo repo, PlatformMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	// CREATE
	public PlatformDTO createPlatform(Platform game) {
		Platform saved = repo.save(game);
		return mapper.mapToDTO(saved);
	}

	// READ
	public List<PlatformDTO> getPlatforms() {
		List<Platform> platforms = this.repo.findAll();
		List<PlatformDTO> dtos = new ArrayList<>();
		for (Platform platform : platforms) {
			PlatformDTO dto = this.mapper.mapToDTO(platform);
			dtos.add(dto);
		}
		return dtos;
	}

	public PlatformDTO findPlatform(Integer id) {
		Optional<Platform> optionalPlatform = this.repo.findById(id);
		Platform found = optionalPlatform.orElseThrow(() -> new EntityNotFoundException());
		return this.mapper.mapToDTO(found);
	}

	// UPDATE
	public PlatformDTO updatePlatform(Integer id, Platform update) {
		Platform existing = this.repo.findById(id).orElseThrow(() -> new EntityNotFoundException());
		existing.setName(update.getName());
		Platform updated = this.repo.save(existing);
		return this.mapper.mapToDTO(updated);
	}
	
	// DELETE
	public boolean removePlatform(@PathVariable Integer id) {
    	this.repo.deleteById(id);
		return !this.repo.existsById(id);
    }

}
