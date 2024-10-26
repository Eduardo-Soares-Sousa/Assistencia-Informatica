package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.PaymentMethod;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.PaymentMethodDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.DataSourceSearcher;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/paymentMethod")
public class PaymentMethodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PaymentMethodServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PaymentMethodDao paymentMethodDao = new PaymentMethodDao(DataSourceSearcher.getInstance().getDataSource());
        List<PaymentMethod> paymentMethods = paymentMethodDao.getAllPaymentMethod();

        request.setAttribute("paymentMethods", paymentMethods);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addPaymentMethod.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String metodo = request.getParameter("method");

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setName(metodo);

        RequestDispatcher dispatcher = null;

        PaymentMethodDao paymentMethodDao = new PaymentMethodDao(DataSourceSearcher.getInstance().getDataSource());

        if(paymentMethodDao.addPaymentMethod(paymentMethod)){
            request.setAttribute("result", "registered");
        }else{
            request.setAttribute("result", "notRegistered");
        }

        response.sendRedirect(request.getContextPath() + "/paymentMethod");
    }
}
