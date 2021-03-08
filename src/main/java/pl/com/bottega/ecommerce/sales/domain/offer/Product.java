package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private final String id;

    private final BigDecimal price;

    private final String name;

    private final Date SnapshotDate;

    private final String type;

    public Product(String productId, BigDecimal productPrice, String productName, Date productSnapshotDate, String productType) {
        this.id = productId;
        this.price = productPrice;
        this.name = productName;
        this.SnapshotDate = productSnapshotDate;
        this.type = productType;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Date getProductSnapshotDate() {
        return SnapshotDate;
    }

    public String getProductType() {
        return type;
    }


}
