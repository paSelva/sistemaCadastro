package com.praticandojava.sistemaCadastro.janelaDeExibicao;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginWindow loginWindow;
    private RegisterWindow registerWindow;

    public MainWindow() {
        super("Main Window");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setFont(new Font("Calibri", Font.BOLD, 40));

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        loginWindow = new LoginWindow(this);
        registerWindow = new RegisterWindow(this);

        mainPanel.add(loginWindow, "Login");
        mainPanel.add(registerWindow, "Registrar");

        JPanel buttonsPanel = new JPanel();
        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> switchToLoginWindow());
        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(e -> switchToRegisterWindow());
        buttonsPanel.add(btnLogin);
        buttonsPanel.add(btnRegister);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        cardLayout.show(mainPanel, "Login");


    }

    public void switchToRegisterWindow() {
        cardLayout.show(mainPanel, "Registrar");
    }

    public void switchToLoginWindow() {
        cardLayout.show(mainPanel, "Login");
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);

        });
    }

}
