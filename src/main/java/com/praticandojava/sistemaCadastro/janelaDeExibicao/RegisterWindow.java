package com.praticandojava.sistemaCadastro.janelaDeExibicao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterWindow extends JFrame {

    private JTextField campoUsuario = new JTextField();
    private JPasswordField campoSenha = new JPasswordField();
    private JPasswordField campoConfirmarSenha = new JPasswordField();

    public RegisterWindow() {

        // Criar e configurar a janela
        JFrame cadastro = new JFrame();
        cadastro.setTitle("cadastro");
        cadastro.setSize(400, 400);
        cadastro.setLocationRelativeTo(null);
        cadastro.setResizable(false);
        cadastro.setFont(new Font("Calibri", Font.BOLD, 40));
        cadastro.setVisible(true);
        cadastro.setLayout(null);

        // Cria os componentes
        JLabel usuario = new JLabel("Usuário");
        JLabel senha = new JLabel("Senha");
        JLabel confirmarSenha = new JLabel("Confirmar Senha");

        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoVoltar = new JButton("Voltar");


        // Configurando o botao cadastrar
        botaoCadastrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed (ActionEvent e){
                String user = campoUsuario.getText();
                String password = new String(campoSenha.getPassword());
                String confirmPassword = new String(campoConfirmarSenha.getPassword());

                if (password.equals(confirmPassword)) {
                    if (registrar(user,password)){
                        JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao registrar novo usuário","Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Senhas não conferem", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        // Configurando o botao voltar
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastro.dispose();
                new LoginWindow();
            }
        });

        // Adiciona os componentes na janela
        cadastro.add(campoUsuario);
        cadastro.add(campoSenha);
        cadastro.add(campoConfirmarSenha);
        cadastro.add(botaoCadastrar);
        cadastro.add(usuario);
        cadastro.add(senha);
        cadastro.add(confirmarSenha);
        cadastro.add(botaoVoltar);
        usuario.setBounds(50, 15, 100, 30);
        campoUsuario.setBounds(100, 45, 200, 30);
        senha.setBounds(50, 75, 150, 30);
        campoSenha.setBounds(100, 105, 200, 30);
        confirmarSenha.setBounds(50, 135, 200, 30);
        campoConfirmarSenha.setBounds(100, 165, 200, 30);
        botaoCadastrar.setBounds(200, 315, 150, 30);
        botaoVoltar.setBounds(25, 315, 150, 30);
    }

    private boolean registrar(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;

        try{
            con = ConectaBanco.fazConexao();
            if (con == null) {
                JOptionPane.showMessageDialog(null, "Erro na conexão", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String sql = "INSERT INTO cadastro (usuario, senha) VALUES (?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, password);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao registrar novo usuário", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegisterWindow();
            }
        });
    }

}