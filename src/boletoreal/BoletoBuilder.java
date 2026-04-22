package boletoreal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class BoletoBuilder {
    private Boleto boleto;

    public BoletoBuilder() {
        boleto = new Boleto();
    }

    public BoletoBuilder banco(String banco) { boleto.banco = banco; return this; }
    public BoletoBuilder agencia(String agencia) { boleto.agencia = agencia; return this; }
    public BoletoBuilder conta(String conta) { boleto.conta = conta; return this; }
    public BoletoBuilder nossoNumero(String nossoNumero) { boleto.nossoNumero = nossoNumero; return this; }
    public BoletoBuilder valor(double valor) { boleto.valor = valor; return this; }
    public BoletoBuilder dataEmissao(String data) { boleto.dataEmissao = data; return this; }
    public BoletoBuilder dataVencimento(String data) { boleto.dataVencimento = data; return this; }

    public BoletoBuilder pagador(String nome, String cpf, String endereco) {
        boleto.pagador = nome;
        boleto.cpfPagador = cpf;
        boleto.enderecoPagador = endereco;
        return this;
    }

    public BoletoBuilder cedente(String nome, String cpf, String endereco) {
        boleto.cedente = nome;
        boleto.cpfCedente = cpf;
        boleto.enderecoCedente = endereco;
        return this;
    }

    private String gerarFatorVencimento(String data) {
        try {
            LocalDate base = LocalDate.of(1997, 10, 7);
            LocalDate venc = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return String.valueOf(ChronoUnit.DAYS.between(base, venc));
        } catch (Exception e) {
            return "0000";
        }
    }

    private int calcularModulo10(String trecho) {
        int soma = 0;
        int peso = 2;

        for (int i = trecho.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(trecho.substring(i, i + 1)) * peso;
            if (n > 9) n = (n / 10) + (n % 10);
            soma += n;
            peso = (peso == 2) ? 1 : 2;
        }

        int resto = soma % 10;
        return (resto == 0) ? 0 : 10 - resto;
    }

    public Boleto build() {
        String fator = gerarFatorVencimento(boleto.dataVencimento);
        String vlr = String.format("%010d", (int) Math.round(boleto.valor * 100));

        String ag = boleto.agencia.replaceAll("\\D", "");
        String conta = boleto.conta.replaceAll("\\D", "");
        String nn = boleto.nossoNumero.replaceAll("\\D", "");

        String campoLivre =
                String.format("%04d", Integer.parseInt(ag.isEmpty() ? "0" : ag)) +
                "06" +
                String.format("%011d", Long.parseLong(nn.isEmpty() ? "0" : nn)) +
                String.format("%07d", Integer.parseInt(conta.isEmpty() ? "0" : conta)) +
                "0";

        String p1 = boleto.banco + "9" + campoLivre.substring(0, 5);
        p1 += calcularModulo10(p1);

        String p2 = campoLivre.substring(5, 15);
        p2 += calcularModulo10(p2);

        String p3 = campoLivre.substring(15, 25);
        p3 += calcularModulo10(p3);

        boleto.linhaDigitavel =
                p1.substring(0, 5) + "." + p1.substring(5) + " " +
                p2.substring(0, 5) + "." + p2.substring(5) + " " +
                p3.substring(0, 5) + "." + p3.substring(5) +
                " 1 " + fator + vlr;

        return boleto;
    }
}