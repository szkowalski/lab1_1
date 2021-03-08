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
    private final String productId;

    private final BigDecimal productPrice;

    private final String productName;

    private final Date productSnapshotDate;

    private final String productType;

    private final int quantity;

    private final BigDecimal totalCost;

    private String currency;

    // discount
    private final String discountCause;

    private final BigDecimal discount;

    public OfferItem(String productId, BigDecimal productPrice, String productName, Date productSnapshotDate,
            String productType, int quantity) {
        this(productId, productPrice, productName, productSnapshotDate, productType, quantity, null, null);
    }

    public OfferItem(String productId, BigDecimal productPrice, String productName, Date productSnapshotDate,
            String productType, int quantity, BigDecimal discount, String discountCause) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productSnapshotDate = productSnapshotDate;
        this.productType = productType;
        this.quantity = quantity;
        this.discount = discount;
        this.discountCause = discountCause;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.subtract(discount);
        }

        this.totalCost = productPrice.multiply(new BigDecimal(quantity)).subtract(discountValue);
    }

    public String getProductId() {
        return productId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public Date getProductSnapshotDate() {
        return productSnapshotDate;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public String getTotalCostCurrency() {
        return currency;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        final int prime = PRIME;
        int result = RESULT;
        result = prime * result + (discount == null ? 0 : discount.hashCode());
        result = prime * result + (productName == null ? 0 : productName.hashCode());
        result = prime * result + (productPrice == null ? 0 : productPrice.hashCode());
        result = prime * result + (productId == null ? 0 : productId.hashCode());
        result = prime * result + (productType == null ? 0 : productType.hashCode());
        result = prime * result + quantity;
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
        if (checIsNull(other)) return false;

        if (totalCost == null) {
            return other.totalCost == null;
        } else return totalCost.equals(other.totalCost);
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

    private boolean checIsNull(OfferItem other) {
        if (productName == null) {
            if (other.productName != null) {
                return true;
            }
        } else if (!productName.equals(other.productName)) {
            return true;
        }
        if (productPrice == null) {
            if (other.productPrice != null) {
                return true;
            }
        } else if (!productPrice.equals(other.productPrice)) {
            return true;
        }
        if (productId == null) {
            if (other.productId != null) {
                return true;
            }
        } else if (!productId.equals(other.productId)) {
            return true;
        }
        if (!productType.equals(other.productType)) {
            return true;
        }

        return quantity != other.quantity;
    }

}
