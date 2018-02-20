package com.sahib.nomos.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sahib.nomos.R;
import com.sahib.nomos.Rest.CryptoService;
import com.sahib.nomos.activities.MainActivity;
import com.sahib.nomos.adapters.AssetsAdapter;
import com.sahib.nomos.models.AssetsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OneFragment extends Fragment {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "https://rest.coinapi.io/";
    private static Retrofit retrofit = null;
    private List<AssetsResponse> assets = new ArrayList<>();
    private final static String API_KEY = "7571DD19-2FEF-40B9-B460-C0DE32075E38";

    private RecyclerView recyclerView;
    private AssetsAdapter assetsAdapter = null;
    private Gson gson = null;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /*recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        connectAndGetApiData();*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        connectAndGetApiData();
    }

    public void connectAndGetApiData() {

        if (retrofit == null) {
            gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        CryptoService coinApiService = retrofit.create(CryptoService.class);

        Call<List<AssetsResponse>> call = coinApiService.getAssets(API_KEY);
        call.enqueue(new Callback<List<AssetsResponse>>() {
            @Override
            public void onResponse(Call<List<AssetsResponse>> call, Response<List<AssetsResponse>> response) {
                //if(response.isSuccessful()) {
                try {

                    for (AssetsResponse assetsResponse : response.body()) {
                        assets.add(assetsResponse);
                    }

                    assetsAdapter = new AssetsAdapter(assets);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(assetsAdapter);

                    Log.d(TAG, "Number of Coins received: " + assets.size());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<AssetsResponse>> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }

}
