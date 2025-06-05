package fatec.sjc.sp.exame2_lp1.dataBase;

import fatec.sjc.sp.exame2_lp1.classes.Carro;
import fatec.sjc.sp.exame2_lp1.dataBase.ConexaoMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {

    public void inserir(Carro carro) {
        String sql = "INSERT INTO Carro (modelo, cor, velocidade) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carro.getModelo());
            stmt.setString(2, carro.getCor());
            stmt.setInt(3, carro.getVelocidade());

            stmt.executeUpdate();
            System.out.println("Carro inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Carro> listar() {
        List<Carro> lista = new ArrayList<>();
        String sql = "SELECT * FROM Animal";

        try (Connection conn = ConexaoMySQL.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Carro c = new Carro(
                        rs.getInt("id"),
                        rs.getString("modelo"),
                        rs.getString("cor"),
                        rs.getInt("velocidade")

                );
                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizar(int id, Carro carro) {
        String sql = "UPDATE Carro SET modelo = ?, cor = ?, velocidade = ? WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.out.println("Falha ao conectar para atualizar.");
                return;
            }

            stmt.setString(1, carro.getModelo());
            stmt.setString(2, carro.getCor());
            stmt.setInt(3, carro.getVelocidade());
            stmt.setInt(4, carro.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Carro WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Carro removido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
