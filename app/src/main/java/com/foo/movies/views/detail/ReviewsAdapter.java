package com.foo.movies.views.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    private final LayoutInflater inflater;

    private List<Review> reviewList;

    public ReviewsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setReviewList(List<Review> reviewList){
        this.reviewList = reviewList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.list_item_reviews,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.reviewAuthor.setText(review.getAuthor());
        holder.reviewContent.setText(review.getContent());
    }

    @Override
    public int getItemCount() {
        return reviewList==null?0:reviewList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.review_description)
        TextView reviewContent;

        @BindView(R.id.review_title)
        TextView reviewAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
