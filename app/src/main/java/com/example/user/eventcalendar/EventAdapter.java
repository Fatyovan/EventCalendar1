package com.example.user.eventcalendar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    public static final String EXTRA_EVENT_ID = "eventID";

    private Context mContext;
    private List<EventModel> eventModel = new ArrayList<EventModel>();

    public EventAdapter(List<EventModel> eventModel, Context context) {
        this.eventModel = eventModel;
        this.mContext = context;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.custom_row, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final EventModel event = eventModel.get(position);

        holder.artistName.setText(event.getArtistName());
        holder.eventTime.setText(event.getEventTime());
        holder.date.setText(event.getEventDate());
        if ( event.getArtistTourName() != null || !event.getArtistTourName().isEmpty()) {
            holder.tourName.setText(event.getArtistTourName());
        } else {
            holder.tourNameText.setVisibility(View.GONE);
            holder.tourName.setVisibility(View.GONE);
        }
        if (event.getArtistImage() != null) {
            Glide
                    .with(mContext)
                    .load(event.getArtistImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.artistImg);
        } else {
            Glide
                    .with(mContext)
                    .load(R.drawable.picture_default)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.artistImg);
        }
        holder.eventLocation.setText((event.getVenueCity() + ", " + event.getVenueCountry()));

        holder.rlLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, MapActivity.class);
                i.putExtra(EXTRA_EVENT_ID, event.getEventId());
                mContext.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return eventModel.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView artistName;
        ImageView artistImg;
        TextView tourName;
        TextView date;
        TextView eventTime;
        TextView eventLocation;
        TextView tourNameText;
        RelativeLayout rlLocation;

        public MyViewHolder(View itemView) {
            super(itemView);

            rlLocation = (RelativeLayout) itemView.findViewById(R.id.Rl_location);
            artistName = (TextView) itemView.findViewById(R.id.Tv_artist_name);
            artistImg = (ImageView) itemView.findViewById(R.id.Iv_artist_img);
            tourName = (TextView) itemView.findViewById(R.id.Tv_tour_name);
            date = (TextView) itemView.findViewById(R.id.Tv_date);
            eventTime = (TextView) itemView.findViewById(R.id.Tv_event_time);
            tourNameText = (TextView) itemView.findViewById(R.id.Tv_tour_name_text);
            eventLocation = (TextView) itemView.findViewById(R.id.tv_location);

        }
    }


}
