package com.example.ecommerce.Controller;

import com.example.ecommerce.ApiResponse.ApiResponse;
import com.example.ecommerce.Model.Category;
import com.example.ecommerce.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryControl {

   private final CategoryService categoryService;

@GetMapping("/get")
   public ResponseEntity getCategory(){

       ArrayList<Category> categories=categoryService.getCategory();

        return ResponseEntity.status(200).body(categories);
   }
@PostMapping("/add")
   public ResponseEntity addCategory(@Valid @RequestBody Category category , Errors errors){
    if(errors.hasErrors()){
        String message=errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }
    categoryService.addCategory(category);
    return ResponseEntity.status(200).body("Category Added");
   }
   @PutMapping("/update/{id}")
public ResponseEntity updateCategory(@Valid @RequestBody Category category , Errors errors ,@PathVariable int id ){
    if (errors.hasErrors()){
        String message=errors.getFieldError().getDefaultMessage();
        return ResponseEntity.status(400).body(new ApiResponse(message));
    }
    boolean isUpdate=categoryService.updateCategory(id, category);
    if (isUpdate){
        return ResponseEntity.status(200).body("Category updated");
    }
    return ResponseEntity.status(400).body("Wrong id");
}
@DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id){
    boolean isDelete=categoryService.deleteCategory(id);
    if (isDelete){
        return ResponseEntity.status(200).body("Category delete");
    }
    return ResponseEntity.status(400).body("Wrong id");
}

}
