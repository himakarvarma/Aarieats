package hima.aarieats.http.models;

public class LoginResponse {

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
        String status;

        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }
    }
}
