package fatec.sjc.sp.exame2_lp1.controller;

import fatec.sjc.sp.exame2_lp1.classes.Carro;
import fatec.sjc.sp.exame2_lp1.dataBase.CarroDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class CarroController {

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtCor;

    @FXML
    private TextField txtVelocidade;

    @FXML
    private TableView<Carro> tabelaCarros;

    @FXML
    private TableColumn<Carro, String> colModelo;

    @FXML
    private TableColumn<Carro, String> colCor;

    @FXML
    private TableColumn<Carro, Integer> colVelocidade;

    @FXML
    private TableColumn<Carro, Void> colAcoes;

    private ObservableList<Carro> listaCarros = FXCollections.observableArrayList();

    private Carro carro;

    private CarroDAO carroDAO = new CarroDAO();

    private Carro getCarroSelecionado() {
        return tabelaCarros.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void initialize() {
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colVelocidade.setCellValueFactory(new PropertyValueFactory<>("velocidade"));
        colAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(null));

        listaCarros.addAll(carroDAO.listar());
        tabelaCarros.setItems(listaCarros);

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
                    Carro carro = getTableView().getItems().get(getIndex());
                    carroDAO.deletar(carro.getId());
                    listaCarros.remove(carro);
                });

                btnEditar.setOnAction(e -> {
                    carro = getTableView().getItems().get(getIndex());
                    txtModelo.setText(carro.getModelo());
                    txtCor.setText(carro.getCor());
                    txtVelocidade.setText(String.valueOf(carro.getVelocidade()));
                    carroDAO.atualizar(carro.getId(), carro);
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
    public void onAcelerar() {
        Carro selecionado = getCarroSelecionado();
        if (selecionado != null) {
            selecionado.acelerar();
            mostrarAlerta("O ", selecionado.getModelo() + " acelerou. Velocidade atual: " + txtVelocidade + "Km/h.");
        }
    }

    @FXML
    public void onFrear() {
        Carro selecionado = getCarroSelecionado();
        if (selecionado != null) {
            selecionado.frear();
            mostrarAlerta("O ", selecionado.getModelo() + " freou. Velocidade atual: " + txtVelocidade + "Km/h.");
        }
    }

    @FXML
    public void onExibirDados() {
        Carro selecionado = getCarroSelecionado();
        if (selecionado != null) {
            selecionado.exibirDados();
            String info = "Modelo: " + selecionado.getModelo() +
                    "\nCor: " + selecionado.getCor() +
                    "\nVelocidade: " + selecionado.getVelocidade();
            mostrarAlerta("Informações do Carro", info);
        }
    }

    @FXML
    public void onCadastrar() {
        try {
            String modelo = txtModelo.getText();
            String cor = txtCor.getText();
            int velocidade = Integer.parseInt(txtVelocidade.getText());

            Carro novoCarro = new Carro(modelo, cor, velocidade);
            carroDAO.inserir(novoCarro);
            listaCarros.add(novoCarro);

            limparCampos();
        } catch (Exception e) {
            mostrarAlerta("Erro", "Preencha todos os campos corretamente!");
        }
    }

    @FXML
    public void onLimpar() {
        limparCampos();
    }

    private boolean instanciarCarro() {
        try {
            String modelo = txtModelo.getText();
            String cor = txtCor.getText();
            int velocidade = Integer.parseInt(txtVelocidade.getText());
            carro = new Carro(modelo, cor, velocidade);
            return true;
        } catch (Exception e) {
            mostrarAlerta("Erro", "Preencha todos os campos corretamente!");
            return false;
        }
    }

    private void limparCampos() {
        txtModelo.clear();
        txtCor.clear();
        txtVelocidade.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}