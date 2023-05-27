package com.example.randomnum;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomNumberService {
    @GET("random_num/numbers")
    Call<List<RandomNumberResponse>> getRandomNumbers();
}

