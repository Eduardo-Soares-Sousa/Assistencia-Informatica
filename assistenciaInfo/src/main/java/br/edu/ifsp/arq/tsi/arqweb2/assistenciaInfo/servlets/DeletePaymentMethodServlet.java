package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.PaymentMethod;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao.PaymentMethodDao;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.DataSourceSearcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/deletePaymentMethod")
public class DeletePaymentMethodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeletePaymentMethodServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/paymentMethod";
        PaymentMethodDao paymentMethodDao = new PaymentMethodDao(DataSourceSearcher.getInstance().getDataSource());
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        Optional<PaymentMethod> paymentMethodOpt = paymentMethodDao.getPaymentMethodById(codigo);

        if(paymentMethodOpt.isPresent()){
            paymentMethodDao.deletePaymentMethod(paymentMethodOpt.get());
        }

        getServletContext().getRequestDispatcher(url).forward(request, response);
    }
}
