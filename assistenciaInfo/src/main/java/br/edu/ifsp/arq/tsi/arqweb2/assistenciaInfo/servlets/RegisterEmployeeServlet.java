package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Employee;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.EmployeeDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.PasswordEncode;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registerEmployee")
public class RegisterEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterEmployeeServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("name");
        String email = request.getParameter("email");
        String senha = request.getParameter("password");

        Employee employee = new Employee();
        employee.setNome(nome);
        employee.setEmail(email);
        employee.setSenha(PasswordEncode.criptografarSenha(senha));

        RequestDispatcher dispatcher = null;

        EmployeeDao employeeDao = new EmployeeDao(DataSourceSearcher.getInstance().getDataSource());

        if(employeeDao.addEmployee(employee)){
            request.setAttribute("result", "registered");
            dispatcher = request.getRequestDispatcher("/registerEmployee.jsp");
        }else{
            request.setAttribute("result", "notRegistered");
            dispatcher = request.getRequestDispatcher("/registerEmployee.jsp");
        }
        dispatcher.forward(request, response);
    }
}
