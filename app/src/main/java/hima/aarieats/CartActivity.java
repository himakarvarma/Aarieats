package hima.aarieats;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.ArrayList;
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

public class CartActivity extends AppCompatActivity implements PaymentResultListener {

    private ListView mCartProductList;

    private ArrayAdapter arrayAdapter;

    private CartViewModel mCartViewModel;

    private Button mPlaceOrderBtn;

    private Spinner mPaymentOption;

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
        mPaymentOption = findViewById(R.id.paymentOption);
        List<String> paymentOption = new ArrayList<>();
        paymentOption.add("cash on delivery");
        paymentOption.add("online payment");
        ArrayAdapter<String> paymentAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,paymentOption);
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPaymentOption.setAdapter(paymentAdapter);
        mPlaceOrderBtn = findViewById(R.id.placeOrderBtn);

        mPlaceOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String option = mPaymentOption.getSelectedItem().toString();
//                Toast.makeText(CartActivity.this,option,Toast.LENGTH_LONG).show();
//                //placeOrder();
                if(option.equals("cash on delivery")) {
                    Toast.makeText(CartActivity.this,"cash",Toast.LENGTH_LONG).show();
                    placeOrder("cash");
                } else {
                    Toast.makeText(CartActivity.this,"online",Toast.LENGTH_LONG).show();
                    startPayment();
                }
            }
        });
    }

    private void placeOrder(String type) {
        PlaceOrderRequest placeOrderRequest = Cart.getInstance().getOrderDetails(type);
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

    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Aarieats");
            options.put("description", "Order Payment");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", "100");

            JSONObject preFill = new JSONObject();
            preFill.put("email", "himakar@aarieats.com");
            preFill.put("contact", "9876543210");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        try {
            Toast.makeText(this, "Payment Successful: " + s, Toast.LENGTH_SHORT).show();
            placeOrder("online");
        } catch (Exception e) {
            Log.e("CartActivity", "Exception in onPaymentSuccess", e);
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(this, "Payment failed: " + i + " " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("CartActivity", "Exception in onPaymentError", e);
        }
    }
}
