package com.ecommerce_yt.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDetails {
    private String accountNumber;
    private String accountHolderName;
    private String ifscCode;


}
