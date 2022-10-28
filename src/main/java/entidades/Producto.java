package entidades;

import java.util.Objects;

public class Producto {
	private long id;
	private String nombre;
	private double precioNormal;
	private double precioMinimo;
	private int cantidad;
	
	public Producto(long id, String nombre, double precioNormal, double precioMinimo, int cantidad) {
		this.id = id;
		this.nombre = nombre;
		this.precioNormal = precioNormal;
		this.precioMinimo = precioMinimo;
		this.cantidad = cantidad;
	}
	
	public Producto(long id, String nombre, double precioNormal) {
		this.id = id;
		this.nombre = nombre;
		this.precioNormal = precioNormal;
	}
	
	public void addProducto() {
		this.cantidad++;	
	}
	
	public void lessProducto() {
		this.cantidad--;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioNormal() {
		return precioNormal;
	}

	public void setPrecioNormal(double precioNormal) {
		this.precioNormal = precioNormal;
	}

	public double getPrecioMinimo() {
		return precioMinimo;
	}

	public void setPrecioMinimo(double precioMinimo) {
		this.precioMinimo = precioMinimo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return id == other.id;
	}
	
}
