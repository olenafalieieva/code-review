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

public class Categories extends HttpServlet {

	private static final long serialVersionUID = 1590679L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();

		Map<String, Category> categories = CategoriesList.newCategoryInstance();
		CategoriesList categoryList = new CategoriesList();
		List<Category> categoriesList = categoryList.getAll(categories);

		for (Category category: categoriesList) {
			writer.write(String.format("<a href=/Servlets/projects?category=%s>%s</a><br>", category.getName(), category.getName()));
		}

	}
}