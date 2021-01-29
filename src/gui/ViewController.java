package gui;

import java.util.Locale;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ViewController {
	
	@FXML
	private TextField valorRecebido;
		
	@FXML
	private Label labelResult;
	
	@FXML
	private Button btSum;
	
	@FXML
	public void onBtSubAction() {
		try {
			Locale.setDefault(Locale.US);
			double number1 = Double.parseDouble(valorRecebido.getText());
			double number2 = 200;
			double sub = number1 - number2;
			labelResult.setText(String.format("R$ %.2f", sub));
		}
		catch(NumberFormatException e){
			Alerts.showAlert("Erro", null, e.getMessage(), AlertType.ERROR);
		}
	}
}
