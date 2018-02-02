package com.foo.movies.views.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.foo.movies.R;
import com.foo.movies.data.model.Trailer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohammed.rampurawala on 2/2/2018.
 */
public class TrailersAdapter extends ArrayAdapter<Trailer> {

    private final LayoutInflater inflater;

    public TrailersAdapter(Context context, List<Trailer> trailers) {
        super(context, R.layout.list_item_trailers, trailers);
        inflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Trailer trailer = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_trailers, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.trailerTitle.setText(trailer.getName());
        viewHolder.trailerTitle.setContentDescription(trailer.getName());

        Glide.with(convertView).load("http://img.youtube.com/vi/" + trailer.getKey() + "/0.jpg").into(viewHolder.trailerThumbnail);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.trailer_title)
        TextView trailerTitle;

        @BindView(R.id.trailer_thumbnail)
        ImageView trailerThumbnail;

        public ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
