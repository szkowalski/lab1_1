package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;

public class Product {

    private String id;
    private String name;
    private String type;

    public String getProductId() {
        return id;
    }

    public String getProductName() {
        return name;
    }

    public String getProductType() {
        return type;
    }

    public Product(String id, String name, String type){
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
