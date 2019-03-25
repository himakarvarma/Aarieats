package hima.aarieats.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import hima.aarieats.models.Product;

public class OrderProductViewModel extends ViewModel {

    private MutableLiveData<List<Product>> mProduct;

    public LiveData<List<Product>> getProducts() {
        if (mProduct == null) {
            mProduct = new MutableLiveData<List<Product>>();
        }
        return mProduct;
    }

    public void setProduct(List<Product> products) {
        mProduct.postValue(products);
    }

    public Product getProductFromPosition(int position) {
        if(mProduct.getValue() == null) {
            return null;
        }
        return mProduct.getValue().get(position);
    }

}
