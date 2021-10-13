package com.init.Challenge.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.init.Challenge.Validator.DtoValidator;
import com.init.Challenge.dto.MovieDto;
import com.init.Challenge.dto.MovieDtoGetAll;
import com.init.Challenge.exception.MovieException;
import com.init.Challenge.exception.msg.ExceptionMsg;
import com.init.Challenge.mapper.MovieMapper;
import com.init.Challenge.model.Movie;
import com.init.Challenge.model.specification.MovieSpecification;
import com.init.Challenge.repository.MovieRepository;
import com.init.Challenge.updater.EntityUpdater;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private MovieMapper movieMapper;
	
	@Autowired
	private EntityUpdater entityUpdater;
	
	@Autowired
	private MovieSpecification movieSpecification;
	
	@Autowired
	private DtoValidator dtoValidator;
	
	//listo
	public MovieDto save(MovieDto movieDto) {
	
		if(dtoValidator.movieDtoIsValid(movieDto)) {
			
			Movie movie=movieMapper.toMovie(movieDto);
		
			movie=movieRepository.save(movie);
		
			return movieMapper.toMovieDto(movie);
			
		}
		
		throw new MovieException(ExceptionMsg.ERROR_TRYING_TO_SAVE);
		
	}

	public MovieDto get(Long showId) {
		
		Movie movie=movieRepository.getById(showId);
		
		return movieMapper.toMovieDto(movie);
	}

	public List<MovieDtoGetAll> getAll() {
		
		List<Movie> movies=movieRepository.findAll();
		
		return movieMapper.toMovieDtoGetAllList(movies);
	}

	public List<MovieDto> getShowsFiltered(String tittle, Set<Long> genres, String order) {
		
		List<Movie> movies=movieRepository.findAll(movieSpecification.getByFilters(tittle, genres, order));
	
		return movieMapper.toMovieDtoListWithCharacters(movies);
	}

	public MovieDto update(Long movieId, MovieDto movieDto)throws SQLException {
		
		if(dtoValidator.movieDtoToUpdateIsValid(movieDto)) {
			
			Movie movie=movieRepository.getById(movieId);
		
			entityUpdater.updateMovie(movie, movieDto);
		
			movie=movieRepository.save(movie);
		
			return movieMapper.toMovieDto(movie);
		
		}
		
		throw new MovieException(ExceptionMsg.ERROR_TRYING_TO_UPDATE);
	}

	public void delete(Long movieId) {
		
		if(!movieRepository.existsById(movieId)) {
			
			throw new EntityNotFoundException(ExceptionMsg.MOVIE_NOT_FOUND);
		}
		
		movieRepository.deleteById(movieId);
		
	}
	
	public Movie getById(Long movieId) {
		
		return movieRepository.getById(movieId);
	}

}
