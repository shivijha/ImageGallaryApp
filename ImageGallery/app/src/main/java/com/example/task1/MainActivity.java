package com.example.task1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PhotoAdapter adapter = new PhotoAdapter();

        binding.photos.setAdapter(adapter);
        FlickrApi.getRecentPhotos(adapter::submitList);
    }
}
