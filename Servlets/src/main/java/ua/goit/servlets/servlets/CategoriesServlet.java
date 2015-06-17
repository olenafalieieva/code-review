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

public class CategoriesServlet extends HttpServlet {	
    static final long serialVersionUID = 1590679L;
    List<Category> categories; //visible for testing

    public void init() throws ServletException {
	super.init();
	categories = CategoriesList.getAllCategories();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
	res.setContentType("text/html");
	PrintWriter writer = res.getWriter();
	int categoryID;
	String categoryName;
	int len = categories.size();
	for (int i = 0; i < len; i++) {
	    categoryID = categories.get(i).getID();
	    categoryName = categories.get(i).getName();
	    writer.write(String.format("<a href=/Servlets/projects?category=%s>%s</a><br>", categoryID, categoryName));
	}
    }
}