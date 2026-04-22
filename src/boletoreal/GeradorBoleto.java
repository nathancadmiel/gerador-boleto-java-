package boletoreal;

public class GeradorBoleto {

    public static void main(String[] args) {

        String meuNome = "Natan Cadmiel Alves Ramos";

        Boleto boletoItau = new BoletoBuilder()
                .banco("341")
                .agencia("1500")
                .conta("44556-1")
                .nossoNumero("12345678")
                .cedente(meuNome, "123.456.789-00", "Rua das Oliveiras, 100 - Eunápolis/BA")
                .pagador("Carlos Eduardo", "987.654.321-11", "Av. Paulista, 1500 - Eunápolis/BA")
                .valor(500.00)
                .dataEmissao("14/04/2026")
                .dataVencimento("20/05/2026")
                .build();

        Boleto boletoBradesco = new BoletoBuilder()
                .banco("237")
                .agencia("1111")
                .conta("0002222-5")
                .nossoNumero("00075896452")
                .cedente(meuNome, "123.456.789-00", "Rua das Oliveiras, 100 - Eunápolis/BA")
                .pagador("Gabriel Maciel", "111.222.333-44", "Rua Direita, 50 - Eunápolis/BA")
                .valor(1200.00)
                .dataEmissao("14/04/2026")
                .dataVencimento("26/05/2026")
                .build();

        Boleto boletoNu = new BoletoBuilder()
                .banco("260")
                .agencia("0001")
                .conta("9876543-2")
                .nossoNumero("20269991234")
                .cedente(meuNome, "123.456.789-00", "Rua das Oliveiras, 100 - Eunápolis/BA")
                .pagador("Ana Ramos", "555.666.777-88", "Al. Santos, 10 - Eunápolis/BA")
                .valor(320.00)
                .dataEmissao("14/04/2026")
                .dataVencimento("25/05/2026")
                .build();

        BoletoDAO dao = new BoletoDAO();

        try {
            dao.salvar(boletoItau);
            dao.salvar(boletoBradesco);
            dao.salvar(boletoNu);

            GeradorPdf.gerarPdf(boletoItau);
            GeradorPdf.gerarPdf(boletoBradesco);
            GeradorPdf.gerarPdf(boletoNu);

            System.out.println("Processo concluído com sucesso.");
            System.out.println("Linhas digitáveis geradas automaticamente.");
        } catch (Exception e) {
            System.err.println("Erro ao processar boletos: " + e.getMessage());
        }
    }
}