package com.example.nerdeyesemproje.Restaurant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Re implements Serializable {

    @SerializedName("res_id")
    @Expose
    private Integer resId;

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

}