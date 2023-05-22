package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    ArrayList<Product> products=new ArrayList<>();

    public ArrayList<Product> getProduct(){
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public boolean updateProduct(int id ,Product product){
        for (int i=0 ; i<products.size(); i++){
            if(products.get(i).getId()==id){
                products.set(i,product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(int id ){
        for (int i=0 ; i<products.size(); i++){
            if(products.get(i).getId()==id){
                products.remove(i);
                return true;
            }
        }
        return false;
    }
// new add
    public Product getProductById(int productid) {
        for (int i=0 ; i<products.size(); i++){
            if(products.get(i).getId()==productid){
                return products.get(i);
            }
        }
        return null;
    }

}
