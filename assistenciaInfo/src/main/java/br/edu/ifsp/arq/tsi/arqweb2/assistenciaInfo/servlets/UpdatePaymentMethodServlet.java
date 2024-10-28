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

@WebServlet("/updatePaymentMethod")
public class UpdatePaymentMethodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdatePaymentMethodServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));

        PaymentMethodDao paymentMethodDao = new PaymentMethodDao(DataSourceSearcher.getInstance().getDataSource());
        PaymentMethod paymentMethod = paymentMethodDao.getPaymentMethodByCode(codigo);

        request.setAttribute("payment", paymentMethod);

        RequestDispatcher dispatcher = request.getRequestDispatcher("updatePaymentMethod.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/addPaymentMethod.jsp";
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String name = request.getParameter("name");

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setCodigo(codigo);
        paymentMethod.setName(name);

        PaymentMethodDao paymentMethodDao = new PaymentMethodDao(DataSourceSearcher.getInstance().getDataSource());
        boolean isUpdated = paymentMethodDao.updatePaymentMethod(paymentMethod);

        if(isUpdated){
            request.setAttribute("result", "updated");
        }else{
            request.setAttribute("result", "notUpdated");
        }

        List<PaymentMethod> paymentMethods = paymentMethodDao.getAllPaymentMethod();
        request.setAttribute("paymentMethods", paymentMethods);

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
