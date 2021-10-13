
package com.init.Challenge.repository;

import org.springframework.stereotype.Repository;
import com.init.Challenge.model.Charact;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface CharacterRepository extends JpaRepository<Charact, Long>, JpaSpecificationExecutor<Charact> {

	List<Charact> findAll(Specification<Charact> spec);
		
}
