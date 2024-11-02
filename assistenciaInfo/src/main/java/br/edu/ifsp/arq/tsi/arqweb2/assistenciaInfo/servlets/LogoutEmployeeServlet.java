package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logoutEmployee")
public class LogoutEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogoutEmployeeServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        session.invalidate();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/registerEmployee.jsp");
        dispatcher.forward(req, resp);
    }
}