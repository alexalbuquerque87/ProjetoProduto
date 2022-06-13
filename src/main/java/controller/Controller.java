package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/home", "/create", "/insert", "/read", "/delete", "/select", "/update" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Objeto de acesso ao Banco de Dados
	DAO dao = new DAO();
	// Objeto para armazenar dados temporariamente
	JavaBeans produto = new JavaBeans();

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		// Exibe urlPatterns selecionada
		System.out.println(action);

		if (action.equals("/home")) {
			response.sendRedirect("index.html");

		} else if (action.equals("/insert")) {
			adicionarProdutos(request, response);

		} else if (action.equals("/read")) {
			listarProdutos(request, response);

		} else if (action.equals("/create")) {
			response.sendRedirect("cadastro.html");

		} else if (action.equals("/select")) {
			recuperarProduto(request, response);

		} else if (action.equals("/update")) {
			editarProduto(request, response);

		} else if (action.equals("/delete")) {
			removerProduto(request, response);
		}

	}

	// Método para listar os produtos
	protected void listarProdutos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criar lista para JavaBeans - lista de produtos
		ArrayList<JavaBeans> lista = dao.listarProdutos();
		// Testar recebimento das informações recebidas do banco de dados no console
		for (int i = 0; i < lista.size(); i++) {
			System.out.println(lista.get(i).getId());
			System.out.println(lista.get(i).getCodigo());
			System.out.println(lista.get(i).getNome());
			System.out.println(lista.get(i).getCategoria());
			System.out.println(lista.get(i).getValor());
			System.out.println(lista.get(i).getQuantidade());
		}
		request.setAttribute("produtos", lista);

		RequestDispatcher rd = request.getRequestDispatcher("produtos.jsp");
		rd.forward(request, response);
	}

	// Método para adicionar produtos
	protected void adicionarProdutos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Mostrar dados recebidos no console
		System.out.println(request.getParameter("codigo"));
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("categoria"));
		System.out.println(request.getParameter("valor"));
		System.out.println(request.getParameter("quantidade"));
		// Passar valores para o JavaBeans
		produto.setCodigo(request.getParameter("codigo"));
		produto.setNome(request.getParameter("nome"));
		produto.setCategoria(request.getParameter("categoria"));
		produto.setValor(request.getParameter("valor"));
		produto.setQuantidade(request.getParameter("quantidade"));
		// Passar objeto produto para método inserirProduto
		dao.inserirProduto(produto);
		// Ir para página lista de produtos
		response.sendRedirect("read");

	}

	// Recupera informação do produto a ser editado
	protected void recuperarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebe parâmetro id do formulário do produto a ser editado
		String id = request.getParameter("id");
		System.out.println("ID do produto a ser editado: " + id);
		// Setar a variável JavaBeans
		produto.setId(id);
		dao.selecionarProduto(produto);
		// Mostrar dados recebidos no console
		System.out.println(produto.getId());
		System.out.println(produto.getCodigo());
		System.out.println(produto.getNome());
		System.out.println(produto.getCategoria());
		System.out.println(produto.getValor());
		System.out.println(produto.getQuantidade());
		// Setar os atributos do formulário com o JavaBeans
		request.setAttribute("id", produto.getId());
		request.setAttribute("codigo", produto.getCodigo());
		request.setAttribute("nome", produto.getNome());
		request.setAttribute("categoria", produto.getCategoria());
		request.setAttribute("valor", produto.getValor());
		request.setAttribute("quantidade", produto.getQuantidade());
		// Encaminhar ao editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}

	// Edita produto selecionado
	private void editarProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Mostrar dados recebidos no console
		System.out.println(request.getParameter("codigo"));
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("categoria"));
		System.out.println(request.getParameter("valor"));
		System.out.println(request.getParameter("quantidade"));
		// Passar valores para o JavaBeans
		produto.setCodigo(request.getParameter("codigo"));
		produto.setNome(request.getParameter("nome"));
		produto.setCategoria(request.getParameter("categoria"));
		produto.setValor(request.getParameter("valor"));
		produto.setQuantidade(request.getParameter("quantidade"));
		// Executar método editar produto
		dao.editarProduto(produto);
		// Ir para página lista de produtos
		response.sendRedirect("read");

	}

	private void removerProduto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		// Mostrar ID do item deletado no console
		System.out.println("ID do produto deletado: " + id);
		// Passar id para o JavaBeans
		produto.setId(id);
		// Executar método deletar produto
		dao.deletarProduto(produto);
		// Ir para página lista de produtos
		response.sendRedirect("read");
	}

}
