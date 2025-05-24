package com.ecommerce_yt.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart" , cascade = CascadeType.ALL)
    private Set<CartItem> cartItems = new HashSet<>();

    private double totalPrice;

    private int totalItems;

    private double totalMrpPrice;

    private double discount;

    private String couponCode;

}
