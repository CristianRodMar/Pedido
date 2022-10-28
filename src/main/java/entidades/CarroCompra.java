package entidades;

import java.util.ArrayList;

public class CarroCompra {

	private ArrayList<Producto> elementos;
	
	public CarroCompra() {
		this.elementos = new ArrayList<Producto>();
	}

	public ArrayList<Producto> getElementos() {
		return elementos;
	}

	public void setElementos(ArrayList<Producto> elementos) {
		this.elementos = elementos;
	}
	
	public void añadirElemento (Producto producto) {
		int posicion = elementos.indexOf(producto);
		if (posicion == -1) {
			producto.setCantidad(1);
			elementos.add(producto);
		} else {
			elementos.get(posicion).setCantidad(elementos.get(posicion).getCantidad() + 1);
		}
	}
	
	public void retirarElemento (Producto producto) {
		int posicion = elementos.indexOf(producto);
		elementos.get(posicion).setCantidad(elementos.get(posicion).getCantidad() - 1);
		if (elementos.get(posicion).getCantidad() == 0) {
			elementos.remove(posicion);
		}
	}
	
	public void removerElemento (Producto producto) {
		elementos.remove(elementos.indexOf(producto));
	}

}
