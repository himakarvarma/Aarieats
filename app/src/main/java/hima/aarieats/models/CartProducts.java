package hima.aarieats.models;

public class CartProducts {

    private Product mProduct;

    private int mUnit;

    private int mTotal;

    public CartProducts(Product product,int unit) {
        this.mProduct = product;
        this.mUnit = unit;
        this.mTotal = Integer.parseInt(mProduct.getProductPrice())*unit;
    }

    public Product getProduct() {
        return mProduct;
    }

    public int getUnit() {
        return mUnit;
    }

    public int getTotal() {
        return mTotal;
    }
}
