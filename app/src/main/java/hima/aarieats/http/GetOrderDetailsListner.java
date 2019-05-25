package hima.aarieats.http;

import java.util.List;

import hima.aarieats.models.OrderDetails;

public interface GetOrderDetailsListner {

    enum ResponseStatus {
        SUCCESS,
        INVALID_PARAMETERS,
        FAILURE
    }

    void onSuccess(ResponseStatus status, List<OrderDetails> info);
    void onFailure(ResponseStatus status, String info);
}
