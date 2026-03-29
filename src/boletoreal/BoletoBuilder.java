package boletoreal;

public class BoletoBuilder {

    private Boleto boleto;

    public BoletoBuilder() {
        this.boleto = new Boleto();
    }

    public BoletoBuilder banco(String banco) {
        boleto.banco = banco;
        return this;
    }

    public BoletoBuilder agencia(String agencia) {
        boleto.agencia = agencia;
        return this;
    }

    public BoletoBuilder conta(String conta) {
        boleto.conta = conta;
        return this;
    }

    public BoletoBuilder nossoNumero(String nossoNumero) {
        boleto.nossoNumero = nossoNumero;
        return this;
    }

    public BoletoBuilder linhaDigitavel(String linha) {
        boleto.linhaDigitavel = linha;
        return this;
    }

    public BoletoBuilder pagador(String pagador) {
        boleto.pagador = pagador;
        return this;
    }

    public BoletoBuilder cedente(String cedente) {
        boleto.cedente = cedente;
        return this;
    }

    public BoletoBuilder valor(double valor) {
        boleto.valor = valor;
        return this;
    }

    public BoletoBuilder dataEmissao(String data) {
        boleto.dataEmissao = data;
        return this;
    }

    public BoletoBuilder dataVencimento(String data) {
        boleto.dataVencimento = data;
        return this;
    }

    public Boleto build() {
        return boleto;
    }
}