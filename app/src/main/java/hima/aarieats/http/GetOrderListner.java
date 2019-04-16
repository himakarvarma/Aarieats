package hima.aarieats.http;

import java.util.List;

import hima.aarieats.models.Order;

public interface GetOrderListner {

    enum ResponseStatus {
        SUCCESS,
        INVALID_PARAMETERS,
        FAILURE
    }

    void onSuccess(ResponseStatus status, List<Order> info);
    void onFailure(ResponseStatus status, String info);
}
