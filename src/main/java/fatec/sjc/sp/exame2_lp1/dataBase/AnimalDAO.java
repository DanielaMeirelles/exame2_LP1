package fatec.sjc.sp.exame2_lp1.dataBase;


import fatec.sjc.sp.exame2_lp1.classes.Animal;
import fatec.sjc.sp.exame2_lp1.dataBase.ConexaoMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    public void inserir(Animal animal) {
        String sql = "INSERT INTO Animal (nome, especie, idade) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setInt(3, animal.getIdade());

            stmt.executeUpdate();
            System.out.println("Animal inserido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Animal> listar() {
        List<Animal> lista = new ArrayList<>();
        String sql = "SELECT * FROM Animal";

        try (Connection conn = ConexaoMySQL.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Animal a = new Animal(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("especie")

                );
                lista.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizar(int id, Animal animal) {
        String sql = "UPDATE Animal SET nome = ?, especie = ?, idade = ? WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.out.println("Falha ao conectar para atualizar.");
                return;
            }

            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setInt(3, animal.getIdade());
            stmt.setInt(4, animal.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Animal WHERE id = ?";

        try (Connection conn = ConexaoMySQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Animal removido com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
