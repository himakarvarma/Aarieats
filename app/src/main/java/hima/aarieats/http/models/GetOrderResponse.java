package hima.aarieats.http.models;

import java.util.List;

import hima.aarieats.models.Order;

public class GetOrderResponse {

    String error;

    List<Order> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Order> getData() {
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }
}
