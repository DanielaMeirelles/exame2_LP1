package fatec.sjc.sp.exame2_lp1.controller;

import fatec.sjc.sp.exame2_lp1.classes.Celular;
import fatec.sjc.sp.exame2_lp1.dataBase.CelularDAO;
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

    private CelularDAO celularDAO = new CelularDAO();

    private Celular getCelularSelecionado() {
        return tabelaCelulares.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void initialize() {
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colArmazenamento.setCellValueFactory(new PropertyValueFactory<>("armazenamento"));
        colAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(null));

        listaCelulares.addAll(celularDAO.listar());
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
                    celularDAO.deletar(celular.getId());
                    listaCelulares.remove(celular);
                });

                btnEditar.setOnAction(e -> {
                    celular = getTableView().getItems().get(getIndex());
                    txtMarca.setText(celular.getMarca());
                    txtModelo.setText(celular.getModelo());
                    txtArmazenamento.setText(String.valueOf(celular.getArmazenamento()));
                    celularDAO.atualizar(celular.getId(), celular);
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
        Celular selecionado = getCelularSelecionado();
        if (selecionado != null) {
            selecionado.ligar();
            mostrarAlerta("O ", selecionado.getMarca() + " do " + celular.getModelo() + " está ligando!");
        }
    }

    @FXML
    public void onDesligar() {
        Celular selecionado = getCelularSelecionado();
        if (selecionado != null) {
            selecionado.desligar();
            mostrarAlerta("O ", selecionado.getMarca() + " do " + celular.getModelo() + " está desligando!");
        }
    }

    @FXML
    public void onExibirInfo() {
        Celular selecionado = getCelularSelecionado();
        if (selecionado != null) {
            selecionado.exibirInfo();
            String info = "Marca: " + selecionado.getMarca() +
                    "\nModelo: " + selecionado.getModelo() +
                    "\nArmazenamento: " + selecionado.getArmazenamento() + " de memória";
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
            celularDAO.inserir(novoCelular);
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
