package prjBuffet.DB_Digital_Buffet.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import prjBuffet.DB_Digital_Buffet.Model.Orcamento;

@WebServlet(name = "gerarOrcamento", value = "/gerarOcr")
public class gerarOrcamento extends HttpServlet {
	
	private static final long serialVersionUID = 1L;   
	
	//METODO POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomeCliente = request.getParameter("nomeCliente");
		String enderecoCliente = request.getParameter("enderecoCliente");
		String telefoneCliente = request.getParameter("telefoneCliente");
		String emailCliente = request.getParameter("emailCliente");
		
		//VALOR PRATO QUENTE
		double valUnitPrato = 22.90;
		
		//CONVIDADO________________________________________________
		//RECEBE DADO EM STRING 
		String qtdConvidado = request.getParameter("qtdConvidado");
		//CONVERTE EM INTEIRO
		int qtdConvidadoInt = Integer.parseInt(qtdConvidado);
		//DETERMINANDO VALOR POR CONVIDADO
		double valUnitConvidado = valUnitPrato;
		double valTotalConvidados = qtdConvidadoInt * valUnitPrato;		
		
		//GARÇON____________________________________________________
		int qtdGarcon = qtdConvidadoInt/15;
		double valUnitGarcon = 250.00;
		double valTotalGarcon = valUnitGarcon * qtdGarcon;
		
		//SOBREMESA_________________________________________________
		//RECEBE DADO EM STRING
		String optSobremesa = request.getParameter("optSobremesa");
		double taxaSobremesa;
		
		if(optSobremesa == null) {
			taxaSobremesa = 0.00;
		}else{
			taxaSobremesa = valTotalConvidados * 0.10;
		}

		//TOTAL ORCAMENTO
		double totalOcr = valTotalConvidados+valTotalGarcon+taxaSobremesa;
		
		
		//INSTANCIAÇÃO ORÇAMENTO
		Orcamento ocr = new Orcamento(nomeCliente, enderecoCliente, telefoneCliente, emailCliente,
									  qtdConvidadoInt, valUnitConvidado, valTotalConvidados, qtdGarcon, valUnitGarcon,
									  valTotalGarcon, optSobremesa, taxaSobremesa, totalOcr);
		
		
		//PRINTS DE TESTES
		System.out.println
		("Nome: " + nomeCliente + " | " + "Endereco: " + enderecoCliente + " | " + "Tel: " + telefoneCliente + " | " + "Email: " + emailCliente + "\n" +
		"QTD Convidado: " + qtdConvidadoInt + " | " + "Valor/Convidado: " + valUnitConvidado + " | " + "Valor Total Convidado: " +  valTotalConvidados + "\n" + 
		"QTD Garçon: " + qtdGarcon + " | " + "Valor/Garçon: " + valUnitGarcon + " | " + "Valor Total Garçon: " + valTotalGarcon + "\n" + 
		"OPT Sobremesa: " + optSobremesa + " | " + "Taxa Sobremesa: " + taxaSobremesa + "\n" + 
		"Total Oçamento: " + totalOcr);
		
		//INSTANCIAÇÃO DAO
		DAO<Orcamento> daoOcr = new DAO<>(Orcamento.class);
		
		//PERSISTENCIA DOS DADOS
		daoOcr.abrirConexao();
		System.out.println("Conexao aberta!");
		
		daoOcr.registrar(ocr);
		System.out.println("Dados Registrados!");
		
		daoOcr.fechaConexao();
		System.out.println("Conexao fechada!");
		
		}
	
}