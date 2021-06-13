package com.qa.gamelibrary.service;

import org.springframework.stereotype.Service;

import com.qa.gamelibrary.domain.Platform;
import com.qa.gamelibrary.dto.PlatformDTO;

@Service
public class PlatformMapper {
	
	public PlatformDTO mapToDTO(Platform platform) {
		PlatformDTO dto = new PlatformDTO();
		dto.setId(platform.getId());
		dto.setName(platform.getName());
		return dto;
	}

}
