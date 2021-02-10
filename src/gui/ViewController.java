package gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

import db.DB;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ViewController {
	
	
	
	@FXML
	private TextField codBarras;
	
	@FXML
	private TextField qtdProd;
	
	@FXML
	private TextField valorRecebido;
		
	@FXML
	private Label labelResult;
	
	@FXML
	private Label labelProduto;
	
	@FXML
	private Label labelQuantidade;
	
	@FXML
	private Label labelValUnit;
	
	@FXML
	private Label labelValInt;
	
	@FXML
	private Label labelSubtotal;
	
	@FXML
	private Button btSum;
	
	@FXML
	private Button btAd;
	
	@FXML 
	private Button btFecha;
	
	private String nome;
	private double preco;
	private int qtd;
	private int qtd_estoque;
	private String query;
	
	@FXML
	public void onBtAdAction() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		this.query = codBarras.getText();
		
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM tb_produtos WHERE cod_barras IN (" + this.query + ")");
			
			while(rs.next()) {
				this.nome = rs.getString("descricao");
				this.preco = rs.getDouble("preco");
				this.qtd_estoque = rs.getInt("qtd_estoque");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(this.nome + ", " + this.preco);
		this.qtd = Integer.parseInt(qtdProd.getText());
		labelProduto.setText(this.nome);
		labelQuantidade.setText(String.format("%d", this.qtd));
		labelValUnit.setText(String.format("R$ %.2f", this.preco));
		labelValInt.setText(String.format("R$ %.2f", (this.preco * this.qtd)));
		labelSubtotal.setText(String.format("R$ %.2f", (this.preco * this.qtd)));
	}
	
	
	@FXML
	public void onBtSubAction() {
		try {
			Locale.setDefault(Locale.US);
			double number1 = Double.parseDouble(valorRecebido.getText());
			double number2 = this.preco * this.qtd;
			double sub = number1 - number2;
			labelResult.setText(String.format("R$ %.2f", sub));
		}
		catch(NumberFormatException e){
			Alerts.showAlert("Erro", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	
	@FXML
	public void onBtFecAction() {
		Connection conn = null;
		PreparedStatement st = null;
		int novaQtd = this.qtd_estoque - this.qtd;
		
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("UPDATE `mercado353`.`tb_produtos` SET `qtd_estoque` = ' "+ novaQtd +" ' WHERE (`cod_barras` = ' "+ this.query +" ')");
			//st.setInt(1, novaQtd);
			//st.setString(2, this.query);
			
			int rowsA = st.executeUpdate();
			System.out.println("Linhas afetadas: " + rowsA);

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeConnection();
		}
	}
	
}
