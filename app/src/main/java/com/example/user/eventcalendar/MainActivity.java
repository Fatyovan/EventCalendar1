package com.example.user.eventcalendar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    Calendar calendar = Calendar.getInstance();
    int DATE_PICKER_TO = 0;
    int DATE_PICKER_FROM = 1;

    Date fromDate, toDate;

    private List<EventModel> allEvents = new ArrayList<>();
    private List<EventModel> filteredEvents = new ArrayList<>();
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
        allEvents.addAll(models);
        filteredEvents.addAll(allEvents);
        adapter = new EventAdapter(filteredEvents, MainActivity.this);
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

                final DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, fromListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setTitle("From: ");
                datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatePicker datePicker = datePickerDialog.getDatePicker();
                        fromListener.onDateSet(datePicker, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());

                        final DatePickerDialog toDatePicker = new DatePickerDialog(MainActivity.this, toListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                        toDatePicker.setTitle("To: ");
                        toDatePicker.setButton(DialogInterface.BUTTON_POSITIVE, "Filter", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatePicker datePicker = toDatePicker.getDatePicker();
                                toListener.onDateSet(datePicker, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());

                                filterEvents();
                            }
                        });

                        toDatePicker.show();


                    }
                });

                datePickerDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    DatePickerDialog.OnDateSetListener fromListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            c.set(year, month, dayOfMonth);
            fromDate = c.getTime();
            fromDate = removeTime(fromDate);
        }

    };


    DatePickerDialog.OnDateSetListener toListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            c.set(year, month, dayOfMonth);
            toDate = c.getTime();
            toDate = removeTime(toDate);
        }

    };

    private void filterEvents() {
        filteredEvents.clear();
        for (EventModel e : Singleton.getInstance().getModels()) {
            DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
            try {
                Date eventDate = format.parse(e.getEventDate());
                Log.d("DATE: ", eventDate.toString() + " compare to " + fromDate + " = " + eventDate.compareTo(fromDate));
                if((eventDate.compareTo(fromDate) > 0 && eventDate.compareTo(toDate) < 0)
                        || eventDate.compareTo((toDate)) == 0 || eventDate.compareTo((fromDate)) == 0 ){
                    filteredEvents.add(e);
                }
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        adapter.notifyDataSetChanged();

    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

}
