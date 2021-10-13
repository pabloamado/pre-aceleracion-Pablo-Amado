package com.init.Challenge.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.init.Challenge.dto.GenreDto;
import com.init.Challenge.model.Genre;
import com.init.Challenge.updater.EntityUpdater;

@Component
public class GenreMapper {
	
	@Autowired
	EntityUpdater entityUpdater;

	public Genre toGenre(GenreDto genreDto) {

		return new Genre(genreDto.getName(), genreDto.getImg());
	}

	public Set<Genre> toGenreSet(List<GenreDto> genres) {
		
		Set<Genre> genreSet=new HashSet<>();
		
		for(GenreDto g:genres) {
			
			genreSet.add(toGenre(g));
			
		}
		
		return genreSet;
	}

	public GenreDto toGenreDto(Genre genre) {

		GenreDto genreDto = new GenreDto();

		if (genre != null) {

			genreDto.setImg(genre.getImg());
			genreDto.setName(genre.getName());

		}

		return genreDto;
	}

	public List<GenreDto> toGenreDtoList(List<Genre> genres) {

		List<GenreDto> genreDtos = new ArrayList<>();

		if (genres != null) {

			for (Genre genre : genres) {

				GenreDto genreDto = new GenreDto();
				genreDto.setImg(genre.getImg());
				genreDto.setName(genre.getName());

				genreDtos.add(genreDto);
			}

		}

		return genreDtos;
	}

	public List<GenreDto> toGenreDtoList(Set<Genre> genres) {
		
		List<GenreDto> genreDtos = new ArrayList<>();
		
		if (genres != null) {
			
			Iterator<Genre> iterator=genres.iterator();
			
			while(iterator.hasNext()) {
				
				Genre genre=iterator.next();
				
				genreDtos.add(toGenreDto(genre));
				
			}
			
		}
		
		return genreDtos;
	}

}
