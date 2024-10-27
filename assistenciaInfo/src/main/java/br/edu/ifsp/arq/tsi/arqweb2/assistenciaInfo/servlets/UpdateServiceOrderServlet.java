package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.*;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.ClienteDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.PaymentMethodDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.ServiceOrderDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.DataSourceSearcher;
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

@WebServlet("/updateServiceOrder")
public class UpdateServiceOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateServiceOrderServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long codigo = Long.parseLong(request.getParameter("codigo"));
        ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());
        ServiceOrder serviceOrder = serviceOrderDao.getServiceById(codigo);
        request.setAttribute("service", serviceOrder);

        ClienteDao clienteDao = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());
        List<Cliente> clientes = clienteDao.getAllClientes();
        request.setAttribute("clientes", clientes);

        PaymentMethodDao paymentMethodDao = new PaymentMethodDao(DataSourceSearcher.getInstance().getDataSource());
        List<PaymentMethod> paymentMethods = paymentMethodDao.getAllPaymentMethod();
        request.setAttribute("paymentMethods", paymentMethods);

        request.setAttribute("StatusValues", Status.values());

        String url = "/updateServiceOrder.jsp";
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/workOrderListManager";
        String codigoParam = request.getParameter("codigo");
        Long codigo = (codigoParam != null && !codigoParam.isEmpty()) ? Long.parseLong(codigoParam) : 0L;
        String descricao = request.getParameter("descricao");
        LocalDate dataEmissao = LocalDate.parse(request.getParameter("dataEmissao"));
        LocalDate dataFinalizacao = LocalDate.parse(request.getParameter("dataFinalizacao"));
        Double valor = Double.parseDouble(request.getParameter("valor"));
        Integer formaPagamentoId = Integer.parseInt(request.getParameter("formaPagamento"));
        Long clienteId = Long.parseLong(request.getParameter("clienteId"));
        String observacao = request.getParameter("observacao");
        Status status = Status.valueOf(request.getParameter("status"));
        HttpSession session = request.getSession(false);

        Employee employee = (Employee) session.getAttribute("employee");

        ClienteDao clienteDao = new ClienteDao(DataSourceSearcher.getInstance().getDataSource());
        clienteDao.getClienteById(clienteId);
        Optional<Cliente> optional = clienteDao.getClienteById(clienteId);
        Cliente cliente = optional.get();

        PaymentMethodDao paymentMethodDao = new PaymentMethodDao(DataSourceSearcher.getInstance().getDataSource());
        Optional<PaymentMethod> optionalPaymentMethod = paymentMethodDao.getPaymentMethodById(formaPagamentoId);
        PaymentMethod paymentMethod = optionalPaymentMethod.get();

        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setCodigo(codigo);
        serviceOrder.setDescricao(descricao);
        serviceOrder.setDataEmissao(dataEmissao);
        serviceOrder.setDataFinalizacao(dataFinalizacao);
        serviceOrder.setValor(valor);
        serviceOrder.setPaymentMethod(paymentMethod);
        serviceOrder.setObservacao(observacao);
        serviceOrder.setStatus(status);
        serviceOrder.setCliente(cliente);

        ServiceOrderDao serviceOrderDao = new ServiceOrderDao(DataSourceSearcher.getInstance().getDataSource());

        if(codigo != 0L){
            serviceOrderDao.updateService(serviceOrder);
        }else{
            serviceOrderDao.saveOrder(serviceOrder);
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
