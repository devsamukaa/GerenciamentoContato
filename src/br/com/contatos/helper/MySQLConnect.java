package br.com.contatos.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnect {

	public static Connection ConectarDb(){

		Connection con = null;

		try{

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.
					getConnection("jdbc:mysql://10.107.134.47/contatos","root", "root");


		}catch(Exception ex){
			System.out.println(ex);
		}

		return con;
	}
}
