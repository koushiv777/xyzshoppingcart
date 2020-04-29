package com.xyz.shopping.online.productservice.specifications;

import com.xyz.shopping.online.productservice.entity.Product;
import com.xyz.shopping.online.productservice.util.SearchCriteria;
import com.xyz.shopping.online.productservice.util.SearchOperation;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecificationsBuilder {
    private List<SearchCriteria> params;

    public ProductSpecificationsBuilder(){
        params = new ArrayList<>();
    }

    public final ProductSpecificationsBuilder with(final String key, final SearchOperation operation, final Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Product> build(){
        if(params.size() == 0){
            return null;
        }

        Specification<Product> result = new ProductSpecification(params.get(0));

        for(int i = 1; i < params.size(); i++){
            result = Specification.where(result).and(new ProductSpecification(params.get(i)));
        }
        return result;
    }
}
