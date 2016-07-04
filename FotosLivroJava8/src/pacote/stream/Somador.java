package pacote.stream;

public class Somador {
	private double total;

	public Somador() {
		present(0);
	}

	public double getTotal() {
		return total;
	}

	public void add(double total) {
		this.total += total;
	}

	protected void present(double total) {
		this.total = total;
	}

	public void reset() {
		present(0);
	}
}
