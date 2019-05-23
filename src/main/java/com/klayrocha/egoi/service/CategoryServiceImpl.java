package com.klayrocha.egoi.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klayrocha.egoi.entity.Category;
import com.klayrocha.egoi.repositories.CategoryRepository;

/**
 * Service class for query in database and business rules
 * 
 * @author Francis Klay Rocha
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category save(Category category) {
		log.info("Saving the category: {}", category);
		return this.categoryRepository.save(category);
	}

	@Override
	public Optional<Category> findById(Integer id) {
		log.info("Find the Category by ID {}", id);
		return this.categoryRepository.findById(id);

	}

	@Override
	public void delete(Integer id) {
		log.info("Deleting the Category ID {}", id);
		this.categoryRepository.deleteById(id);
	}

	@Override
	public List<Category> findAll() {
		log.info("Find All ");
		return categoryRepository.findAll();
	}

	@Override
	public List<Category> findByName(String name) {
		log.info("Find the Category by name {}", name);
		return categoryRepository.findByName("%"+name+"%");
	}
}
