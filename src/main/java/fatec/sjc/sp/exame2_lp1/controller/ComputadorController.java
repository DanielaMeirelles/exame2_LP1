package fatec.sjc.sp.exame2_lp1.controller;

import fatec.sjc.sp.exame2_lp1.classes.Computador;
import fatec.sjc.sp.exame2_lp1.dataBase.ComputadorDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class ComputadorController {

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtProcessador;

    @FXML
    private TextField txtMemoriaRam;

    @FXML
    private TableView<Computador> tabelaComputadores;

    @FXML
    private TableColumn<Computador, String> colMarca;

    @FXML
    private TableColumn<Computador, String> colProcessador;

    @FXML
    private TableColumn<Computador, Integer> colMemoriaRam;

    @FXML
    private TableColumn<Computador, Void> colAcoes;

    private ObservableList<Computador> listaComputadores = FXCollections.observableArrayList();

    private Computador computador;

    private ComputadorDAO computadorDAO = new ComputadorDAO();

    private Computador getComputadorSelecionado() {
        return tabelaComputadores.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void initialize() {
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colProcessador.setCellValueFactory(new PropertyValueFactory<>("processador"));
        colMemoriaRam.setCellValueFactory(new PropertyValueFactory<>("memoriaRam"));
        colAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(null));

        listaComputadores.addAll(computadorDAO.listar());
        tabelaComputadores.setItems(listaComputadores);

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
                    Computador computador = getTableView().getItems().get(getIndex());
                    computadorDAO.deletar(computador.getId());
                    listaComputadores.remove(computador);
                });

                btnEditar.setOnAction(e -> {
                    computador = getTableView().getItems().get(getIndex());
                    txtMarca.setText(computador.getMarca());
                    txtProcessador.setText(computador.getProcessador());
                    txtMemoriaRam.setText(String.valueOf(computador.getMemoriaRAM()));
                    computadorDAO.atualizar(computador.getId(), computador);
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
        Computador selecionado = getComputadorSelecionado();
        if (selecionado != null) {
            selecionado.ligar();
            mostrarAlerta("Ação", selecionado.getMarca() + "está ligando.");
        }
    }

    @FXML
    public void onDesligar() {
        Computador selecionado = getComputadorSelecionado();
        if (selecionado != null) {
            selecionado.desligar();
            mostrarAlerta("Ação", selecionado.getMarca() + " está desligando.");
        }
    }

    @FXML
    public void onInfoSistema() {
        Computador selecionado = getComputadorSelecionado();
        if (selecionado != null) {
            selecionado.infoSistema();
            String info = "Marca: " + selecionado.getMarca() +
                    "\nProcessador: " + selecionado.getProcessador() +
                    "\nMemória Ram: " + selecionado.getMemoriaRAM() + " anos.";
            mostrarAlerta("Informações do Computador", info);
        }
    }

    @FXML
    public void onCadastrar() {
        try {
            String marca = txtMarca.getText();
            String processador = txtProcessador.getText();
            int memoriaRam = Integer.parseInt(txtMemoriaRam.getText());

            Computador novoComputador = new Computador(marca, processador, memoriaRam);
            listaComputadores.add(novoComputador);

            limparCampos();
        } catch (Exception e) {
            mostrarAlerta("Erro", "Preencha todos os campos corretamente!");
        }
    }

    @FXML
    public void onLimpar() {
        limparCampos();
    }

    private boolean instanciarAnimal() {
        try {
            String marca = txtMarca.getText();
            String processador = txtProcessador.getText();
            int memoriaRam = Integer.parseInt(txtMemoriaRam.getText());
            computador = new Computador(marca, processador, memoriaRam);
            return true;
        } catch (Exception e) {
            mostrarAlerta("Erro", "Preencha todos os campos corretamente!");
            return false;
        }
    }

    private void limparCampos() {
        txtMarca.clear();
        txtProcessador.clear();
        txtMemoriaRam.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
