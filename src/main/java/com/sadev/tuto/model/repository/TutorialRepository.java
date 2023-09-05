package com.sadev.tuto.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sadev.tuto.entity.Tutorial;
import java.util.List;
import java.util.Optional;


@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long>{

	List<Tutorial> findByTitle(String title);
	
	List<Tutorial> findByIsPublished(Boolean isPublished);
}
