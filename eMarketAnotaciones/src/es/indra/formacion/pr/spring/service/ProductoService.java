package es.indra.formacion.pr.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.indra.formacion.pr.spring.dao.IProductoDao;
import es.indra.formacion.pr.spring.exception.EmarketDaoException;
import es.indra.formacion.pr.spring.exception.EmarketServiceException;
import es.indra.formacion.pr.spring.to.Producto;

public class ProductoService implements IProductoService {
	// Inyectado automáticamente por el contexto
	@Autowired 
	private IProductoDao productoDao;
	
	@Override
	public void agregarProducto(Producto p) throws EmarketServiceException {
		try {
			productoDao.agregar(p);
		} catch (EmarketDaoException e) {
			throw new EmarketServiceException("Error al agregar Producto: " + p, e); 
		}
	}

	@Override
	public List<Producto> obtenerProductos() throws EmarketServiceException {
		try {
			return productoDao.obtenerTodos();
		} catch (EmarketDaoException e) {
			throw new EmarketServiceException("Error al obtener Productos", e); 
		}
	}

	@Override
	public Producto obtenerProducto(Integer id) throws EmarketServiceException {
		try {
			return productoDao.obtener(id);
		} catch (EmarketDaoException e) {
			throw new EmarketServiceException("Error al obtener el Producto con id = " + id, e); 
		}
	}

	@Override
	public void eliminarProducto(Integer id) throws EmarketServiceException {
		try {
			productoDao.eliminar(id);
		} catch (EmarketDaoException e) {
			throw new EmarketServiceException("Error al eliminar el Producto con id = " + id, e); 
		}
	}

	@Override
	public void modificarProducto(Producto p) throws EmarketServiceException {
		try {
			productoDao.modificar(p);
		} catch (EmarketDaoException e) {
			throw new EmarketServiceException("Error al modificar Producto: " + p, e); 
		}
	}

	public void setProductoDao(IProductoDao productoDao) {
		this.productoDao = productoDao;
	}

}
