package ua.goit.servlets.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.goit.servlets.dao.CategoriesList;
import ua.goit.servlets.model.Category;
import ua.goit.servlets.model.Project;

public class Projects extends HttpServlet {

	private static final long serialVersionUID = 15909L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		Map<String, Category> categories = CategoriesList.newCategoryInstance();
		CategoriesList categoryList = new CategoriesList();
		List<Category> categoriesList = categoryList.getAll(categories);
		
		for (Category category: categoriesList) {
			if (req.getParameter("category").equals(category.getName())) {
				List<Project> projects = category.getProjects();
				for (int i = 0; i < projects.size(); i++) {
					writer.write(String.format("%s <br>", projects.get(i).getName()));
				}
			}
		}

	}
}	
