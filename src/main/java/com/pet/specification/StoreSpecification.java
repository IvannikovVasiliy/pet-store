package com.pet.specification;

import com.pet.entity.StoreEntity;
import com.pet.entity.StoreEntity_;
import com.pet.pojo.StoreSearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class StoreSpecification implements Specification<StoreEntity> {
    private final StoreSearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<StoreEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();

        if (criteria.name != null) {
            predicate
                    .getExpressions()
                    .add(criteriaBuilder.like(
                            root.get(StoreEntity_.NAME), "%" + criteria.name + "%"
                    ));
        }
        if (criteria.cityId != null) {
            predicate
                    .getExpressions()
                    .add(criteriaBuilder.equal(
                            root.get(StoreEntity_.CITY), criteria.cityId
                    ));
        }
        return predicate;
    }
}
