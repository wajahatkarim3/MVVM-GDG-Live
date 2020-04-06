package com.wajahatkarim3.mvvm.gdglive.data.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.wajahatkarim3.mvvm.model.ActorModel

class ActorsListResponse : BaseResponse() {

    @SerializedName("cast") @Expose var actorsList: ArrayList<ActorModel>? = null
    @SerializedName("id") @Expose var page: Int = 1

}