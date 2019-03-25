package hima.aarieats;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import hima.aarieats.adapters.CartAdapter;
import hima.aarieats.http.PlaceOrderListner;
import hima.aarieats.http.api.AariEatsApi;
import hima.aarieats.http.api.ApiService;
import hima.aarieats.http.models.PlaceOrderRequest;
import hima.aarieats.models.CartProducts;
import hima.aarieats.singletons.Cart;
import hima.aarieats.viewmodels.CartViewModel;
import hima.aarieats.viewmodels.OrderProductViewModel;

public class CartActivity extends AppCompatActivity {

    private ListView mCartProductList;

    private ArrayAdapter arrayAdapter;

    private CartViewModel mCartViewModel;

    private Button mPlaceOrderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mCartProductList = findViewById(R.id.cartProducts);
        mCartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        mCartViewModel.getProducts().observe(this, new Observer<List<CartProducts>>() {
            @Override
            public void onChanged(@Nullable List<CartProducts> cartProducts) {
                arrayAdapter = new CartAdapter(cartProducts,CartActivity.this);
                mCartProductList.setAdapter(arrayAdapter);
            }
        });
        mPlaceOrderBtn = findViewById(R.id.placeOrderBtn);

        mPlaceOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });
    }

    private void placeOrder() {
        PlaceOrderRequest placeOrderRequest = Cart.getInstance().getOrderDetails();
        ApiService.getInstance().placeOrder(placeOrderRequest, new PlaceOrderListner() {
            @Override
            public void onSuccess(ResponseStatus status, String orderId) {
                Toast.makeText(CartActivity.this,"Order Placed Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(ResponseStatus status, String info) {
                Toast.makeText(CartActivity.this,"Order Placed Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
