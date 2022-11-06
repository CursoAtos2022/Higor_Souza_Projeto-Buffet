package prjBuffet.DB_Digital_Buffet.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import prjBuffet.DB_Digital_Buffet.Model.Orcamento;

@WebServlet(name = "gerarOrcamento", value = "/consultaOcr")
public class consultaOrcamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//INSTANCIAÇÃO DAO
	DAO<Orcamento> daoOcr = new DAO<>(Orcamento.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String findOcr = request.getParameter("findOcr");
				
		//PERSISTENCIA DOS DADOS
		daoOcr.abrirConexao();
		System.out.println("Conexao aberta!");
		
		//REALIZANDO BUSCA NO BANCO
		Orcamento orcamento = daoOcr.buscar(findOcr);
		System.out.println("Buscando Dados...");
		
		//IMPRIMINDO DADOS ENCONTRADOS PARA O USUARIO
		response.getWriter().append
		("Nome: " + orcamento.getNomeCliente() + " | " + "Endereco: " + orcamento.getEnderecoCliente() + " | " + "Tel: " + orcamento.getTelefoneCliente() + " | " + "Email: " + orcamento.getEmailCliente() + "\n" +
		 "QTD Convidado: " + orcamento.getQtdConvidado() + " | " + "Valor/Convidado: " + orcamento.getValUnitConvidado() + " | " + "Valor Total Convidado: " +  orcamento.getValTotalConvidados() + "\n" + 
		 "QTD Garçon: " + orcamento.getQtdGarcon() + " | " + "Valor/Garçon: " + orcamento.getValUnitGarcon() + " | " + "Valor Total Garçon: " + orcamento.getValTotalGarcon() + "\n" + 
		 "OPT Sobremesa: " + orcamento.getOptSobremesa() + " | " + "Taxa Sobremesa: " + orcamento.getTaxaSobremesa() + "\n" + 
		 "Total Oçamento: " + orcamento.getTotalOcr());
			
		//FECHANDO CONEXAO COM BD
		daoOcr.fechaConexao();
		System.out.println("Conexao fechada!");
	}

}
