package br.com.contatos.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.contatos.helper.MySQLConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ContatoController implements Initializable{

	@FXML TextField txtNome;
	@FXML TextField txtTelefone;
	@FXML Button btnInserir;
	@FXML ListView lstContatos;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		preencherLista();
	}

	@FXML public void inserirContato() {

		//Conexão com Banco de Dados
		Connection con = MySQLConnect.ConectarDb();

		//String SQL
		String sql = "insert into contact(name, phone) values(? , ?)";

		//Preparando os parâmetros
		PreparedStatement parametros;
		try {
			//Passando String a ser preparada
			parametros = con.prepareStatement(sql);

			//Setando os parâmetros por index
			parametros.setString(1, txtNome.getText());
			parametros.setString(2, txtTelefone.getText());

			//Executando a Query
			//OBS IMPORTANTE: Execute update é para atualizar registros no banco, já execute
			//query é para consultas(selects)
			parametros.executeUpdate();

			//Fechando a conexão
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		preencherLista();
	}

	private void preencherLista(){

		Connection con = MySQLConnect.ConectarDb();

		lstContatos.getItems().clear();

		String sql = "select * from contact";

		try {
			//Recebendo dados do Banco de Dados
			ResultSet rs = con.createStatement().executeQuery(sql);

			//Preenchendo a Lista com os dados do banco
			//OBS IMPORTANTE: O objeto rs é um "cursor", ou seja é necessário o next
			while(rs.next()){
				String contato = "";

				//Recenbendo os dados pelo index da tabela...
				contato += rs.getString("name");
				contato += " - ";
				contato += rs.getString("phone");

				lstContatos.getItems().add(contato);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}