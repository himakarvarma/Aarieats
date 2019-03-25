package hima.aarieats.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import hima.aarieats.CartListner;
import hima.aarieats.models.CartProducts;
import hima.aarieats.models.Product;
import hima.aarieats.singletons.Cart;

public class CartViewModel extends ViewModel implements CartListner {

    private MutableLiveData<List<CartProducts>> mCartProduct;

    public LiveData<List<CartProducts>> getProducts() {
        if (mCartProduct == null) {
            mCartProduct = new MutableLiveData<>();
            loadProducts();
        }
        return mCartProduct;
    }

    private void loadProducts() {
        List<CartProducts> cartProducts = new ArrayList<>(Cart.getInstance().getProducts());
        if(cartProducts!=null) {
            mCartProduct.postValue(cartProducts);
        }
    }

    @Override
    public void onProductUpdated() {

    }

    @Override
    public void onProductDeleted() {
        loadProducts();
    }
}
