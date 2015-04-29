package ua.goit.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.mockito.Mockito;

import ua.goit.servlets.servlets.Projects;

public class TestProjectsServlet extends Mockito {

	@Test
	public void testServlet() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);       
		HttpServletResponse response = mock(HttpServletResponse.class); 
		
		when(request.getRequestURI()).thenReturn("http://localhost:8080/Servlets/projects?category=ART");
		when(request.getParameter("category")).thenReturn("ART");

		PrintWriter writer = new PrintWriter("fileServletProjects.txt");
		when(response.getWriter()).thenReturn(writer);

		new Projects().doGet(request, response);
		verify(request, atLeast(1)).getParameter("category");
		writer.flush();
		assertTrue(FileUtils.readFileToString(new File("fileServletProjects.txt"))
				.contains("Art.1 <br>Art.2 <br>Art.3 <br>"));
	}
}