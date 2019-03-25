package hima.aarieats.http.models;

public class PlaceOrderResponse {
    String error;

    Data data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private class Data {
        String OrderId;
        String Status;
        public void setOrderId(String orderId) {
            this.OrderId = orderId;
        }
        public String getOrderId() {
            return OrderId;
        }
        public void setStatus(String status) {
            this.Status = status;
        }
        public String getStatus() {
            return Status;
        }
    }
}
