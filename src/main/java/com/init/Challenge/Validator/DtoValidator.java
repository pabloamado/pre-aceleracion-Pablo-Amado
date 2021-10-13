package com.init.Challenge.Validator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.init.Challenge.dto.CharacterDto;
import com.init.Challenge.dto.GenreDto;
import com.init.Challenge.dto.MovieDto;

@Component
public class DtoValidator {

	public boolean genreDtoIsValid(GenreDto genreDto) {

		if (genreDto.getImg() != null && genreDto.getName() != null) {

			return true;
		}

		return false;
	}

	public boolean movieDtoIsValid(MovieDto movieDto) {

		boolean valid = false;

		if (movieDto.getImg() != null && movieDto.getTittle() != null && movieDto.getCreationDate() != null
				&& movieDto.getCalification() >= 1 && movieDto.getCalification() <= 5
				&& movieDto.getCharacters() != null && movieDto.getGenres() != null) {

			if (!movieDto.getCharacters().isEmpty()) {

				valid = runThroughtCharacters(movieDto.getCharacters());

			} else {

				valid = true;

			}

			if (!movieDto.getCharacters().isEmpty()) {

				valid = runThroughtGenres(movieDto.getGenres());
			}

		}

		return valid;
	}

	public boolean movieDtoToUpdateIsValid(MovieDto movieDto) {

		if (movieDto.getImg() != null && movieDto.getTittle() != null && movieDto.getCreationDate() != null
				&& movieDto.getCalification() >= 1 && movieDto.getCalification() <= 5) {

			return true;

		}

		return false;
	}

	public boolean characterDtoIsValid(CharacterDto characterDto) {

		if (characterDto.getImg() != null && characterDto.getName() != null && characterDto.getStory() != null
				&& characterDto.getAge() > 0 && characterDto.getWeight() > 0) {

			return true;
		}

		return false;
	}


	private boolean runThroughtCharacters(List<CharacterDto> charactersDto) {

		for (CharacterDto c : charactersDto) {

			if (!characterDtoIsValid(c)) {

				return false;

			}

		}

		return true;
	}

	private boolean runThroughtGenres(List<GenreDto> genres) {

		for (GenreDto g : genres) {

			if (!genreDtoIsValid(g)) {

				return false;
			}
		}
		return true;
	}
}
