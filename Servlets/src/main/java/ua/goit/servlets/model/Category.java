package ua.goit.servlets.model;

import java.util.ArrayList;
import java.util.List;

public final class Category {
    private int id;
    private String name;
    private List<Project> projects;

    public Category(int id, String name) {
	this.id = id;
	this.name = name;
	projects = new ArrayList<Project>();
    }

    public int getID() {
	return id;
    }
    
    public String getName() {
	return name;
    }

    public  List<Project> getProjects() {
	return projects;
    }

    public void addProject(Integer id, String projectName){
	projects.add(new Project(id, projectName));
    }
}