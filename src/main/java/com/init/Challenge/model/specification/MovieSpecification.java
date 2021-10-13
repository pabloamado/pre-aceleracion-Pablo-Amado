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

import com.init.Challenge.model.Genre;
import com.init.Challenge.model.Movie;

@Component
public class MovieSpecification {

	public Specification<Movie> getByFilters(String tittle, Set<Long> genres, String order) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            
            if (StringUtils.hasLength(tittle)) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("tittle")),
                                "%" + tittle.toLowerCase() + "%"
                        )
                );
            }

            if (!CollectionUtils.isEmpty(genres)) {
                Join<Genre, Movie> join = root.join("genres", JoinType.INNER);
                Expression<String> idGenre = join.get("genId");
                predicates.add(idGenre.in(genres));
            }
            
            query.distinct(true);

            String orderByField = "tittle";
            query.orderBy(
                    order.equalsIgnoreCase("asc") ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
