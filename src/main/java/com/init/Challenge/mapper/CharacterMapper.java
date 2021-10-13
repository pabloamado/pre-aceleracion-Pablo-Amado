package com.init.Challenge.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.init.Challenge.dto.CharacterDto;
import com.init.Challenge.dto.CharacterDtoGetAll;
import com.init.Challenge.dto.CharacterDtoGetOne;
import com.init.Challenge.model.Charact;

import com.init.Challenge.updater.EntityUpdater;

@Component
public class CharacterMapper {

	@Autowired
	MovieMapper movieMapper;

	@Autowired
	EntityUpdater entityUpdater;

	public Charact toCharacter(CharacterDto characterDto) {

		Charact character = new Charact();

		entityUpdater.updateCharacter(character, characterDto);

		return character;
	}

	public CharacterDto toCharacterDto(Charact character) {

		CharacterDto characterDto = new CharacterDto();

		setCharacterDtoValues(character, characterDto);

		return characterDto;
	}

	public Set<Charact> toCharacterSet(List<CharacterDto> characters) {

		Set<Charact> characterSet = new HashSet<>();

		Iterator<CharacterDto> iterator = characters.iterator();

		while (iterator.hasNext()) {

			CharacterDto characterDto = iterator.next();

			Charact character = new Charact();

			entityUpdater.updateCharacter(character, characterDto);

			characterSet.add(character);
		}

		return characterSet;
	}

	public List<CharacterDto> toCharacterDtoList(Set<Charact> characters) {

		List<CharacterDto> characterDtos = new ArrayList<>();

		Iterator<Charact> iterator = characters.iterator();

		while (iterator.hasNext()) {

			Charact character = iterator.next();

			CharacterDto characterDto = new CharacterDto();

			setCharacterDtoValues(character, characterDto);

			characterDtos.add(characterDto);
		}

		return characterDtos;

	}

	public CharacterDtoGetOne ToCharacterDtoGetOne(Charact character) {

		CharacterDtoGetOne characterDto = new CharacterDtoGetOne();

		characterDto.setImg(character.getImg());
		characterDto.setName(character.getName());
		characterDto.setAge(character.getAge());
		characterDto.setWeight(character.getWeight());
		characterDto.setStory(character.getStory());

		characterDto.setMovies(movieMapper.toMovieDtoList(character.getMovies()));

		return characterDto;
	}

	public List<CharacterDtoGetAll> toCharacterDtoGetAllList(List<Charact> characters) {

		List<CharacterDtoGetAll> characterDtos = new ArrayList<>();

		if(characters!=null) {
			
			for (Charact charact : characters) {

				CharacterDtoGetAll characterDto = new CharacterDtoGetAll();

				characterDto.setImg(charact.getImg());
				characterDto.setName(charact.getName());

				characterDtos.add(characterDto);
			}
			
		}

		return characterDtos;
	}
	
	
	public List<CharacterDtoGetOne> toCharacterDtoList(List<Charact> characters) {
		
		List<CharacterDtoGetOne> characterDtos=new ArrayList<>();
		
		if(characters!=null){
			
			for(Charact character:characters) {
				
				characterDtos.add(ToCharacterDtoGetOne(character));
			}
			
		}
		
		return characterDtos;
	}

	private void setCharacterDtoValues(Charact character, CharacterDto characterDto) {
		characterDto.setImg(character.getImg());
		characterDto.setName(character.getName());
		characterDto.setAge(character.getAge());
		characterDto.setWeight(character.getWeight());
		characterDto.setStory(character.getStory());
	}

	
}
