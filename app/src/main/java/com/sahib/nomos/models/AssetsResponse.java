package com.sahib.nomos.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sahib on 1/14/2018.
 */

public class AssetsResponse {
    @SerializedName("asset_id")
    private String asset_id;
    @SerializedName("name")
    private String name;

    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
