package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Address;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.ClienteDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.PasswordEncode;

@WebServlet("/registerClient")
public class RegisterClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterClientServlet() {
        super();
    }



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("name");
		String email = request.getParameter("email");
		String telefone = request.getParameter("phone");
		String cpf = request.getParameter("cpf");
		String senha = request.getParameter("password");

		String logradouro = request.getParameter("logradouro");
		String numero = request.getParameter("numero");
		String complemento = request.getParameter("complemento");
		String bairro = request.getParameter("bairro");
		String cep = request.getParameter("cep");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("estado");

		Address address = new Address();
		address.setLogradouro(logradouro);
		address.setNumero(numero);
		address.setComplemento(complemento);
		address.setBairro(bairro);
		address.setCep(cep);
		address.setCidade(cidade);
		address.setEstado(estado);

		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setEmail(email);
		cliente.setTelefone(telefone);
		cliente.setCpf(cpf);
		cliente.setSenha(PasswordEncode.criptografarSenha(senha));
		cliente.setAddress(address);

		RequestDispatcher dispatcher = null;
		
		ClienteDao clienteDao = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());
		
		if(clienteDao.addCliente(cliente)) {
			request.setAttribute("result", "registered");
			dispatcher = request.getRequestDispatcher("/home.jsp");
		}else{
			request.setAttribute("result", "notRegistered");
			dispatcher = request.getRequestDispatcher("/home.jsp");
		}
		dispatcher.forward(request, response);
	}

}
