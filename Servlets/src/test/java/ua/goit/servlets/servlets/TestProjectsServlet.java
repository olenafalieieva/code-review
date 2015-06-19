package ua.goit.servlets.servlets;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mockito.Mockito;

public class TestProjectsServlet extends Mockito {
  ProjectsServlet servlet;

  @Before
  public void setUp() throws ServletException {
	servlet = new ProjectsServlet();
	servlet.init();
  }

  @Test
  public void testServlet() throws Exception {
	HttpServletRequest request = mock(HttpServletRequest.class);       
	HttpServletResponse response = mock(HttpServletResponse.class); 
	when(request.getRequestURI()).thenReturn("http://localhost:8080/Servlets/projects?category=2");
	when(request.getParameter("category")).thenReturn("2");

	StringWriter stringWriter = new StringWriter();
	PrintWriter writer = new PrintWriter(stringWriter);
	when(response.getWriter()).thenReturn(writer);

	servlet.doGet(request, response);
	verify(request, atLeast(1)).getParameter("category");
	String result = stringWriter.toString();
	writer.flush();

	assertEquals("<ul><li> Art.1 </li><li> Art.2 </li><li> Art.3 </li></ul>", result);
  }
}