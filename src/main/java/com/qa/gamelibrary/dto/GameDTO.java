package com.qa.gamelibrary.dto;

public class GameDTO {

	private Integer id;
	private String name;
	private String genre;
	private String progress;
	private int platformId;

	public GameDTO() {

	}

	public GameDTO(Integer id, String name, String genre, String progress, Integer platformId) {
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.progress = progress;
		this.platformId = platformId;
	}

	public GameDTO(String name, String genre, String progress, Integer platformId) {
		this.name = name;
		this.genre = genre;
		this.progress = progress;
		this.platformId = platformId;
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public int getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Integer platformId) {
		this.platformId = platformId;
	}

	@Override
	public String toString() {
		return "Game [Id=" + id + ", name=" + name + ", genre=" + genre + ", progress=" + progress + ", platformId="
				+ platformId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + platformId;
		result = prime * result + ((progress == null) ? 0 : progress.hashCode());
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
		GameDTO other = (GameDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (platformId != other.platformId)
			return false;
		if (progress == null) {
			if (other.progress != null)
				return false;
		} else if (!progress.equals(other.progress))
			return false;
		return true;
	}

}
