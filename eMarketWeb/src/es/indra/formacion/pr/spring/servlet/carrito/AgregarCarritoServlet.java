package es.indra.formacion.pr.spring.servlet.carrito;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.indra.formacion.pr.spring.exception.EmarketServiceException;
import es.indra.formacion.pr.spring.service.IProductoService;
import es.indra.formacion.pr.spring.to.Producto;
import es.indra.formacion.pr.spring.util.Contexto;

/**
 * Servlet implementation class CarritoServlet
 */
@WebServlet("/carrito/Agregar")
public class AgregarCarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgregarCarritoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		IProductoService productoService = 
				Contexto.getInstance().getBean("productoService", IProductoService.class);

		HttpSession sesion = request.getSession();

		String[] scantidades = request.getParameterValues("cantidad");
		String[] sproductoIds = request.getParameterValues("productoId");

		// Validar si el producto ya existe en la sesión
		Object obj = sesion.getAttribute("productos");
		Map<Integer, Producto> productos = null;

		if (obj == null) {
			productos = new Hashtable<Integer, Producto>();
			sesion.setAttribute("productos", productos);
		} else {
			productos = (Map<Integer, Producto>) obj;
		}

		for (int i = 0; i < sproductoIds.length; i++) {
			Integer id = Integer.parseInt(sproductoIds[i]);
			Integer cantidad = null;
			try {
				cantidad = Integer.parseInt(scantidades[i]);
			} catch (Exception e) {
				continue; // Obviarlo porque no es un número la cantidad
			}

			if (cantidad > 0) { // Están agregando un producto
				Producto prod = productos.get(id);

				if (prod == null) { // Debo agregar el producto
					try {
						es.indra.formacion.pr.spring.model.Producto pModel = 
								productoService.obtenerProducto(id); // Buscando el producto

						if (pModel == null) // SI el producto no existe ignoralo
							continue;

						// Clonando objeto
						prod = new Producto();
						prod.setId(pModel.getId());
						prod.setNombre(pModel.getNombre());
						prod.setPrecio(pModel.getPrecio());
						prod.setCantidad(cantidad);

						productos.put(pModel.getId(), prod); // Agregando al hashtable
					} catch (EmarketServiceException e) {
						e.printStackTrace();
					}
				} else { // Modifico la cantidad del producto
					prod.setCantidad(cantidad + prod.getCantidad()); // Este dato está en la sesión
				}
			}
		}
		
		//getServletContext().getRequestDispatcher("/carrito/Mostrar").forward(request, response); // Se especifica el mapping!
		response.sendRedirect("Mostrar");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
