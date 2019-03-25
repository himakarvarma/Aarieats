package hima.aarieats.models;

public class Product {
    private String ProductId;
    private String ProductName;
    private String ProductType;
    private String ProductUnit;
    private String ProductPrice;

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public String getProductUnit() {
        return ProductUnit;
    }

    public void setProductUnit(String productUnit) {
        ProductUnit = productUnit;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }
}
