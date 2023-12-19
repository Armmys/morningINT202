package int202.prefinalint202.filters;

import int202.prefinalint202.entities.Customer;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        Customer currentUser = (Customer) session.getAttribute("currentUser");
        if (currentUser == null) {
            session.setAttribute("error", "please login first");
            ((HttpServletResponse) response).sendRedirect("../044/login");
        } else {
            chain.doFilter(request, response);
        }
    }
}
