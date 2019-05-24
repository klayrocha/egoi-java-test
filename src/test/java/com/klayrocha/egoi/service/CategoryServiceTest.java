package com.klayrocha.egoi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.klayrocha.egoi.EgoiApplicationTests;
import com.klayrocha.egoi.entity.Category;
import com.klayrocha.egoi.helper.CategoryBuilder;

public class CategoryServiceTest extends EgoiApplicationTests {

	@Test
	public void saveCategory() {
		Category category = CategoryBuilder.oneCategory().now();
		category.setName("Test Method save Seervice");
		Category categoryDB = categoryService.save(category);
		assertNotNull(categoryDB.getId());
	}

	@Test
	public void findById() {
		Optional<Category> optionalCategory = categoryService.findById(new Integer(1));
		assertEquals(true, optionalCategory.isPresent());
	}

	@Test
	public void delete() {
		categoryService.delete(10);
		Optional<Category> optionalCategory = categoryService.findById(new Integer(10));
		assertEquals(false, optionalCategory.isPresent());
	}

	@Test
	public void findAll() {
		List<Category> categories = categoryService.findByName("%%");
		assertNotNull(categories);
	}

	@Test
	public void findByNameOneCategory() {
		List<Category> categories = categoryService.findByName("Main Category Test");
		assertEquals(1, categories.size());
	}

	@Test
	public void findByNameAllSubCategory() {
		List<Category> categories = categoryService.findByName("%SubCategory%");
		assertNotNull(categories);
	}

	@Test
	public void findByNameNoResult() {
		List<Category> categories = categoryService.findByName("%xptoxptoxpto%");
		assertEquals(0, categories.size());
	}

}
