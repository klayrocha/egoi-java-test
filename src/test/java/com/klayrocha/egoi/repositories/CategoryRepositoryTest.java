package com.klayrocha.egoi.repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.klayrocha.egoi.entity.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoryRepositoryTest {
	
	@Autowired
	public CategoryRepository categoryRepository;
	
	private static boolean exec = true;

	@Before
	public void setUp() {
		if(exec) {
			Category category = this.categoryRepository.save(getCategoryData());
			saveSubCategories(category);
			exec = false;
		}
	}

	private void saveSubCategories(Category category) {
		for (int i = 1; i <= 20; i++) {
			Category subCategory = new Category();
			subCategory.setName("SubCategory Test_" + i);
			subCategory.setCategoryId(category);
			this.categoryRepository.save(subCategory);
		}
	}

	private Category getCategoryData() {
		Category category = new Category();
		category.setName("Category Test");
		return category;
	}

	@Test
	public void findAll() {
		List<Category> categories = categoryRepository.findAll();
		assertEquals(21,categories.size());
	}

	@Test
	public void findByNameOneCategory() {
		List<Category> categories = categoryRepository.findByName("Category Test");
		assertEquals(1, categories.size());
	}
	
	@Test
	public void findByNameAllSubCategory() {
		List<Category> categories = categoryRepository.findByName("%SubCategory%");
		assertEquals(20, categories.size());
	}
}
