package ua.goit.servlets.servlets;

import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ua.goit.servlets.servlets.Categories;

public class TestCategoriesServlet extends Mockito {
	private Categories servlet;

	@Before
	public void setUp() throws ServletException {
		servlet = new Categories();
		servlet.init();
	}
	
	@Test
	public void testServlet() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);       
		HttpServletResponse response = mock(HttpServletResponse.class); 
		when(request.getRequestURI()).thenReturn("http://localhost:8080/Servlets/categories");
		PrintWriter writer = new PrintWriter("fileServletCategories.txt");
		when(response.getWriter()).thenReturn(writer);

		servlet.doGet(request, response);
		writer.flush();
		
		assertTrue(FileUtils.readFileToString(new File("fileServletCategories.txt"))
				.contains("<a href=/Servlets/projects?category=IT>IT</a><br>"
						+ "<a href=/Servlets/projects?category=ART>ART</a><br>"));
	}
}