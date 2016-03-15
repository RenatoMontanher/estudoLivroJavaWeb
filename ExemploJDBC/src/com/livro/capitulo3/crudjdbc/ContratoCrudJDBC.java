package com.livro.capitulo3.crudjdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.InstanceAlreadyExistsException;
import javax.naming.directory.InvalidSearchFilterException;

public class ContratoCrudJDBC {
	public void salvar(Contato contato){
		Connection conexao = this.geraConexao();
		PreparedStatement insereSt = null; 
		
		
		String sql = "insert into contato(nome, telefone, email, dt_cad, obs) values (?,?,?,?,?)";
		
		try {
			insereSt = conexao.prepareStatement(sql);
			insereSt.setString(1,  contato.getNome());
			insereSt.setString(2,  contato.getTelefone());
			insereSt.setString(3, contato.getEmail());
			insereSt.setDate(4, contato.getDataCadastro());
			insereSt.setString(5, contato.getObservacao());
			insereSt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao incluir contato. Mensagem: "+ e.getMessage());
		}finally {
			
			try {
				insereSt.close();
				conexao.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de inserção.Mensagem : "+ e.getMessage());
			}
		}
		
	}
	
	//atualizar e excluir é praticamente a mesma implementacao
	public void atualizar(Contato contato){}
	public void excluir (Contato ccontato){}
	
	public List<Contato> listar(){
		Connection conexao = this.geraConexao();
		List <Contato> contatos = new ArrayList<Contato>();
		Statement consulta = null;
		ResultSet resultado = null;
		Contato contato = null;
		
		String sql = "select * from contato";
		try {
			consulta = conexao.createStatement();
			resultado = consulta.executeQuery(sql);
			
			while(resultado.next()){
				contato = new Contato();
				contato.setCodigo(new Integer(resultado.getInt("codigo")));
				contato.setNome(resultado.getString("nome"));
				contato.setTelefone(resultado.getString("telefone"));
				contato.setEmail(resultado.getString("email"));
				contato.setDataCadastro(resultado.getDate("dt_cad"));
				contato.setObservacao(resultado.getString("obs"));
				contatos.add(contato);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar o código do contato. Mensagem: "+ e.getMessage());
		}finally {
			try {
				
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de consulta. Mensagem: "+e.getMessage());
			}
		}
		
		return contatos;
	}
	public Contato buscaContato(int valor){
		return null;}
	public Connection geraConexao(){
		
		Connection conexao = null;
		try {
			//Registrando a classe JDBC no sistema em tempo de execucao
			String url = "jdbc:mysql://localhost/agenda";
			String usuario = "root";
			String senha = "admin";
			conexao = DriverManager.getConnection(url,usuario,senha);
			System.out.println("Conectou!!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro de SQL. Erro: "+e.getMessage());
		}
		return conexao;
		}
	public static void main(String[] args) {
		ContratoCrudJDBC contratoCRUDJDBC = new ContratoCrudJDBC();
		
		//Criando um primeiro contato
		Contato beltrano = new Contato();
		beltrano.setNome("Beltrano solar");
		beltrano.setTelefone("(11)1111111111");
		beltrano.setEmail("beltrano@hotmail.com");
		beltrano.setDataCadastro(new Date(System.currentTimeMillis()));
		beltrano.setObservacao("Novo Cliente");
		contratoCRUDJDBC.salvar(beltrano);
		
		
		Contato fulano = new Contato();
		fulano.setNome("fulano fulano");
		fulano.setTelefone("(22)1111111111");
		fulano.setEmail("fulano@hotmail.com");
		fulano.setDataCadastro(new Date(System.currentTimeMillis()));
		fulano.setObservacao("Novo Cliente fulano");
		contratoCRUDJDBC.salvar(fulano);
		
		System.out.println("Contatos cadastrados " + contratoCRUDJDBC.listar().size() );
		
	}
	
}
