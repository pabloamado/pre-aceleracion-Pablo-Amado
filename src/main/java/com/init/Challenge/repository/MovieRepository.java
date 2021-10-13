
package com.init.Challenge.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.init.Challenge.model.Movie;

public interface  MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

	List<Movie> findAll(Specification<Movie> spec);
		
}
