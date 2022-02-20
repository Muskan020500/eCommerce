package com.example.Shopper.Entities;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table
public class Customer {
    @PrimaryKey
    private @NonNull UUID customer_id;
    private @NonNull String customer_name;
    private @NonNull String customer_email;
    private @NonNull Long customer_phone;
}
