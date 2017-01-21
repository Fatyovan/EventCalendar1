package com.example.user.eventcalendar;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<EventModel> listData = new ArrayList<EventModel>();
    private static final String ENDPOINT_URL = "https://www.rang1tickets.nl/ost/apptest/";

    public ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchEvent();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        progress = new ProgressDialog(this);


    }

    private void fetchEvent() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ENDPOINT_URL).addConverterFactory(GsonConverterFactory.create()).
                build();
        GetEvents getEvents = retrofit.create(GetEvents.class);
        Call<List<EventModel>> eventModelCall = getEvents.all();
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        eventModelCall.enqueue(new Callback<List<EventModel>>() {
            @Override
            public void onResponse(Call<List<EventModel>> call, Response<List<EventModel>> response) {
                fillData(response.body(), getBaseContext());
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<List<EventModel>> call, Throwable t) {

            }
        });
    }

    public static void fillData(List<EventModel> models, Context context) {
        for (EventModel model : models) {
            adapter = new EventAdapter(models, context);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            //// TODO: 21.01.2017  
        }

    }


}
