package com.example.user.eventcalendar;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by User on 20.01.2017.
 */

public interface GetEvents {

    @GET("ost/apptest/")
    Call<List<EventModel>> all();
//    @GET("ost/apptest/EventId/")
//    Call<EventModel> select(@Path("eventId") int eventId);
}
