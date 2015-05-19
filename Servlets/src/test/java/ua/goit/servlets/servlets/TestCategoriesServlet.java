package ua.goit.servlets.servlets;

import static org.junit.Assert.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestCategoriesServlet extends Mockito {
  private CategoriesServlet servlet;

  @Before
  public void setUp() throws ServletException {
	servlet = new CategoriesServlet();
	servlet.init();
  }

  @Test
  public void testServlet() throws Exception {
	HttpServletRequest request = mock(HttpServletRequest.class);       
	HttpServletResponse response = mock(HttpServletResponse.class); 
	when(request.getRequestURI()).thenReturn("http://localhost:8080/Servlets/categories");

	StringWriter stringWriter = new StringWriter();
	PrintWriter writer = new PrintWriter(stringWriter);
	when(response.getWriter()).thenReturn(writer);

	servlet.doGet(request, response);
	String result = stringWriter.toString();
	writer.flush();

	assertEquals("<a href=/Servlets/projects?category=IT>IT</a><br>"
		+ "<a href=/Servlets/projects?category=ART>ART</a><br>", result);
  }
}
