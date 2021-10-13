package com.init.Challenge.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class Genre {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "genre_id")
	private Long genId;

	@Column(name = "genre_name")
	private String name;

	@Column(name = "genre_img")
	private String img;
	
	@OneToMany(mappedBy = "genres", cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,
			CascadeType.REFRESH},fetch=FetchType.LAZY)
	private Set<Movie> movies;
	
	public Genre() {}

	public Genre(String name, String img) {
		this.name = name;
		this.img = img;
	}
	
	public Long getId() {
		return genId;
	}

	public void setId(Long id) {
		this.genId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setShows(Set<Movie> show) {
		this.movies = show;
	}
	
	public Set<Movie> getMovies() {

		if (movies == null)
			movies = new HashSet<>();

		return movies;
	}

	public void addMovies(Movie show) {

		getMovies().add(show);
		
		//falta agregrar el add a media?

	}

}
