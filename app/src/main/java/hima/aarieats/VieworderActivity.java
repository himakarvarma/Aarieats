package hima.aarieats;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import hima.aarieats.adapters.OrderListAdapter;
import hima.aarieats.http.GetOrderListner;
import hima.aarieats.http.api.ApiService;
import hima.aarieats.models.Order;
import hima.aarieats.viewmodels.ViewOrdersViewModel;

public class VieworderActivity extends AppCompatActivity {
    private ListView mOrderListView;
    private OrderListAdapter mOrderListAdapter;
    private ViewOrdersViewModel mViewOrdersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieworder);
        mOrderListView = findViewById(R.id.viewOrderList);
        mViewOrdersViewModel = ViewModelProviders.of(this).get(ViewOrdersViewModel.class);
        mViewOrdersViewModel.getOrders().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(@Nullable List<Order> orders) {
                mOrderListAdapter = new OrderListAdapter(orders,VieworderActivity.this);
                mOrderListView.setAdapter(mOrderListAdapter);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getOrderFromServer();
    }

    private void getOrderFromServer() {
        ApiService.getInstance().getOrders(new GetOrderListner() {
            @Override
            public void onSuccess(ResponseStatus status, List<Order> info) {
                if(info!=null) {
                    mViewOrdersViewModel.setOrders(info);
                } else {
                    Toast.makeText(VieworderActivity.this,"Internal Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(ResponseStatus status, String info) {
                Toast.makeText(VieworderActivity.this,info,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
