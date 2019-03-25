package hima.aarieats.http;

public interface PlaceOrderListner {
    enum ResponseStatus {
        SUCCESS,
        FAILURE
    }

    void onSuccess(ResponseStatus status, String orderId);
    void onFailure(ResponseStatus status,String info);
}
