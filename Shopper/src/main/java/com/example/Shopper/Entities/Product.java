package com.example.Shopper.Entities;




import lombok.NonNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;


@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table
public class Product {

//@GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKey
    private @NonNull UUID product_id;
    private @NonNull String product_name;
    private @NonNull Double product_price;
}
