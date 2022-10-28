package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoCarroCompra;
import dao.DaoCliente;
import dao.DaoProducto;
import entidades.CarroCompra;
import entidades.Cliente;
import entidades.Producto;

/**
 * Servlet implementation class Controller
 */
@WebServlet({"/Controller", "/controller"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String operacion = request.getParameter("operacion");
		DaoProducto daoProducto = new DaoProducto();
		ArrayList<Producto> listadoProductos;
		CarroCompra carroCompra = null;
		String error = null;
		switch (operacion) {
		case "iniciocompra":
			try {
				carroCompra = (CarroCompra) session.getAttribute("carroCompra");
				if (carroCompra == null) {
					carroCompra = new CarroCompra();
				}
				listadoProductos = daoProducto.listarProductos();
				request.setAttribute("listadoProductos", listadoProductos);
				request.setAttribute("error", error);
				request.getRequestDispatcher("pedido.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "anadir":
			try {
				carroCompra = (CarroCompra) session.getAttribute("carroCompra");
				if (carroCompra == null) {
					carroCompra = new CarroCompra();
				}
				listadoProductos = daoProducto.listarProductos();
				request.setAttribute("listadoProductos", listadoProductos);
				
				Producto productoAñadido = new Producto(Long.parseLong(request.getParameter("idproducto")), 
						request.getParameter("nombreproducto"), Double.parseDouble(request.getParameter("productoprecio")));
				carroCompra.añadirElemento(productoAñadido);
				session.setAttribute("carroCompra", carroCompra);
				request.setAttribute("error", error);
				request.getRequestDispatcher("pedido.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "eliminar":
			try {
				carroCompra = (CarroCompra) session.getAttribute("carroCompra");
				if (carroCompra == null) {
					carroCompra = new CarroCompra();
				}
				listadoProductos = daoProducto.listarProductos();
				request.setAttribute("listadoProductos", listadoProductos);
				Producto productoRetirado = new Producto(Long.parseLong(request.getParameter("idproducto")), 
						request.getParameter("nombreproducto"), Double.parseDouble(request.getParameter("productoprecio")));
				carroCompra.retirarElemento(productoRetirado);
				session.setAttribute("carroCompra", carroCompra);
				request.setAttribute("error", error);
				request.getRequestDispatcher("pedido.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "remover":
			try {
				carroCompra = (CarroCompra) session.getAttribute("carroCompra");
				if (carroCompra == null) {
					carroCompra = new CarroCompra();
				}
				listadoProductos = daoProducto.listarProductos();
				request.setAttribute("listadoProductos", listadoProductos);
				Producto productoRetirado = new Producto(Long.parseLong(request.getParameter("idproducto")), 
						request.getParameter("nombreproducto"), Double.parseDouble(request.getParameter("productoprecio")));
				carroCompra.removerElemento(productoRetirado);
				session.setAttribute("carroCompra", carroCompra);
				request.setAttribute("error", error);
				request.getRequestDispatcher("pedido.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "enviarpedido":
			request.getRequestDispatcher("formulario.jsp").forward(request, response);
			break;
		case "enviarformulario":
			carroCompra = (CarroCompra) session.getAttribute("carroCompra");
			String direccion = request.getParameter("direccion");
			DaoCliente daoCliente = new DaoCliente();
			DaoCarroCompra daoCarroCompra = new DaoCarroCompra();
			Cliente cliente;
			try {
				cliente = daoCliente.getClienteById(Integer.parseInt(request.getParameter("id")));
				daoCarroCompra.enviarPedido(carroCompra, cliente, direccion);
				session.invalidate();
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				error = e.getMessage();
				request.setAttribute("error", error);
				request.setAttribute("id", request.getParameter("id"));
				request.setAttribute("direccion", direccion);
				request.getRequestDispatcher("formulario.jsp").forward(request,response);
			}
			
			break;
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
