package com.example.user.eventcalendar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;

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
    RelativeLayout location;


    private List<EventModel> filteredList = new ArrayList<EventModel>();
    private static final String ENDPOINT_URL = "https://www.rang1tickets.nl/ost/apptest/";

    public ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(getString(R.string.events_activity));

        progress = new ProgressDialog(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        fetchEvent();
    }

    private void fetchEvent() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ENDPOINT_URL).addConverterFactory(GsonConverterFactory.create()).
                build();
        GetEvents getEvents = retrofit.create(GetEvents.class);
        Call<List<EventModel>> eventModelCall = getEvents.all();
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setTitle(R.string.please_wait);
        progress.setCancelable(false);
        progress.show();
        eventModelCall.enqueue(new Callback<List<EventModel>>() {
            @Override
            public void onResponse(Call<List<EventModel>> call, Response<List<EventModel>> response) {
                fillData(response.body());
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<List<EventModel>> call, Throwable t) {

            }
        });
    }

    public void fillData(List<EventModel> models) {
        Singleton.getInstance().setModels(models);
        filteredList.addAll(models);
        adapter = new EventAdapter(filteredList, MainActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //// TODO: 21.01.2017
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.events_filter_menu_item:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
