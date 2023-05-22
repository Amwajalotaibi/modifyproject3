package com.example.ecommerce.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MerchantStock {

    @NotNull(message = "must not be empty")
    @Size(min = 3,message = "have to be 3 character long")
    private int id ;

    @NotEmpty(message = "must not be empty")
    @Size(min = 3,message = "have to be 3 character long")
    private int productid;

    @NotNull(message = "must not be empty")
    @Size(min = 3,message = "have to be 3 character long")
    private int merchantid;

    @NotNull(message = "must not be empty")
    @Size(min = 11,message = "have to be more than 10 at start ")
    private int stock;

}
