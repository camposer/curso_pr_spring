package es.indra.formacion.pr.spring.servlet.carrito;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.indra.formacion.pr.spring.to.Producto;

/**
 * Servlet implementation class EliminarServlet
 */
@WebServlet("/carrito/Eliminar")
public class EliminarCarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCarritoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("id");
		Integer id = Integer.parseInt(sid);
		
		Map<Integer, Producto> productos = (Map<Integer, Producto>) request.getSession().getAttribute("productos");
		
		if (productos != null && productos.containsKey(id)) {
			productos.remove(id);
		}
		
		response.sendRedirect("Mostrar");
	}

}
