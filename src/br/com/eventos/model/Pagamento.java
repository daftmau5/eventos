package br.com.eventos.model;

public enum Pagamento {
	DINHEIRO("Dinheiro"),
    CARTAO_D("Cartão - Débito"),
    CARTAO_C("Cartão - Crédito"),
    BOLETO("Boleto"),
    CHEQUE("Cheque");

    private String nome;

    private Pagamento(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
