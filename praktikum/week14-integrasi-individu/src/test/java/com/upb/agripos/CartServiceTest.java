package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.upb.agripos.model.Product;
import com.upb.agripos.service.CartService;

public class CartServiceTest {

    @Test
    void testHitungTotal() {
        CartService cs = new CartService();
        cs.addToCart(new Product("001","Benih Padi",10000,10), 2);
        assertEquals(20000, cs.getTotal());
    }
}
