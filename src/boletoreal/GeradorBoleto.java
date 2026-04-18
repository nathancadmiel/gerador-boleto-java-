package boletoreal;

public class GeradorBoleto {
    public static void main(String[] args) {
        
        // 1. ITAÚ (Código 341)
        Boleto boletoItau = new BoletoBuilder()
                .banco("341") 
                .agencia("1500")
                .conta("44556-1")
                .nossoNumero("12345678")
                .linhaDigitavel("34191.50006 45678.901234 56789.012345 1 0000000050000")
                .pagador("Nathan Cadmiel")
                .cedente("Itaú Unibanco")
                .valor(500.00)
                .dataEmissao("09/04/2026")
                .dataVencimento("20/04/2026")
                .build();

        // 2. BRADESCO (Código 237)
        Boleto boletoBradesco = new BoletoBuilder()
                .banco("237") 
                .agencia("0101")
                .conta("0020304-5")
                .nossoNumero("00000001452")
                .linhaDigitavel("23790.01017 00000.014529 02030.450001 8 0000000015000")
                .pagador("Nathan Cadmiel")
                .cedente("Bradesco Seguros")
                .valor(150.00)
                .dataEmissao("09/04/2026")
                .dataVencimento("25/04/2026")
                .build();

        // 3. NUBANK (Código 260)
        Boleto boletoNu = new BoletoBuilder()
                .banco("260") 
                .agencia("0001")
                .conta("9876543-2")
                .nossoNumero("2026999")
                .linhaDigitavel("26090.00006 01234.567890 12345.678901 2 0000000032000")
                .pagador("Nathan Cadmiel")
                .cedente("Nu Pagamentos S.A.")
                .valor(320.00)
                .dataEmissao("09/04/2026")
                .dataVencimento("15/04/2026")
                .build();

        BoletoDAO dao = new BoletoDAO();
        
        try { dao.salvar(boletoItau); } catch (Exception e) {}
        GeradorPdf.gerarPdf(boletoItau);

        try { dao.salvar(boletoBradesco); } catch (Exception e) {}
        GeradorPdf.gerarPdf(boletoBradesco);

        try { dao.salvar(boletoNu); } catch (Exception e) {}
        GeradorPdf.gerarPdf(boletoNu);
        
        System.out.println("Cheque a pasta do projeto: Itaú (341), Bradesco (237) e Nubank (260).");
    }
}