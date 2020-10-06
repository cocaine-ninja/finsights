package com.kingsmen.finsights.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kingsmen.finsights.R;
import com.kingsmen.finsights.dao.Offer;

import java.util.ArrayList;
import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {
    private List<Offer> offers;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private List<Integer> resources;

    // data is passed into the constructor
    public OfferAdapter(Context context, List<Offer> offers) {
        this.mInflater = LayoutInflater.from(context);
        this.offers = offers;
        this.resources = new ArrayList<>();
        resources.add(R.drawable.offer1);
        resources.add(R.drawable.offer2);
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.offers_recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Offer o = this.offers.get(position);
        String offerType = o.getType();
        String offerDescription = o.getDescription();
//        holder.offerTypeTextView.setText(offerType);
//        holder.offerDescriptionTextView.setText(offerDescription);
        holder.offersImageView.setImageResource(this.resources.get(position));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return offers.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView offerTypeTextView;
        TextView offerDescriptionTextView;
        ImageView offersImageView;

        ViewHolder(View itemView) {
            super(itemView);
//            offerTypeTextView = itemView.findViewById(R.id.offerTypeTextView);
//            offerDescriptionTextView = itemView.findViewById(R.id.offerDescriptionTextView);
            offersImageView = itemView.findViewById(R.id.imageView6);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
