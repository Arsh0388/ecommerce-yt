package com.ecommerce_yt.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    private String discountPercentage;

    private LocalDate validityStartDate;

    private LocalDate validityEndDate;

    private String minimumOrderValue;

    private boolean isActive = true;

    // to map it. the name should match the attribute name.
    @ManyToMany(mappedBy = "usedCoupons") // it will just map it to the user usedCoupons field.
    @JsonIgnore // no need to show in the frontend
    private Set<User> usedByUsers = new HashSet<>();
}

