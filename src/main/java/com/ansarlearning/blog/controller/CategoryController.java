package com.ansarlearning.blog.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ansarlearning.blog.payload.CategoryDto;
import com.ansarlearning.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	
	
	//Create Category
	
	@PostMapping("/create-category")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {

		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);

		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);

	}

	//Update Categogry
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("id") Integer categoryId){
		
		CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto, categoryId);
		
		return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
		
	}

	
	//Get Category By Id
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Integer categoryId) {

		CategoryDto categoryById = this.categoryService.getCategoryById(categoryId);

		return new ResponseEntity<CategoryDto>(categoryById, HttpStatus.OK);

	}

	//Get All Category
	
	@GetMapping("/all-categories")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {

		List<CategoryDto> allCategory = this.categoryService.getAllCategory();

		return new ResponseEntity<List<CategoryDto>>(allCategory, HttpStatus.OK);

	}

	//Delete Category By ID
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer categoryId) {
		this.categoryService.deleteCategory(categoryId);

		return new ResponseEntity(Map.of("Message", "Categor Delete Successfully with ID: " + categoryId),
				HttpStatus.OK);
	}

}
