package auca.servlet;



import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorHandlingServlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req, res);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        res.setContentType("text/html");

        if (statusCode == 404) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Error 404 - Page Not Found</title>");
            out.println("<style>");
            out.println("body {");
            out.println("font-family: 'Arial', sans-serif;");
            out.println("background-color: #f4f4f4;");
            out.println("text-align: center;");
            out.println("margin: 50px;");
            out.println("}");
            out.println("h1 {");
            out.println("color: #d9534f;");
            out.println("}");
            out.println("p {");
            out.println("color: #333;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Error 404 - Page Not Found</h1>");
            out.println("<p>The requested page could not be found.</p>");
            out.println("</body>");
            out.println("</html>");
        } else if (statusCode == 500) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Error 500 - Internal Server Error</title>");
            out.println("<style>");
            out.println("body {");
            out.println("font-family: 'Arial', sans-serif;");
            out.println("background-color: #f4f4f4;");
            out.println("text-align: center;");
            out.println("margin: 50px;");
            out.println("}");
            out.println("h1 {");
            out.println("color: #d9534f;");
            out.println("}");
            out.println("p {");
            out.println("color: #333;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Error 500 - Internal Server Error</h1>");
            out.println("<p>There was an internal server error. Please try again later.</p>");
            out.println("</body>");
            out.println("</html>");
        } else {
            out.println("<html><body>");
            out.println("<h1>Error</h1>");
            out.println("<p>An error occurred.</p>");
            out.println("</body></html>");
        }
    }
}