package hima.aarieats.singletons;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import hima.aarieats.Vendors;
import hima.aarieats.http.models.PlaceOrderRequest;
import hima.aarieats.models.CartProducts;
import hima.aarieats.models.Product;

public class Cart {

    private static Cart mCart;

    private Vendors mCurrentVendor;

    private HashMap<String, CartProducts> orderMap = new HashMap<>();

    private Cart()
    {
        if (mCart != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static Cart getInstance() {
        if (mCart == null) { //if there is no instance available... create new one
            synchronized (Cart.class) {
                if (mCart == null) mCart = new Cart();
            }
        }
        return mCart;
    }

    public Collection<CartProducts> getProducts() {
        if(orderMap == null) {
            return null;
        }

        return orderMap.values();
    }

    public void addToCart(String productId,CartProducts product,Vendors vendor) {
        if(orderMap!=null) {
            if(mCurrentVendor==null) {
                setCurrentVendor(vendor);
                Log.i("CurrentVendorAddToCart",vendor.getEmail());
            }
            if(!checkIfSameVendor(vendor)) {
                orderMap.clear();
                setCurrentVendor(vendor);
                Log.i("CurrentVendorAddToCart",vendor.getEmail());
            }
            orderMap.put(productId,product);
        }
    }

    private boolean checkIfSameVendor(Vendors vendors) {
        if(mCurrentVendor.getEmail().equals(vendors.getEmail())) {
            return true;
        } else {
            return false;
        }
    }

    public void setCurrentVendor(Vendors vendor) {
        this.mCurrentVendor = vendor;
    }

    public Vendors getCurrentVendor() {
        return mCurrentVendor;
    }

    public PlaceOrderRequest getOrderDetails(String type) {
        List<CartProducts> cartProductsList = new ArrayList<>(getProducts());
        User user = User.getInstance();
        PlaceOrderRequest placeOrderRequest = new PlaceOrderRequest(user.getUserEmail(),mCurrentVendor.getEmail(),user.getLatLng(),type);
        for(CartProducts products : cartProductsList) {
            placeOrderRequest.addProductList(products.getProduct().getProductId(),products.getUnit(),products.getTotal());
        }
        return placeOrderRequest;
    }





}
