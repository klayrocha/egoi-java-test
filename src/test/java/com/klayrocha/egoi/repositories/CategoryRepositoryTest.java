package com.klayrocha.egoi.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.klayrocha.egoi.EgoiApplicationTests;
import com.klayrocha.egoi.entity.Category;

public class CategoryRepositoryTest extends EgoiApplicationTests {

	@Test
	public void findAll() {
		List<Category> categories = categoryRepository.findByName("%%");
		assertNotNull(categories);
	}

	@Test
	public void findByNameOneCategory() {
		List<Category> categories = categoryRepository.findByName("Main Category Test");
		assertEquals(1, categories.size());
	}

	@Test
	public void findByNameAllSubCategory() {
		List<Category> categories = categoryRepository.findByName("%SubCategory%");
		assertNotNull(categories);
	}

	@Test
	public void findByNameNoResult() {
		List<Category> categories = categoryRepository.findByName("%xptoxptoxpto%");
		assertEquals(0, categories.size());
	}
}
