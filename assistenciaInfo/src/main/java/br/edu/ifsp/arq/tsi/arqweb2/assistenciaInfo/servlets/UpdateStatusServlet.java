package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.ServiceOrderDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.DataSourceSearcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateStatus")
public class UpdateStatusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateStatusServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long codigo = Long.parseLong(request.getParameter("codigo"));
        int statusCode = Integer.parseInt(request.getParameter("status"));

        ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());

        if (serviceOrderDao.updateServiceOrderStatus(codigo, statusCode)) {
            getServletContext().getRequestDispatcher("/workOrderListManager").forward(request, response);
        } else {
            request.setAttribute("result", "registered");
        }
    }
}
