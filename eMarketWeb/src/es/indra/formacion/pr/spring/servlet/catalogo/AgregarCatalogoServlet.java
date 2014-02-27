package es.indra.formacion.pr.spring.servlet.catalogo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.indra.formacion.pr.spring.exception.EmarketServiceException;
import es.indra.formacion.pr.spring.service.IProductoService;
import es.indra.formacion.pr.spring.model.Producto;
import es.indra.formacion.pr.spring.util.Contexto;

/**
 * Servlet implementation class AgregarCatalogoServlet
 */
@WebServlet("/catalogo/AgregarCatalogo")
public class AgregarCatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarCatalogoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private List<String> validar(String nombre, String sprecio) {
    	List<String> errores = null;
    	
		Float precio = null;
		
		// Validando el nombre
		if (nombre == null || nombre.trim().equals("")) {
			errores = new ArrayList<String>();
			errores.add("Debe especificar un nombre");
		}
		
		try {
			precio = Float.parseFloat(sprecio);
			
			if (precio <= 0f)
				throw new Exception();
		} catch(Exception e) {
			if (errores == null)
				errores = new ArrayList<String>();
			
			errores.add("Debe especificar un precio mayor que 0");
		}	
		
		return errores;
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IProductoService productoService = 
				Contexto.getInstance().getBean("productoService", IProductoService.class);
		
		String nombre = request.getParameter("nombre");
		String sprecio = request.getParameter("precio");
		
		// Validaciones
		List<String> errores = validar(nombre, sprecio);
		
		if (errores == null) {
			try {
				productoService.agregarProducto(new Producto(nombre, Float.parseFloat(sprecio)));
			} catch (EmarketServiceException e) {
				errores = new ArrayList<String>();
				errores.add("Error al añadir el producto a BD");
				e.printStackTrace();
			}
		} 

		request.getSession().setAttribute("errores", errores);
		response.sendRedirect("Principal");
		//response.sendRedirect("/eMarket/catalogo/Principal");
		//response.sendRedirect(getServletContext().getContextPath() + "/catalogo/Principal");
		/*
		// No se recomienda utilizar un forward en este caso porque se mantienen los datos de la petición en el POST
		getServletContext()
			.getRequestDispatcher("/catalogo/Principal")
			.forward(request, response);
		*/
	}

}
