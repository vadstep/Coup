
package com.example.vadym.coupbatterystatusapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Scooter {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("vin")
    @Expose
    private String vin;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("market_id")
    @Expose
    private String marketId;
    @SerializedName("license_plate")
    @Expose
    private String licensePlate;
    @SerializedName("energy_level")
    @Expose
    private Integer energyLevel;
    @SerializedName("distance_to_travel")
    @Expose
    private Double distanceToTravel;
    @SerializedName("location")
    @Expose
    private Location location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(Integer energyLevel) {
        this.energyLevel = energyLevel;
    }

    public Double getDistanceToTravel() {
        return distanceToTravel;
    }

    public void setDistanceToTravel(Double distanceToTravel) {
        this.distanceToTravel = distanceToTravel;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
