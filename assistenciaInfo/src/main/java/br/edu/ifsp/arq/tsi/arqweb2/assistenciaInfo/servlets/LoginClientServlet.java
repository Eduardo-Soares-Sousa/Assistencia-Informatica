package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import java.util.Optional;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.ClienteDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.DataSourceSearcher;

@WebServlet("/loginClient")
public class LoginClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginClientServlet() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		String email = request.getParameter("email");
		String senha = request.getParameter("password");
		
		ClienteDao clienteDao = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());
		Optional<Cliente> optional = clienteDao.getClienteByEmailAndPassword(email, senha);
		if(optional.isPresent()) {
			Cliente cliente = optional.get();
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(600);
			session.setAttribute("cliente", cliente);
			url = "/homeSession";
		}else {
			request.setAttribute("result", "loginError");
			url = "/home.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
