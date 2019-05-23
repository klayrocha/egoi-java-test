package com.klayrocha.egoi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klayrocha.egoi.entity.Category;

/**
 * Repository interface for searching the database
 * 
 * @author Francis Klay Rocha
 *
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query(value = "SELECT c FROM Category c join fetch c.subcategories WHERE c.name = :name and c.categoryId = null")
	List<Category> findByName(@Param("name") String name);

	@Query(value = "SELECT c FROM Category c WHERE c.categoryId = null ")
	Page<Category> findAll(Pageable pages);
}
