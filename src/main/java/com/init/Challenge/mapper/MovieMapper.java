package com.init.Challenge.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.init.Challenge.dto.MovieDto;
import com.init.Challenge.dto.MovieDtoGetAll;
import com.init.Challenge.model.Movie;
import com.init.Challenge.updater.EntityUpdater;

@Component
public class MovieMapper {

	@Autowired
	CharacterMapper characterMapper;
	
	@Autowired
	GenreMapper genreMapper;

	@Autowired
	EntityUpdater entityUpdater;

	public Movie toMovie(MovieDto movieDto) {

		Movie movie = new Movie();

		entityUpdater.updateMovie(movie, movieDto);

		movie.setCharacters(characterMapper.toCharacterSet(movieDto.getCharacters()));
		
		movie.setGenres(genreMapper.toGenreSet(movieDto.getGenres()));

		return movie;
	}

	public MovieDto toMovieDto(Movie movie) {

		MovieDto movieDto = new MovieDto();
		
		if(movie!=null) {
			
			setMovieDtoAllValues(movie, movieDto);
		}

		return movieDto;
	}

	public List<MovieDtoGetAll> toMovieDtoGetAllList(List<Movie> movies) {

		List<MovieDtoGetAll> movieDtos = new ArrayList<>();

		if (movies != null) {

			for (Movie movie : movies) {

				MovieDtoGetAll movieDto = new MovieDtoGetAll();

				movieDto.setImg(movie.getImg());
				movieDto.setTittle(movie.getTittle());
				movieDto.setCreationDate(movie.getCreationDate());

				movieDtos.add(movieDto);

			}

		}

		return movieDtos;
	}

	public List<MovieDto> toMovieDtoList(List<Movie> movies) {

		List<MovieDto> movieDtos = new ArrayList<>();

		if(movies!=null){
			
			for (Movie movie : movies) {

				MovieDto movieDto = new MovieDto();

				setMovieDtoValues(movieDto, movie);
				
				movieDtos.add(movieDto);
				
			}
			
		}
		
		return movieDtos;
	}

	
	public List<MovieDto> toMovieDtoListWithCharacters(List<Movie> movies) {

		List<MovieDto> movieDtos = new ArrayList<>();

		if(movies!=null){
			
			for (Movie movie : movies) {

				MovieDto movieDto = new MovieDto();

				setMovieDtoAllValues(movie, movieDto);

				movieDtos.add(movieDto);
			}
			
		}
		
		return movieDtos;
	}


	private void setMovieDtoAllValues(Movie movie, MovieDto movieDto) {
		
		setMovieDtoValues(movieDto, movie);
		
		movieDto.setCharacters(characterMapper.toCharacterDtoList(movie.getCharacters()));
		
		movieDto.setGenres(genreMapper.toGenreDtoList(movie.getGenres()));
	}


	private void setMovieDtoValues(MovieDto movieDto, Movie movie) {
		movieDto.setImg(movie.getImg());
		movieDto.setTittle(movie.getTittle());
		movieDto.setCalification(movie.getCalification());
		movieDto.setCreationDate(movie.getCreationDate());
		movieDto.setCharacters(new ArrayList<>());
		movieDto.setGenres(new ArrayList<>());
	}

}
