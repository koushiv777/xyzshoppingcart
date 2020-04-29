package com.xyz.shopping.online.productservice.specifications;

import com.xyz.shopping.online.productservice.entity.Product;
import com.xyz.shopping.online.productservice.util.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecification implements Specification<Product>{

    private SearchCriteria criteria;

    public ProductSpecification(final SearchCriteria criteria){
        super();
        this.criteria = criteria;
    }

    public SearchCriteria getCriteria(){
        return criteria;
    }


    @Override
    public Predicate toPredicate(final Root<Product> root, final CriteriaQuery<?> criteriaQuery, final CriteriaBuilder builder) {
        switch (criteria.getOperation()){
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case LIKE:
                return builder.like(root.get(criteria.getKey()), criteria.getValue().toString());
            default:
                return null;
        }
    }
}
