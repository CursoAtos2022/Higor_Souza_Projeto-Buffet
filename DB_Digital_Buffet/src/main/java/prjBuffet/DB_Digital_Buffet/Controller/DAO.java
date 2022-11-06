package prjBuffet.DB_Digital_Buffet.Controller;
import javax.persistence.*;

import prjBuffet.DB_Digital_Buffet.Model.Orcamento;

public class DAO<E> {

	//CRIANDO O GERENCIADOR FABRICANTE DE ENTIDADE
	//QUE PERMITE CRIAR O GERENCIADOR DE ENTIDADE - RESPONSAVEL PELA PERSISTENCIA DOS DADOS
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("db_digital_buffet");
	EntityManager em;
	
	private Class<E> entidade;

	//CONSTRUTOR PASANDO ENTIDADE COMO PARAMETRO
	public DAO(Class<E> entidade) {
		super();
		this.entidade = entidade;
		
		//CRIA ENTIDADE
		em = emf.createEntityManager();
	}
	
	//METODOS CRUD
	//METODO QUE ABRE CONEXAO COM BD
	public DAO<E> abrirConexao(){
		em.getTransaction().begin();
		return this;
	}
	
	//CREATE
	//METODO DE REGISTRO NO BANCO
	public DAO<E> registrar(E entidade){
		em.persist(entidade);
		return this;
	}
	
//********************************************	
	//READ
	//METODO DE BUSCA NO BANCO
	//PARAMETRO DE BUSCA (findOcr)
	public Orcamento buscar(String findOcr) {
		/*
		return em.find(entidade, findOcr);
		*/
		String query = String.format("from Orcamento where nomeCliente = '%s'", findOcr);
		
		//CRIA-SE UMA VARIAVEL DE TIPO ORCAMENTO PARA PASSAR A QUERY E RETORNAR UM UNICO RESULTADO DO TIPO ORCAMENTO
		Orcamento orcamento = em.createQuery(query, Orcamento.class).getSingleResult();
		
		return orcamento;
	}
	
//********************************************
	//UPDATE
	//METODO DE ATUALIZACAO DOS DADOS 
	//PARAMETRO DE BUSCA (findOcr)
	public Orcamento atualizar(String findOcr, String nomeCliente, String enderecoCliente, String telefoneCliente, String emailCliente,
					 		   int qtdConvidado, double valUnitConvidado, double valTotalConvidados, int qtdGarcon, double valUnitGarcon,
								 double valTotalGarcon, String optSobremesa, double taxaSobremesa, double totalOcr) {
	
		DAO<E> dao = new DAO<E>(entidade);
		dao.abrirConexao();
		
		Orcamento ocr = (Orcamento) dao.buscar(findOcr);
		ocr.setNomeCliente(nomeCliente);
		ocr.setEnderecoCliente(enderecoCliente);
		ocr.setTelefoneCliente(telefoneCliente); 
		ocr.setEmailCliente(emailCliente);
		ocr.setQtdConvidado(qtdConvidado);
		ocr.setValUnitConvidado(valUnitConvidado);
		ocr.setValTotalConvidados(valTotalConvidados);
		ocr.setQtdGarcon(qtdGarcon);
		ocr.setValUnitGarcon(valUnitGarcon);
		ocr.setValTotalGarcon(valTotalGarcon);
		ocr.setOptSobremesa(optSobremesa);
		ocr.setTaxaSobremesa(taxaSobremesa);
		ocr.setTotalOcr(totalOcr);
		
		em.merge(ocr);
		
		return ocr;
		
	}
	
	/*
//********************************************
	//DELETE
	//METODO DE DELECAO NO BANCO 
	//PARAMETRO DE BUSCA (findOcr)
	public DAO<E> deletar(String findOcr){
		DAO<E> dao = new DAO<E>(entidade);
		E entidadeEncontrada = dao.buscar(findOcr);
		em.remove(em.contains(entidadeEncontrada)? entidadeEncontrada : em.merge(entidadeEncontrada));
		return this;
	}
	*/
		
	//METODO FECHA CONEXAO COM BANCO
	public DAO<E> fechaConexao(){
		em.getTransaction().commit();
		return this;
	}
	
}
