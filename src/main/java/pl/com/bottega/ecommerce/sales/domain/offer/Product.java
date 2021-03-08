package pl.com.bottega.ecommerce.sales.domain.offer;

public class Product {

    private String name;
    private String id;
    private String type;

    public Product(String id, String name, String type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
