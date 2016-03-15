package com.livro.capitulo3.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaMysql {

	public static void main(String[] args) {
		
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
		}finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar a conexao!! Erro: "+e.getMessage());
			}
		}
	}
	
}
