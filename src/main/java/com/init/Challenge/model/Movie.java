package com.init.Challenge.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private Long movId;

	@Column(name = "movie_img")
	private String img;

	@Column(name = "movie_title")
	private String tittle;

	@Column(name = "movie_creation_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate creationDate;

	@Column(name = "movie_calification")
	private short calification;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "movie_x_genre",
		joinColumns={@JoinColumn(name="mxg_movie_id")},
		inverseJoinColumns= {@JoinColumn(name="mxg_genre_id")})
	private Set<Genre> genres;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "movie_x_character", 
		joinColumns = { @JoinColumn(name = "mxc_movie_id") },
		inverseJoinColumns = {@JoinColumn(name = "mxc_char_id") })
	private Set<Charact> characters;

	public Movie() {
	}

	public Long getId() {
		return movId;
	}

	public void setId(Long id) {
		this.movId = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String title) {
		this.tittle = title;
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

	public Set<Charact> getCharacters() {

		if (characters == null)
			characters = new HashSet<>();

		return characters;
	}

	public void setCharacters(Set<Charact> characters) {

		this.characters = characters;

	}

	public void addCharacter(Charact charac) {

		getCharacters().add(charac);
	}

	public void removeCharacter(Charact charac) {

		getCharacters().remove(charac);
	}

	public Set<Genre> getGenres() {

		if (genres == null) {
			genres = new HashSet<>();
		}

		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public void addGenre(Genre genre) {

		getGenres().add(genre);
	}

	public void removeGenre(Genre genre) {

		getGenres().remove(genre);
	}

}
