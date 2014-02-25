package es.indra.formacion.pr.spring.dao;

import java.util.List;

import es.indra.formacion.pr.spring.exception.EmarketDaoException;
import es.indra.formacion.pr.spring.to.Producto;

public interface IProductoDao {
	public List<Producto> obtenerTodos() throws EmarketDaoException;
	public void agregar(Producto p) throws EmarketDaoException;
	public void modificar(Producto p) throws EmarketDaoException;
	public void eliminar(Integer id) throws EmarketDaoException;
	public Producto obtener(Integer id) throws EmarketDaoException;
}
