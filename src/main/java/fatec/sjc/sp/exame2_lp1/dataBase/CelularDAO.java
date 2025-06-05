package fatec.sjc.sp.exame2_lp1.dataBase;

import fatec.sjc.sp.exame2_lp1.classes.Celular;
import fatec.sjc.sp.exame2_lp1.dataBase.ConexaoMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CelularDAO {

    public void inserir(Celular celular) {
        String sql = "INSERT INTO Celular (marca, modelo, armazenamento) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, celular.getMarca());
            stmt.setString(2, celular.getModelo());
            stmt.setInt(3, celular.getArmazenamento());

            stmt.executeUpdate();
            System.out.println("Celular inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Celular> listar() {
        List<Celular> lista = new ArrayList<>();
        String sql = "SELECT * FROM Celular";

        try (Connection conn = ConexaoMySQL.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Celular c = new Celular(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("armazenamento")

                );
                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizar(int id, Celular celular) {
        String sql = "UPDATE Celular SET marca = ?, modelo = ?, armazenamento = ? WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.out.println("Falha ao conectar para atualizar.");
                return;
            }

            stmt.setString(1, celular.getMarca());
            stmt.setString(2, celular.getModelo());
            stmt.setInt(3, celular.getArmazenamento());
            stmt.setInt(4, celular.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Celular WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Celular removido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
