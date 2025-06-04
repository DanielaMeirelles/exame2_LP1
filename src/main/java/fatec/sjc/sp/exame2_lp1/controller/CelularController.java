package fatec.sjc.sp.exame2_lp1.controller;

import fatec.sjc.sp.exame2_lp1.classes.Celular;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class CelularController {

    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtArmazenamento;

    @FXML
    private TableView<Celular> tabelaCelulares;

    @FXML
    private TableColumn<Celular, String> colMarca;

    @FXML
    private TableColumn<Celular, String> colModelo;

    @FXML
    private TableColumn<Celular, Integer> colArmazenamento;

    @FXML
    private TableColumn<Celular, Void> colAcoes;

    private ObservableList<Celular> listaCelulares = FXCollections.observableArrayList();

    private Celular celular;

    @FXML
    public void initialize() {
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colArmazenamento.setCellValueFactory(new PropertyValueFactory<>("armazenamento"));
        colAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(null));

        tabelaCelulares.setItems(listaCelulares);

        configurarColunaAcoes();
    }

    private void configurarColunaAcoes() {
        colAcoes.setCellFactory(coluna -> new TableCell<>() {
            final Button btnExcluir = new Button("Excluir");
            final Button btnEditar = new Button("Editar");
            final HBox hbox = new HBox(10, btnExcluir, btnEditar);

            {
                btnExcluir.setStyle("-fx-background-color: #ff4136; -fx-text-fill: white;");
                btnEditar.setStyle("-fx-background-color: #ffdc00; -fx-text-fill: black;");

                btnExcluir.setOnAction(e -> {
                    Celular celular = getTableView().getItems().get(getIndex());
                    listaCelulares.remove(celular);
                });

                btnEditar.setOnAction(e -> {
                    celular = getTableView().getItems().get(getIndex());
                    txtMarca.setText(celular.getMarca());
                    txtModelo.setText(celular.getModelo());
                    txtArmazenamento.setText(String.valueOf(celular.getArmazenamento()));
                    listaCelulares.remove(celular);
                });

                hbox.setStyle("-fx-alignment: CENTER;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(hbox);
                }
            }
        });
    }
    @FXML
    public void onLigar() {
        if (instanciarCelular()) {
            celular.ligar();
            mostrarAlerta("O ", celular.getMarca() + " do " + celular.getModelo() + " está ligando!");
        }
    }

    @FXML
    public void onDesligar() {
        if (instanciarCelular()) {
            celular.desligar();
            mostrarAlerta("O ", celular.getMarca() + " do " + celular.getModelo() + " está desligando!");
        }
    }

    @FXML
    public void onExibirInfo() {
        if (instanciarCelular()) {
            celular.exibirInfo();
            String info = "Marca: " + celular.getMarca() +
                    "\nModelo: " + celular.getModelo() +
                    "\nArmazenamento: " + celular.getArmazenamento() + " de memória";
            mostrarAlerta("Informações do Celular", info);
        }
    }

    @FXML
    public void onCadastrar() {
        try {
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            int armazenamento = Integer.parseInt(txtArmazenamento.getText());

            Celular novoCelular = new Celular(marca, modelo, armazenamento);
            listaCelulares.add(novoCelular);

            limparCampos();
        } catch (Exception e) {
            mostrarAlerta("Erro", "Preencha todos os campos corretamente!");
        }
    }

    @FXML
    public void onLimpar() {
        limparCampos();
    }

    private boolean instanciarCelular() {
        try {
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            int armazenamento = Integer.parseInt(txtArmazenamento.getText());
            celular = new Celular(marca, modelo, armazenamento);
            return true;
        } catch (Exception e) {
            mostrarAlerta("Erro", "Preencha todos os campos corretamente!");
            return false;
        }
    }

    private void limparCampos() {
        txtMarca.clear();
        txtModelo.clear();
        txtArmazenamento.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
