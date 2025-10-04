package model;
/**
 * Entidade que representa um Doador.
 * Responsavel apenas por armaenar dados consistentes
 */
public class Doador {
    private String cpfDoador;
    private int idade;
    private String sexo;
    private double peso;
    private String nome;

    // Construtor recebe valores já tipados
    public Doador(String cpfDoador, int idade, String sexo, double peso, String nome) {
        validarDoador(idade, peso, cpfDoador, sexo, nome);
        this.cpfDoador = cpfDoador;
        this.idade = idade;
        this.sexo = sexo;
        this.peso = peso;
        this.nome = nome;
    }

    // Getters e Setters

    public String getCpfDoador() {
        return cpfDoador;
    }

    public void setCpfDoador(String cpfDoador) {
        if (cpfDoador == null || cpfDoador.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser vazio.");
        }
        this.cpfDoador = cpfDoador;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade < 18 || idade > 100) {
            throw new IllegalArgumentException("Idade inválida para doador.");
        }
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        if (!sexo.equalsIgnoreCase("M") && !sexo.equalsIgnoreCase("F")) {
            throw new IllegalArgumentException("Sexo deve ser M ou F.");
        }
        this.sexo = sexo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (peso < 50.0) {
            throw new IllegalArgumentException("Peso mínimo para doador é 50kg.");
        }
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    // Métodos auxiliares
    private void validarDoador(int idade, double peso, String cpf, String sexo, String nome) {
        if (cpf == null || cpf.isBlank()) throw new IllegalArgumentException("CPF é obrigatório.");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório.");
        if (idade < 18 || idade > 100) throw new IllegalArgumentException("Idade inválida para doador.");
        if (peso < 50.0) throw new IllegalArgumentException("Peso mínimo para doador é 50kg.");
        if (!sexo.equalsIgnoreCase("M") && !sexo.equalsIgnoreCase("F")) {
            throw new IllegalArgumentException("Sexo deve ser M ou F.");
        }
    }
}