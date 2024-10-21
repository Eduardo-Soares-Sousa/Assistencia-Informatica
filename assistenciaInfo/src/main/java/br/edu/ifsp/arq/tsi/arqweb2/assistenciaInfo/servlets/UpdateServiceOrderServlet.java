package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.PaymentMethod;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.ServiceOrder;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Status;
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
        Long formaPagamentoCode = Long.parseLong(request.getParameter("formaPagamento"));
        PaymentMethod formaPagamento = PaymentMethod.fromCode(formaPagamentoCode);
        String observacao = request.getParameter("observacao");
        Status status = Status.valueOf(request.getParameter("status"));
        HttpSession session = request.getSession(false);

        Cliente cliente = (Cliente) session.getAttribute("cliente");
        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setCodigo(codigo);
        serviceOrder.setDescricao(descricao);
        serviceOrder.setDataEmissao(dataEmissao);
        serviceOrder.setDataFinalizacao(dataFinalizacao);
        serviceOrder.setValor(valor);
        serviceOrder.setPaymentMethod(formaPagamento);
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