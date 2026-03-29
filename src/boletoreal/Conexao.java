package boletoreal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    // Adicionei o serverTimezone para evitar erros comuns no Windows/XAMPP
    private static final String URL = "jdbc:mysql://localhost:3306/boleto_db?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = ""; 

    public static Connection conectar() throws SQLException {
        try {
            // Força o carregamento do driver (importante em algumas versões do Eclipse)
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL não encontrado no Build Path!");
        }
    }
}