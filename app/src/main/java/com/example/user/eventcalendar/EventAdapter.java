package com.example.user.eventcalendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {


    private Context mContext;
    private List<EventModel> eventModel = new ArrayList<EventModel>();
    private TextView tourNameText;


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


        holder.artistName.setText(eventModel.get(position).getArtistName());
        holder.eventTime.setText(eventModel.get(position).getEventTime());
        holder.date.setText(eventModel.get(position).getEventDate());
        if (eventModel.get(position).getArtistTourName() != null) {
            holder.tourName.setText(eventModel.get(position).getArtistTourName());
        } else {
            tourNameText.setVisibility(View.INVISIBLE);
        }
        if (eventModel.get(position).getArtistImage() != null) {
            Glide
                    .with(mContext)
                    .load(eventModel.get(position).getArtistImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.artistImg);
        } else {
            Glide
                    .with(mContext)
                    .load(R.drawable.picture_default)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.artistImg);
        }
        holder.eventLocationCity.setText((eventModel.get(position).getVenueCity() + ", "));
        holder.eventLocationCountry.setText(eventModel.get(position).getVenueCountry());
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
        TextView eventLocationCity;
        TextView eventLocationCountry;

        public MyViewHolder(View itemView) {
            super(itemView);

            artistName = (TextView) itemView.findViewById(R.id.Tv_artist_name);
            artistImg = (ImageView) itemView.findViewById(R.id.Iv_artist_img);
            tourName = (TextView) itemView.findViewById(R.id.Tv_tour_name);
            date = (TextView) itemView.findViewById(R.id.Tv_date);
            eventTime = (TextView) itemView.findViewById(R.id.Tv_event_time);
            tourNameText = (TextView) itemView.findViewById(R.id.Tv_tour_name_text);
            eventLocationCity = (TextView) itemView.findViewById(R.id.Tv_location_city);
            eventLocationCountry = (TextView) itemView.findViewById(R.id.Tv_location_country);

        }
    }


}
