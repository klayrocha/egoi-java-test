package com.klayrocha.egoi.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klayrocha.egoi.EgoiApplicationTests;
import com.klayrocha.egoi.entity.Category;
import com.klayrocha.egoi.helper.CategoryBuilder;

public class CategoryControllerTest extends EgoiApplicationTests {

	@Test
	public void testfindByNameAll() throws Exception {
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get("/api/category/findByName/all").accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void testfindById() throws Exception {
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.get("/api/category/1").accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void save() throws Exception {
		Category category = CategoryBuilder.oneCategory().now();
		category.setName("Test Method save");
		int status = mvc
				.perform(MockMvcRequestBuilders.post("/api/category/")
						.contentType(MediaType.APPLICATION_STREAM_JSON_VALUE).content(asJsonString(category)))
				.andReturn().getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void update() throws Exception {
		Category category = CategoryBuilder.oneCategory().now();
		category.setId(new Integer(9));
		category.setName("Test Method update");
		int status = mvc
				.perform(MockMvcRequestBuilders.put("/api/category/")
						.contentType(MediaType.APPLICATION_STREAM_JSON_VALUE).content(asJsonString(category)))
				.andReturn().getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void delete() throws Exception {
		int status = mvc.perform(
				MockMvcRequestBuilders.delete("/api/category/8").contentType(MediaType.APPLICATION_STREAM_JSON_VALUE))
				.andReturn().getResponse().getStatus();
		assertEquals(200, status);
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
