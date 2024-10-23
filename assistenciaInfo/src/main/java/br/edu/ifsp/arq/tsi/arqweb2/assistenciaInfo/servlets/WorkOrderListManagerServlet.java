package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Employee;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.ServiceOrderDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/workOrderListManager")
public class WorkOrderListManagerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public WorkOrderListManagerServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = (Employee) request.getSession().getAttribute("funcionarioLogado");

        if(employee != null){
            ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());
            List<ServiceOrder> serviceOrders = serviceOrderDao.getServiceOrder();
            request.setAttribute("serviceOrders", serviceOrders);
        }else{
            ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());
            List<ServiceOrder> serviceOrders = serviceOrderDao.getServiceOrder();
            request.setAttribute("serviceOrders", serviceOrders);
        }

        String url = "/listOfWorkOrders.jsp";
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
