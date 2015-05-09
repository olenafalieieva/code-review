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

public class Projects extends HttpServlet {
	static final long serialVersionUID = 15909L;
	List<Category> categories;

	public void init() throws ServletException {
		super.init();
		categories = CategoriesList.newCategoryInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		
		String categoryName = req.getParameter("category");
		Category category;
		int len = categories.size();
		for (int i = 0; i < len; i++) {
			category = categories.get(i);
			if (categoryName.equals(category.getName())) {
				List<Project> projects = category.getProjects();
				writer.write("<ul>");
				for (int j = 0; j < projects.size(); j++) {
					String projectName = projects.get(j).getName();
					writer.write(String.format(" <li> %s <br>", projectName));
				}
				writer.write("</li>");
				writer.write("</ul>");
			}
		}
	}
}	