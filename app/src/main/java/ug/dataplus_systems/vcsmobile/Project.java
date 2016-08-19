package ug.dataplus_systems.vcsmobile;

import android.content.Context;

/**
 * Created by senyer on 8/8/2016.
 */
public class Project {

    Long projectId;
    String projectName;
    String projectCode;
    String projectObjective;
    String projectStartDate;
    String projectEndDate;
    String projectDescription;
    String projectManager;
    Context context;

    public Project(Context c) {

        context = c;


    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectObjective() {
        return projectObjective;
    }

    public void setProjectObjective(String projectObjective) {
        this.projectObjective = projectObjective;
    }

    public String getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }
}
