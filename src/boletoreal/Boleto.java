package boletoreal;

public class Boleto {
    String banco;
    String agencia;
    String conta;
    String nossoNumero;
    String linhaDigitavel;

    String pagador;
    String cpfPagador;
    String enderecoPagador;

    String cedente;
    String cpfCedente;
    String enderecoCedente;

    double valor;

    String dataEmissao;
    String dataVencimento;

    public Boleto() {}

    public String getBanco() { return banco; }
    public String getAgencia() { return agencia; }
    public String getConta() { return conta; }
    public String getNossoNumero() { return nossoNumero; }
    public String getLinhaDigitavel() { return linhaDigitavel; }

    public String getPagador() { return pagador; }
    public String getCpfPagador() { return cpfPagador; }
    public String getEnderecoPagador() { return enderecoPagador; }

    public String getCedente() { return cedente; }
    public String getCpfCedente() { return cpfCedente; }
    public String getEnderecoCedente() { return enderecoCedente; }

    public double getValor() { return valor; }

    public String getDataEmissao() { return dataEmissao; }
    public String getDataVencimento() { return dataVencimento; }
}