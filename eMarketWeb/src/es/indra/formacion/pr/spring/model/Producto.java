package es.indra.formacion.pr.spring.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PRODUCTO database table.
 * 
 */
@Entity
@Table(name="PRODUCTO")
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=50)
	private String nombre;

	@Column(nullable=false)
	private Float precio;
	
	public Producto() {}
	public Producto(String nombre, Float precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getPrecio() {
		return this.precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio="
				+ precio + "]";
	}
}