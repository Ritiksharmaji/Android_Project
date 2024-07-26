package com.example.json_parsing_response_by_library_retrofit;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/users")
    Call<List<User>> getUsers();
}

