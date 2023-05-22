package com.example.ecommerce.Controller;

import com.example.ecommerce.ApiResponse.ApiResponse;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Model.User;
import com.example.ecommerce.Service.MerchantService;
import com.example.ecommerce.Service.MerchantStockService;
import com.example.ecommerce.Service.ProductService;
import com.example.ecommerce.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserControl {

    private final UserService userService;
    private final ProductService productService;
    private final MerchantStockService merchantStockService;
    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getUser(){
        ArrayList<User> users=userService.getUser();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user , Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(" User Added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@Valid @RequestBody User user ,Errors errors ,@PathVariable int id){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdate=userService.updateUser(id, user);
        if(isUpdate){
            return ResponseEntity.status(200).body("User Update");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        boolean isDelete=userService.deleteUser(id);
        if(isDelete){
            return ResponseEntity.status(200).body("User Deleted");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }

    @PostMapping("/user/{userid}/product/{productid}/merchant/{merchantid}")
    public ResponseEntity buy (@PathVariable() int userid, @PathVariable() int productid, @PathVariable() int merchantid) {

        if (!merchantStockService.haveProduct(merchantid, productid)) {
            return ResponseEntity.status(400).body("The merchant don't have Product");
        }

        Product product = productService.getProductById(productid);
        if (!userService.haveBalance(userid, product.getPrice())) {
            return ResponseEntity.status(400).body("The user don't have balance");
        }

        merchantStockService.reduceProduct(merchantid, productid);
        userService.reduceBalance(userid, product.getPrice());

        return ResponseEntity.status(200).build();
    }

    @GetMapping("/getuser")
    public ResponseEntity User(@Valid @PathVariable int id) {
        boolean isUser = userService.user(id);
        if (isUser) {
            return ResponseEntity.status(200).body("User Valid");
        }
        return ResponseEntity.status(400).body("User not Found");
    }
@GetMapping("/getproduct")
    public ResponseEntity product (@Valid@PathVariable int id) {
        boolean isProduct = userService.product(id);
        if (isProduct) {
            return ResponseEntity.status(200).body("product Valid");
        }
        return ResponseEntity.status(400).body("product not Found");
    }
    @GetMapping("/getmerchant")
    public ResponseEntity merchant (@Valid @PathVariable int id){
        boolean isMerchant = userService.merchant(id);
        if (isMerchant) {
            return ResponseEntity.status(200).body("merchant Valid");
        }
        return ResponseEntity.status(400).body("merchant not Found");

    }

}


