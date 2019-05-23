package com.klayrocha.egoi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.klayrocha.egoi.entity.Category;
import com.klayrocha.egoi.helper.CategoryBuilder;
import com.klayrocha.egoi.repositories.CategoryRepository;
import com.klayrocha.egoi.service.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EgoiApplicationTests {

	@Autowired
	public CategoryRepository categoryRepository;

	@Autowired
	public CategoryService categoryService;

	private static boolean exec = true;

	protected MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		if (exec) {
			Category category = this.categoryRepository.save(CategoryBuilder.oneCategory().now());
			saveSubCategories(category);
			exec = false;
		}
	}

	private void saveSubCategories(Category category) {
		for (int i = 1; i <= 20; i++) {
			Category subCategory = CategoryBuilder.oneCategory().now();
			subCategory.setName("SubCategory Test_" + i);
			subCategory.setCategoryId(category);
			this.categoryRepository.save(subCategory);
		}
	}

	@Test
	public void contextLoads() {
	}
}
