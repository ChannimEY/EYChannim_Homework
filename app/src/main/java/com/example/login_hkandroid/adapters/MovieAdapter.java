package com.example.login_hkandroid.adapters;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login_hkandroid.R;
import com.example.login_hkandroid.models.MovieModel;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<MovieModel> data;

    public MovieAdapter(List<MovieModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPoster;
        private TextView tvRating;
        private TextView tvType;
        private TextView tvTitle;
        private TextView tvYear;
        private TextView tvDuration;
        private TextView tvCertificate;
        private TextView tvGenre;
        private TextView tvMovie;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            tvRating = itemView.findViewById(R.id.tvRating);
            tvType = itemView.findViewById(R.id.tvType);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvYear = itemView.findViewById(R.id.tvYear);
            tvDuration = itemView.findViewById(R.id.tvDuration);
            tvCertificate = itemView.findViewById(R.id.tvCertificate);
            tvGenre = itemView.findViewById(R.id.tvGenre);
            tvMovie = itemView.findViewById(R.id.tvMovie);
        }

        public void setData(MovieModel item) {
            ivPoster.setImageResource(item.getImage());
            tvRating.setText(String.valueOf(item.getRating()));
            tvType.setText(item.getType());
            tvTitle.setText(item.getTitle());
            tvYear.setText(String.valueOf(item.getYear()));
            tvDuration.setText(item.getDuration() + " Minutes");
            tvCertificate.setText(item.getCertificate());
            tvGenre.setText(item.getGenre());
            tvMovie.setText("Movie");

            if ("Free".equalsIgnoreCase(item.getType())) {
                tvType.setBackgroundTintList(ColorStateList.valueOf(0xFF00BCD4));
            } else {
                tvType.setBackgroundTintList(ColorStateList.valueOf(0xFFFF9800));
            }
        }
    }
}
