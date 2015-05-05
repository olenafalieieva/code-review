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

public class Categories extends HttpServlet {

	private static final long serialVersionUID = 1590679L;
	private List<Category> categories;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		categories = CategoriesList.newCategoryInstance();
		String categoryName;
		int len = categories.size();
		for (int i = 0; i < len; i++) {
			categoryName = categories.get(i).getName();
			writer.write(String.format("<a href=/Servlets/projects?category=%s>%s</a><br>", categoryName, categoryName));
		}
	}
}