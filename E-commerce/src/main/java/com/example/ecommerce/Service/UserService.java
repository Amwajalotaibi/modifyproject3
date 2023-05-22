package com.example.ecommerce.Service;

import com.example.ecommerce.Model.Merchant;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> users= new ArrayList<>();
    ArrayList<Product> products=new ArrayList<>();
    ArrayList<Merchant> merchants=new ArrayList<>();

    public ArrayList<User> getUser(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

    public boolean updateUser(int id ,User user){
        for (int i = 0; i <users.size() ; i++) {
            if(users.get(i).getId()==id){
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id){
        for (int i = 0; i <users.size() ; i++) {
            if (users.get(i).getId()==id){
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    // new add

    public boolean haveBalance(int userid, double balance) {
        for (int i = 0; i <users.size() ; i++) {
            if (users.get(i).getId()==userid){
                if(users.get(i).getBalance() >= balance){
                    return true;
                }
            }
        }
        return false;
    }

    public void reduceBalance(int userid, double balance) {
        for (int i = 0; i <users.size() ; i++) {
            if (users.get(i).getId()==userid){
                users.get(i).setBalance(users.get(i).getBalance() - balance);
            }
        }
    }

    public boolean user(int id){
        for (int i = 0; i <users.size();i++) {
            if (users.get(i).getId()==id){
                return true;
            }
        }
        return false;
    }

    public boolean product(int id){
        for (int i = 0; i <products.size() ; i++) {
            if (products.get(i).getId()==id){
                return true;
            }
        }
        return false;
    }

    public boolean merchant(int id){
        for (int i = 0; i <merchants.size() ; i++) {
            if (merchants.get(i).getId()==id){
                return true;
            }
        }
        return false;
    }
}
