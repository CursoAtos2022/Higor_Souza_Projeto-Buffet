package prjBuffet.DB_Digital_Buffet.Controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		
		//INSTANCIAÇÃO DAO
		DAO<Orcamento> daoOcr = new DAO<>(Orcamento.class);
		
		//PERSISTENCIA DOS DADOS
		daoOcr.abrirConexao();
		System.out.println("Conexao aberta!");
		
		daoOcr.registrar(ocr);
		System.out.println("Dados Registrados!");
		
		daoOcr.fechaConexao();
		System.out.println("Conexao fechada!");
		
	
		/*HTML RESPONSE  writer.println("  ");*/
		PrintWriter writer = response.getWriter();
	
		writer.println("<html>");
		writer.println(" <head> ");
			writer.println("  ");
			writer.println(" <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> ");
			writer.println("<title>DB</title>");
			writer.println(" <!-- BOOTSTRAP CSS --><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi\" crossorigin=\"anonymous\"> ");
		writer.println(" </head> ");
		writer.println("<body class=\"text-bg-dark p-3\">");
			
			writer.println(" <!-- BOOTSTRAP JS --><script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3\" crossorigin=\"anonymous\"></script> ");
			
			//DIV-TABLE
			writer.println("<br><div class=\"container\">");
		
				writer.printf(" Caro(a) %s, seus dados foram registrados com sucesso!", nomeCliente);
				writer.println(" <br>Confira abaixo: ");
			
				//TABLE
				writer.println("<table class=\"table table-dark table-striped\">");
				
				writer.println(" <thead> ");
					
					//LOGO
					writer.println(" <tr> ");
						writer.println(" <td rowspan=\"3\" class=\"logo\">DIGITAL BUFFET</td><th class=\"tituloProposta\" colspan=\"4\"scope=\"col\">ORÇAMENTO</th> ");
					writer.println(" </tr> ");
					
					writer.println(" <tr class=\"dadosEsdmpresa\"> ");
						writer.println(" <td>EMPRESA:<br>Digital Buffet</td><td>CNPJ:<br>100200300400-500</td><td colspan=\"2\">ENDEREÇO DA EMPRESA:<br>Rua Fictício/Bairro: Ficção/Número: 0</td> ");
					writer.println(" </tr> ");
					
					writer.println(" <tr class=\"empresaContato\"> ");
						writer.println(" <td colspan=\"2\" class=\"espaco\"></td><td>TELEFONE:<br>(15)99999-9999</td><td>E-MAIL:<br>db@email.com</td> ");
					writer.println(" </tr> ");
					
					writer.println(" <tr> ");
						writer.println(" <th scope=\"col\">DADOS DO CLIENTE</th><th scope=\"col\">DESCRIÇÃO</th><th scope=\"col\">QUANTIDADE</th><th scope=\"col\">PREÇO UNITÁRIO</td><th scope=\"col\">MONTANTE</th> ");
					writer.println(" </tr> ");
					
				writer.println(" </thead> ");
				
				writer.println(" <tbody> ");
					//LINHA 1
					writer.println(" <tr> ");
						writer.println(" <!-- NUMERO ORCAMENTO --><td name=\"nOcr\">Número Orçamento:<br>####</td> ");
						writer.println(" <!-- DESCRICAO ITEM 1 - CONVIDADOS: VAL DO PRATO QUENTER $22,90--><th scope=\"col\" name=\"descItem1\">CONVIDADOS</th> ");
						writer.printf(" <!-- QUANTIDADE ITEM 1 --><td name=\"qtdItem1\">%d</td> ", qtdConvidadoInt);
						writer.printf(" <!-- PRECO UNITARIO ITEM 1 --><td name=\"precoItem1\">R$ %f</td> ", valUnitConvidado);
						writer.printf(" <!-- MONTANTE ITEM 1 --><td name=\"totalItem1\">R$ %f</td> ", valTotalConvidados);
					writer.println(" </tr> ");
					
					//LINHA 2
					writer.println(" <tr> ");
						writer.printf(" <!-- NOME DO CLIENTE --><td name=\"nomeCliente\">Nome cliente:<br> %s</td> ", nomeCliente);
						writer.println(" <!-- DESCRICAO ITEM 2 - GARÇON: 1/15Convidados - R$250,00/GARÇON--><th scope=\"col\" name=\"descItem2\">GARÇON</th> ");
						writer.printf(" <!-- QUANTIDADE ITEM 2 --><td name=\"qtdItem2\">%d</td> ", qtdGarcon);
						writer.printf(" <!-- PRECO UNITARIO ITEM 2 --><td name=\"precoItem2\">R$ %f</td> ", valUnitGarcon);
						writer.printf(" <!-- MONTANTE ITEM 2 --><td name=\"totalItem2\">R$ %f</td> ", valTotalGarcon);
					writer.println(" </tr> ");
				
					//LINHA 3
					writer.println(" <tr> ");
						writer.printf(" <!-- ENDERECO CLIENTE --><td name = \"enderecoCliente\">Endereço:<br> %s</td> ", enderecoCliente);
						writer.println(" <!-- DESCRICAO ITEM 3 - SOBREMESA: 10% do valor total/CONVIDADO--><th scope=\"col\" name=\"descItem3\">SOBREMESA</th> ");
						writer.printf(" <!-- QUANTIDADE ITEM 3 --><td name=\"qtdItem3\">%d</td>", qtdConvidadoInt);
						writer.printf(" <!-- PRECO UNITARIO ITEM 3 --><td name=\"precoItem3\">R$ ####</td> ");
						writer.printf(" <!-- MONTANTE ITEM 3 --><td name=\"totalItem3\">R$ %f</td> ", taxaSobremesa);
					writer.println(" </tr> ");
					
					//LINHA 4
					writer.println(" <tr> ");
						writer.printf(" <!-- TELEFONE CLIENTE --><td name = \"telefoneCliente\">Telefone:<br> %s</td> ", telefoneCliente);
						writer.println(" <!-- ############## --><td name=\"descItem4\"></td> ");
						writer.printf(" <!-- QUANTIDADE ITEM 4 --><td name=\"qtdItem4\"></td>");
						writer.printf(" <!-- PRECO UNITARIO ITEM 4 --><td name=\"precoItem4\"></td> ");
						writer.printf(" <!-- MONTANTE ITEM 4 --><td name=\"totalItem4\"></td>");
					writer.println(" </tr> ");
					
					//LINHA 5
					writer.println(" <tr> ");
						writer.printf(" <!-- E-MAIL CLIENTE --><td name = \"emailCliente\">E-mail:<br> %s</td> ", emailCliente);
						writer.println(" <!-- ############## --><td></td> ");
						writer.printf(" <!-- ############## --><td></td>");
						writer.printf(" <!-- ############## --><th scope=\"col\">TOTAL -></th> ");
						writer.printf(" <!-- TOTAL --><th scope=\"col\" name=\"totalOcr\">R$ %f</th>", totalOcr);
					writer.println(" </tr> ");
					
				writer.println(" </tbody> ");
				
				writer.println("</table>");
				
				writer.println("<br>Você pode visualizar novamente ou editar os dados inseridos, clicando no botão abaixo!");
				writer.println("<br><br><button onclick=\"window.location.href='consultaOcr.html';\">CONSULTAR ORÇAMENTO</button>");
				
			writer.println("</div>");
		writer.println("</body>");
		writer.println("</html>");
		
		
		}
	
}