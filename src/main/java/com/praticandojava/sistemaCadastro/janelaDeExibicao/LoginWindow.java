package com.praticandojava.sistemaCadastro.janelaDeExibicao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginWindow extends JFrame{

    private JTextField campoUsuario = new JTextField();
    private JPasswordField campoSenha = new JPasswordField();

    public LoginWindow() {
        // Criar e configurar a janela
        JFrame cadastro = new JFrame();
        cadastro.setTitle("login");
        cadastro.setSize(400, 400);
        cadastro.setLocationRelativeTo(null);
        cadastro.setResizable(false);
        cadastro.setFont(new Font("Calibri", Font.BOLD, 40));
        cadastro.setVisible(true);
        cadastro.setLayout(null);
        // Cria os componentes
        JLabel usuario = new JLabel("Usuário");
        JLabel senha = new JLabel("Senha");

        JButton botaoLogin = new JButton("Entrar");
        JButton botaoCadastrar = new JButton("Cadastrar");

        // Configurando o botao login
        botaoLogin.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String user = campoUsuario.getText();
                String password = new String(campoSenha.getPassword());

                if (autenticar(user, password)) {
                    JOptionPane.showMessageDialog(null, "Login efetuado com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
                }
            }
        });

        // Configurando o botao cadastrar
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterWindow();
            }
        });

        // Adiciona os componentes
        cadastro.add(usuario);
        usuario.setBounds(100, 100, 100, 30);
        cadastro.add(campoUsuario);
        campoUsuario.setBounds(150, 100, 100, 30);
        cadastro.add(senha);
        senha.setBounds(100, 150, 100, 30);
        cadastro.add(campoSenha);
        campoSenha.setBounds(150, 150, 100, 30);
        cadastro.add(botaoLogin);
        botaoLogin.setBounds(150, 200, 100, 30);
        cadastro.add(botaoCadastrar);
        botaoCadastrar.setBounds(150, 250, 100, 30);
        cadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    // Autenticação
    private boolean autenticar(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConectaBanco.fazConexao();
            if (con == null) {
                JOptionPane.showMessageDialog(null, "Erro na conexão", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String sql = "SELECT * FROM cadastro where usuario = ? and senha = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro na autenticação", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginWindow();
            }
        });
    }
}
