package es.indra.formacion.pr.spring.dao;

import java.util.ArrayList;
import java.util.List;

import es.indra.formacion.pr.spring.to.Producto;

public class ProductoDaoMockup { //implements IProductoDao {
	private List<Producto> productos = new ArrayList<Producto>();
	private int contador = 0;
	
	public ProductoDaoMockup() {
		agregar(new Producto("Portátiles", 599f));
		agregar(new Producto("Cámaras", 230f));
		agregar(new Producto("DVD", 60f));
		agregar(new Producto("Teléfono", 500f));
	}
	
	public List<Producto> obtenerTodos() {
		return productos;
	}

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
	
	public Producto obtener(Integer id) {
		int pos = buscarPos(id);
		
		if (pos >= 0)
			return productos.get(pos);
		else
			return null;
	}

	public void modificar(Producto p) {
		int pos = buscarPos(p.getId());
		
		if (pos >= 0)
			productos.set(pos, p);
	}

	public void eliminar(Integer id) {
		int pos = buscarPos(id);
		
		if (pos >= 0)
			productos.remove(pos);
	}
}
