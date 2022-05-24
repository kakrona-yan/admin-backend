package com.hatthabank.adminbackend.search;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
public class GenericSpecification<T> implements Specification<T> {
	private static final long serialVersionUID = 1900581010229669687L;

    private List<SearchCriteria> list;

    public GenericSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }
    
    Path getPath(String keyPath, Root<T> root) {
    	Path path = root; 
    	for(String tmp : keyPath.split("[.]")) {
    		path = path.get(tmp);
    	}
    	return path;
    }

    @SuppressWarnings("unchecked")
	@Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();

        //add add criteria to predicates
        for (SearchCriteria criteria : list) {
        	
        	Path path = getPath(criteria.getKey(),root);
        	
            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                predicates.add(builder.greaterThan(
                        path, criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                predicates.add(builder.lessThan(
                        path, criteria.getValue().toString()));

            } else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
            	if(criteria.getValue() instanceof Date) {
                    predicates.add(builder.greaterThanOrEqualTo(
                            path, (Date) criteria.getValue()));
            	}else {
	                predicates.add(builder.greaterThanOrEqualTo(
	                        path, criteria.getValue().toString()));
            	}
            } else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
            	if(criteria.getValue() instanceof Date) {
                    predicates.add(builder.lessThanOrEqualTo(
                            path,  (Date) criteria.getValue()));
            	}else {
	                predicates.add(builder.lessThanOrEqualTo(
	                        path, criteria.getValue().toString()));
            	}
            } else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(builder.notEqual(
                        path, criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(
                		path, criteria.getValue()));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(builder.like(
                        builder.lower(path),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(builder.like(
                        builder.lower(path),
                        criteria.getValue().toString().toLowerCase() + "%"));
            } else if (criteria.getOperation().equals(SearchOperation.BETWEEN)) {
            	String[] value = criteria.getValue().toString().split(",");
            	predicates.add(builder.between(path, value[0], value[1]));
            }else if (criteria.getOperation().equals(SearchOperation.BETWEEN_DATE)) {
            	SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
            	String[] value = criteria.getValue().toString().split(",");
            	try {
					predicates.add(builder.between(path, format.parse(value[0]), format.parse(value[1])));
				} catch (ParseException e) {e.printStackTrace();}
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
