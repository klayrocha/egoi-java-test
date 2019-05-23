package com.klayrocha.egoi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
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

	Page<Category> findAll(int page, int count);

	List<Category> findByName(String name);

	Category save(Category category);

	Optional<Category> findById(Integer id);

	void delete(Integer id);

}
