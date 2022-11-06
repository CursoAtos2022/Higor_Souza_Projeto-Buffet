package prjBuffet.DB_Digital_Buffet.Model;

import javax.persistence.*;

@Entity
public class Orcamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrcamento;
	private String nomeCliente;
	private String enderecoCliente;
	private String telefoneCliente;
	private String emailCliente;
	
	//CONVIDADOS_______________________
	private int qtdConvidado;
	private double valUnitConvidado;
	private double valTotalConvidados;
	
	//GARÇONS__________________________
	private int qtdGarcon;
	private double valUnitGarcon;
	private double valTotalGarcon;
	
	//SOBREMESA_______________________
	private String optSobremesa;
	private double taxaSobremesa;
	
	private double totalOcr;
	
	//CONSTRUTORES
	public Orcamento(){super();}

	public Orcamento(String nomeCliente, String enderecoCliente, String telefoneCliente, String emailCliente,
					 int qtdConvidado, double valUnitConvidado, double valTotalConvidados, int qtdGarcon, double valUnitGarcon,
					 double valTotalGarcon, String optSobremesa, double taxaSobremesa, double totalOcr) {
		super();
		this.nomeCliente = nomeCliente;
		this.enderecoCliente = enderecoCliente;
		this.telefoneCliente = telefoneCliente;
		this.emailCliente = emailCliente;
		this.qtdConvidado = qtdConvidado;
		this.valUnitConvidado = valUnitConvidado;
		this.valTotalConvidados = valTotalConvidados;
		this.qtdGarcon = qtdGarcon;
		this.valUnitGarcon = valUnitGarcon;
		this.valTotalGarcon = valTotalGarcon;
		this.optSobremesa = optSobremesa;
		this.taxaSobremesa = taxaSobremesa;
		this.totalOcr = totalOcr;
	}

	//GETTERS AND SETTERS
	public long getIdOrcamento() {
		return idOrcamento;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public int getQtdConvidado() {
		return qtdConvidado;
	}

	public void setQtdConvidado(int qtdConvidado) {
		this.qtdConvidado = qtdConvidado;
	}

	public double getValUnitConvidado() {
		return valUnitConvidado;
	}

	public void setValUnitConvidado(double valUnitConvidado) {
		this.valUnitConvidado = valUnitConvidado;
	}

	public double getValTotalConvidados() {
		return valTotalConvidados;
	}

	public void setValTotalConvidados(double valTotalConvidados) {
		this.valTotalConvidados = valTotalConvidados;
	}

	public int getQtdGarcon() {
		return qtdGarcon;
	}

	public void setQtdGarcon(int qtdGarcon) {
		this.qtdGarcon = qtdGarcon;
	}

	public double getValUnitGarcon() {
		return valUnitGarcon;
	}

	public void setValUnitGarcon(double valUnitGarcon) {
		this.valUnitGarcon = valUnitGarcon;
	}

	public double getValTotalGarcon() {
		return valTotalGarcon;
	}

	public void setValTotalGarcon(double valTotalGarcon) {
		this.valTotalGarcon = valTotalGarcon;
	}

	public String getOptSobremesa() {
		return optSobremesa;
	}

	public void setOptSobremesa(String optSobremesa) {
		this.optSobremesa = optSobremesa;
	}

	public double getTaxaSobremesa() {
		return taxaSobremesa;
	}

	public void setTaxaSobremesa(double taxaSobremesa) {
		this.taxaSobremesa = taxaSobremesa;
	}

	public double getTotalOcr() {
		return totalOcr;
	}

	public void setTotalOcr(double totalOcr) {
		this.totalOcr = totalOcr;
	}

	
	
}
