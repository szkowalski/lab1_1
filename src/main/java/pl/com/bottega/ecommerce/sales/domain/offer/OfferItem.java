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

    // product
    private Product product;

    private Money totalCost;

    private Date productSnapshotDate;

    private int quantity;

    // discount
    private Discount discount;

    public OfferItem(String productId, BigDecimal productPrice, String productName, Date productSnapshotDate,
                     String productType, String currency, int quantity) {
        this(productId, productPrice, productName, productSnapshotDate, productType, currency, quantity, null, null);
    }

    public OfferItem(String productId, BigDecimal productPrice, String productName, Date productSnapshotDate,
            String productType, String currency, int quantity, BigDecimal discount, String discountCause) {
        this.product = new Product(productId, productName, productType);
        this.totalCost = new Money(productPrice, currency);
        this.productSnapshotDate = productSnapshotDate;
        this.quantity = quantity;
        this.discount = new Discount(discountCause, discount);

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.subtract(discount);
        }

    }

    public String getProductId() {
        return product.getProductId();
    }

    public BigDecimal getProductPrice() {
        return product.getPrice().getAmount();
    }

    public String getProductName() {
        return product.getProductName();
    }

    public Date getProductSnapshotDate() {
        return productSnapshotDate;
    }

    public String getProductType() {
        return product.getProductType();
    }

    public BigDecimal getTotalCost() {
        return totalCost.getAmount();
    }

    public String getTotalCostCurrency() {
        return totalCost.getCurrency();
    }

    public BigDecimal getDiscount() {
        return discount.getDiscount();
    }

    public String getDiscountCause() {
        return discount.getDiscountCause();
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (discount.getDiscount() == null ? 0 : discount.getDiscount().hashCode());
        result = prime * result + (getProductName() == null ? 0 : getProductName().hashCode());
        result = prime * result + (getProductPrice() == null ? 0 : getProductPrice().hashCode());
        result = prime * result + (getProductId() == null ? 0 : getProductId().hashCode());
        result = prime * result + (getProductType() == null ? 0 : getProductType().hashCode());
        result = prime * result + quantity;
        result = prime * result + (totalCost.getAmount() == null ? 0 : totalCost.getAmount().hashCode());
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
        if (discount == null) {
            if (other.discount != null) {
                return false;
            }
        } else if (!discount.equals(other.discount)) {
            return false;
        }
        if (getProductName() == null) {
            if (other.getProductName() != null) {
                return false;
            }
        } else if (!getProductName().equals(other.getProductName())) {
            return false;
        }
        if (getProductPrice() == null) {
            if (other.getProductPrice() != null) {
                return false;
            }
        } else if (!getProductPrice().equals(other.getProductPrice())) {
            return false;
        }
        if (getProductId() == null) {
            if (other.getProductId() != null) {
                return false;
            }
        } else if (!getProductId().equals(other.getProductId())) {
            return false;
        }
        if (getProductType() != other.getProductType()) {
            return false;
        }
        if (quantity != other.quantity) {
            return false;
        }
        if (totalCost == null) {
            if (other.totalCost != null) {
                return false;
            }
        } else if (!totalCost.equals(other.totalCost)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param item
     * @param delta
     *            acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (getProductName() == null) {
            if (other.getProductName() != null) {
                return false;
            }
        } else if (!getProductName().equals(other.getProductName())) {
            return false;
        }
        if (getProductPrice() == null) {
            if (other.getProductPrice() != null) {
                return false;
            }
        } else if (!getProductPrice().equals(other.getProductPrice())) {
            return false;
        }
        if (getProductId() == null) {
            if (other.getProductId() != null) {
                return false;
            }
        } else if (!getProductId().equals(other.getProductId())) {
            return false;
        }
        if (getProductType() != other.getProductType()) {
            return false;
        }

        if (quantity != other.quantity) {
            return false;
        }

        Money max;
        Money min;
        if (totalCost.getAmount().compareTo(other.totalCost.getAmount()) > 0) {
            max = totalCost;
            min = other.totalCost;
        } else {
            max = other.totalCost;
            min = totalCost;
        }

        BigDecimal difference = max.getAmount().subtract(min.getAmount());
        BigDecimal acceptableDelta = max.getAmount().multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
