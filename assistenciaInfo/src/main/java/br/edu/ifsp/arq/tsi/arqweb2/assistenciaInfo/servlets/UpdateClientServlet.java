package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Address;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Employee;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.ClienteDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.PasswordEncode;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/updateClient")
public class UpdateClientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateClientServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long codigo = Long.valueOf(request.getParameter("codigo"));
        ClienteDao clienteDao = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());
        Optional<Cliente> clienteOptional = clienteDao.getClienteById(codigo);

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            request.setAttribute("cliente", cliente);

            String url = "/updateClient.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        } else {
            response.sendRedirect("clientList?error=Cliente não encontrado");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/listOfClient";
        String codigoClient = request.getParameter("codigo");
        Long codigoCliente = (codigoClient != null && !codigoClient.isEmpty()) ? Long.parseLong(codigoClient) : 0L;
        String nome = request.getParameter("name");
        String email = request.getParameter("email");
        String telefone = request.getParameter("phone");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("password");
        String codigoParam = request.getParameter("addressCodigo");
        Long codigoAddress = (codigoParam != null && !codigoParam.isEmpty()) ? Long.parseLong(codigoParam) : 0L;
        String logradouro = request.getParameter("logradouro");
        String numero = request.getParameter("numero");
        String complemento = request.getParameter("complemento");
        String bairro = request.getParameter("bairro");
        String cep = request.getParameter("cep");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");

        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setCodigo(codigoCliente);
        clienteAtualizado.setNome(nome);
        clienteAtualizado.setEmail(email);
        clienteAtualizado.setTelefone(telefone);
        clienteAtualizado.setCpf(cpf);
        clienteAtualizado.setSenha(senha);

        Address endereco = new Address();
        endereco.setCodigo(codigoAddress);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setCep(cep);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        clienteAtualizado.setAddress(endereco);

        ClienteDao clienteDao = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());
        clienteDao.updateCliente(clienteAtualizado);
        boolean deuCerto = clienteDao.updateCliente(clienteAtualizado);

        if(deuCerto){
            response.sendRedirect("listOfClient");
        }else{
            request.getRequestDispatcher("updateClient.jsp").forward(request, response);
        }
    }
}
