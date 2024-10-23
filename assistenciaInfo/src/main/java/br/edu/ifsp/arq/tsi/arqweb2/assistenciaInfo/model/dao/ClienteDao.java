package br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import br.edu.ifsp.arq.tsi.arqweb2.assistenciaInfo.model.*;
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

	public Cliente getClientById(Long id) {
		String sql = "select * from cliente where codigo=?";
		Cliente cliente = null;
		Address address = null;
		try(Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setLong(1, id);
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()){
					cliente = new Cliente();
					cliente.setCodigo(rs.getLong(1));
					cliente.setNome(rs.getString(2));
					cliente.setEmail(rs.getString(3));
					cliente.setTelefone(rs.getString(4));
					cliente.setCpf(rs.getString(5));

					Address endereco = new Address();
					endereco.setLogradouro(rs.getString(6));
					endereco.setNumero(rs.getString(7));
					endereco.setComplemento(rs.getString(8));
					endereco.setBairro(rs.getString(9));
					endereco.setCep(rs.getString(10));
					endereco.setCidade(rs.getString(11));
					endereco.setEstado(rs.getString(12));

					cliente.setAddress(endereco);
				}
			}
			return cliente;
		}catch(SQLException e){
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}
	}

	public Optional<Cliente> getClienteById(long id) {
		String sql = "SELECT c.codigo, c.nome, c.email, c.telefone, c.cpf, e.logradouro, e.numero, e.complemento, e.bairro, e.cep, e.cidade, e.estado "
				+ "FROM cliente c JOIN endereco e ON c.endereco_id = e.codigo WHERE c.codigo=?";
		Optional<Cliente> optional = Optional.empty();
		try(Connection connection = dataSource.getConnection();
			 PreparedStatement ps = connection.prepareStatement(sql)){
			ps.setLong(1, id);

			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()){
					Cliente cliente = new Cliente();
					cliente.setCodigo(rs.getLong(1));
					cliente.setNome(rs.getString(2));
					cliente.setEmail(rs.getString(3));
					cliente.setTelefone(rs.getString(4));
					cliente.setCpf(rs.getString(5));

					Address endereco = new Address();
					endereco.setLogradouro(rs.getString(6));
					endereco.setNumero(rs.getString(7));
					endereco.setComplemento(rs.getString(8));
					endereco.setBairro(rs.getString(9));
					endereco.setCep(rs.getString(10));
					endereco.setCidade(rs.getString(11));
					endereco.setEstado(rs.getString(12));

					cliente.setAddress(endereco);
					optional = Optional.of(cliente);
				}
			}
		}catch(SQLException e){
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

	public List<Cliente> getAllClientesByName() {
		List<Cliente> clientes = new ArrayList<>();
		String sql = "select codigo, nome from cliente";

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
			throw new RuntimeException("Erro ao consultar clientes", e);
		}
		return clientes;
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

	public boolean updateCliente(Cliente cliente) {
		String sql = "UPDATE cliente c "
				+ "JOIN endereco e ON c.endereco_id = e.codigo "
				+ "SET c.nome = ?, c.email = ?, c.telefone = ?, c.cpf = ?, c.senha = ?, "
				+ "e.logradouro = ?, e.numero = ?, e.complemento = ?, e.bairro = ?, e.cep = ?, e.cidade = ?, e.estado = ? "
				+ "WHERE c.codigo = ?";

		try(Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql)){

			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getEmail());
			ps.setString(3, cliente.getTelefone());
			ps.setString(4, cliente.getCpf());
			ps.setString(5, PasswordEncode.criptografarSenha(cliente.getSenha()));

			ps.setString(6, cliente.getAddress().getLogradouro());
			ps.setString(7, cliente.getAddress().getNumero());
			ps.setString(8, cliente.getAddress().getComplemento());
			ps.setString(9, cliente.getAddress().getBairro());
			ps.setString(10, cliente.getAddress().getCep());
			ps.setString(11, cliente.getAddress().getCidade());
			ps.setString(12, cliente.getAddress().getEstado());
			ps.setLong(13, cliente.getCodigo());

			ps.executeUpdate();

			return true;
		}catch(SQLException e){
			throw new RuntimeException("Erro durante a consulta no BD", e);
		}
	}

	public void deleteClienteAndRelatedEntities(Long codigo) {
		String sqlEnderecoId = "SELECT endereco_id FROM cliente WHERE codigo = ?";
		String sqlDeleteService = "DELETE FROM service WHERE cliente_id = ?";
		String sqlDeleteCliente = "DELETE FROM cliente WHERE codigo = ?";
		String sqlDeleteEndereco = "DELETE FROM endereco WHERE codigo = ?";

		try (Connection connection = dataSource.getConnection()) {
			connection.setAutoCommit(false);

			try(PreparedStatement psEnderecoId = connection.prepareStatement(sqlEnderecoId);
				 PreparedStatement psDeleteService = connection.prepareStatement(sqlDeleteService);
				 PreparedStatement psDeleteCliente = connection.prepareStatement(sqlDeleteCliente);
				 PreparedStatement psDeleteEndereco = connection.prepareStatement(sqlDeleteEndereco)){

				psEnderecoId.setLong(1, codigo);
				ResultSet rs = psEnderecoId.executeQuery();

				Long enderecoId = null;
				if(rs.next()){
					enderecoId = rs.getLong(1);
				}

				psDeleteService.setLong(1, codigo);
				psDeleteService.executeUpdate();

				psDeleteCliente.setLong(1, codigo);
				psDeleteCliente.executeUpdate();

				if(enderecoId != null){
					psDeleteEndereco.setLong(1, enderecoId);
					psDeleteEndereco.executeUpdate();
				}
				connection.commit();
			}catch(SQLException e){
				connection.rollback();
				throw new RuntimeException("Erro durante a exclusão do cliente e suas entidades relacionadas", e);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao obter conexão com o banco de dados", e);
		}
	}
}
