package hima.aarieats.http.api;

import android.util.Log;

import hima.aarieats.http.HttpListner;
import hima.aarieats.http.ServiceGenerator;
import hima.aarieats.http.models.LoginRequest;
import hima.aarieats.http.models.LoginResponse;
import hima.aarieats.http.models.RegisterRequest;
import hima.aarieats.http.models.RegisterResponse;
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

    public void login(String username, String password, final HttpListner httpListner) {
        LoginRequest loginRequest = new LoginRequest(username,password);
        AariEatsApi aariEatsApi = ServiceGenerator.createRetrofit(AariEatsApi.class);
        Call<LoginResponse> loginCall = aariEatsApi.login(loginRequest);
        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.i("apiservice",response.code()+"");
                if(response.code() == 200) {
                    if(response.code() == 200) {
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

    public void register(String username, String password, final HttpListner httpListner) {
        RegisterRequest registerRequest = new RegisterRequest(username,password);
        AariEatsApi aariEatsApi = ServiceGenerator.createRetrofit(AariEatsApi.class);
        Call<RegisterResponse> registerCall = aariEatsApi.register(registerRequest);
        registerCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Log.i("apiservice",response.code()+"");
                if(response.code() == 200) {
                    if(response.body().getCode() == 200) {
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
}
