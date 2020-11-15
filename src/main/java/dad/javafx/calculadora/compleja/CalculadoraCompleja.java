package dad.javafx.calculadora.compleja;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraCompleja extends Application{
	
	private Complejo numerador = new Complejo();
	private Complejo denominador = new Complejo();
	private Complejo resultado = new Complejo();
	
	private ComboBox<String> cb_operacion; 			//combobox que contiene strings
	
	private TextField tx_numerador_real;			//Textfield numerador real
	private TextField tx_numerador_imaginario;  	//Textfield numerador imaginario
	private TextField tx_denominador_real;			//Textfield denominador real
	private TextField tx_denominador_imaginario;	//Textfield denominador imaginario
	private TextField tx_resultado_real;			//Textfield resultado real
	private TextField tx_resultado_imaginario;		//Textfield resultado imaginario
	
	private String[] tipo_operacion  = {"+","-","*","/"};
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//TIPO DE OPERACION
		cb_operacion = new ComboBox<String>();
		cb_operacion.getItems().addAll(tipo_operacion);
		cb_operacion.setOnAction(e -> cambiarOperacion());
		
		
		VBox vb_operacion = new VBox();
		vb_operacion.getChildren().add(cb_operacion);
		vb_operacion.setAlignment(Pos.CENTER);
		
		//NUMERADOR
		tx_numerador_real = new TextField();
		tx_numerador_real.setPrefColumnCount(5);
		tx_numerador_real.setMaxWidth(100);
		tx_numerador_real.setAlignment(Pos.CENTER);
		
		tx_numerador_imaginario = new TextField();
		tx_numerador_imaginario.setPrefColumnCount(5);
		tx_numerador_imaginario.setMaxWidth(100);
		tx_numerador_imaginario.setAlignment(Pos.CENTER);
		
		//HBOX NUMERADOR
		HBox hb_numerador = new HBox();
		hb_numerador.setSpacing(5);
		hb_numerador.getChildren().addAll(tx_numerador_real, new Label("+"), tx_numerador_imaginario, new Label("i")); //añadimos todos los elementos del hbox
		
		Bindings.bindBidirectional(tx_numerador_real.textProperty(), numerador.numero_realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(tx_numerador_imaginario.textProperty(), numerador.numero_imaginarioProperty(), new NumberStringConverter());
		
		//DENOMINADOR
		tx_denominador_real = new TextField();
		tx_denominador_real.setPrefColumnCount(5);
		tx_denominador_real.setMaxWidth(100);
		tx_denominador_real.setAlignment(Pos.CENTER);
		
		tx_denominador_imaginario = new TextField();
		tx_denominador_imaginario.setPrefColumnCount(5);
		tx_denominador_imaginario.setMaxWidth(100);
		tx_denominador_imaginario.setAlignment(Pos.CENTER);
		
		//HBOX DENOMINADOR
		HBox hb_denominador = new HBox();
		hb_denominador.setSpacing(5);
		hb_denominador.getChildren().addAll(tx_denominador_real, new Label("+"), tx_denominador_imaginario, new Label("i")); //añadimos todos los elementos del hbox
		
		Bindings.bindBidirectional(tx_denominador_real.textProperty(), denominador.numero_realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(tx_denominador_imaginario.textProperty(), denominador.numero_imaginarioProperty(), new NumberStringConverter());
		
		//RESULTADO
		tx_resultado_real = new TextField();
		tx_resultado_real.setPrefColumnCount(5);
		tx_resultado_real.setMaxWidth(100);
		tx_resultado_real.setAlignment(Pos.CENTER);
		tx_resultado_real.setDisable(true); //evitamos que se pueda modificar manualmente
		
		tx_resultado_imaginario = new TextField();
		tx_resultado_imaginario.setPrefColumnCount(5);
		tx_resultado_imaginario.setMaxWidth(100);
		tx_resultado_imaginario.setAlignment(Pos.CENTER);
		tx_resultado_imaginario.setDisable(true); //evitamos que se pueda modificar manualmente
		
		
		//HBOX RESULTADO
		HBox hb_resultado = new HBox();
		hb_resultado.setSpacing(5);
		hb_resultado.getChildren().addAll(tx_resultado_real, new Label("+"), tx_resultado_imaginario, new Label("i")); //añadimos todos los elementos del hbox
		
		Bindings.bindBidirectional(tx_resultado_real.textProperty(), resultado.numero_realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(tx_resultado_imaginario.textProperty(), resultado.numero_imaginarioProperty(), new NumberStringConverter());
		
		//VBox donde meter los tres numeros imaginarios y el resultado
		VBox vb_numeros = new VBox();
		vb_numeros.setAlignment(Pos.CENTER);
		vb_numeros.getChildren().addAll(hb_numerador, hb_denominador, hb_resultado);
		
		
		//ESCENA PRINCIPAL
		HBox root = new HBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(vb_operacion, vb_numeros);
		Scene escena = new Scene(root, 320, 200);
		primaryStage.setScene(escena);
		primaryStage.setTitle("Calculadora Compleja");
		primaryStage.setResizable(false);
		primaryStage.show();	
	}
	
	private void cambiarOperacion() {
		String operacion = cb_operacion.getSelectionModel().getSelectedItem(); //convertimos la opcion elegida en el combobox a un string
		
		if(operacion == "+") {
			/*NUMERO REAL*/
			resultado.numero_realProperty().bind(numerador.numero_realProperty().add(denominador.numero_realProperty())); //Bindeamos al numero real del N Complejo resultado la suma de los numeros reales del numerador y denominador
			/*NUMERO IMAGINARIO*/
			resultado.numero_imaginarioProperty().bind(numerador.numero_imaginarioProperty().add(denominador.numero_imaginarioProperty())); //Lo mismo pero con la parte imaginaria
		}
		else if(operacion == "-") {
			/*NUMERO REAL*/
			resultado.numero_realProperty().bind(numerador.numero_realProperty().subtract(denominador.numero_realProperty())); //Bindeamos al numero real del N Complejo resultado la resta de los numeros reales del numerador y denominador
			/*NUMERO IMAGINARIO*/
			resultado.numero_imaginarioProperty().bind(numerador.numero_imaginarioProperty().subtract(denominador.numero_imaginarioProperty())); //Lo mismo pero con la parte imaginaria
		}
		else if(operacion == "*") {
			/*NUMERO REAL*/
			resultado.numero_realProperty().bind(numerador.numero_realProperty().multiply(denominador.numero_realProperty())
					 .subtract(numerador.numero_imaginarioProperty().multiply(denominador.numero_imaginarioProperty())));
			/*NUMERO IMAGINARIO*/
			resultado.numero_imaginarioProperty().bind(numerador.numero_realProperty().multiply(denominador.numero_imaginarioProperty())
					 .add(numerador.numero_imaginarioProperty().multiply(denominador.numero_realProperty())));
		}
		else if(operacion == "/") {
			/*NUMERO REAL*/
			resultado.numero_realProperty().bind(
					(numerador.numero_realProperty().multiply(denominador.numero_realProperty()).add(numerador.numero_imaginarioProperty().multiply(denominador.numero_imaginarioProperty())))
					.divide(
					(denominador.numero_realProperty().multiply(denominador.numero_realProperty()).add(denominador.numero_imaginarioProperty().multiply(denominador.numero_imaginarioProperty())))));
			
			/*NUMERO IMAGINARIO*/
			resultado.numero_imaginarioProperty().bind(
					(numerador.numero_imaginarioProperty().multiply(denominador.numero_realProperty()).subtract(numerador.numero_realProperty().multiply(denominador.numero_imaginarioProperty())))
					.divide(
					(denominador.numero_realProperty().multiply(denominador.numero_realProperty()).add(denominador.numero_imaginarioProperty().multiply(denominador.numero_imaginarioProperty()))))
			);
			
		}
		
		
	}
	
	public static void main(String[] ar) {
		launch(ar);
	}
	
	
}
