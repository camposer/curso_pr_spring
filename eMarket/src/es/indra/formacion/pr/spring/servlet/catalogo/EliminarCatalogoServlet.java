package es.indra.formacion.pr.spring.servlet.catalogo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.indra.formacion.pr.spring.service.IProductoService;
import es.indra.formacion.pr.spring.util.Contexto;

/**
 * Servlet implementation class EliminarCatalogoServlet
 */
@WebServlet("/catalogo/EliminarCatalogo")
public class EliminarCatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCatalogoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IProductoService productoService = 
				Contexto.getInstance().getBean("productoService", IProductoService.class);
		String sid = request.getParameter("id");
		
		if (sid != null) {
			try {
				productoService.eliminarProducto(Integer.parseInt(sid));
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		response.sendRedirect("Principal");
	}

}
