package com.example.nitesh.payu.mvvm.viewmodel;

import com.example.nitesh.payu.mvvm.model.Project;

/**
 * Created by nitesh on 13/8/17.
 */

public class ProjectViewModel {
    private Project project;

    public ProjectViewModel(Project project) {
        this();
        this.project = project;
    }

    private ProjectViewModel() {
    }

    public Integer getAmtPledged() {
        return project.getAmtPledged();
    }

    public String getBlurb() {
        return project.getBlurb();
    }

    public String getBy() {
        return project.getBy();
    }

    public String getCountry() {
        return project.getCountry();
    }

    public String getCurrency() {
        return getAmtPledged().toString().concat(" "+project.getCurrency());
    }

    public String getEndTime() {
        return project.getEndTime();
    }

    public String getLocation() {
        return project.getLocation();
    }

    public Integer getPercentageFunded() {
        return project.getPercentageFunded();
    }

    public String getNumBackers() {
        return project.getNumBackers();
    }

    public String getState() {
        return project.getState();
    }

    public String getTitle() {
        return project.getTitle();
    }

    public String getType() {
        return project.getType();
    }

    public String getUrl() {
        return project.getUrl();
    }

}
