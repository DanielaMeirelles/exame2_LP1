package fatec.sjc.sp.exame2_lp1.dataBase;

import fatec.sjc.sp.exame2_lp1.classes.Bicicleta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BicicletaDAO {

    public void inserir(Bicicleta bicicleta) {
        String sql = "INSERT INTO Bicicleta (modelo, cor, marchas) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bicicleta.getModelo());
            stmt.setString(2, bicicleta.getCor());
            stmt.setInt(3, bicicleta.getMarchas());

            stmt.executeUpdate();
            System.out.println("Bicicleta inserida com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Bicicleta> listar() {
        List<Bicicleta> lista = new ArrayList<>();
        String sql = "SELECT * FROM Bicicleta";

        try (Connection conn = ConexaoMySQL.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Bicicleta b = new Bicicleta(
                        rs.getInt("id"),
                        rs.getString("modelo"),
                        rs.getString("cor"),
                        rs.getInt("marchas")


                );
                lista.add(b);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizar(int id, Bicicleta bicicleta) {
        String sql = "UPDATE Bicicleta SET modelo = ?, cor = ?, marchas = ? WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.out.println("Falha ao conectar para atualizar.");
                return;
            }

            stmt.setString(1, bicicleta.getModelo());
            stmt.setString(2, bicicleta.getCor());
            stmt.setInt(3, bicicleta.getMarchas());
            stmt.setInt(4, bicicleta.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Bicicleta WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Bicicleta removida com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
