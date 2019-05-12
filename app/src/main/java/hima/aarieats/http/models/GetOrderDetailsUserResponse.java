package hima.aarieats.http.models;

import java.util.List;

import hima.aarieats.models.OrderDetails;

public class GetOrderDetailsUserResponse {

    String error;

    List<OrderDetails> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<OrderDetails> getData() {
        return data;
    }

    public void setData(List<OrderDetails> data) {
        this.data = data;
    }
}
