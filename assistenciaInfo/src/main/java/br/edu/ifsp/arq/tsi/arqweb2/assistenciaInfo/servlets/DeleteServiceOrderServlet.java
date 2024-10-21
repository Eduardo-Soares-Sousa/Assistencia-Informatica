package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.ServiceOrderDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.DataSourceSearcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteServiceOrder")
public class DeleteServiceOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteServiceOrderServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/workOrderListManager";
        Long codigo = Long.parseLong(request.getParameter("codigo"));
        ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());
        serviceOrderDao.deleteService(serviceOrderDao.getServiceById(codigo));

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
