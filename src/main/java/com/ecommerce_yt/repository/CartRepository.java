package com.ecommerce_yt.repository;

import com.ecommerce_yt.Model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository  extends CrudRepository<Cart, Long> {

}
