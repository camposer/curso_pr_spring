package es.indra.formacion.pr.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.indra.formacion.pr.spring.dao.IProductoDao;
import es.indra.formacion.pr.spring.exception.EmarketServiceException;
import es.indra.formacion.pr.spring.to.Producto;

public class ProductoService implements IProductoService {
	// Inyectado automáticamente por el contexto
	@Autowired 
	private IProductoDao productoDao;
	
	@Override
	public void agregarProducto(Producto p) throws EmarketServiceException {
		productoDao.agregar(p);
	}

	@Override
	public List<Producto> obtenerProductos() throws EmarketServiceException {
		return productoDao.obtenerTodos();
	}

	@Override
	public Producto obtenerProducto(Integer id) throws EmarketServiceException {
		return productoDao.obtener(id);
	}

	@Override
	public void eliminarProducto(Integer id) throws EmarketServiceException {
		productoDao.eliminar(id);
	}

	@Override
	public void modificarProducto(Producto p) throws EmarketServiceException {
		productoDao.modificar(p);
	}

	public void setProductoDao(IProductoDao productoDao) {
		this.productoDao = productoDao;
	}

}
