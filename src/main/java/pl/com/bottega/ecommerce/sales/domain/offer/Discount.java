package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Discount {

    private String discountCause;

    private BigDecimal discount;

    public Discount (String discountCause, BigDecimal discount){
        this.discount = discount;
        this. discountCause = discountCause;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}
