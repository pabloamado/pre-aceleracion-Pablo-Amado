package com.init.Challenge.updater;

import org.springframework.stereotype.Component;

import com.init.Challenge.dto.CharacterDto;
import com.init.Challenge.dto.GenreDto;
import com.init.Challenge.dto.MovieDto;
import com.init.Challenge.model.Charact;
import com.init.Challenge.model.Genre;
import com.init.Challenge.model.Movie;

@Component
public class EntityUpdater {

	public void updateGenre(Genre genre, GenreDto genreDto) {
		
		genre.setImg(genreDto.getImg());
		genre.setName(genreDto.getName());
		
	}
	
	public void updateCharacter(Charact character, CharacterDto characterDto) {
		
		character.setImg(characterDto.getImg());
		character.setName(characterDto.getName());
		character.setAge(characterDto.getAge());
		character.setWeight(characterDto.getWeight());
		character.setStory(characterDto.getStory());
		
	}
	
	public void updateMovie(Movie movie, MovieDto movieDto) {
		
		movie.setImg(movieDto.getImg());
		movie.setTittle(movieDto.getTittle());
		movie.setCalification(movieDto.getCalification());
		movie.setCreationDate(movieDto.getCreationDate());
		
	}
	
}
