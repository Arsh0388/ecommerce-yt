package com.ecommerce_yt.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class BusinessDetails {

    private String businessName;

    private String businessEmail;
    private String businessMobile;
    private String businessAddress;
    private String logo;
    private String banner;


}
