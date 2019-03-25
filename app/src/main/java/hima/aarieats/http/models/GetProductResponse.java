package hima.aarieats.http.models;

import java.util.List;

import hima.aarieats.models.Product;

public class GetProductResponse {

    private String error;

    private List<Product> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}
