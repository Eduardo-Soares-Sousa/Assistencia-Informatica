package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Employee;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.EmployeeDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/loginEmployee")
public class LoginEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginEmployeeServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        String email = request.getParameter("email");
        String senha = request.getParameter("password");

        EmployeeDao employeeDao = new EmployeeDao(DataSourceSearcher.getInstance().getDataSource());
        Optional<Employee> optional = employeeDao.getEmployeeByEmailAndPassword(email, senha);
        if(optional.isPresent()){
            Employee employee = optional.get();
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(600);
            session.setAttribute("funcionarioLogado", employee);
            url = "/homeSession";
        }else{
            request.setAttribute("result", "loginError");
            url = "/registerEmployee.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
