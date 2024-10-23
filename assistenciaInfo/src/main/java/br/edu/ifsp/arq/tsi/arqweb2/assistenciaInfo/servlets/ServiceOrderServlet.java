package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.*;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.ClienteDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.ServiceOrderDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebServlet("/serviceOrder")
public class ServiceOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServiceOrderServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClienteDao clienteDao = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());
        List<Cliente> clientes = clienteDao.getAllClientesByName();
        request.setAttribute("clientes", clientes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("service.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        String codigoParam = request.getParameter("codigo");
        Long codigo = (codigoParam != null && !codigoParam.isEmpty()) ? Long.parseLong(codigoParam) : 0L;
        String descricao = request.getParameter("descricao");
        LocalDate dataEmissao = LocalDate.parse(request.getParameter("dataEmissao"));
        LocalDate dataFinalizacao = LocalDate.parse(request.getParameter("dataFinalizacao"));
        Double valor = Double.parseDouble(request.getParameter("valor"));
        Long formaPagamentoCode = Long.parseLong(request.getParameter("formaPagamento"));
        PaymentMethod formaPagamento = PaymentMethod.fromCode(formaPagamentoCode);
        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        String observacao = request.getParameter("observacao");
        Status status = Status.valueOf(request.getParameter("status"));
        HttpSession session = request.getSession(false);

        Employee employee = (Employee) session.getAttribute("funcionarioLogado");

        ClienteDao clienteDao = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());
        clienteDao.getClienteById(clienteId);
        Optional<Cliente> optional = clienteDao.getClienteById(clienteId);
        Cliente cliente = optional.get();

        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setDescricao(descricao);
        serviceOrder.setDataEmissao(dataEmissao);
        serviceOrder.setDataFinalizacao(dataFinalizacao);
        serviceOrder.setValor(valor);
        serviceOrder.setPaymentMethod(formaPagamento);
        serviceOrder.setCliente(cliente);
        serviceOrder.setObservacao(observacao);
        serviceOrder.setStatus(status);
        serviceOrder.setEmployee(employee);

        ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());

        if(codigo == 0){
            if(serviceOrderDao.saveOrder(serviceOrder)){
                request.setAttribute("result", "registered");
            }
        }else{
            serviceOrder.setCodigo(codigo);
            if(serviceOrderDao.updateService(serviceOrder)){
                request.setAttribute("result", "registered");
            }
        }

        List<ServiceOrder> serviceOrders = serviceOrderDao.getServiceOrder();
        request.setAttribute("serviceOrders", serviceOrders);

        url = "/listOfWorkOrders.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
