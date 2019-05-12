package hima.aarieats.http.models;

import com.google.gson.annotations.SerializedName;

public class GetOrderDetailsRequest {

    @SerializedName("orderId")
    private
    String orderId;

    @SerializedName("email")
    private
    String email;

    public GetOrderDetailsRequest(String orderId, String email) {
        this.orderId = orderId;
        this.email = email;
    }

    public String getOrderId() {
        return orderId;
    }


    public String getEmail() {
        return email;
    }
}
