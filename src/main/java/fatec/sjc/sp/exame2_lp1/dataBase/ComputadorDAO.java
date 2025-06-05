package fatec.sjc.sp.exame2_lp1.dataBase;


import fatec.sjc.sp.exame2_lp1.classes.Computador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComputadorDAO {

    public void inserir(Computador computador) {
        String sql = "INSERT INTO Computador (marca, processador, memoriaRam) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, computador.getMarca());
            stmt.setString(2, computador.getProcessador());
            stmt.setInt(3, computador.getMemoriaRAM());

            stmt.executeUpdate();
            System.out.println("Computador inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Computador> listar() {
        List<Computador> lista = new ArrayList<>();
        String sql = "SELECT * FROM Computador";

        try (Connection conn = ConexaoMySQL.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Computador c = new Computador(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("processador"),
                        rs.getInt("memoriaRam")
                );
                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizar(int id, Computador computador) {
        String sql = "UPDATE Computador SET marca = ?, processador = ?, memoriaRam = ? WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.out.println("Falha ao conectar para atualizar.");
                return;
            }

            stmt.setString(1, computador.getMarca());
            stmt.setString(2, computador.getProcessador());
            stmt.setInt(3, computador.getMemoriaRAM());
            stmt.setInt(4, computador.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Computador WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Pessoa removida com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
