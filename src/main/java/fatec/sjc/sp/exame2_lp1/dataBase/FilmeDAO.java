package fatec.sjc.sp.exame2_lp1.dataBase;

import fatec.sjc.sp.exame2_lp1.classes.Filme;
import fatec.sjc.sp.exame2_lp1.dataBase.ConexaoMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {

    public void inserir(Filme filme) {
        String sql = "INSERT INTO Filme (titulo, genero, duracao) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, filme.getTitulo());
            stmt.setString(2, filme.getGenero());
            stmt.setInt(3, filme.getDuracao());

            stmt.executeUpdate();
            System.out.println("Filme inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Filme> listar() {
        List<Filme> lista = new ArrayList<>();
        String sql = "SELECT * FROM Filme";

        try (Connection conn = ConexaoMySQL.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Filme f = new Filme(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        rs.getInt("duracao")

                );
                lista.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizar(int id, Filme filme) {
        String sql = "UPDATE Filme SET titulo = ?, genero = ?, duracao = ? WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.out.println("Falha ao conectar para atualizar.");
                return;
            }

            stmt.setString(1, filme.getTitulo());
            stmt.setString(2, filme.getGenero());
            stmt.setInt(3, filme.getDuracao());
            stmt.setInt(4, filme.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Filme WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Filme removido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
