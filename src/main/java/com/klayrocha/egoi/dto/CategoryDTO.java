package com.klayrocha.egoi.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Class representing the model of an Category DTO
 * 
 * @author Francis Klay Rocha
 *
 */
public class CategoryDTO {

	private Integer id;

	private Integer category_id;

	@NotEmpty(message = "The field ‘name’ can not be empty")
	@NotNull(message = "The field ‘name’ is mandatory")
	private String name;

	@NotNull(message = "The field ‘created’ is mandatory")
	private Date created;

	@NotNull(message = "The field ‘modified’ is mandatory")
	private Date modified;

	private List<CategoryDTO> subcategories = new ArrayList<CategoryDTO>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public List<CategoryDTO> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<CategoryDTO> subcategories) {
		this.subcategories = subcategories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category_id == null) ? 0 : category_id.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modified == null) ? 0 : modified.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryDTO other = (CategoryDTO) obj;
		if (category_id == null) {
			if (other.category_id != null)
				return false;
		} else if (!category_id.equals(other.category_id))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modified == null) {
			if (other.modified != null)
				return false;
		} else if (!modified.equals(other.modified))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + ", category_id=" + category_id + ", name=" + name + ", created=" + created
				+ ", modified=" + modified + "]";
	}

}
