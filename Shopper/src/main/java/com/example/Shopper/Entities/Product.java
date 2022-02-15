package com.example.Shopper.Entities;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import javax.persistence.*;
import java.util.UUID;

//@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table
public class Product {
//@Id
//@GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKey
    private @NotNull UUID product_id;
    private @NotNull String product_name;
    private @NotNull double product_price;
}
