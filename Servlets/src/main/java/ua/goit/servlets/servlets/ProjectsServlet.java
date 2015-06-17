package ua.goit.servlets.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.goit.servlets.dao.CategoriesList;
import ua.goit.servlets.model.Category;
import ua.goit.servlets.model.Project;

public class ProjectsServlet extends HttpServlet {
    static final long serialVersionUID = 15909L;
    List<Category> categories; //visible for testing

    public void init() throws ServletException {
	super.init();
	categories = CategoriesList.getAllCategories();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
	String categoryIDstring = request.getParameter("category");
	int categoryID = Integer.valueOf(categoryIDstring);

	List<Project> projects = getProjectsForCategory(categoryID);
	writeProjectsList(projects, response);
    }

    private List<Project> getProjectsForCategory(int categoryID) {
	int len = categories.size();
	List<Project> projects = null;
	for (int i = 0; i < len; i++) {
	    Category category = categories.get(i);
	    int actualCatID = category.getID();
	    if (categoryID == (actualCatID)) {
		projects = category.getProjects();
	    }
	}
	return projects;
    }

    private void writeProjectsList(List<Project> projects, HttpServletResponse res) throws IOException {
	res.setContentType("text/html");
	PrintWriter writer = res.getWriter();
	writer.write("<ul>");
	for (int j = 0; j < projects.size(); j++) {
	    String projectName = projects.get(j).getName();
	    writer.write(String.format("<li> %s </li>", projectName));
	}
	writer.write("</ul>");
    }
}