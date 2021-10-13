package com.init.Challenge.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.init.Challenge.Validator.DtoValidator;
import com.init.Challenge.dto.CharacterDto;
import com.init.Challenge.dto.CharacterDtoGetAll;
import com.init.Challenge.dto.CharacterDtoGetOne;
import com.init.Challenge.exception.CharacterException;
import com.init.Challenge.exception.msg.ExceptionMsg;
import com.init.Challenge.mapper.CharacterMapper;
import com.init.Challenge.model.Charact;
import com.init.Challenge.model.Movie;
import com.init.Challenge.model.specification.CharactSpecification;
import com.init.Challenge.repository.CharacterRepository;
import com.init.Challenge.updater.EntityUpdater;

@Service
public class CharacterService {

	@Autowired
	private CharacterRepository characterRepository;

	@Autowired
	private MovieService movieService;

	@Autowired
	private CharacterMapper characterMapper;
	
	@Autowired
	private CharactSpecification characterSpecification;

	@Autowired
	private EntityUpdater entityUpdater;
	
	@Autowired
	private DtoValidator dtoValidator;
	
	@Transactional
	public CharacterDto save(CharacterDto characterDto) {

		if(dtoValidator.characterDtoIsValid(characterDto)) {
			
			Charact character = characterMapper.toCharacter(characterDto);

			character = characterRepository.save(character);

			return characterMapper.toCharacterDto(character);
		}
			
		throw new CharacterException(ExceptionMsg.ERROR_TRYING_TO_SAVE);
	}

	//
	public CharacterDtoGetOne get(Long idCharacter) {

		Charact character = characterRepository.getById(idCharacter);

		return characterMapper.ToCharacterDtoGetOne(character);
	
	}

	public List<CharacterDtoGetAll> getAll() {

		List<Charact> characters = characterRepository.findAll();

		return characterMapper.toCharacterDtoGetAllList(characters);

	}

	public List<CharacterDtoGetOne> getCharactersFiltered(String name, Short age, Set<Long> movies) {

		List<Charact>characters=characterRepository.findAll(characterSpecification.getByFilters(name, age, movies));
		
		return characterMapper.toCharacterDtoList(characters);

	}

	public CharacterDto update(Long idCharacter, CharacterDto characterDto) {

		if(dtoValidator.characterDtoIsValid(characterDto)) {
			
		Charact character=characterRepository.getById(idCharacter);
		
		entityUpdater.updateCharacter(character, characterDto);
		
		character=characterRepository.save(character);

		return characterMapper.toCharacterDto(character);

		}
		
		throw new CharacterException(ExceptionMsg.ERROR_TRYING_TO_UPDATE);
	}
		
	@Transactional
	public void saveCharacterInMovie(Long idCharacter, Long idMovie) {

		Movie movie = movieService.getById(idMovie);

		Charact character = characterRepository.getById(idCharacter);

		movie.addCharacter(character);

	}

	@Transactional
	public void deleteCharacterFromMovie(Long idCharacter, Long idMovie) {

		Movie movie = movieService.getById(idMovie);

		Charact character = characterRepository.getById(idCharacter);

		movie.removeCharacter(character);

	}

	public void deleteCharacter(Long idCharacter) throws SQLException {

		if (!characterRepository.existsById(idCharacter)) {

			throw new EntityNotFoundException(ExceptionMsg.CHARACTER_NOT_FOUND);

		}
		
		characterRepository.deleteById(idCharacter);
		
	}
}
