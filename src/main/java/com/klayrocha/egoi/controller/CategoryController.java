package com.klayrocha.egoi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

	@PostMapping()
	public ResponseEntity<Response<CategoryDTO>> create(@Valid @RequestBody CategoryDTO categoryDTO,
			BindingResult result) {
		log.info("create : {}", categoryDTO.toString());
		Response<CategoryDTO> response = new Response<CategoryDTO>();
		validateCreate(categoryDTO, result);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			log.error("Erro create Category: {}", result.getAllErrors());
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(convertToCategoryDTO(categoryService.save(convertToCategory(categoryDTO))));
		return ResponseEntity.ok(response);
	}

	@PutMapping
	public ResponseEntity<Response<CategoryDTO>> update(@Valid @RequestBody CategoryDTO categoryDTO,
			BindingResult result) {
		log.info("update : {}", categoryDTO.toString());
		Response<CategoryDTO> response = new Response<CategoryDTO>();
		validateUpdate(categoryDTO, result);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			log.error("Error update Category: {}", result.getAllErrors());
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(convertToCategoryDTO(categoryService.save(convertToCategory(categoryDTO))));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/findByName/{name}")
	public ResponseEntity<Response<List<CategoryDTO>>> findAll(@PathVariable("name") String name) {
		log.info("findAll : {}", name);
		Response<List<CategoryDTO>> response = new Response<List<CategoryDTO>>();
		if (name.equals("all")) {
			name = "";
		}
		List<Category> categories = categoryService.findByName(name);
		response.setData(convertToListCategoryDTO(categories));
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") Integer id) {
		log.info("delete : {}", id);
		Response<String> response = new Response<String>();
		validateDelete(id, response);
		if (response.getErrors() != null && response.getErrors().size() > 0) {
			log.error("Error delete : {}", response.getErrors());
			return ResponseEntity.badRequest().body(response);
		}
		categoryService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<CategoryDTO>> findById(@PathVariable("id") Integer id) {
		log.info("findById : {}", id);
		Response<CategoryDTO> response = new Response<CategoryDTO>();
		Optional<Category> optionalCategory = categoryService.findById(id);
		if (!optionalCategory.isPresent()) {
			response.getErrors().add("Category not found");
			log.error("Error findById : {}", "Category not found");
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(convertToCategoryDTO(optionalCategory.get()));
		return ResponseEntity.ok(response);
	}

	private void validateCreate(CategoryDTO categoryDTO, BindingResult result) {
		if (categoryDTO.getCategory_id() != null) {
			Optional<Category> optionalSubCategory = categoryService.findById(categoryDTO.getCategory_id());
			if (!optionalSubCategory.isPresent()) {
				result.addError(new ObjectError("SubCategory", "SubCategory not found"));
				log.error("Error create: {}", result.getAllErrors());
				return;
			}
		}
	}

	private void validateUpdate(CategoryDTO categoryDTO, BindingResult result) {
		if (categoryDTO.getId() == null) {
			result.addError(new ObjectError("id", "Error 'id' is mandatory"));
			log.error("Error update: {}", result.getAllErrors());
			return;
		}
		Optional<Category> optionalCategory = categoryService.findById(categoryDTO.getId());
		if (!optionalCategory.isPresent()) {
			result.addError(new ObjectError("Category", "Category not found"));
			log.error("Error update: {}", result.getAllErrors());
			return;
		}
		if (categoryDTO.getCategory_id() != null) {
			Optional<Category> optionalSubCategory = categoryService.findById(categoryDTO.getCategory_id());
			if (!optionalSubCategory.isPresent()) {
				result.addError(new ObjectError("SubCategory", "SubCategory not found"));
				log.error("Error update: {}", result.getAllErrors());
				return;
			}
		}
	}

	private void validateDelete(Integer id, Response<String> response) {
		Optional<Category> optionalCategory = categoryService.findById(id);
		if (!optionalCategory.isPresent()) {
			response.getErrors().add("Category not found");
			log.error("Error delete: {}", "Category not found");
			return;
		}
		if (optionalCategory.get().getSubcategories() != null && optionalCategory.get().getSubcategories().size() > 0) {
			response.getErrors().add("This category has subcategories linked");
			log.error("Error delete: {}", "This category has subcategories linked");
			return;
		}

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
