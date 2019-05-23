package com.klayrocha.egoi.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klayrocha.egoi.dto.CategoryDTO;
import com.klayrocha.egoi.entity.Category;
import com.klayrocha.egoi.response.Response;
import com.klayrocha.egoi.service.CategoryService;

/**
 * Class RESTful for CategoryController
 * 
 * @author Francis Klay Rocha
 *
 */

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
public class CategoryController {

	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	public CategoryController() {
	}

	@PostMapping()
	public ResponseEntity<Response<CategoryDTO>> create(HttpServletRequest request,
			@RequestBody CategoryDTO categoryDTO) {
		Response<CategoryDTO> response = new Response<CategoryDTO>();
		log.info("Saving PJ: {}", categoryDTO.toString());
		response.setData(convertToCategoryDTO(categoryService.save(convertToCategory(categoryDTO))));
		return ResponseEntity.ok(response);
	}

	private Category convertToCategory(CategoryDTO categoryDTO) {
		return null;
	}

	private CategoryDTO convertToCategoryDTO(Category category) {
		return null;
	}

}
