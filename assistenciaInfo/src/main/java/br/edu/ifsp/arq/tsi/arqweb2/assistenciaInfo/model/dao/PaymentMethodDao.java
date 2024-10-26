package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.PaymentMethod;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentMethodDao {
    private DataSource dataSource;

    public PaymentMethodDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Boolean addPaymentMethod(PaymentMethod paymentMethod) {
        String sql = "insert into formapagamento (nome) values(?)";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, paymentMethod.getName());
            ps.executeUpdate();

            return true;
        }catch(SQLException e) {
            throw new RuntimeException("Erro durante a escrita no BD (forma de pagamento)", e);
        }
    }

    public List<PaymentMethod> getAllPaymentMethod() {
        String sql = "select * from formapagamento";
        List<PaymentMethod> paymentMethods = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    PaymentMethod paymentMethod = new PaymentMethod();
                    paymentMethod.setCodigo(rs.getInt("codigo"));
                    paymentMethod.setName(rs.getString("nome"));

                    paymentMethods.add(paymentMethod);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("Erro durante a consulta no BD", e);
        }
        return paymentMethods;
    }

    public Optional<PaymentMethod> getPaymentMethodById(int id) {
        String sql = "select * from formapagamento where codigo=?";
        try(Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    PaymentMethod paymentMethod = new PaymentMethod();
                    paymentMethod.setCodigo(rs.getInt("codigo"));
                    paymentMethod.setName(rs.getString("nome"));
                    return Optional.of(paymentMethod);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar método de pagamento no BD", e);
        }
        return Optional.empty();
    }

    public boolean deletePaymentMethod(PaymentMethod paymentMethod) {
        String sql = "delete from formapagamento where codigo=?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, paymentMethod.getCodigo());
            ps.executeUpdate();

            return true;
        }catch(SQLException e){
            throw new RuntimeException("Erro durante a consulta no BD", e);
        }
    }


}
