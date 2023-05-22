package com.example.ecommerce.Service;

import com.example.ecommerce.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks=new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStock(){
        return merchantStocks;
    }

    public boolean addMerchantStock(MerchantStock merchantStock){ // حولناها لبولين

        return merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(int id ,MerchantStock merchantStock){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId()==id){
                merchantStocks.set(i, merchantStock);
                return true;
            }

        }
        return false;
    }

    public boolean deleteMerchantStock(int id){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId()==id){
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }
//new add
    public boolean haveProduct(int merchantid,int productid) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantid() == merchantid) {
                if (merchantStocks.get(i).getProductid() == productid) {
                    if (merchantStocks.get(i).getStock() >= 1){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void reduceProduct(int merchantid,int productid) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantid() == merchantid) {
                if (merchantStocks.get(i).getProductid() == productid) {
                    merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() - 1);
                }
            }
        }
    }


}
