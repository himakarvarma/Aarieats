package hima.aarieats.http.api;

import android.util.Log;
import android.widget.Toast;

import hima.aarieats.http.GetVendorListner;
import hima.aarieats.http.HttpListner;
import hima.aarieats.http.PlaceOrderListner;
import hima.aarieats.http.ProductListner;
import hima.aarieats.http.ServiceGenerator;
import hima.aarieats.http.models.GetProductRequest;
import hima.aarieats.http.models.GetProductResponse;
import hima.aarieats.http.models.GetVendorsResponse;
import hima.aarieats.http.models.LoginRequest;
import hima.aarieats.http.models.LoginResponse;
import hima.aarieats.http.models.PlaceOrderRequest;
import hima.aarieats.http.models.PlaceOrderResponse;
import hima.aarieats.http.models.RegisterRequest;
import hima.aarieats.http.models.RegisterResponse;
import hima.aarieats.singletons.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiService {
    private static final ApiService ourInstance = new ApiService();

    public static ApiService getInstance() {
        return ourInstance;
    }

    private ApiService() {
    }

    public void login(final String username, String password, final HttpListner httpListner) {
        LoginRequest loginRequest = new LoginRequest(username,password);
        AariEatsApi aariEatsApi = ServiceGenerator.createRetrofit(AariEatsApi.class);
        Call<LoginResponse> loginCall = aariEatsApi.login(loginRequest);
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.i("apiservice",response.code()+"");
                if(response.code() == 200) {
                    if(response.code() == 200) {
                        User.getInstance().setUserEmail(username);
                        httpListner.onSuccess(HttpListner.ResponseStatus.LOGIN_SUCCESS, "sussess");
                    } else if(response.code() == 401) {
                        httpListner.onSuccess(HttpListner.ResponseStatus.LOGIN_AUTHENTICATION_FAILURE, "Authentication failure");
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                httpListner.onFailure(HttpListner.ResponseStatus.LOGIN_NETWORK_FAILURE,t.getMessage());
            }
        });
    }

    public void register(String username, String password, String email,String phno,final HttpListner httpListner) {
        RegisterRequest registerRequest = new RegisterRequest(username,password,email,phno);
        AariEatsApi aariEatsApi = ServiceGenerator.createRetrofit(AariEatsApi.class);
        Call<RegisterResponse> registerCall = aariEatsApi.register(registerRequest);
        registerCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Log.i("apiservice",response.code()+"");
                if(response.code() == 200) {
                    if(response.code() == 200) {
                        httpListner.onSuccess(HttpListner.ResponseStatus.REGISTER_SUCCESS, "sussess");
                    } else {
                        httpListner.onSuccess(HttpListner.ResponseStatus.FAILURE, "Register failure");
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                    httpListner.onFailure(HttpListner.ResponseStatus.FAILURE,"Register failure");
            }
        });
    }

    public void getVendors(final GetVendorListner getVendorListner) {
        AariEatsApi aariEatsApi = ServiceGenerator.createRetrofit(AariEatsApi.class);
        Call<GetVendorsResponse> getVendorsCall = aariEatsApi.getVendors();
        getVendorsCall.enqueue(new Callback<GetVendorsResponse>() {
            @Override
            public void onResponse(Call<GetVendorsResponse> call, Response<GetVendorsResponse> response) {
                if(response.code() == 200) {
                    Log.i("response",response.body().getData().size()+"");
                    getVendorListner.onSuccess(GetVendorListner.ResponseStatus.SUCCESS,response.body().getData());
                } else {
                    getVendorListner.onFailure(GetVendorListner.ResponseStatus.FAILURE,"Failure");
                }
            }

            @Override
            public void onFailure(Call<GetVendorsResponse> call, Throwable t) {
                getVendorListner.onFailure(GetVendorListner.ResponseStatus.FAILURE,t.getMessage());
            }
        });
    }

    public void getProducts(String email, final ProductListner httpListner) {
        GetProductRequest getProductRequest = new GetProductRequest(email);
        AariEatsApi aariEatsApi = ServiceGenerator.createRetrofit(AariEatsApi.class);
        Call<GetProductResponse> getProductsCall = aariEatsApi.getProducts(getProductRequest);
        getProductsCall.enqueue(new Callback<GetProductResponse>() {
            @Override
            public void onResponse(Call<GetProductResponse> call, Response<GetProductResponse> response) {
                Log.i("apiservice",response.body()+"");
                if(response.code() == 200) {
                    httpListner.onSuccess(HttpListner.ResponseStatus.REGISTER_SUCCESS, response.body().getData());
                } else {
                    httpListner.onSuccess(HttpListner.ResponseStatus.FAILURE, null);
                }
            }

            @Override
            public void onFailure(Call<GetProductResponse> call, Throwable t) {
                httpListner.onFailure(HttpListner.ResponseStatus.FAILURE, t.getMessage());
            }
        });
    }

    public void placeOrder(PlaceOrderRequest placeOrderRequest, final PlaceOrderListner listner) {
        AariEatsApi aariEatsApi = ServiceGenerator.createRetrofit(AariEatsApi.class);
        Call<PlaceOrderResponse> placeOrderCall= aariEatsApi.placeOrder(placeOrderRequest);
        placeOrderCall.enqueue(new Callback<PlaceOrderResponse>() {
            @Override
            public void onResponse(Call<PlaceOrderResponse> call, Response<PlaceOrderResponse> response) {
                if(response.code() == 200) {
                    listner.onSuccess(PlaceOrderListner.ResponseStatus.SUCCESS,"success");
                } else {
                    listner.onSuccess(PlaceOrderListner.ResponseStatus.FAILURE, null);
                }
            }

            @Override
            public void onFailure(Call<PlaceOrderResponse> call, Throwable t) {
                listner.onFailure(PlaceOrderListner.ResponseStatus.FAILURE, t.getMessage());
            }
        });
    }


}
