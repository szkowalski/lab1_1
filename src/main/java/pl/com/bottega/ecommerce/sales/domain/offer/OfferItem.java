/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;

public class OfferItem {
    public static final int PRIME = 31;
    public static final int RESULT = 31;
    // product
    private Product product;

    private final int quantity;
    private  Money money;


    // discount
    private Disscount disscount;



    public OfferItem(String productId, BigDecimal productPrice,String productCurrenty, String productName, Date productSnapshotDate,
            String productType, int quantity, BigDecimal discount, String discountCause) {

        //product
        this.product = new Product(productId, new Money(productPrice,productCurrenty), productName,productSnapshotDate,productType);

        this.quantity = quantity;


        // disount
        this.disscount = new Disscount(discountCause,discount);

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.subtract(discount);
        }

        this.money = new Money(productPrice.multiply(new BigDecimal(quantity)).subtract(discountValue),productCurrenty);
    }

    public String getProductId() {
        return product.getId();
    }

    public BigDecimal getProductPrice() {
        return product.getPrice();
    }

    public String getProductName() {
        return product.getName();
    }

    public Date getProductSnapshotDate() {
        return product.getProductSnapshotDate();
    }

    public String getProductType() {
        return product.getProductType();
    }

    public BigDecimal getTotalCost() {
        return money.getPrice();
    }

    public String getTotalCostCurrency() {
        return money.getCurrency();
    }

    public BigDecimal getDiscount() {
        return disscount.getDiscount();
    }

    public String getDiscountCause() {
        return disscount.getDiscountCause();
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        final int prime = PRIME;
        int result = RESULT;
        result = prime * result + (disscount.getDiscount() == null ? 0 : disscount.getDiscount().hashCode());
        result = prime * result + (product.getName() == null ? 0 : product.getName().hashCode());
        result = prime * result + (product.getPrice() == null ? 0 : product.getPrice().hashCode());
        result = prime * result + (product.getId() == null ? 0 : product.getId().hashCode());
        result = prime * result + (product.getProductType() == null ? 0 : product.getProductType().hashCode());
        result = prime * result + quantity;
        result = prime * result + (money.getPrice() == null ? 0 : money.getPrice().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OfferItem other = (OfferItem) obj;
        if (disscount.getDiscount() == null) {
            if (other.disscount.getDiscount() != null) {
                return false;
            }
        } else if (!disscount.getDiscount().equals(other.getDiscount())) {
            return false;
        }
        if (checIsNull(other)) return false;

        if (money.getPrice() == null) {
            return other.money.getPrice() == null;
        } else return money.getPrice().equals(other.getTotalCost());
    }

    /**
     *
     * @param other
     * @param delta
     *            acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (checIsNull(other)) return false;

        BigDecimal max;
        BigDecimal min;
        if (money.getPrice().compareTo(other.getTotalCost()) > 0) {
            max = money.getPrice();
            min = other.getTotalCost();
        } else {
            max = other.getTotalCost();
            min = money.getPrice();
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

    private boolean checIsNull(OfferItem other) {
        if (product.getName() == null) {
            if (product.getName() != null) {
                return true;
            }
        } else if (!product.getName().equals(other.getProductName())) {
            return true;
        }
        if (product.getPrice() == null) {
            if (other.getProductPrice() != null) {
                return true;
            }
        } else if (!product.getPrice().equals(other.getProductPrice())) {
            return true;
        }
        if (product.getId() == null) {
            if (other.getProductId() != null) {
                return true;
            }
        } else if (!product.getId().equals(other.getProductId())) {
            return true;
        }
        if (!product.getProductType().equals(other.getProductType())) {
            return true;
        }

        return quantity != other.quantity;
    }

}
