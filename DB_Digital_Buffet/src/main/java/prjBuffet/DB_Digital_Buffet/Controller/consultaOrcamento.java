package prjBuffet.DB_Digital_Buffet.Controller;

import java.io.IOException;
import java.io.PrintWriter;

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

			
		//FECHANDO CONEXAO COM BD
		daoOcr.fechaConexao();
		System.out.println("Conexao fechada!");
		
		/*HTML RESPONSE  writer.println("  ");*/
		PrintWriter writer = response.getWriter();
	
		writer.println("<html>");
		writer.println(" <head> ");
			writer.println("<title>DB</title>");
			writer.println(" <link href=\"style.css\" rel=\"stylesheet\"> ");
		writer.println(" </head> ");
		writer.println("<body>");
				
				//FORM DE BUSCA
				writer.println("<!-- FORM DE BUSCA DE DADOS --><div class=\"formBusca\">");
			
					writer.println("<form method=\"POST\" action=\"consultaOcr\">");
				
						writer.println("<p>Insira seu nome:</p>");
						writer.println("<input name=\"findOcr\"/>");
						writer.println("<button type=\"submit\">BUSCAR ORÇAMENTO</button>");
				
					writer.println("</form>");
				
				writer.println("</div>");
		
			writer.printf(" Caro(a) %s, seus dados foram carregados com sucesso!", orcamento.getNomeCliente());
			writer.println(" <br>Confira abaixo: ");
			
			//TABELA
			writer.println("<br><div class=\"tableContainer\">");
				writer.println("<table class=\"tableProposta\">");
				
				writer.println(" <thead> ");
					
					//LOGO
					writer.println(" <tr> ");
						writer.println(" <td rowspan=\"3\" class=\"logo\">DIGITAL BUFFET</td><td class=\"tituloProposta\" colspan=\"4\">ORÇAMENTO</td> ");
					writer.println(" </tr> ");
					
					writer.println(" <tr class=\"dadosEsdmpresa\"> ");
						writer.println(" <td>Nome da empresa:<br>Digital Buffet</td><td>CNPJ:<br>100200300400-500</td><td colspan=\"2\">Endereço da empresa:<br>Rua Fictício/Bairro: Ficção/Número: 0</td> ");
					writer.println(" </tr> ");
					
					writer.println(" <tr class=\"empresaContato\"> ");
						writer.println(" <td colspan=\"2\" class=\"espaco\"></td><td>Telefone:<br>(15)99999-9999</td><td>E-mail:<br>db@email.com</td> ");
					writer.println(" </tr> ");
					
					writer.println(" <tr> ");
						writer.println(" <td>Dados do Cliente</td><td>DESCRIÇÃO</td><td>QUANTIDADE</td><td>PREÇO UNITÁRIO</td><td>MONTANTE</td> ");
					writer.println(" </tr> ");
					
				writer.println(" </thead> ");
				
				writer.println(" <tbody> ");
					//LINHA 1
					writer.println(" <tr> ");
						writer.println(" <!-- NUMERO ORCAMENTO --><td name=\"nOcr\">Número Orçamento:<br>####</td> ");
						writer.println(" <!-- DESCRICAO ITEM 1 - CONVIDADOS: VAL DO PRATO QUENTER $22,90--><td name=\"descItem1\">CONVIDADOS</td> ");
						writer.printf(" <!-- QUANTIDADE ITEM 1 --><td name=\"qtdItem1\">%d</td> ", orcamento.getQtdConvidado());
						writer.printf(" <!-- PRECO UNITARIO ITEM 1 --><td name=\"precoItem1\">R$ %f</td> ", orcamento.getValUnitConvidado());
						writer.printf(" <!-- MONTANTE ITEM 1 --><td name=\"totalItem1\">R$ %f</td> ", orcamento.getValTotalConvidados());
					writer.println(" </tr> ");
					
					//LINHA 2
					writer.println(" <tr> ");
						writer.printf(" <!-- NOME DO CLIENTE --><td name=\"nomeCliente\">Nome cliente:<br> %s</td> ", orcamento.getNomeCliente());
						writer.println(" <!-- DESCRICAO ITEM 2 - GARÇON: 1/15Convidados - R$250,00/GARÇON--><td name=\"descItem2\">GARÇON</td> ");
						writer.printf(" <!-- QUANTIDADE ITEM 2 --><td name=\"qtdItem2\">%d</td> ", orcamento.getQtdGarcon());
						writer.printf(" <!-- PRECO UNITARIO ITEM 2 --><td name=\"precoItem2\">R$ %f</td> ", orcamento.getValUnitGarcon());
						writer.printf(" <!-- MONTANTE ITEM 2 --><td name=\"totalItem2\">R$ %f</td> ", orcamento.getValTotalGarcon());
					writer.println(" </tr> ");
				
					//LINHA 3
					writer.println(" <tr> ");
						writer.printf(" <!-- ENDERECO CLIENTE --><td name = \"enderecoCliente\">Endereço:<br> %s</td> ", orcamento.getEnderecoCliente());
						writer.println(" <!-- DESCRICAO ITEM 3 - SOBREMESA: 10% do valor total/CONVIDADO--><td name=\"descItem3\">SOBREMESA</td> ");
						writer.printf(" <!-- QUANTIDADE ITEM 3 --><td name=\"qtdItem3\">%d</td>", orcamento.getQtdConvidado());
						writer.printf(" <!-- PRECO UNITARIO ITEM 3 --><td name=\"precoItem3\">R$ ####</td> ");
						writer.printf(" <!-- MONTANTE ITEM 3 --><td name=\"totalItem3\">R$ %f</td> ", orcamento.getTaxaSobremesa());
					writer.println(" </tr> ");
					
					//LINHA 4
					writer.println(" <tr> ");
						writer.printf(" <!-- TELEFONE CLIENTE --><td name = \"telefoneCliente\">Telefone:<br> %s</td> ", orcamento.getTelefoneCliente());
						writer.println(" <!-- ############## --><td name=\"descItem4\"></td> ");
						writer.printf(" <!-- QUANTIDADE ITEM 4 --><td name=\"qtdItem4\"></td>");
						writer.printf(" <!-- PRECO UNITARIO ITEM 4 --><td name=\"precoItem4\"></td> ");
						writer.printf(" <!-- MONTANTE ITEM 4 --><td name=\"totalItem4\"></td>");
					writer.println(" </tr> ");
					
					//LINHA 5
					writer.println(" <tr> ");
						writer.printf(" <!-- E-MAIL CLIENTE --><td name = \"emailCliente\">E-mail:<br> %s</td> ", orcamento.getEmailCliente());
						writer.println(" <!-- ############## --><td></td> ");
						writer.printf(" <!-- ############## --><td></td>");
						writer.printf(" <!-- ############## --><td>TOTAL -></td> ");
						writer.printf(" <!-- TOTAL --><td name=\"totalOcr\">R$ %f</td>", orcamento.getTotalOcr());
					writer.println(" </tr> ");
					
				writer.println(" </tbody> ");
				
				writer.println("</table>");
				
				writer.println("<br>Opções:");
				writer.println("<br><br><button>EDITAR</button>");
				writer.println("<button>EXCLUIR</button>");
				
			writer.println("</div>");
		writer.println("</body>");
		writer.println("</html>");
	}

}
