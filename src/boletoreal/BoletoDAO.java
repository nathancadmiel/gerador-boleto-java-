package boletoreal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BoletoDAO {

    public void salvar(Boleto b) {
        String sql = "INSERT INTO boletos (banco, agencia, conta, nosso_numero, linha_digitavel, pagador, cedente, valor, data_emissao, data_vencimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, b.getBanco());
            stmt.setString(2, b.getAgencia());
            stmt.setString(3, b.getConta());
            stmt.setString(4, b.getNossoNumero());
            stmt.setString(5, b.getLinhaDigitavel());
            stmt.setString(6, b.getPagador());
            stmt.setString(7, b.getCedente());
            stmt.setDouble(8, b.getValor());
            stmt.setString(9, b.getDataEmissao());
            stmt.setString(10, b.getDataVencimento());

            stmt.executeUpdate();
            System.out.println("Dados salvos no MySQL com sucesso!");

        } catch (SQLException e) {
            System.err.println("❌ Erro ao salvar no banco: " + e.getMessage());
            e.printStackTrace();
        }
    }
}