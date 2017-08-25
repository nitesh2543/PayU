package com.example.nitesh.payu.mvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

/**
 * Created by nitesh on 13/8/17.
 */

public class Project extends SugarRecord {

    @SerializedName("s.no")
    @Expose
    private Integer sNo;
    @SerializedName("amt.pledged")
    @Expose
    private Integer amtPledged;
    @SerializedName("blurb")
    @Expose
    private String blurb;
    @SerializedName("by")
    @Expose
    private String by;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("end.time")
    @Expose
    private String endTime;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("percentage.funded")
    @Expose
    private Integer percentageFunded;
    @SerializedName("num.backers")
    @Expose
    private String numBackers;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;

    private int viewType;

    public Project(int viewType) {
        this.viewType = viewType;
    }

    public Project(){}

    public int getViewType() {
        return viewType;
    }

    public Integer getSNo() {
        return sNo;
    }

    public Integer getAmtPledged() {
        return amtPledged;
    }

    public String getBlurb() {
        return blurb;
    }

    public String getBy() {
        return by;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrency() {
        return currency;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public Integer getPercentageFunded() {
        return percentageFunded;
    }

    public String getNumBackers() {
        return numBackers;
    }

    public String getState() {
        return state;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }
}
