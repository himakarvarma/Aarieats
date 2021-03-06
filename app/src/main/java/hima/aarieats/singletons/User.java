package hima.aarieats.singletons;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

import hima.aarieats.Vendors;
import hima.aarieats.models.CartProducts;

public class User {

    private static User mUser;

    private String mUserEmail;

    private String mLatLng;




    private User()
    {
        if (mUser != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static User getInstance() {
        if (mUser == null) { //if there is no instance available... create new one
            synchronized (Cart.class) {
                if (mUser == null) mUser = new User();
            }
        }
        return mUser;
    }

    public void setUserEmail(String email) {
        this.mUserEmail = email;
    }

    public String getUserEmail() {
        return mUserEmail;
    }

    public void setLatLng(String latLng) {
        this.mLatLng = latLng;
    }

    public String getLatLng() {
        return this.mLatLng;
    }
}
