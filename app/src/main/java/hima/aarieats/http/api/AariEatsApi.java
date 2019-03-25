package hima.aarieats.http.api;

import hima.aarieats.http.models.GetProductRequest;
import hima.aarieats.http.models.GetProductResponse;
import hima.aarieats.http.models.GetVendorsResponse;
import hima.aarieats.http.models.LoginRequest;
import hima.aarieats.http.models.LoginResponse;
import hima.aarieats.http.models.PlaceOrderRequest;
import hima.aarieats.http.models.PlaceOrderResponse;
import hima.aarieats.http.models.RegisterRequest;
import hima.aarieats.http.models.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AariEatsApi {

    @POST("/loginuser")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("/registeruser")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @POST("/getproducts")
    Call<GetProductResponse> getProducts(@Body GetProductRequest getProductRequest);

    @GET("/getvendors")
    Call<GetVendorsResponse> getVendors();

    @POST("/placeorder")
    Call<PlaceOrderResponse> placeOrder(@Body PlaceOrderRequest placeOrderRequest);


}
