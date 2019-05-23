package com.klayrocha.egoi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.klayrocha.egoi.entity.Category;

/**
 * Service interface for query in database and business rules
 * 
 * @author Francis Klay Rocha
 *
 */
@Service
public interface CategoryService {

	List<Category> findByName(String name);

	Category save(Category category);

	Optional<Category> findById(Integer id);

	void delete(Integer id);

}
