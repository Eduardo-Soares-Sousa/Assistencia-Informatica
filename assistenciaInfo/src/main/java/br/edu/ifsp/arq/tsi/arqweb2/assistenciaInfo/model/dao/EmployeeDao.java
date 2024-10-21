package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Employee;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.DataSourceSearcher;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.PasswordEncode;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class EmployeeDao {
    private DataSource dataSource;

    public EmployeeDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<Employee> getEmployeeByEmailAndPassword(String email, String senha) {
        String senhaCriptografada = PasswordEncode.criptografarSenha(senha);
        String sql = "select codigo,nome,email from funcionario where email=? and senha=?";
        Optional<Employee> optional = Optional.empty();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, email);
            ps.setString(2, senhaCriptografada);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Employee employee = new Employee();
                    employee.setCodigo(rs.getLong(1));
                    employee.setNome(rs.getString(2));
                    employee.setEmail(rs.getString(3));

                    optional = Optional.of(employee);
                }
            }
            return optional;
        }catch(SQLException e) {
            throw new RuntimeException("Erro durante a consulta no BD", e);
        }
    }

    public Optional<Employee> getEmployeeByEmail(String email) {
        String sql = "select codigo,nome,email from funcionario where email=?";
        Optional<Employee> optional = Optional.empty();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, email);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Employee employee = new Employee();
                    employee.setCodigo(rs.getLong(1));
                    employee.setNome(rs.getString(2));
                    employee.setEmail(rs.getString(3));

                    optional = Optional.of(employee);
                }
            }
        }catch(SQLException e) {
            throw new RuntimeException("Erro durante a consulta no BD", e);
        }
        return optional;
    }

    public Boolean addEmployee(Employee employee) {
        Optional<Employee> optional = getEmployeeByEmail(employee.getEmail());
        if(optional.isPresent()) {
            return false;
        }

        String sql = "insert into funcionario (nome, email, senha) values (?,?,?)";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, employee.getNome());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getSenha());

            ps.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException("Erro durante a escrita no BD (funcionario)", e);
        }
        return true;
    }
}
