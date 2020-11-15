package dad.javafx.calculadora.compleja;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Complejo{
		
	private DoubleProperty numero_real = new SimpleDoubleProperty(0);
	private DoubleProperty numero_imaginario = new SimpleDoubleProperty(0);
	
	
	//CONSTRUCTOR//////////////////////////////////////////////////////////////
	public Complejo(double numero_real, double numero_imaginario) {
		super();
		setNumero_real(numero_real);
		setNumero_imaginario(numero_imaginario);
	}
	
	
	public Complejo(){
		
	}
	//////////////////////////////////////////////////////////////////////////
	
	
	//JAVAFX GETTERS Y SETTERS////////////////////////////////////////////////
	public final DoubleProperty numero_realProperty() {
		return this.numero_real;
	}
	

	public final double getNumero_real() {
		return this.numero_realProperty().get();
	}
	

	public final void setNumero_real(final double numero_real) {
		this.numero_realProperty().set(numero_real);
	}
	

	public final DoubleProperty numero_imaginarioProperty() {
		return this.numero_imaginario;
	}
	

	public final double getNumero_imaginario() {
		return this.numero_imaginarioProperty().get();
	}
	

	public final void setNumero_imaginario(final double numero_imaginario) {
		this.numero_imaginarioProperty().set(numero_imaginario);
	}
	
	/////////////////////////////////////////////////////////////////////////
}
