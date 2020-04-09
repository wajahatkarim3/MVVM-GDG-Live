package com.wajahatkarim3.mvvm.gdglive.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ActorModel(
    @SerializedName("id") @Expose var id: Int,
    @SerializedName("credit_id") @Expose var credit_id: String,
    @SerializedName("name") @Expose var name: String,
    @SerializedName("character") @Expose var character: String,
    @SerializedName("profile_path") @Expose var profile: String
)