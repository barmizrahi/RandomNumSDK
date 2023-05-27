package com.example.randomnum;

import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RandomNumberSDK {
    private static final String BASE_URL = "https://646a518f5a6ce7a8a21827d2.mockapi.io/";
    private RandomNumberService randomNumberService;


    public RandomNumberSDK() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        randomNumberService = retrofit.create(RandomNumberService.class);
    }

    public void getRandomNumber(final RandomNumberListener listener) {
        Call<List<RandomNumberResponse>> call = randomNumberService.getRandomNumbers();
        call.enqueue(new Callback<List<RandomNumberResponse>>() {
            @Override
            public void onResponse(Call<List<RandomNumberResponse>> call, Response<List<RandomNumberResponse>> response) {
                if (response.isSuccessful()) {
                    List<RandomNumberResponse> randomNumberList = response.body();
                    if (randomNumberList != null && !randomNumberList.isEmpty()) {
                        // Get a random number from the list
                        RandomNumberResponse randomNumberResponse = randomNumberList.get(new Random().nextInt(randomNumberList.size()));
                        String randomNumberId = randomNumberResponse.getId();
                        int randomNumber = Integer.parseInt(randomNumberId);
                        listener.onRandomNumberGenerated(randomNumber);
                    } else {
                        listener.onRandomNumberError("Empty response or invalid data");
                    }
                } else {
                    listener.onRandomNumberError(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<RandomNumberResponse>> call, Throwable t) {
                listener.onRandomNumberError(t.getMessage());
            }
        });
    }


    public interface RandomNumberListener {
        void onRandomNumberGenerated(int randomNumber);

        void onRandomNumberError(String errorMessage);
    }
}
