package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Money {

    private int quantity;

    private String currency;

    private BigDecimal productPrice;

    public Money(int quantity, String currency, BigDecimal productPrice){
        this.quantity = quantity;
        this.currency = currency;
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }
}
