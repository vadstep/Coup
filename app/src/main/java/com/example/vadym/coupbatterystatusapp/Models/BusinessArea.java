
package com.example.vadym.coupbatterystatusapp.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusinessArea {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("polygon")
    @Expose
    private List<List<List<Double>>> polygon = null;
    @SerializedName("active")
    @Expose
    private Boolean active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<List<List<Double>>> getPolygon() {
        return polygon;
    }

    public void setPolygon(List<List<List<Double>>> polygon) {
        this.polygon = polygon;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
