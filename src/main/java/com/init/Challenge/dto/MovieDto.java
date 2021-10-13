package com.init.Challenge.dto;

import java.time.LocalDate;
import java.util.List;

public class MovieDto {

	private String img;
	private String tittle;
	private LocalDate creationDate;
	private short calification;
	private List<CharacterDto> characters;
	private List<GenreDto> genres;
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getTittle() {
		return tittle;
	}
	
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	
	public LocalDate getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
	public short getCalification() {
		return calification;
	}
	
	public void setCalification(short calification) {
		this.calification = calification;
	}
	
	public List<CharacterDto> getCharacters() {
		return characters;
	}
	
	public void setCharacters(List<CharacterDto> characters) {
		this.characters = characters;
	}

	public List<GenreDto> getGenres() {
		return genres;
	}

	public void setGenres(List<GenreDto> genres) {
		this.genres = genres;
	}

}
