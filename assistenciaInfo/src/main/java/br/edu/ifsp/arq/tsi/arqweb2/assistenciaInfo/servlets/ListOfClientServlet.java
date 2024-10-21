package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.ClienteDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/listOfClient")
public class ListOfClientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListOfClientServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClienteDao clienteDao = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());
        List<Cliente> clientes = clienteDao.getAllClientes();
        request.setAttribute("clientes", clientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("service.jsp");
        dispatcher.forward(request, response);
    }
}
