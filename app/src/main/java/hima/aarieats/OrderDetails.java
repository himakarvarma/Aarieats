package hima.aarieats;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hima.aarieats.http.GetOrderDetailsListner;
import hima.aarieats.http.api.ApiService;
import hima.aarieats.singletons.User;
import hima.aarieats.viewmodels.OrderDetailsViewModel;

public class OrderDetails extends AppCompatActivity {

    private ListView mOrderDetailsListView;

    private OrderDetailsViewModel mOrderDetailsViewModel;

    private ArrayAdapter<String> mAdapter;

    private TextView mTotalText;

    private String mOrderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        mTotalText = findViewById(R.id.priceText);
        Bundle mExtras = getIntent().getExtras();
        if(mExtras !=null) {
            mOrderId = mExtras.getString("orderId");
        }
        mOrderDetailsListView = findViewById(R.id.orderDetailsListView);
        mOrderDetailsViewModel = ViewModelProviders.of(this).get(OrderDetailsViewModel.class);
        mOrderDetailsViewModel.getOrderDetails().observe(this, new Observer<List<hima.aarieats.models.OrderDetails>>() {
            @Override
            public void onChanged(@Nullable List<hima.aarieats.models.OrderDetails> orderDetails) {
                mAdapter = new ArrayAdapter<String>(OrderDetails.this,android.R.layout.simple_list_item_1, android.R.id.text1,getOrderDetailsList(orderDetails));
                mOrderDetailsListView.setAdapter(mAdapter);
                displayTotal(orderDetails);
            }
        });
    }

    private List<String> getOrderDetailsList(List<hima.aarieats.models.OrderDetails> orderDetails) {
        List<String> orderDetailList = new ArrayList<>();
        for(hima.aarieats.models.OrderDetails orderDetail : orderDetails) {
            orderDetailList.add(orderDetail.getProductName());
        }
        return orderDetailList;
    }

    private void displayTotal(List<hima.aarieats.models.OrderDetails> orderDetails) {
        int total = 0;
        for(hima.aarieats.models.OrderDetails orderDetail : orderDetails) {
            total = total + orderDetail.getProductPrice();
        }
        mTotalText.setText("Total :"+total);
    }


    @Override
    protected void onStart() {
        super.onStart();
        ApiService apiService = ApiService.getInstance();
        apiService.getOrderDetails(mOrderId, User.getInstance().getUserEmail(), new GetOrderDetailsListner() {
            @Override
            public void onSuccess(ResponseStatus status, List<hima.aarieats.models.OrderDetails> info) {
                mOrderDetailsViewModel.setOrderDetails(info);
            }

            @Override
            public void onFailure(ResponseStatus status, String info) {
                Toast.makeText(OrderDetails.this,info,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
