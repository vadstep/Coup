
package com.example.vadym.coupbatterystatusapp.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("scooters")
    @Expose
    private List<Scooter> scooters = null;

    public List<Scooter> getScooters() {
        return scooters;
    }

    public void setScooters(List<Scooter> scooters) {
        this.scooters = scooters;
    }
    @SerializedName("business_areas")
    @Expose
    private List<BusinessArea> businessAreas = null;

    public List<BusinessArea> getBusinessAreas() {
        return businessAreas;
    }

    public void setBusinessAreas(List<BusinessArea> businessAreas) {
        this.businessAreas = businessAreas;
    }


}
