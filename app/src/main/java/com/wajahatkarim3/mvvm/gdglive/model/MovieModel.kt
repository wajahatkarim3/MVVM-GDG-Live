package com.wajahatkarim3.mvvm.gdglive.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(
    @SerializedName("vote_count") @Expose var voteCount: Int,
    @SerializedName("vote_average") @Expose var voteAverage: Float,
    @SerializedName("id") @Expose var id: Int,
    @SerializedName("title") @Expose var title: String,
    @SerializedName("poster_path") @Expose var posterPath: String,
    @SerializedName("overview") @Expose var overview: String,
    @SerializedName("release_date") @Expose var release_date: String
) : Parcelable