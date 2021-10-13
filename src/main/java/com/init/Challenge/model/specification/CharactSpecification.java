package com.init.Challenge.model.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import com.init.Challenge.model.Charact;
import com.init.Challenge.model.Movie;

@Component
public class CharactSpecification {

	public Specification<Charact> getByFilters(String name, Short age, Set<Long> movies) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(name)) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + name.toLowerCase() + "%"
                        )
                );
            }
            if (age!=null) {
                predicates.add(criteriaBuilder.equal(root.get("age"), age));
            }
            
            if (!CollectionUtils.isEmpty(movies)) {
                Join<Movie, Charact> join = root.join("movies", JoinType.INNER);
                Expression<String> movieId = join.get("movId");
                predicates.add(movieId.in(movies));
            }

            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
