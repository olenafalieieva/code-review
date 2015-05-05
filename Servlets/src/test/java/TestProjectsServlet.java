
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.mockito.Mockito;

import ua.goit.servlets.servlets.Projects;

public class TestProjectsServlet extends Mockito {
	private Projects servlet;

	@Before
	public void setUp() {
		servlet = new Projects();
	}

	@Test
	public void testServlet() throws Exception {
		HttpServletRequest request = mock(HttpServletRequest.class);       
		HttpServletResponse response = mock(HttpServletResponse.class); 
		when(request.getRequestURI()).thenReturn("http://localhost:8080/Servlets/projects?category=ART");
		when(request.getParameter("category")).thenReturn("ART");
		PrintWriter writer = new PrintWriter("fileServletProjects.txt");
		when(response.getWriter()).thenReturn(writer);

		servlet.doGet(request, response);
		verify(request, atLeast(1)).getParameter("category");
		writer.flush();
		assertTrue(FileUtils.readFileToString(new File("fileServletProjects.txt"))
				.contains("<ul> <li> Art.1 <br> <li> Art.2 <br> <li> Art.3 <br></li></ul>"));
	}
}
