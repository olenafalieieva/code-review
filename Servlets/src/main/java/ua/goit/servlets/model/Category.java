package ua.goit.servlets.model;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private String name;
	private List<Project> projects;

	public Category(int i, String name) {
		this.name = name;
		projects = new ArrayList<Project>();
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
