package hima.aarieats.http;

import java.util.List;

import hima.aarieats.models.Product;

public interface ProductListner {
    enum ResponseStatus {
        SUCCESS,
        FAILURE
    }
    void onSuccess(HttpListner.ResponseStatus status, List<Product> info);
    void onFailure(HttpListner.ResponseStatus status, String info);
}
