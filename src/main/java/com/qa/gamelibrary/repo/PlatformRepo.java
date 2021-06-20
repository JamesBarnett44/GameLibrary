package com.qa.gamelibrary.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.gamelibrary.domain.Platform;

@Repository
public interface PlatformRepo extends JpaRepository<Platform, Integer>{

}
