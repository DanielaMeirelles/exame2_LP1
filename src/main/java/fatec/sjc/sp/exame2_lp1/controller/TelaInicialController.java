package fatec.sjc.sp.exame2_lp1.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaInicialController {

    @FXML
    private void onAnimais() {
        abrirTela("/fatec/sjc/sp/exame2_lp1/AnimalView.fxml", "Animais");
    }

    @FXML
    private void onBicicletas() {
        abrirTela("/fatec/sjc/sp/exame2_lp1/BicicletaView.fxml", "Bicicletas");
    }

    @FXML
    private void onCarros() {
        abrirTela("/fatec/sjc/sp/exame2_lp1/CarroView.fxml", "Carros");
    }

    @FXML
    private void onCelulares() {
        abrirTela("/fatec/sjc/sp/exame2_lp1/CelularesView.fxml", "Celulares");
    }

    @FXML
    private void onComputadores() {
        abrirTela("/fatec/sjc/sp/exame2_lp1/ComputadorView.fxml", "Computadores");
    }

    @FXML
    private void onFilmes() {
        abrirTela("/fatec/sjc/sp/exame2_lp1/FilmeView.fxml", "Filmes");
    }

    @FXML
    private void onFlores() {
        exibirMensagem("Flores selecionado.");
    }

    @FXML
    private void onLivros() {
        exibirMensagem("Livros selecionado.");
    }

    @FXML
    private void onPessoas() {
        abrirTela("/fatec/sjc/sp/exame2_lp1/PessoaView.fxml", "Pessoas");
    }

    @FXML
    private void onProdutos() {
        exibirMensagem("Produtos selecionado.");
    }

    @FXML
    private void onFracoes() {
        exibirMensagem("Frações selecionado.");
    }

    private void exibirMensagem(String texto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Categoria não implementada!");
        alert.setHeaderText(null);
        alert.setContentText(texto);
        alert.showAndWait();
    }
    private void abrirTela(String caminhoFXML, String tituloJanela) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(tituloJanela);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            exibirMensagem("Erro ao abrir a tela: " + tituloJanela);
        }
    }
}