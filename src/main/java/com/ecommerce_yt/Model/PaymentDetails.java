package com.ecommerce_yt.Model;

import com.ecommerce_yt.domain.PaymentStatus;
import jakarta.persistence.Column;
import lombok.*;

@Data
public class PaymentDetails {

    private String paymentId;
    private String razorPaymentLinkId;
    private String razorpayPaymentLinkReferenceId;
    private String razorPaymentStatus;
    private String razorPaymentIdZWSP;
    @Column(name = "payment_status_detail")
    private PaymentStatus paymentStatus;
}
