package com.init.Challenge.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.init.Challenge.Validator.DtoValidator;
import com.init.Challenge.dto.GenreDto;
import com.init.Challenge.exception.GenreException;
import com.init.Challenge.exception.msg.ExceptionMsg;
import com.init.Challenge.mapper.GenreMapper;
import com.init.Challenge.model.Genre;
import com.init.Challenge.model.Movie;
import com.init.Challenge.repository.GenreRepository;
import com.init.Challenge.updater.EntityUpdater;

@Service
public class GenreService {
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private GenreMapper genreMapper;

	@Autowired
	private EntityUpdater entityUpdater;
	
	@Autowired
	private DtoValidator dtoValidator;
	
	@Autowired
	private MovieService movieService;

	public GenreDto save(GenreDto genreDto) {
		
		if(dtoValidator.genreDtoIsValid(genreDto)){
			
			Genre genre=genreMapper.toGenre(genreDto);
		
			genre=genreRepository.save(genre);
		
			return genreMapper.toGenreDto(genre);
		}
		
		throw new GenreException(ExceptionMsg.ERROR_TRYING_TO_SAVE);
		
	}

	public GenreDto get(Long genreId) {
		
		Genre genre=genreRepository.getById(genreId);
		
		return genreMapper.toGenreDto(genre);
	}

	public List<GenreDto> getAll() {
		
		List<Genre> genres=genreRepository.findAll();
		
		return genreMapper.toGenreDtoList(genres);
	}

	public GenreDto update(Long genreId, GenreDto genreDto) {
		
		if(dtoValidator.genreDtoIsValid(genreDto)) {
			
			Genre genre=genreRepository.getById(genreId);
			
			entityUpdater.updateGenre(genre,genreDto);
			
			genre=genreRepository.save(genre);
		
			return genreMapper.toGenreDto(genre);
			
		}
		
		throw new GenreException(ExceptionMsg.ERROR_TRYING_TO_UPDATE);
		
	}

	
	public void delete(Long genreId) throws SQLException {
		
		if(!genreRepository.existsById(genreId)) {
			
			throw new EntityNotFoundException(ExceptionMsg.GENRE_NOT_FOUND);
				
		}
			
		genreRepository.deleteById(genreId);
			
	}

	@Transactional
	public void postGenreInMovie(Long genreId, Long movieId) {
		
		Movie movie=movieService.getById(movieId);
		
		Genre genre=genreRepository.getById(genreId);
		
		movie.addGenre(genre);
		
	}

	@Transactional
	public void deleteGenreInMovie(Long genreId, Long movieId) {
		
		Movie movie=movieService.getById(movieId);
		
		Genre genre=genreRepository.getById(genreId);
		
		movie.removeGenre(genre);
		
	}
	
	

}
