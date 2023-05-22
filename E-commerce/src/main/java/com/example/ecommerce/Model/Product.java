package com.example.ecommerce.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@AllArgsConstructor
@Data
public class Product {
    @NotNull(message = "must not be empty ")
    @Size(min = 3,message = "have to be 3 character long")
    private int id;

    @NotEmpty(message = "must not be empty")
    @Size(min = 3,message = "have to be 3 length long")
    private String name;

    @NotNull(message = "must not be empty")
    @Positive(message = "must be positive number")
    private double price;

    @NotNull(message = "must not be empty")
    @Size(min = 3,message = "have to be 3 character long")
    private int CategoryID;
}
