package fatec.sjc.sp.exame2_lp1.controller;

import fatec.sjc.sp.exame2_lp1.classes.Bicicleta;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class BicicletasController {

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtCor;

    @FXML
    private TextField txtMarchas;

    @FXML
    private TableView<Bicicleta> tabelaBicicletas;

    @FXML
    private TableColumn<Bicicleta, String> colModelo;

    @FXML
    private TableColumn<Bicicleta, String> colCor;

    @FXML
    private TableColumn<Bicicleta, Integer> colMarchas;

    @FXML
    private TableColumn<Bicicleta, Void> colAcoes;

    private ObservableList<Bicicleta> listaBicicletas = FXCollections.observableArrayList();

    private Bicicleta bicicleta;

    @FXML
    public void initialize() {
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colMarchas.setCellValueFactory(new PropertyValueFactory<>("marchas"));
        colAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(null));

        tabelaBicicletas.setItems(listaBicicletas);

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
                    Bicicleta bicicleta = getTableView().getItems().get(getIndex());
                    listaBicicletas.remove(bicicleta);
                });

                btnEditar.setOnAction(e -> {
                    bicicleta = getTableView().getItems().get(getIndex());
                    txtModelo.setText(bicicleta.getModelo());
                    txtCor.setText(bicicleta.getCor());
                    txtMarchas.setText(String.valueOf(bicicleta.getMarchas()));
                    listaBicicletas.remove(bicicleta);
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
    public void onPedalar() {
        if (instanciarBicicleta()) {
            bicicleta.pedalar();
            mostrarAlerta("A bicicleta ", bicicleta.getModelo() + "está pedalando.");
        }
    }

    @FXML
    public void onFreiar() {
        if (instanciarBicicleta()) {
            bicicleta.frear();
            mostrarAlerta("A bicicleta ", bicicleta.getModelo() + " está freiando.");
        }
    }

    @FXML
    public void onDetalhes() {
        if (instanciarBicicleta()) {
            bicicleta.detalhes();
            String info = "Modelo: " + bicicleta.getModelo() +
                    "\nCor: " + bicicleta.getCor() +
                    "\nMarchas: " + bicicleta.getMarchas();
            mostrarAlerta("Informações da Bicicleta", info);
        }
    }

    @FXML
    public void onCadastrar() {
        try {
            String modelo = txtModelo.getText();
            String cor = txtCor.getText();
            int marchas = Integer.parseInt(txtMarchas.getText());

            Bicicleta novaBicicleta = new Bicicleta(modelo, cor, marchas);
            listaBicicletas.add(novaBicicleta);

            limparCampos();
        } catch (Exception e) {
            mostrarAlerta("Erro", "Preencha todos os campos corretamente!");
        }
    }

    @FXML
    public void onLimpar() {
        limparCampos();
    }

    private boolean instanciarBicicleta() {
        try {
            String modelo = txtModelo.getText();
            String cor = txtCor.getText();
            int marchas = Integer.parseInt(txtMarchas.getText());
            bicicleta = new Bicicleta(modelo, cor, marchas);
            return true;
        } catch (Exception e) {
            mostrarAlerta("Erro", "Preencha todos os campos corretamente!");
            return false;
        }
    }

    private void limparCampos() {
        txtModelo.clear();
        txtCor.clear();
        txtMarchas.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}