package int202.prefinalint202.servlets;

import int202.prefinalint202.entities.Office;
import int202.prefinalint202.repositories.OfficeRepositoy;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OfficeServlet", value = "/044/office")
public class OfficeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String back = request.getParameter("back") == null ? "" : request.getParameter("back");
        if (back.equalsIgnoreCase("1")) {
            response.sendRedirect("../");
        } else if (session.getAttribute("currentUser") == null) {
            session.setAttribute("error", "please login first");
            response.sendRedirect("../044/login");
        } else {
            OfficeRepositoy officeRepositoy = new OfficeRepositoy();
            List<Office> officeList = officeRepositoy.findAll();
            request.setAttribute("officeList", officeList);
            request.getRequestDispatcher("../office.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
