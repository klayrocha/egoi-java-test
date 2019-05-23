	package com.klayrocha.egoi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping(value = "/findByName/{name}")
	public ResponseEntity<Response<List<CategoryDTO>>> findAll(@PathVariable("name") String name) {
		Response<List<CategoryDTO>> response = new Response<List<CategoryDTO>>();
		if(name.equals("all")) {
			name =  "";
		}
		List<Category> categories = categoryService.findByName(name);
		response.setData(convertToListCategoryDTO(categories));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") Integer id) {
		Response<String> response = new Response<String>();
		Optional<Category> optionalCategory = categoryService.findById(id);
		if (!optionalCategory.isPresent()) {
			log.error("Error findById Category not found");
			response.getErrors().add("Category not found");
			return ResponseEntity.badRequest().body(response);
		}
		categoryService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<CategoryDTO>> findById(@PathVariable("id") Integer id) {
		Response<CategoryDTO> response = new Response<CategoryDTO>();
		Optional<Category> optionalCategory = categoryService.findById(id);
		if (!optionalCategory.isPresent()) {
			log.error("Error findById Category not found");
			response.getErrors().add("Category not found");
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(convertToCategoryDTO(optionalCategory.get()));
		return ResponseEntity.ok(response);
	}

	@PostMapping()
	public ResponseEntity<Response<CategoryDTO>> create(HttpServletRequest request,
			@RequestBody CategoryDTO categoryDTO) {
		Response<CategoryDTO> response = new Response<CategoryDTO>();
		log.info("Saving : {}", categoryDTO.toString());
		response.setData(convertToCategoryDTO(categoryService.save(convertToCategory(categoryDTO))));
		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<Response<CategoryDTO>> update(HttpServletRequest request,
			@RequestBody CategoryDTO categoryDTO, BindingResult result) {
		Response<CategoryDTO> response = new Response<CategoryDTO>();
		log.info("Saving : {}", categoryDTO.toString());
		if (categoryDTO.getId() == null) {
			log.error("Error id invalid: {}", result.getAllErrors());
			response.getErrors().add("Error id is mandatory");
			return ResponseEntity.badRequest().body(response);
		}
		Optional<Category> optionalCategory = categoryService.findById(categoryDTO.getId());
		if (!optionalCategory.isPresent()) {
			result.addError(new ObjectError("Category", "Category not found"));
		}
		if (result.hasErrors()) {
			log.error("Error update Category: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(convertToCategoryDTO(categoryService.save(convertToCategory(categoryDTO))));
		return ResponseEntity.ok(response);
	}

	private Category convertToCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
		if (categoryDTO.getId() != null) {
			Optional<Category> optionalCategory = categoryService.findById(categoryDTO.getId());
			if (optionalCategory.isPresent()) {
				category = optionalCategory.get();
			}
		}
		category.setId(categoryDTO.getId());
		category.setName(categoryDTO.getName());
		category.setCreated(categoryDTO.getCreated());
		category.setModified(categoryDTO.getModified());
		if (categoryDTO.getCategory_id() != null) {
			Optional<Category> optionalCategory = categoryService.findById(categoryDTO.getCategory_id());
			if (optionalCategory.isPresent()) {
				category.setCategoryId(optionalCategory.get());
			}
		}
		return category;
	}

	private CategoryDTO convertToCategoryDTO(Category category) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(category.getId());
		dto.setName(category.getName());
		dto.setCreated(category.getCreated());
		dto.setModified(category.getModified());
		if (category.getCategoryId() != null) {
			dto.setCategory_id(category.getCategoryId().getId());
		}
		if (category.getSubcategories() != null && category.getSubcategories().size() > 0) {
			for (Category categoryDB : category.getSubcategories()) {
				dto.getSubcategories().add(convertToCategoryDTO(categoryDB));
			}
		}
		return dto;
	}

	private List<CategoryDTO> convertToListCategoryDTO(List<Category> categories) {
		List<CategoryDTO> listRet = new ArrayList<CategoryDTO>();
		if (categories != null && categories.size() > 0) {
			for (Category category : categories) {
				listRet.add(convertToCategoryDTO(category));
			}
		}
		return listRet;
	}

}
