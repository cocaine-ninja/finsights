package com.kingsmen.finsights.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kingsmen.finsights.R;
import com.kingsmen.finsights.dao.News;
import com.kingsmen.finsights.values.NewsList;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> news;
    private List<Integer> resources;
    private LayoutInflater mInflater;
    private OfferAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    public NewsAdapter(Context context, List<News> news) {
        this.mInflater = LayoutInflater.from(context);
        this.news = news;
        this.resources = new ArrayList<>();
        resources.add(R.drawable.news1);
        resources.add(R.drawable.news2);
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.news_recycler_view_item, parent, false);
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News o = this.news.get(position);
        String newsHeading = o.getHeading();
        String newsDetails = o.getDetails();
//        holder.newsHeadingTextView.setText(newsHeading);
//        holder.newsDetailsTextView.setText(newsDetails);
        holder.newsImageView.setImageResource(this.resources.get(position));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return news.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView newsHeadingTextView;
        TextView newsDetailsTextView;
        ImageView newsImageView;

        ViewHolder(View itemView) {
            super(itemView);
//            newsHeadingTextView = itemView.findViewById(R.id.newsHeadingTextView);
//            newsDetailsTextView = itemView.findViewById(R.id.newsDetailsTextView);
            newsImageView = itemView.findViewById(R.id.imageView5);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    public void setClickListener(OfferAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
