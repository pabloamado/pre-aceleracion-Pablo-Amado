package com.init.Challenge.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.init.Challenge.dto.CharacterDto;
import com.init.Challenge.dto.CharacterDtoGetAll;
import com.init.Challenge.dto.CharacterDtoGetOne;
import com.init.Challenge.service.CharacterService;

@RestController
@RequestMapping("/characters")
public class CharacterController {

	@Autowired
	private CharacterService characterService;

	
	@PostMapping
	public ResponseEntity<CharacterDto> saveCharacter(@RequestBody CharacterDto characterDto) {

		CharacterDto character = characterService.save(characterDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(character);

	}

	@GetMapping("/{idCharacter}")
	public ResponseEntity<CharacterDtoGetOne> getCharacter(@PathVariable Long idCharacter) {

		CharacterDtoGetOne character = characterService.get(idCharacter);

		return ResponseEntity.ok(character);
	}

	@GetMapping("/all")
	public ResponseEntity<List<CharacterDtoGetAll>> getCharacters() {

		List<CharacterDtoGetAll> characters = characterService.getAll();

		return ResponseEntity.ok(characters);
	}

	@GetMapping
	public ResponseEntity<List<CharacterDtoGetOne>> getCharactersFiltered(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Short age, 
			@RequestParam(required = false) Set<Long> movies) {

		List<CharacterDtoGetOne> characters = characterService.getCharactersFiltered(name, age, movies);

		return ResponseEntity.ok(characters);
	}

	@PutMapping("/{idCharacter}")
	public ResponseEntity<CharacterDto> updateCharacter(@PathVariable Long idCharacter,
			@RequestBody CharacterDto characterDto) {

		CharacterDto character = characterService.update(idCharacter, characterDto);

		return ResponseEntity.ok(character);

	}

	@PostMapping("/{idCharacter}/movie/{idMovie}")
	public ResponseEntity<Void> saveCharacterInMovie(@PathVariable Long idCharacter,
			@PathVariable Long idMovie) {

		characterService.saveCharacterInMovie(idCharacter, idMovie);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{idCharacter}/movie/{idMovie}")
	public ResponseEntity<Void> deleteCharacterFromMovie(@PathVariable Long idCharacter,
			@PathVariable Long idMovie) {

		characterService.deleteCharacterFromMovie(idCharacter, idMovie);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{idCharacter}")
	public ResponseEntity<Void> deleteCharacter(@PathVariable Long idCharacter) throws SQLException {

		characterService.deleteCharacter(idCharacter);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
