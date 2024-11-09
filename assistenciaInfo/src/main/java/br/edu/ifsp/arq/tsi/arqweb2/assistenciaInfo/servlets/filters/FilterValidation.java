package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.servlets.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/clientList", "/paymentMethod", "/deletePaymentMethod",
        "/serviceOrder", "/workOrderListManager", "/registerClient", "/homeSession"},
        filterName = "Authorization")
public class FilterValidation implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession(false);

        if(session == null || session.getAttribute("funcionarioLogado") == null) {
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/registerEmployee.jsp");
        }else{
            chain.doFilter(request, response);
        }
    }
}
