package com.kingsmen.finsights.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kingsmen.finsights.R;

import java.util.ArrayList;
import java.util.List;

public class MicroLessonsAdapter extends RecyclerView.Adapter<MicroLessonsAdapter.ViewHolder>  {
    private MicroLessonsAdapter.ItemClickListener mClickListener;
    private LayoutInflater mInflater;
    private List<Integer> resources;

    public MicroLessonsAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.resources = new ArrayList<>();
        resources.add(R.drawable.lesson1);
        resources.add(R.drawable.lesson2);
        resources.add(R.drawable.lesson3);
    }

    @NonNull
    @Override
    public MicroLessonsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.microlessons_recycler_view_item, parent, false);
        return new MicroLessonsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MicroLessonsAdapter.ViewHolder holder, int position) {
//        String url = this.resources.get(position);
//        Uri uri = Uri.parse(url);

        //Setting MediaController and URI, then starting the videoView
//        holder.videoView.setMediaController(holder.mediaController);
//        holder.videoView.setVideoPath(url);
//        holder.videoView.setVideoURI(uri);
//        holder.videoView.requestFocus();
//        holder.videoView.start();

        // holder.textView.setText(url);
        holder.imgView.setImageResource(this.resources.get(position));
    }

    @Override
    public int getItemCount() {
        return this.resources.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        VideoView videoView;
        MediaController mediaController;
        TextView textView;
        ImageView imgView;

        ViewHolder(View itemView) {
            super(itemView);
//            videoView = itemView.findViewById(R.id.videoView2);
//            textView = itemView.findViewById(R.id.textView5);

//            mediaController= new MediaController(itemView.getContext());
//            mediaController.setAnchorView(videoView);
            imgView = itemView.findViewById(R.id.imageView7);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
