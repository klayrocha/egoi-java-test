package com.klayrocha.egoi.helper;

import java.util.Date;

import com.klayrocha.egoi.entity.Category;

public class CategoryBuilder {

	private Category category;

	private CategoryBuilder() {

	}

	public static CategoryBuilder oneCategory() {
		CategoryBuilder builder = new CategoryBuilder();
		builder.category = new Category();
		builder.category.setName("Main Category Test");
		builder.category.setCreated(new Date());
		builder.category.setModified(new Date());
		return builder;
	}

	public Category now() {
		return category;
	}
}
