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

    private Money money;

    private Date productSnapshotDate;

    private BigDecimal totalCost;


    // discount
    private Discount discount;

    public OfferItem(Product product, Money money, Date productSnapshotDate) {
        this(product, money, productSnapshotDate, null);
    }

    public OfferItem(Product product, Money money, Date productSnapshotDate, Discount discount) {
        this.product = product;
        this.productSnapshotDate = productSnapshotDate;

        this.discount = discount;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.subtract(discount.getDiscount());
        }

        this.totalCost = money.getProductPrice().multiply(new BigDecimal(getQuantity())).subtract(discountValue);
    }

    public String getProductId() {
        return product.getId();
    }

    public BigDecimal getProductPrice() { return money.getProductPrice(); }

    public String getProductName() { return product.getName(); }

    public Date getProductSnapshotDate() {
        return productSnapshotDate;
    }

    public String getProductType() {
        return product.getType();
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public String getTotalCostCurrency() { return money.getCurrency(); }

    public BigDecimal getDiscount() {
        return discount.getDiscount();
    }

    public String getDiscountCause() {
        return discount.getDiscountCause();
    }

    public int getQuantity() { return money.getQuantity(); }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (discount == null ? 0 : discount.hashCode());
        result = prime * result + (product.getName() == null ? 0 : product.getName().hashCode());
        result = prime * result + (money.getProductPrice() == null ? 0 : money.getProductPrice().hashCode());
        result = prime * result + (product.getId() == null ? 0 : product.getId().hashCode());
        result = prime * result + (product.getType() == null ? 0 : product.getType().hashCode());
        result = prime * result + money.getQuantity();
        result = prime * result + (totalCost == null ? 0 : totalCost.hashCode());
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
        if (product.getName() == null) {
            if (other.getProductName() != null) {
                return false;
            }
        } else if (!product.getName().equals(other.getProductName())) {
            return false;
        }
        if (money.getProductPrice() == null) {
            if (other.getProductPrice() != null) {
                return false;
            }
        } else if (!getProductPrice().equals(other.getProductPrice())) {
            return false;
        }
        if (product.getId() == null) {
            if (other.getProductId() != null) {
                return false;
            }
        } else if (!product.getId().equals(other.getProductId())) {
            return false;
        }
        if (product.getType() != other.getProductType()) {
            return false;
        }
        if (money.getQuantity() != other.getQuantity()) {
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
        if (product.getName() == null) {
            if (other.getProductName() != null) {
                return false;
            }
        } else if (!product.getName().equals(other.getProductName())) {
            return false;
        }
        if (getProductPrice() == null) {
            if (other.getProductPrice() != null) {
                return false;
            }
        } else if (!getProductPrice().equals(other.getProductPrice())) {
            return false;
        }
        if (product.getId() == null) {
            if (other.getProductId() != null) {
                return false;
            }
        } else if (!product.getId().equals(other.getProductId())) {
            return false;
        }
        if (product.getType() != other.getProductType()) {
            return false;
        }

        if (getQuantity() != other.getQuantity()) {
            return false;
        }

        BigDecimal max;
        BigDecimal min;
        if (totalCost.compareTo(other.totalCost) > 0) {
            max = totalCost;
            min = other.totalCost;
        } else {
            max = other.totalCost;
            min = totalCost;
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
