package hima.aarieats.singletons;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import hima.aarieats.Vendors;

public class VendorData {
    private static final VendorData ourInstance = new VendorData();

    public static VendorData getInstance() {
        return ourInstance;
    }

    public HashMap<String, Vendors> vendorsHashMap;

    private VendorData() {

    }

    public void putVendors(String email,Vendors vendor) {
        if(vendorsHashMap == null) {
            vendorsHashMap = new HashMap<>();
        }
        vendorsHashMap.put(email,vendor);
    }

    public Vendors getVendor(String email) {
        if(vendorsHashMap!=null) {
            return vendorsHashMap.get(email);
        }
        return null;
    }

    public Collection<Vendors> getVendorList() {
        if(vendorsHashMap!=null) {
            return vendorsHashMap.values();
        }
        return null;
    }
//    public Vendors getVendorFromPosition() {
//
//    }


}
