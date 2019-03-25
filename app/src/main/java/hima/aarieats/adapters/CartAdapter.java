package hima.aarieats.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import hima.aarieats.R;
import hima.aarieats.models.CartProducts;

public class CartAdapter extends ArrayAdapter<CartProducts> {

    private List<CartProducts> mData;

    private Context mContext;

    public CartAdapter(List<CartProducts> cartProducts,Context context) {
        super(context, R.layout.cart_row_item,cartProducts);
        this.mData = cartProducts;
        this.mContext = context;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.cart_row_item, parent, false);
        }

        TextView productName = convertView.findViewById(R.id.productNameValue);
        TextView unit = convertView.findViewById(R.id.productUnitValue);
        TextView total = convertView.findViewById(R.id.productTotalValue);
        CartProducts cartProduct = mData.get(position);
        productName.setText(cartProduct.getProduct().getProductName());
        unit.setText(cartProduct.getUnit()+"");
        total.setText(cartProduct.getTotal()+"");

        return convertView;
    }
}
