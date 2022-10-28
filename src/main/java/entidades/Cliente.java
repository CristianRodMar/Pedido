package entidades;

public class Cliente {
	private int id;
	private String nombre;
	private String tipo;
	private double limiteCredito;
	
	public Cliente(int id, String nombre, String tipo, double limiteCredito) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.limiteCredito = limiteCredito;
	}

	public int getId() {
		return id;
	}

	public void setNumber(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	
	
	
}
