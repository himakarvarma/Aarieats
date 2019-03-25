package hima.aarieats.http.models;

import java.util.List;

import hima.aarieats.Vendors;
import hima.aarieats.singletons.VendorData;

public class GetVendorsResponse {
    String error;

    List<Vendors> data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Vendors> getData() {
        return data;
    }

    public void setData(List<Vendors> data) {
        this.data = data;
    }


}
