package hima.aarieats.http;

import java.util.List;

import hima.aarieats.Vendors;

public interface GetVendorListner {

    enum ResponseStatus {
        SUCCESS,
        FAILURE
    }

    void onSuccess(ResponseStatus status, List<Vendors> info);
    void onFailure(ResponseStatus status,String info);
}
