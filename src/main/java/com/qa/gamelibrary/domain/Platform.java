//package com.qa.gamelibrary.domain;
//
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//@Entity
//public class Platform {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//	
//	@Column(name = "Name", nullable = false)
//	private String name;
//	
//	@OneToMany
//	@JsonIgnore
//	private List<Game> games;
//
//	public Platform() {
//	}
//
//	public Platform(Integer id, String name) {
//		this.id = id;
//		this.name = name;
//	}
//
//	public Platform(String name) {
//		this.name = name;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	@Override
//	public String toString() {
//		return "Platform [Id=" + id + ", name=" + name + "]";
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + id;
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Platform other = (Platform) obj;
//		if (id != other.id)
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}
//	
//	
//
//}
