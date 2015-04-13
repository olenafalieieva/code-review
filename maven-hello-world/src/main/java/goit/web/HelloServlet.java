package goit.web;

import javax.servlet.http.*;
import java.io.*;

public class HelloServlet extends HttpServlet {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException {
res.getWriter().println("Hello World!");
}
}