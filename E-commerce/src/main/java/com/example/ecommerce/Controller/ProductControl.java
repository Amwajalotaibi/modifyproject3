package com.example.ecommerce.Controller;

import com.example.ecommerce.ApiResponse.ApiResponse;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductControl {
    private final ProductService productService;


    @GetMapping("/get")
    public ResponseEntity getProduct(){
        ArrayList<Product> products=productService.getProduct();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));

        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Product Added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@Valid @RequestBody Product product,Errors errors,@PathVariable int id){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdate= productService.updateProduct(id, product);
        if(isUpdate){
            return ResponseEntity.status(200).body(" Product Updated");
        }
        return ResponseEntity.status(400).body(" Wrong id");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id){
        boolean isDelete= productService.deleteProduct(id);
        if(isDelete){
            return ResponseEntity.status(200).body(" Product Deleted");
        }
        return ResponseEntity.status(400).body(" Wrong id");
    }



}
