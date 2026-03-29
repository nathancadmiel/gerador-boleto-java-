package boletoreal;

public class Boleto {

    String banco;
    String agencia;
    String conta;
    String nossoNumero;
    String linhaDigitavel;

    String pagador;
    String cedente;

    double valor;
    String dataEmissao;
    String dataVencimento;

    // Construtor privado: só o Builder pode criar
    Boleto() {}

    // Getters
    public String getBanco() { return banco; }
    public String getAgencia() { return agencia; }
    public String getConta() { return conta; }
    public String getNossoNumero() { return nossoNumero; }
    public String getLinhaDigitavel() { return linhaDigitavel; }
    public String getPagador() { return pagador; }
    public String getCedente() { return cedente; }
    public double getValor() { return valor; }
    public String getDataEmissao() { return dataEmissao; }
    public String getDataVencimento() { return dataVencimento; }

    @Override
    public String toString() {
        return "Boleto{" +
                "banco='" + banco + '\'' +
                ", agencia='" + agencia + '\'' +
                ", conta='" + conta + '\'' +
                ", nossoNumero='" + nossoNumero + '\'' +
                ", linhaDigitavel='" + linhaDigitavel + '\'' +
                ", pagador='" + pagador + '\'' +
                ", cedente='" + cedente + '\'' +
                ", valor=" + valor +
                ", dataEmissao='" + dataEmissao + '\'' +
                ", dataVencimento='" + dataVencimento + '\'' +
                '}';
    }
}