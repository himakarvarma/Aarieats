package hima.aarieats.http.models;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hima.aarieats.models.Product;

public class PlaceOrderRequest {

    private String userEmail;
    private String vendorEmail;
    private List<PlaceOrderData> products;
    private String latLng;
    private String paymentType;


    public PlaceOrderRequest(String userEmail, String vendorEmail,String latLng,String paymentType) {
        this.userEmail = userEmail;
        this.vendorEmail = vendorEmail;
        this.latLng = latLng;
        this.paymentType = paymentType;
    }

    public void addProductList(String productId,int units,int total) {
        PlaceOrderData placeOrderData = new PlaceOrderData(productId,units,total);
        if(products == null) {
            products = new ArrayList<>();
            products.add(placeOrderData);
        } else {
            products.add(placeOrderData);
        }
    }

    public class PlaceOrderData {
        String ProductId;
        int Units;
        int Total;

        PlaceOrderData(String productId,int units,int total) {
            ProductId = productId;
            Units = units;
            Total = total;
        }
    }
}
