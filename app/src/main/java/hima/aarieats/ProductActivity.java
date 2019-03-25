package hima.aarieats;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import hima.aarieats.http.HttpListner;
import hima.aarieats.http.ProductListner;
import hima.aarieats.http.api.ApiService;
import hima.aarieats.models.CartProducts;
import hima.aarieats.models.Product;
import hima.aarieats.singletons.Cart;
import hima.aarieats.singletons.VendorData;
import hima.aarieats.viewmodels.OrderProductViewModel;

public class ProductActivity extends AppCompatActivity {

    private ListView mProductListView;
    private ArrayAdapter<String> mAdapter;
    private Bundle mExtras;
    private String mVendorEmail;
    private OrderProductViewModel mOrderProductViewModel;
    private Button mCartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mProductListView = findViewById(R.id.orderProductsList);
        mExtras = getIntent().getExtras();
        if(mExtras!=null) {
            mVendorEmail = mExtras.getString("vendorEmail");
        }
        mOrderProductViewModel = ViewModelProviders.of(this).get(OrderProductViewModel.class);
        mOrderProductViewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                mAdapter = new ArrayAdapter<>(ProductActivity.this,
                        android.R.layout.simple_list_item_1, android.R.id.text1, getProductList(products));
                mProductListView.setAdapter(mAdapter);
                mProductListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Product product = mOrderProductViewModel.getProductFromPosition(position);
                        if(product!=null) {
                            showAddToCartDialog(product);
                        } else {
                            Toast.makeText(ProductActivity.this,"Internal Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        mCartBtn = findViewById(R.id.cartBtn);
        mCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductActivity.this,CartActivity.class));
            }
        });
    }

    private void showAddToCartDialog(final Product product){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.addtocartdialog, null);
        dialogBuilder.setView(dialogView);
        final TextView productName = dialogView.findViewById(R.id.productName);
        final TextView productType = dialogView.findViewById(R.id.typeValue);
        final EditText noOfProducts = dialogView.findViewById(R.id.noOfProducts);
        productType.setText(product.getProductType());
        productName.setText(product.getProductName());
        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int value = Integer.parseInt(noOfProducts.getText().toString());
                if(value>5) {
                    Toast.makeText(ProductActivity.this,"Cannot select more than 5",Toast.LENGTH_SHORT).show();
                } else {
                    Vendors vendors = VendorData.getInstance().getVendor(mVendorEmail);

                    addToCart(product,value,vendors);
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();

    }

    private void addToCart(Product product,int unit,Vendors vendors) {
        CartProducts cartProducts = new CartProducts(product,unit);
        Cart.getInstance().addToCart(product.getProductId(),cartProducts,vendors);
        Toast.makeText(ProductActivity.this,"Added to cart",Toast.LENGTH_SHORT).show();
    }

    private List<String> getProductList(List<Product> productsList) {
        List<String> productList = new ArrayList<>();
        for(Product products : productsList) {
            productList.add(products.getProductName());
        }
        return productList;
    }



    @Override
    protected void onStart() {
        super.onStart();
        getProductFromServer();
    }

    private void getProductFromServer() {
        if(mVendorEmail!=null) {
            Vendors vendor = VendorData.getInstance().getVendor(mVendorEmail);
            if(vendor!=null) {
                ApiService.getInstance().getProducts(vendor.getEmail(), new ProductListner() {
                    @Override
                    public void onSuccess(HttpListner.ResponseStatus status, List<Product> info) {
                        mOrderProductViewModel.setProduct(info);
                    }

                    @Override
                    public void onFailure(HttpListner.ResponseStatus status, String info) {

                    }
                });
            }
        }

    }
}
