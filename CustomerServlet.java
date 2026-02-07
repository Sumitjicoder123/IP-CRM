package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.CustomerDao;
import model.Customer;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {

    CustomerDao dao = new CustomerDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

       
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.deleteCustomer(id);
            response.sendRedirect("CustomerServlet");
            return;
        }

        
        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Customer c = dao.getCustomerById(id);
            request.setAttribute("customer", c);
            request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
            return;
        }

        
        List<Customer> list = dao.getAllCustomers();
        request.setAttribute("customers", list);
        request.getRequestDispatcher("customerList.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        
        if ("add".equals(action)) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            Customer c = new Customer(0, name, email, phone, address);
            dao.saveCustomer(c);
        }

        
        if ("update".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            Customer c = new Customer(id, name, email, phone, address);
            dao.updateCustomer(c);
        }


        response.sendRedirect("CustomerServlet");
    }
}
