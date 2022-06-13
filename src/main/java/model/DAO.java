package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	// Dados da conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/produto?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "14551455";

	// Conectar
	private Connection conectar() {
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// CRUD - CREATE insere dados no banco de dados
	public void inserirProduto(JavaBeans produto) {
		// Referência para a String create
		// insert into produto (codigo, nome, categoria, valor, quantidade)
		// values (123,'Produto','Categoria',45.6,789);
		String create = "insert into produto (codigo, nome, categoria, valor, quantidade) values (?,?,?,?,?);";
		try {
			// Abrir a conexão com o bd
			Connection con = conectar();
			// Preparar a query
			PreparedStatement pst = con.prepareStatement(create);
			// Inserir variáveis em ?
			pst.setString(1, produto.getCodigo());
			pst.setString(2, produto.getNome());
			pst.setString(3, produto.getCategoria());
			pst.setString(4, produto.getValor());
			pst.setString(5, produto.getQuantidade());
			// Executar a query
			pst.executeUpdate();
			// Encerrar a conexão com o bd
			con.close();

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Erro em: CRUD - CREATE");
		}

	}

	// CRUD - READ recupera os valores contidos no banco de dados
	public ArrayList<JavaBeans> listarProdutos() {
		ArrayList<JavaBeans> produtos = new ArrayList<>();
		String read = "select * from produto order by nome;";
		try {
			// Abrir a conexão com o bd
			Connection con = conectar();
			// Preparar a query
			PreparedStatement pst = con.prepareStatement(read);
			// Executa a query e armazena na variável rs
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String codigo = rs.getString(2);
				String nome = rs.getString(3);
				String categoria = rs.getString(4);
				String valor = rs.getString(5);
				String quantidade = rs.getString(6);
				// Armazenar os valores no ArrayList
				produtos.add(new JavaBeans(id, codigo, nome, categoria, valor, quantidade));
			}
			con.close();
			return produtos;

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Erro em: CRUD - READ");
			return null;
		}

	}

	// CRUD - UPDATE seleciona um produto
	public void selecionarProduto(JavaBeans produto) {
		String select = "select * from produto where id = ?;";
		try {
			// Abrir a conexão com o bd
			Connection con = conectar();
			// Preparar a query
			PreparedStatement pst = con.prepareStatement(select);
			// Substitui ? pelo id do produto selecionado
			pst.setString(1, produto.getId());
			// Executa a query e armazena na variável rs
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				produto.setId(rs.getString(1));
				produto.setCodigo(rs.getString(2));
				produto.setNome(rs.getString(3));
				produto.setCategoria(rs.getString(4));
				produto.setValor(rs.getString(5));
				produto.setQuantidade(rs.getString(6));
			}
			con.close();

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Erro em: CRUD - UPDATE - Selecionar");

		}
	}

	// CRUD - UPDATE edita produto selecionado
	public void editarProduto(JavaBeans produto) {
		String update = "update produto set codigo = ?, nome = ?, categoria = ?, valor = ?, quantidade = ? where id = ?;";
		try {
			// Abrir a conexão com o bd
			Connection con = conectar();
			// Preparar a query
			PreparedStatement pst = con.prepareStatement(update);
			// Inserir variáveis em ?
			pst.setString(1, produto.getCodigo());
			pst.setString(2, produto.getNome());
			pst.setString(3, produto.getCategoria());
			pst.setString(4, produto.getValor());
			pst.setString(5, produto.getQuantidade());
			pst.setString(6, produto.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Erro em: CRUD - UPDATE - Editar");
		}
	}

	// CRUDE - DELETE - deleta um produto do banco de dados
	public void deletarProduto(JavaBeans produto) {
		String delete = "delete from produto where id = ?;";
		try {
			// Abrir a conexão com o bd
			Connection con = conectar();
			// Preparar a query
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, produto.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Erro em: CRUD - DELETE");
		}
	}

}
