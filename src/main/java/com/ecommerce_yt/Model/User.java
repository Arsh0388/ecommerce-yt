package com.ecommerce_yt.Model;


import com.ecommerce_yt.domain.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty( access = JsonProperty.Access.WRITE_ONLY) // password will not come in front end
    private String password;

    private String email;

    private String fullName;

    private String mobile;

    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER; // default role user

    @OneToMany
    private Set<Address> addresses = new HashSet<>(); // one user has multiple addresses. one-to-many relation

    @ManyToMany   // many-to-many relation
    @JsonIgnore    // this will not come in the frontend. to check if the coupon is already used or not.  
    private Set<Coupon> usedCoupons = new HashSet<>();   // already used the coupon        

}
