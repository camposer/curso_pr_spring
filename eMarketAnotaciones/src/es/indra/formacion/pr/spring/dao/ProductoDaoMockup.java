package es.indra.formacion.pr.spring.dao;

import java.util.ArrayList;
import java.util.List;

import es.indra.formacion.pr.spring.exception.EmarketDaoException;
import es.indra.formacion.pr.spring.to.Producto;

public class ProductoDaoMockup implements IProductoDao {
	private List<Producto> productos = new ArrayList<Producto>();
	private int contador = 0;
	
	public ProductoDaoMockup() {
		agregar(new Producto("Portátiles", 599f));
		agregar(new Producto("Cámaras", 230f));
		agregar(new Producto("DVD", 60f));
		agregar(new Producto("Teléfono", 500f));
	}
	
	@Override
	public List<Producto> obtenerTodos() {
		return productos;
	}

	@Override
	public void agregar(Producto p) {
		contador++;
		p.setId(contador);
		productos.add(p);
	}

	private int buscarPos(Integer id) {
		for(int i = 0; i < productos.size(); i++) {
			if (productos.get(i).getId().equals(id)) 
				return i;
		}
		
		return -1;
	}
	
	@Override
	public Producto obtener(Integer id) throws EmarketDaoException {
		int pos = buscarPos(id);
		
		if (pos >= 0)
			return productos.get(pos);
		else
			return null;
	}

	@Override
	public void modificar(Producto p) throws EmarketDaoException {
		int pos = buscarPos(p.getId());
		
		if (pos >= 0)
			productos.set(pos, p);
	}

	@Override
	public void eliminar(Integer id) throws EmarketDaoException {
		int pos = buscarPos(id);
		
		if (pos >= 0)
			productos.remove(pos);
	}
}
