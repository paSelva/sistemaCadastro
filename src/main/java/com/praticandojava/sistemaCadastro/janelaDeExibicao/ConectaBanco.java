package com.praticandojava.sistemaCadastro.janelaDeExibicao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBanco {

    private static String caminho = "jdbc:mysql://localhost:3306/sistemacadastro";
    private static String usuario = "root";
    private static String senha = "P@gsf2891";


    public static Connection fazConexao() throws SQLException {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(caminho, usuario, senha);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(null, "Erro na conexão");
        }

        return con;
    }

}
