package pl.com.bottega.ecommerce.sales.domain.offer;

import java.util.Date;

public class Product {
    private String productId;
    private String productName;
    private String productType;
    private Money price;
    private Date productSnapshotDate;

    public Product(String productId, String productName, Money productPrice, Date productSnapshotDate) {
        this.productId = productId;
        this.productName = productName;
        this.price = productPrice;
        this.productSnapshotDate = productSnapshotDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Money getPrice() {
        return price;
    }

    public void setProductPrice(Money productPrice) {
        this.price = productPrice;
    }

    public Date getProductSnapshotDate() {
        return productSnapshotDate;
    }

    public void setProductSnapshotDate(Date productSnapshotDate) {
        this.productSnapshotDate = productSnapshotDate;
    }


}
