package com.example.task1;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

@Keep
class Photo {
    @SerializedName("id")
    String id;

    @SerializedName("url_s")
    String url;
}
