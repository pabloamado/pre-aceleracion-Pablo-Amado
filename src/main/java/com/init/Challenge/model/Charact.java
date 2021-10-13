package com.init.Challenge.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="charact")
public class Charact{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="char_id")
	private Long charId;
	
	@Column(name="char_img")
	private String img;
	
	@Column(name="char_name")
	private String name;
	
	@Column(name="char_age")
	private short age;
		
	@Column(name="char_weight")
	private float weight;

	@Column(name="char_story")
	private String story;
	
	@ManyToMany(mappedBy = "characters",fetch = FetchType.LAZY)
	private List<Movie> movies;
	
	public Charact() {}
	

	public Long getId() {
		return charId;
	}

	public void setId(Long id) {
		this.charId = id;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getAge() {
		return age;
	}

	public void setAge(short age) {
		this.age = age;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public List<Movie> getMovies() {
		
		if(movies==null) movies=new ArrayList<>();
		
		return movies;
	}

	public void setMovies(List<Movie> shows) {
		this.movies = shows;
	}

	

}
