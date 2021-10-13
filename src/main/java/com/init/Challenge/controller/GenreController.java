package com.init.Challenge.controller;

import java.sql.SQLException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.init.Challenge.dto.GenreDto;
import com.init.Challenge.service.GenreService;

@RestController
@RequestMapping("/genre")
public class GenreController {

	@Autowired
	private GenreService genreService;

	@PostMapping
	public ResponseEntity<GenreDto> saveGenre(@RequestBody GenreDto genreDto) {

		GenreDto genre = genreService.save(genreDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(genre);

	}

	@GetMapping("/{genreId}")
	public ResponseEntity<GenreDto> getGenre(@PathVariable Long genreId) {

		GenreDto genre = genreService.get(genreId);

		return ResponseEntity.ok(genre);

	}

	@GetMapping
	public ResponseEntity<List<GenreDto>> getGenres() {

		List<GenreDto> genres = genreService.getAll();

		return ResponseEntity.ok(genres);

	}

	@PutMapping("/{genreId}")
	public ResponseEntity<GenreDto> updateGenre(@PathVariable Long genreId, @RequestBody GenreDto genreDto) {

		GenreDto genre = genreService.update(genreId, genreDto);

		return ResponseEntity.ok(genre);

	}

	@DeleteMapping("/{genreId}")
	public ResponseEntity<Void> deleteGenre(@PathVariable Long genreId) throws SQLException{

		genreService.delete(genreId);

		return ResponseEntity.status(HttpStatus.OK).build();

	}
	
	@PostMapping("/{genreId}/movie/{movieId}")
	public ResponseEntity<Void> postGenreInMovie(@PathVariable Long genreId,@PathVariable Long movieId){
		
		genreService.postGenreInMovie(genreId,movieId);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping("/{genreId}/movie/{movieId}")
	public ResponseEntity<Void> deleteGenreInMovie(@PathVariable Long genreId,@PathVariable Long movieId){
		
		genreService.deleteGenreInMovie(genreId,movieId);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
