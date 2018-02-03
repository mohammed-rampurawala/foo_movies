package com.foo.movies.views.detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.foo.movies.R;
import com.foo.movies.data.model.Trailer;
import com.foo.movies.listener.ITrailerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohammed.rampurawala on 2/2/2018.
 */
public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder>{

    private static final String YOUTUBE_THUMBNAIL = "https://img.youtube.com/vi/%s/mqdefault.jpg";

    private final LayoutInflater inflater;

    private final ITrailerClickListener trailerClickListener;

    private List<Trailer> trailers;

    public TrailersAdapter(Context context, ITrailerClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.trailerClickListener = listener;
    }

    public void setTrailers(List<Trailer> trailers){
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.list_item_trailers,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Glide.with(holder.itemView).load(String.format(YOUTUBE_THUMBNAIL,trailers.get(position).getKey())).into(holder.trailerThumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Trailer trailer = trailers.get(position);
                if(trailer!=null && trailerClickListener!=null){
                    trailerClickListener.onTrailerClicked(trailer.getKey());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailers==null?0:trailers.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.trailer_thumbnail)
        ImageView trailerThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
