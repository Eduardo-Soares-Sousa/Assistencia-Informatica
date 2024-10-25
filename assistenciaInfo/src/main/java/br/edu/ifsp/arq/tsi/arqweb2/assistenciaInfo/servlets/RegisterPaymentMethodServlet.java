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

@WebServlet("/registerPaymentMethod")
public class RegisterPaymentMethodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterPaymentMethodServlet() {
        super();
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
            dispatcher = request.getRequestDispatcher("/addPaymentMethod.jsp");
        }else{
            request.setAttribute("result", "notRegistered");
            dispatcher = request.getRequestDispatcher("/addPaymentMethod.jsp");
        }
        dispatcher.forward(request, response);
    }
}
