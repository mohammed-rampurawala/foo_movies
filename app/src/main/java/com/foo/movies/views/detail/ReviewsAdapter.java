package com.foo.movies.views.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.foo.movies.R;
import com.foo.movies.data.model.Review;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohammed.rampurawala on 2/2/2018.
 */
public class ReviewsAdapter extends ArrayAdapter<Review> {

    private final LayoutInflater inflater;

    public ReviewsAdapter(Context context, List<Review> reviews) {
        super(context, R.layout.list_item_reviews, reviews);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Review review = getItem(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_reviews, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.reviewAuthor.setText(review.getAuthor());
        viewHolder.reviewAuthor.setContentDescription(review.getAuthor());

        viewHolder.reviewContent.setText(review.getContent());
        viewHolder.reviewContent.setContentDescription(review.getContent());
        return convertView;

    }

    static class ViewHolder {

        @BindView(R.id.review_content)
        TextView reviewContent;

        @BindView(R.id.review_author)
        TextView reviewAuthor;

        public ViewHolder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
