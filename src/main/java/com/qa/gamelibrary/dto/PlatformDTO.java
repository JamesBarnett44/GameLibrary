package com.qa.gamelibrary.dto;

import java.util.ArrayList;
import java.util.List;

public class PlatformDTO {

	private Integer id;
	private String name;
	private List<GameDTO> games = new ArrayList<>();

	public PlatformDTO() {
	}

	public PlatformDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public PlatformDTO(Integer id, String name, List<GameDTO> games) {
		this.id = id;
		this.name = name;
		this.games = games;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GameDTO> getGames() {
		return games;
	}

	public void setGames(List<GameDTO> games) {
		this.games = games;
	}

	@Override
	public String toString() {
		return "PlatformDTO [Id=" + id + ", name=" + name + ", games=" + games + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((games == null) ? 0 : games.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlatformDTO other = (PlatformDTO) obj;
		if (games == null) {
			if (other.games != null)
				return false;
		} else if (!games.equals(other.games))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
