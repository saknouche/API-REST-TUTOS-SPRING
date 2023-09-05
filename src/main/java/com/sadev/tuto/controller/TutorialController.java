package com.sadev.tuto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sadev.tuto.entity.Tutorial;
import com.sadev.tuto.model.repository.TutorialRepository;

@RestController
public class TutorialController {

	@Autowired
	private TutorialRepository tutorialRepository;

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/tutos")
	public Iterable<Tutorial> getAllTuturials(@RequestParam(required = false) String title,
			@RequestParam(required = false) Boolean isPublished) {
		if (title != null) {
			List<Tutorial> list = tutorialRepository.findByTitle(title);
			if (!list.isEmpty()) {
				return list;
			} else {
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Pas de résultat à votre recherche !");
			}
		} else if (isPublished != null) {
			if(isPublished == true) {				
				List<Tutorial> list = tutorialRepository.findByIsPublished(isPublished);
				if (!list.isEmpty()) {
					return list;
				} else {
					throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Aucun résultat !");
				}
			}else{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas de résultat à votre recherche !");
			}
		} else {
			List<Tutorial> list = tutorialRepository.findAll();
			if (!list.isEmpty()) {
				return tutorialRepository.findAll();
			} else {
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Le contenu est vide !");
			}
		}

	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/tuto/{id}")
	public Tutorial getTutorial(@PathVariable("id") final Long id) {
		Optional<Tutorial> tutorial = tutorialRepository.findById(id);
		if (tutorial.isPresent()) {
			return tutorial.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Pas de résultat correspondant à votre recherche !");
		}
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/tuto")
	public Tutorial createTutorial(@RequestBody Tutorial tutorial) {
		if (tutorial.getTitle() != null && tutorial.getDescription() != null && tutorial.getIsPublished() != null) {
			return tutorialRepository.save(tutorial);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Données manquantes!");
		}
	}

	@ResponseStatus(code = HttpStatus.CREATED)
	@PutMapping("/tuto/{id}")
	public Tutorial updateTutorial(@PathVariable("id") final Long id, @RequestBody Tutorial tutorial) {
		if (id != null) {
			Optional<Tutorial> t = tutorialRepository.findById(id);
			if (t.isPresent()) {
				Tutorial currentTuto = t.get();
				String title = tutorial.getTitle();
				if (title != null) {
					currentTuto.setTitle(title);
				}
				String description = tutorial.getDescription();
				if (description != null) {
					currentTuto.setDescription(description);
				}
				Boolean isPublished = tutorial.getIsPublished();
				if (isPublished != null) {
					currentTuto.setIsPublished(isPublished);
				}
				tutorialRepository.save(currentTuto);
				return currentTuto;

			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas de résultat à votre recherche !");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requête erronée !");
		}
	}


	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/tuto/{id}")
	public void deleteById(@PathVariable("id") final Long id) {
		if (id != null) {
			Optional<Tutorial> tuto = tutorialRepository.findById(id);
			if (tuto.isPresent()) {
				tutorialRepository.deleteById(id);
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas de résultat à votre recherche !");
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas de résultat à votre recherche !");
		}
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("/tutos")
	public void deleteAllTutos() {
		tutorialRepository.deleteAll();
	}

}
