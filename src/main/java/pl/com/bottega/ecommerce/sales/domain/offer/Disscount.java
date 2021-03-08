package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Disscount {
    public String getDiscountCause() {
        return discountCause;
    }

    public void setDiscountCause(String discountCause) {
        this.discountCause = discountCause;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    private String discountCause;

    private BigDecimal discount;

    public Disscount(String discountCause, BigDecimal discount) {
        this.discountCause = discountCause;
        this.discount = discount;
    }

}
