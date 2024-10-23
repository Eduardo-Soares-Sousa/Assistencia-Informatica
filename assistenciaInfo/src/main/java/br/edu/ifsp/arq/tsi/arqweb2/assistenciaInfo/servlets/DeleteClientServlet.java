package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Address;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.ClienteDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.DataSourceSearcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/deleteClient")
public class DeleteClientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteClientServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/listOfClient";
        Long codigo = Long.parseLong(request.getParameter("codigo"));
        ClienteDao clienteDao = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());

        clienteDao.deleteClienteAndRelatedEntities(codigo);

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
