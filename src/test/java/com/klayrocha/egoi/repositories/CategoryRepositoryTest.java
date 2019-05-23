package com.klayrocha.egoi.repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.klayrocha.egoi.entity.Category;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoryRepositoryTest {

	@Autowired
	public CategoryRepository categoryRepository;

	@Before
	public void setUp() {
		Category category = this.categoryRepository.save(getCategoryData());
		saveSubCategories(category);
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
		System.out.println("#########################  COMEÇOU #######################");
		Pageable pages = PageRequest.of(0, 1);
		Page<Category> categories = categoryRepository.findAll(pages);
		List<Category> categoriesRet = categories.get().collect(Collectors.toList());
		for (Category category : categoriesRet) {
			System.out.println(category.getName());
		}
		assertEquals(1, categories.getTotalElements());
	}

	@Test
	public void findByName() {
		List<Category> categories = categoryRepository.findByName("Category Test");
		assertEquals(2, categories.size());
	}
}
