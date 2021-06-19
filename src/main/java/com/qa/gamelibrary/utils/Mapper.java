package com.qa.gamelibrary.utils;

public interface Mapper<E, D> {
	
	D mapToDTO(E entity);		

}
