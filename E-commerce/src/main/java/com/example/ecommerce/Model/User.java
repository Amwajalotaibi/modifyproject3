package com.example.ecommerce.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {

    @NotNull(message = "must not be empty")
    @Size(min = 3,message = "have to be 3 character long")
    private int id;

    @NotEmpty(message = "must not be empty")
    @Size(min = 5,message = "have to be 5 length long")
    private String username;

    @NotEmpty(message = "must not be empty")
    @Size(min = 6,message = "have to be 6 length long , must have characters and digits ")
    private String password;

    @NotEmpty(message = "must not be empty")
    @Email(message = "must be valid email ")
    private String email;

    @NotEmpty(message = "must not be empty")
    @NotEmpty(message = " have to be in ( “Admin”,”Customer”)")
    private String role;

    @NotNull(message = "must not be empty")
    @Positive(message = " have to be positive")
    private double balance;
}
