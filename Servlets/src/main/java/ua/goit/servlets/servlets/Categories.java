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
	static final long serialVersionUID = 1590679L;
	List<Category> categories;
	
	public void init() throws ServletException {
	super.init();
	categories = CategoriesList.newCategoryInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		String categoryName;
		int len = categories.size();
		for (int i = 0; i < len; i++) {
			categoryName = categories.get(i).getName();
			writer.write(String.format("<a href=/Servlets/projects?category=%s>%s</a><br>", categoryName, categoryName));
		}
	}
}