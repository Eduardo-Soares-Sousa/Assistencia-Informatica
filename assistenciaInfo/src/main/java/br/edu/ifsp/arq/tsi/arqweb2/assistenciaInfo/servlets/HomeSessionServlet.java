package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Employee;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Cliente;

@WebServlet("/homeSession")
public class HomeSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomeSessionServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		HttpSession session = request.getSession(false);
		
		Employee employee = (Employee) session.getAttribute("employee");
		request.setAttribute("result", "loginSucess");
		url = "/home.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
