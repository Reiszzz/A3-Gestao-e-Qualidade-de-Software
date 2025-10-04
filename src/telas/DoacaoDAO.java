package telas;

import config.DataBaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class DoacaoDAO {
    private Connection connection;

    public DoacaoDAO() {
        this.connection = DataBaseManager.obtemConexao();
    }

    // Método para inserir uma doação no banco de dados
    public void inserirDoacaoNoBanco(Doacao doacao) throws SQLException {
        String sql = "INSERT INTO doacoes (cpfDoador, nome_enfermeiro, tipo_sanguineo, alimentado,  ultima_doacao, horas_sono, data_doacao, horario_doacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, doacao.getCpfDoador());
            stmt.setString(2, doacao.getNomeEnfermeiro());
            stmt.setString(3, doacao.getTipoSanguineo());
            stmt.setString(4, doacao.getAlimentado());
            stmt.setString(5, doacao.getUltimaDoacao());
            stmt.setString(6, doacao.getHorasSono());
            stmt.setString(7, doacao.getDataDoacao());
            stmt.setString(8, doacao.getHoraDoacao());
            stmt.executeUpdate();
        }
    }
    
    //Método para verificar se o CPF do doador está cadastrado
    public boolean verificarCPFDoador(Doacao doacao) {
        String sql = "SELECT COUNT(*) FROM doadores WHERE cpfDoador = " + doacao.getCpfDoador();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                return count > 0;
                }
            }
        } catch (SQLException e) {
    }
    return false;
    }
    
    // Método para atualizar as labels com o número de doações para cada tipo sanguíneo
    public void atualizarLabelsDoacoes(JLabel txtApos, JLabel txtAneg, JLabel txtBpos, JLabel txtBneg, JLabel txtABpos, JLabel txtABneg, JLabel txtOpos, JLabel txtOneg) {
        String sql = "SELECT tipo_sanguineo, COUNT(*) AS quantidade FROM doacoes GROUP BY tipo_sanguineo";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                String tipoSanguineo = rs.getString("tipo_sanguineo");
                int quantidade = rs.getInt("quantidade");
               
                SwingUtilities.invokeLater(() -> {
                    switch (tipoSanguineo) {
                        case "A+":
                            txtApos.setText(String.valueOf(quantidade));
                            break;
                        case "A-":
                            txtAneg.setText(String.valueOf(quantidade));
                            break;
                        case "B+":
                            txtBpos.setText(String.valueOf(quantidade));
                            break;
                        case "B-":
                            txtBneg.setText(String.valueOf(quantidade));
                            break;
                        case "AB+":
                            txtABpos.setText(String.valueOf(quantidade));
                            break;
                        case "AB-":
                            txtABneg.setText(String.valueOf(quantidade));
                            break;
                        case "O+":
                            txtOpos.setText(String.valueOf(quantidade));
                            break;
                        case "O-":
                            txtOneg.setText(String.valueOf(quantidade));
                            break;
                        default:
                            // Tipo sanguíneo desconhecido, não faz nada
                            break;
                    }
                });
            }
        } catch (SQLException e) {
        }
    }
}
       
    