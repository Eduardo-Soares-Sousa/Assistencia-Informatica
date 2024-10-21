package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.Cliente;
import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.utils.PasswordEncode;

public class ClienteDao {
	private DataSource dataSource;
	
	public ClienteDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Optional<Cliente> getClienteByEmailAndPassword(String email, String senha){
		String senhaCriptografada = PasswordEncode.criptografarSenha(senha);
		String sql = "select codigo,nome,email,telefone,cpf from cliente where email=? and senha=?";
		Optional<Cliente> optional = Optional.empty();

		try(Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, email);
			ps.setString(2, senhaCriptografada);

			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCodigo(rs.getLong(1));
					cliente.setNome(rs.getString(2));
					cliente.setEmail(rs.getString(3));
					cliente.setTelefone(rs.getString(4));
					cliente.setCpf(rs.getString(5));

					optional = Optional.of(cliente);
				}
			}
			return optional;
		}catch(SQLException e) {
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}
	}

	public Optional<Cliente> getClienteById(long id) {
		String sql = "select codigo,nome from cliente where codigo=?";
		Optional<Cliente> optional = Optional.empty();
		try(Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setLong(1, id);

			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()){
					Cliente cliente = new Cliente();
					cliente.setCodigo(rs.getLong(1));
					cliente.setNome(rs.getString(2));
					optional = Optional.of(cliente);
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}
		return optional;
	}

	public Optional<Cliente> getClienteByEmail(String email){
		String sql = "select codigo,nome,email,telefone,cpf from cliente where email=?";
		Optional<Cliente> optional = Optional.empty();
		try(Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setString(1, email);
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()){
					Cliente cliente = new Cliente();
					cliente.setCodigo(rs.getLong(1));
					cliente.setNome(rs.getString(2));
					cliente.setEmail(rs.getString(3));
					cliente.setTelefone(rs.getString(4));
					cliente.setCpf(rs.getString(5));
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}
		return optional;
	}

	public List<Cliente> getAllClientes() {
		String sql = "select codigo,nome,email,telefone,cpf from cliente";
		List<Cliente> clientes = new ArrayList<>();

		try (Connection connection = dataSource.getConnection();
			 PreparedStatement ps = connection.prepareStatement(sql)){

			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()){
					Cliente cliente = new Cliente();
					cliente.setCodigo(rs.getLong("codigo"));
					cliente.setNome(rs.getString("nome"));
					cliente.setEmail(rs.getString("email"));
					cliente.setTelefone(rs.getString("telefone"));
					cliente.setCpf(rs.getString("cpf"));

					clientes.add(cliente);
				}
			}
		}catch (SQLException e){
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}
		return clientes;
	}

	public List<Cliente> getAllClientesByNameAndId() {
		List<Cliente> clientes = new ArrayList<>();
		String sql = "select codigo,nome from cliente where codigo=?";

		try(Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql)){

			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()){
					Cliente cliente = new Cliente();
					cliente.setCodigo(rs.getLong("codigo"));
					cliente.setNome(rs.getString("nome"));

					clientes.add(cliente);
				}
			}
		}catch(SQLException e){
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}
		return clientes;
	}

	public Boolean addCliente(Cliente cliente) {
		Optional<Cliente> optional = getClienteByEmail(cliente.getEmail());
		if(optional.isPresent()) {
			return false;
		}

		Long enderecoId = null;
		String sqlEndereco = "insert into endereco (logradouro, numero, complemento, bairro, cep, cidade, estado) values (?,?,?,?,?,?,?)";
		try(Connection connection = dataSource.getConnection();
			PreparedStatement psEndereco = connection.prepareStatement(sqlEndereco, Statement.RETURN_GENERATED_KEYS)){
			psEndereco.setString(1, cliente.getAddress().getLogradouro());
			psEndereco.setString(2, cliente.getAddress().getNumero());
			psEndereco.setString(3, cliente.getAddress().getComplemento());
			psEndereco.setString(4, cliente.getAddress().getBairro());
			psEndereco.setString(5, cliente.getAddress().getCep());
			psEndereco.setString(6, cliente.getAddress().getCidade());
			psEndereco.setString(7, cliente.getAddress().getEstado());
			psEndereco.executeUpdate();

			try(ResultSet rs = psEndereco.getGeneratedKeys()){
				if(rs.next()){
					enderecoId = rs.getLong(1);
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException("Erro durante a escrita no BD (endereco)", e);
		}


		String sql = "insert into cliente (nome, email, telefone, cpf, senha, endereco_id) values (?,?,?,?,?,?)";
		try(Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getEmail());
			ps.setString(3, cliente.getTelefone());
			ps.setString(4, cliente.getCpf());
			ps.setString(5, cliente.getSenha());
			ps.setLong(6, enderecoId);

			ps.executeUpdate();
		}catch(SQLException e) {
			throw new RuntimeException("Erro durante a escrita no BD (cliente)", e);
		}
		return true;
	}
}
