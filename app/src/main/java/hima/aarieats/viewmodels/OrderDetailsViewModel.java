package hima.aarieats.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import hima.aarieats.models.OrderDetails;

public class OrderDetailsViewModel extends ViewModel {

    private MutableLiveData<List<OrderDetails>> mOrderDetails;

    public LiveData<List<OrderDetails>> getOrderDetails() {
        if (mOrderDetails == null) {
            mOrderDetails = new MutableLiveData<List<OrderDetails>>();
        }
        return mOrderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orders) {
        this.mOrderDetails.postValue(orders);
    }
}
