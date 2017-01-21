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

import java.util.ArrayList;
import java.util.List;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {


    private Context mContext;
    private List<EventModel> eventModel = new ArrayList<EventModel>();



    public EventAdapter(List<EventModel> eventModel,Context context) {
        this.eventModel = eventModel;
        this.mContext= context;

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
        holder.artistImg.setImageResource(Integer.parseInt(eventModel.get(position).getArtistImage()));
        holder.date.setText(eventModel.get(position).getEventDate());
        holder.tourName.setText(eventModel.get(position).getArtistTourName());

        Glide
                .with(mContext)
                .load(eventModel.get(position).getArtistImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.artistImg);

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

        public MyViewHolder(View itemView) {
            super(itemView);

            artistName = (TextView) itemView.findViewById(R.id.Tv_artist_name);
            artistImg = (ImageView) itemView.findViewById(R.id.Iv_artist_img);
            tourName= (TextView) itemView.findViewById(R.id.Tv_tour_name);
            date = (TextView) itemView.findViewById(R.id.Tv_date);
            eventTime = (TextView) itemView.findViewById(R.id.Tv_event_time);

        }
    }


}
