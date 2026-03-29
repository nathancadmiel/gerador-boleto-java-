package boletoreal;

public class GeradorBoleto {
    public static void main(String[] args) {
        Boleto boleto = new BoletoBuilder()
                .banco("001")
                .agencia("1234")
                .conta("987654")
                .nossoNumero("00012345678")
                .linhaDigitavel("00190000090123456789012345678901200000000010000")
                .pagador("Nathan Cadmiel")
                .cedente("IFBA - Campus Eunápolis")
                .valor(100.00)
                .dataEmissao("29/03/2026")
                .dataVencimento("03/04/2026")
                .build();

        // 1. Salva no banco
        BoletoDAO dao = new BoletoDAO();
        dao.salvar(boleto);

        // 2. Gera o PDF
        GeradorPdf.gerarPdf(boleto);
    }
}