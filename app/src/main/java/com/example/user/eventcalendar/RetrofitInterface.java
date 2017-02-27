package com.example.user.eventcalendar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by User on 20.01.2017.
 */

public interface RetrofitInterface {

    @GET("ost/apptest/")
    Call<List<EventModel>> getAllEvents();
//    @GET("ost/apptest/EventId/")
//    Call<EventModel> select(@Path("eventId") int eventId);
}
