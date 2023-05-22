package com.example.ecommerce.Controller;

import com.example.ecommerce.ApiResponse.ApiResponse;
import com.example.ecommerce.Model.MerchantStock;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Service.MerchantStockService;
import com.example.ecommerce.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantstockControl {

    private final MerchantStockService  merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getMerchantstock(){
        ArrayList<MerchantStock> merchantStocks=merchantStockService.getMerchantStock();
        return ResponseEntity.status(200).body(merchantStocks);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantstock(@Valid @RequestBody MerchantStock merchantStock , Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body("Merchant Stock Added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantstock(@Valid @RequestBody MerchantStock merchantStock,Errors errors,@PathVariable int id){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        boolean isUpdate=merchantStockService.updateMerchantStock(id, merchantStock);
        if (isUpdate){
            return ResponseEntity.status(200).body("Merchant Sock Updated");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }

    @DeleteMapping("/delet/{id}")
    public ResponseEntity deleteMerchantstock(@PathVariable int id ){
        boolean isDelete=merchantStockService.deleteMerchantStock(id);
        if(isDelete){
            return ResponseEntity.status(200).body("Merchant Stock Deleted");
        }
        return ResponseEntity.status(400).body("Wrong id");
    }

    @PostMapping("/addproduct") // الاضافة الجديدة
    public ResponseEntity addProduct(MerchantStock merchantStock){
        boolean isAdded=merchantStockService.addMerchantStock(merchantStock);
        if(isAdded){
            return ResponseEntity.status(200).body("Merchant Stock was added");
        }
        return ResponseEntity.status(400).body("not added");
    }


}
