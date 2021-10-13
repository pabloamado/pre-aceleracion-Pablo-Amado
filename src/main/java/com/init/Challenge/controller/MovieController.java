package com.init.Challenge.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.init.Challenge.dto.MovieDto;
import com.init.Challenge.dto.MovieDtoGetAll;
import com.init.Challenge.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@PostMapping
	public ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movieDto) {

		MovieDto movie = movieService.save(movieDto);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(movie);

	}

	@GetMapping("/{movieId}")
	public ResponseEntity<MovieDto> getMovie(@PathVariable Long movieId) {

		MovieDto movie = movieService.get(movieId);

		return ResponseEntity.ok(movie);

	}

	@GetMapping("/all")
	public ResponseEntity<List<MovieDtoGetAll>> getMovies() {

		List<MovieDtoGetAll> movies = movieService.getAll();

		return ResponseEntity.ok(movies);
	}

	@GetMapping
	public ResponseEntity<List<MovieDto>> getMoviesByTittleAndFilters(@RequestParam(required = false) String name,
			@RequestParam(required = false) Set<Long> genres,
			@RequestParam(required = false, defaultValue = "ASC") String order) {

		List<MovieDto> movies = movieService.getShowsFiltered(name, genres, order);

		return ResponseEntity.ok(movies);

	}

	@PutMapping("/{movieId}")
	public ResponseEntity<MovieDto> updateMovie(@PathVariable Long movieId, @RequestBody MovieDto movieDto)
			throws SQLException{

		MovieDto movie = movieService.update(movieId, movieDto);

		return ResponseEntity.ok(movie);

	}

	@DeleteMapping("/{movieId}")
	public ResponseEntity<Void> deleteMovie(@PathVariable Long movieId) {

		movieService.delete(movieId);

		return ResponseEntity.ok().build();

	}
	
}
