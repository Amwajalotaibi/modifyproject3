package com.example.ecommerce.Controller;

import com.example.ecommerce.ApiResponse.ApiResponse;
import com.example.ecommerce.Model.Merchant;
import com.example.ecommerce.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantControl {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getMerchant(){

        ArrayList<Merchant> merchants=merchantService.getMerchant();
        return ResponseEntity.status(200).body(merchants);
    }


    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant,Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body("Merchant Added");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@Valid @RequestBody Merchant merchant, Errors errors , @PathVariable int id){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));

        }
        boolean isUpdate= merchantService.updateMerchant(id, merchant);
        if(isUpdate){
            return ResponseEntity.status(200).body("Merchant Updated");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }


    @DeleteMapping("/delete/{id}")
        public ResponseEntity deleteMerchant(@PathVariable int id){
            boolean isDelete= merchantService.deleteMerchant(id);
            if(isDelete){
                return ResponseEntity.status(200).body("Merchant Deleted");
            }
            return ResponseEntity.status(400).body("Wrong id");
        }
    }



