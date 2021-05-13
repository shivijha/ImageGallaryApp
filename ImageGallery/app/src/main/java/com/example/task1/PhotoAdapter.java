package com.example.task1;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task1.databinding.ListItemPhotoBinding;

class PhotoAdapter extends ListAdapter<Photo, PhotoAdapter.ViewHolder> {
    public PhotoAdapter() {
        super(new DiffUtilCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemPhotoBinding binding = ListItemPhotoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemPhotoBinding binding;

        public ViewHolder(ListItemPhotoBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(Photo photo) {
            Glide.with(binding.image).load(photo.url).into(binding.image);
        }
    }

    private static class DiffUtilCallback extends DiffUtil.ItemCallback<Photo> {
        @Override
        public boolean areItemsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Photo oldItem, @NonNull Photo newItem) {
            return oldItem.url.equals(newItem.url);
        }
    }
}
