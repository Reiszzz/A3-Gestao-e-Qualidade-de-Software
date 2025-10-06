package service;

import daoImpl.DoadorDAO;
import model.Doador;
import repository.DoadorRepository;
import java.util.List;

public class DoadorService {
    private final DoadorRepository repository;

    public DoadorService() {
        this.repository = new DoadorDAO(); // Pode ser substituído facilmente
    }

    public void cadastrarDoador(Doador doador) {
        repository.adicionar(doador);
    }

    public List<Doador> listarDoadores() {
        return repository.listarTodos();
    }

    /**
     * Valida o doador e lança exceções se houver problema
     */
    public void validarDoador(Doador doador) {
        if (doador.getIdade() < 16) {
            throw new IllegalArgumentException("O voluntário não pode doar se for menor que 16 anos.");
        }
        if (doador.getIdade() > 69) {
            throw new IllegalArgumentException("O voluntário não pode doar se for maior que 69 anos.");
        }
        if (doador.getPeso() < 50) {
            throw new IllegalArgumentException("O voluntário não pode doar se pesar menos que 50Kg.");
        }
        if (doador.getCpfDoador().length() != 11) {
            throw new IllegalArgumentException("O CPF deve conter 11 dígitos.");
        }
    }

    /**
     * Cadastra o doador após validação
     */
    public void cadastrarDoador(String cpf, int idade, String sexo, double peso, String nome) {
        Doador doador = new Doador(cpf, idade, sexo, peso, nome);
        validarDoador(doador);  // valida antes de cadastrar
        repository.adicionar(doador); // cadastra no banco
    }
}

