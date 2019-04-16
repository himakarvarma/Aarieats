package hima.aarieats.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import hima.aarieats.models.Order;

public class ViewOrdersViewModel extends ViewModel {

    private MutableLiveData<List<Order>> mOrders;

    public LiveData<List<Order>> getOrders() {
        if (mOrders == null) {
            mOrders = new MutableLiveData<List<Order>>();
        }
        return mOrders;
    }

    public void setOrders(List<Order> orders) {
        this.mOrders.postValue(orders);
    }
}
