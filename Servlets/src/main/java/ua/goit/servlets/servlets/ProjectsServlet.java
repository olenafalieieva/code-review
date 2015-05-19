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
	categories = CategoriesList.newCategoryInstance();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
	String categoryName = request.getParameter("category");

	List<Project> projects = getProjectsForCategory(categoryName);
	writeProjectsList(projects, response);
  }

  private List<Project> getProjectsForCategory(String categoryName) {
	int len = categories.size();
	List<Project> projects = null;
	for (int i = 0; i < len; i++) {
	  Category category = categories.get(i);
	  if (categoryName.equals(category.getName())) {
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