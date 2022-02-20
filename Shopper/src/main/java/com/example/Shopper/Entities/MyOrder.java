package com.example.Shopper.Entities;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table
public class MyOrder {
    @PrimaryKey
    private @NonNull UUID order_id;
    private @NonNull Double total_amount;
    private @NonNull UUID customer_id;
    private @NonNull List<UUID> product_list;
}
