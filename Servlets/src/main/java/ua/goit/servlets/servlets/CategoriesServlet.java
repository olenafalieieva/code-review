package ua.goit.servlets.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.goit.servlets.dao.CategoriesList;
import ua.goit.servlets.model.Category;

public class CategoriesServlet extends HttpServlet {	
    static final long serialVersionUID = 1590679L;
    Map<Integer, Category> categories; //visible for testing

    public void init() throws ServletException {
	super.init();
	categories = CategoriesList.getAllCategories();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
	res.setContentType("text/html");
	PrintWriter writer = res.getWriter();

	for (Map.Entry<Integer, Category> entry : categories.entrySet()) {
	    Integer categoryID = entry.getKey();
	    Category category = entry.getValue();
	    String categoryName = category.getName();
	    writer.write(String.format("<a href=/Servlets/projects?category=%s>%s</a><br>", categoryID, categoryName));
	}
    }
}