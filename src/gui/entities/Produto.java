package gui.entities;

import java.io.Serializable;

public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descricao;
	private float preco;
	private Integer qtd_estoque;
	private String cod_barras;
	
	public Produto() {
		
	}

	public Produto(Integer id, String descricao, float preco, Integer qtd_estoque, String cod_barras) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.preco = preco;
		this.qtd_estoque = qtd_estoque;
		this.cod_barras = cod_barras;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Integer getQtd_estoque() {
		return qtd_estoque;
	}

	public void setQtd_estoque(Integer qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}

	public String getCod_barras() {
		return cod_barras;
	}

	public void setCod_barras(String cod_barras) {
		this.cod_barras = cod_barras;
	}
	
	
	

}
