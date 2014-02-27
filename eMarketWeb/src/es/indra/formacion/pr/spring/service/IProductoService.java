package es.indra.formacion.pr.spring.service;

import java.util.List;

import es.indra.formacion.pr.spring.exception.EmarketServiceException;
import es.indra.formacion.pr.spring.model.Producto;

public interface IProductoService {
	public void agregarProducto(Producto p) throws EmarketServiceException;
	public void modificarProducto(Producto p) throws EmarketServiceException;
	public void eliminarProducto(Integer id) throws EmarketServiceException;
	public Producto obtenerProducto(Integer id) throws EmarketServiceException;
	public List<Producto> obtenerProductos() throws EmarketServiceException;
}
