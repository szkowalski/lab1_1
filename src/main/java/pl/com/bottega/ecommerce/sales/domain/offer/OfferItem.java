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
    private Product product;

    private Money money;

    private int quantity;

    private Discount discount;

    private BigDecimal totalCost;

    // discount


    public OfferItem(Product product, int quantity, Money money) {
        this(product, quantity, money, null);
    }

    public OfferItem(Product product, int quantity, Money money, Discount discount) {
        this.product = product;
        this.quantity = quantity;
        this.discount = discount;
        this.money = money;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.subtract(discount.getValue());
        }

        this.totalCost = product.getMoney().getAmount().multiply(new BigDecimal(quantity)).subtract(discountValue);
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public String getTotalCostCurrency() {
        return money.getCurrency();
    }

    public Discount getDiscount(){
        return discount;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (discount == null ? 0 : discount.hashCode());
        result = prime * result + (product == null ?  0 : product.hashCode());
        result = prime * result + quantity;
        result = prime * result + (money == null ? 0 : money.hashCode());
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
            if (other.product.getName() != null) {
                return false;
            }
        } else if (!product.getName().equals(other.product.getName())) {
            return false;
        }
        if (product.getMoney().getAmount() == null) {
            if (other.product.getMoney().getAmount() != null) {
                return false;
            }
        } else if (!product.getMoney().getAmount().equals(other.product.getMoney().getAmount())) {
            return false;
        }
        if (product.getId() == null) {
            if (other.product.getId() != null) {
                return false;
            }
        } else if (!product.getId().equals(other.product.getId())) {
            return false;
        }
        if (product.getType() != other.product.getType()) {
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
     * @param other
     * @param delta
     *            acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (product.getName() == null) {
            if (other.product.getName() != null) {
                return false;
            }
        } else if (!product.getName().equals(other.product.getName())) {
            return false;
        }
        if (product.getMoney().getAmount() == null) {
            if (other.product.getMoney().getAmount() != null) {
                return false;
            }
        } else if (!product.getMoney().getAmount().equals(other.product.getMoney().getAmount())) {
            return false;
        }
        if (product.getId() == null) {
            if (other.product.getId() != null) {
                return false;
            }
        } else if (!product.getId().equals(other.product.getId())) {
            return false;
        }
        if (product.getType() != other.product.getType()) {
            return false;
        }

        if (quantity != other.quantity) {
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

class Product{
    private String id;

    private Money money;

    private String name;

    private Date snapshotDate;

    private String type;

    Product(String id, Money money, String name, Date snapshotDate, String type) {
        this.id = id;
        this.money = money;
        this.name = name;
        this.snapshotDate = snapshotDate;
        this.type = type;
    }

    public String getId(){
        return id;
    }

    public Money getMoney() {
        return money;
    }

    public String getName(){
        return name;
    }

    public Date getSnapshotDate() {
        return snapshotDate;
    }

    public String getType() {
        return type;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (id == null ? 0 : id.hashCode());
        result = prime * result + (money == null ? 0 : money.hashCode());
        result = prime * result + (name == null ? 0 : name.hashCode());
        result = prime * result + (snapshotDate == null ? 0 : snapshotDate.hashCode());
        result = prime * result + (type == null ? 0 : type.hashCode());
        return result;
    }
}

class Discount{
    private String cause;

    private BigDecimal value;

    Discount(String cause, BigDecimal value) {
        this.cause = cause;
        this.value = value;
    }

    public String getCause() {
        return cause;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + (cause == null ? 0 : cause.hashCode());
        result = prime * result + (value == null ? 0 : value.hashCode());
        return result;
    }
}

class Money{
    private String currency;

    private BigDecimal amount;

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount(){
        return amount;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = result * result + (currency == null ? 0 : currency.hashCode());
        result = result * result + (amount == null ? 0 : amount.hashCode());
        return  result;
    }


}