package auca.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/teacher/*", "/admin/*", "/student/*", "/studentform.jsp", "/logout",
		"/UpdateStudentServlet","/DisplayStudentsServlet","/DeleteAcademicUnitServlet","/DeleteCourseDefinitionServlet",
		"/DeleteCourseServlet","/DeleteSemesterServlet","/DeleteStudentRegistrationServlet","/DeleteStudentServlet",
		"/DeleteTeacherServlet","/studenttableform.html","/AddStudentServlet","/AddAcademicUnitServlet","/AddCourseServlet",
		"/AddCourseDefinitionServlet","/AddSemesterServlet","/AddStudentServlet","/AddStudentRegistrationServlet",
		"/AddTeacherServlet",
        "/academicunitform.html","/UpdateAcademicUnitServlet", "/DisplayAcademicUnitsServlet",
        "/semesterform.html","/UpdateSemesterServlet", "/DisplaySemesterServlet",
        "/courseform.html","/UpdateCourseServlet", "/DisplayCoursesServlet",
        "/coursedefinitionform.html","/UpdateCourseDefinitionServlet", "/DisplayCourseDefinitionsServlet",
        "/studentrregistrationform.html","/UpdateStudentRegistrationServlet", "/DisplayStudentRegistrationsServlet",
        "/teacherform.html","/UpdateTeacherServlet", "/DisplayTeacherServlet"})
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String path = request.getRequestURI().substring(request.getContextPath().length());

        if (session == null || session.getAttribute("user") == null) {
            // User is not logged in, redirect to login page
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // User is logged in, check role-based access
        String role = (String) session.getAttribute("role");

        if (path.startsWith("/teacher/") && "teacher".equals(role)) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.startsWith("/admin/") && "admin".equals(role)) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.startsWith("/student/") && "student".equals(role)) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/studentform.jsp") && ("student".equals(role) || "admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/logout")) {
            filterChain.doFilter(request, response);
        
        } else if (path.equals("/UpdateStudentServlet") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/DisplayStudentsServlet") && ("admin".equals(role))) {
            setCacheControlHeaders(response);    
        } else if (path.equals("/academicunitform.html") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/UpdateAcademicUnitServlet") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/DisplayAcademicUnitsServlet") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/semesterform.html") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/UpdateSemesterServlet") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/DisplaySemesterServlet") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/courseform.html") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/UpdateCourseServlet") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/DisplayCoursesServlet") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/coursedefinitionform.html") && ("admin".equals(role))) {
            setCacheControlHeaders(response);
            filterChain.doFilter(request, response);
        } else if (path.equals("/UpdateCourseDefinitionServlet") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/DisplayCourseDefinitionsServlet") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/studentrregistrationform.html") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/UpdateStudentRegistrationServlet") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/DisplayStudentRegistrationsServlet") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/teacherform.html") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/UpdateTeacherServlet") && ("admin".equals(role))) {
            setCacheControlHeaders(response); 
            filterChain.doFilter(request, response);
        } else if (path.equals("/DisplayTeacherServlet") && ("admin".equals(role))) {
            setCacheControlHeaders(response);
            filterChain.doFilter(request, response);
        } else if (path.equals("/DeleteAcademicUnitServlet") && ("admin".equals(role))) {
        	 setCacheControlHeaders(response);
             filterChain.doFilter(request, response);
        	}
        	else if (path.equals("/DeleteCourseDefinitionServlet") && ("admin".equals(role))) {
        		 setCacheControlHeaders(response);
                 filterChain.doFilter(request, response);
        	}
        	else if (path.equals("/DeleteCourseServlet") && ("admin".equals(role))) {
        		 setCacheControlHeaders(response);
                 filterChain.doFilter(request, response);
        	}
        	else if (path.equals("/DeleteSemesterServlet") && ("admin".equals(role))) {
        		 setCacheControlHeaders(response);
                 filterChain.doFilter(request, response);
        	}
        	else if (path.equals("/DeleteStudentRegistrationServlet") && ("admin".equals(role))) {
        		 setCacheControlHeaders(response);
                 filterChain.doFilter(request, response);
        	}
        	else if (path.equals("/DeleteStudentServlet") && ("admin".equals(role))) {
        		 setCacheControlHeaders(response);
                 filterChain.doFilter(request, response);
        	}
        	else if (path.equals("/DeleteTeacherServlet") && ("admin".equals(role))) {
        		 setCacheControlHeaders(response);
                 filterChain.doFilter(request, response);
        	}
        	else if (path.equals("/studenttableform.html") && ("admin".equals(role))) {
       		 setCacheControlHeaders(response);
                filterChain.doFilter(request, response);
        	}
        	else if (path.equals("/studenttableform.html") && ("admin".equals(role))) {
        	    setCacheControlHeaders(response);
        	    filterChain.doFilter(request, response);
        	}

        	else if (path.equals("/AddStudentServlet") && ("admin".equals(role))) {
        	    setCacheControlHeaders(response);
        	    filterChain.doFilter(request, response);
        	}

        	else if (path.equals("/AddAcademicUnitServlet") && ("admin".equals(role))) {
        	    setCacheControlHeaders(response);
        	    filterChain.doFilter(request, response);
        	}

        	else if (path.equals("/AddCourseServlet") && ("admin".equals(role))) {
        	    setCacheControlHeaders(response);
        	    filterChain.doFilter(request, response);
        	}

        	else if (path.equals("/AddCourseDefinitionServlet") && ("admin".equals(role))) {
        	    setCacheControlHeaders(response);
        	    filterChain.doFilter(request, response);
        	}

        	else if (path.equals("/AddSemesterServlet") && ("admin".equals(role))) {
        	    setCacheControlHeaders(response);
        	    filterChain.doFilter(request, response);
        	}

        	else if (path.equals("/AddStudentRegistrationServlet") && ("admin".equals(role))) {
        	    setCacheControlHeaders(response);
        	    filterChain.doFilter(request, response);
        	}

        	else if (path.equals("/AddTeacherServlet") && ("admin".equals(role))) {
        	    setCacheControlHeaders(response);
        	    filterChain.doFilter(request, response);
        	}
        	else {
            // Access denied, redirect to login page
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    private void setCacheControlHeaders(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
}