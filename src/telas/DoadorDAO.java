package telas;
/**
 *
 * @author João Vitor
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoadorDAO {
    private Connection connection;
    
    public DoadorDAO(){
        this.connection = DataBaseManager.obtemConexao();
    }

    public void adicionarDoador(Doador doador) {
        String sql = "INSERT INTO doadores (cpfDoador, idade, sexo, peso, nome) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, doador.getCpfDoador());
            statement.setInt(2, doador.getIdade());
            statement.setString(3, doador.getSexo());
            statement.setDouble(4, doador.getPeso());
            statement.setString(5, doador.getNome());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Doador cadastrado com sucesso!");
            }
        } catch (SQLException ex) {
        }
    }
}
