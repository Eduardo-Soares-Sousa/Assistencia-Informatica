package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.*;
import com.google.protobuf.Service;

import javax.sql.DataSource;
import java.sql.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceOrderDao {
    private DataSource dataSource;

    public ServiceOrderDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean saveOrder(ServiceOrder serviceOrder) {
        String sql = "insert into service (descricao, dataEmissao, dataFinalizacao, valor, formaPagamento, estado, observacao, cliente_id, funcionario_id) values (?,?,?,?,?,?,?,?,?)";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, serviceOrder.getDescricao());
            ps.setDate(2, Date.valueOf(serviceOrder.getDataEmissao()));
            ps.setDate(3, Date.valueOf(serviceOrder.getDataFinalizacao()));
            ps.setDouble(4, serviceOrder.getValor());
            ps.setLong(5, serviceOrder.getPaymentMethod().getCode());
            ps.setString(6, serviceOrder.getStatus().toString());
            ps.setString(7, serviceOrder.getObservacao());
            ps.setLong(8, serviceOrder.getCliente().getCodigo());
            ps.setLong(9, serviceOrder.getEmployee().getCodigo());
            ps.executeUpdate();

            return true;
        }catch(SQLException e){
            throw new RuntimeException("Erro durante a consulta no BD", e);
        }
    }

    public List<ServiceOrder> getServiceByCliente(Cliente cliente){
        String sql = "select * from service where cliente_id=?";
        List<ServiceOrder> services = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, cliente.getCodigo());
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    ServiceOrder serviceOrder = new ServiceOrder();
                    serviceOrder.setCodigo(rs.getLong(1));
                    serviceOrder.setDescricao(rs.getString(2));
                    serviceOrder.setDataEmissao(LocalDate.parse(rs.getDate(3).toString()));
                    serviceOrder.setDataFinalizacao(LocalDate.parse(rs.getDate(4).toString()));
                    serviceOrder.setValor(rs.getDouble(5));
                    serviceOrder.setPaymentMethod(PaymentMethod.fromCode(rs.getLong(6)));
                    serviceOrder.setStatus(Status.valueOf(rs.getString(7)));
                    serviceOrder.setObservacao(rs.getString(8));
                    serviceOrder.setCliente(cliente);

                    services.add(serviceOrder);
                }
            }
            return services;
        }catch(SQLException e){
            throw new RuntimeException("Erro durante a consulta no BD", e);
        }
    }

    public ServiceOrder getServiceById(Long id){
        String sql = "select * from service where codigo=?";
        ServiceOrder service = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    service = new ServiceOrder();
                    service.setCodigo(rs.getLong(1));
                    service.setDescricao(rs.getString(2));
                    service.setDataEmissao(LocalDate.parse(rs.getDate(3).toString()));
                    service.setDataFinalizacao(LocalDate.parse(rs.getDate(4).toString()));
                    service.setValor(rs.getDouble(5));
                    service.setPaymentMethod(PaymentMethod.fromCode(rs.getLong(6)));
                    service.setStatus(Status.valueOf(rs.getString(7)));
                    service.setObservacao(rs.getString(8));
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(rs.getLong(9));
                    service.setCliente(cliente);
                }
            }
            return service;
        }catch(SQLException e){
            throw new RuntimeException("Erro durante a consulta no BD", e);
        }
    }

    public boolean updateService(ServiceOrder serviceOrder) {
        String sql = "update service set "
                + "descricao=?,"
                + "dataEmissao=?,"
                + "dataFinalizacao=?,"
                + "valor=?,"
                + "formaPagamento=?,"
                + "estado=?,"
                + "observacao=?,"
                + "cliente_id=? "
                + "where codigo=?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, serviceOrder.getDescricao());
            ps.setDate(2, Date.valueOf(serviceOrder.getDataEmissao()));
            ps.setDate(3, Date.valueOf(serviceOrder.getDataFinalizacao()));
            ps.setDouble(4, serviceOrder.getValor());
            ps.setLong(5, serviceOrder.getPaymentMethod().getCode());
            ps.setString(6, serviceOrder.getStatus().name());
            ps.setString(7, serviceOrder.getObservacao());
            ps.setLong(8, serviceOrder.getCliente().getCodigo());
            ps.setLong(9, serviceOrder.getCodigo());
            ps.executeUpdate();

            return true;
        }catch(SQLException e){
            throw new RuntimeException("Erro durante a consulta no BD", e);
        }
    }

    public boolean deleteService(ServiceOrder serviceOrder){
        String sql = "delete from service where codigo=?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, serviceOrder.getCodigo());
            ps.executeUpdate();

            return true;
        }catch(SQLException e){
            throw new RuntimeException("Erro durante a consulta no BD", e);
        }
    }

    public List<ServiceOrder> getServiceOrder() {
        String sql = "SELECT s.*, c.nome AS cliente_nome FROM service s "
                    + "JOIN cliente c ON s.cliente_id = c.codigo ORDER BY s.dataEmissao";
        List<ServiceOrder> serviceOrders = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);){

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    ServiceOrder serviceOrder = new ServiceOrder();
                    serviceOrder.setCodigo(rs.getLong("codigo"));
                    serviceOrder.setDescricao(rs.getString("descricao"));
                    serviceOrder.setDataEmissao(rs.getDate("dataEmissao").toLocalDate());
                    serviceOrder.setDataFinalizacao(rs.getDate("dataFinalizacao").toLocalDate());
                    serviceOrder.setValor(rs.getDouble("valor"));
                    serviceOrder.setPaymentMethod(PaymentMethod.fromCode(rs.getLong("formaPagamento")));
                    serviceOrder.setStatus(Status.valueOf(rs.getString("estado")));
                    serviceOrder.setObservacao(rs.getString("observacao"));

                    Cliente cliente = new Cliente();
                    cliente.setCodigo(rs.getLong("cliente_id"));
                    cliente.setNome(rs.getString("cliente_nome"));
                    serviceOrder.setCliente(cliente);

                    serviceOrders.add(serviceOrder);
                }
            }

        }catch(SQLException e){
            throw new RuntimeException("Erro ao recuperar ordens de serviço do BD", e);
        }
        return serviceOrders;
    }

    public List<ServiceOrder> getServiceByFuncionario(Employee funcionario) {
        String sql = "select * from service where funcionario_id = ?";
        List<ServiceOrder> orders = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setLong(1, funcionario.getCodigo());

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    ServiceOrder order = new ServiceOrder();
                    order.setCodigo(rs.getLong("codigo"));
                    order.setDescricao(rs.getString("descricao"));
                    order.setDataEmissao(rs.getDate("dataEmissao").toLocalDate());
                    order.setDataFinalizacao(rs.getDate("dataFinalizacao").toLocalDate());
                    order.setValor(rs.getDouble("valor"));
                    order.setPaymentMethod(PaymentMethod.fromCode(rs.getLong("formaPagamento")));
                    order.setStatus(Status.valueOf(rs.getString("estado")));
                    order.setObservacao(rs.getString("observacao"));

                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar ordens de serviço do funcionário", e);
        }
        return orders;
    }
}
