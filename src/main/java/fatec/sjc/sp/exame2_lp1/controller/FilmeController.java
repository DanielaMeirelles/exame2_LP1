package fatec.sjc.sp.exame2_lp1.controller;

import fatec.sjc.sp.exame2_lp1.classes.Filme;
import fatec.sjc.sp.exame2_lp1.dataBase.FilmeDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.io.File;

public class FilmeController {

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtGenero;

    @FXML
    private TextField txtDuracao;

    @FXML
    private TableView<Filme> tabelaFilmes;

    @FXML
    private TableColumn<Filme, String> colTitulo;

    @FXML
    private TableColumn<Filme, String> colGenero;

    @FXML
    private TableColumn<Filme, Integer> colDuracao;

    @FXML
    private TableColumn<Filme, Void> colAcoes;

    private ObservableList<Filme> listaFilmes = FXCollections.observableArrayList();

    private Filme filme;

    private FilmeDAO filmeDAO = new FilmeDAO();

    private Filme getFilmeSelecionado() {
        return tabelaFilmes.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void initialize() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colDuracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));
        colAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(null));

        listaFilmes.addAll(filmeDAO.listar());
        tabelaFilmes.setItems(listaFilmes);

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
                    Filme filme = getTableView().getItems().get(getIndex());
                    filmeDAO.deletar(filme.getId());
                    listaFilmes.remove(filme);
                });

                btnEditar.setOnAction(e -> {
                    filme = getTableView().getItems().get(getIndex());
                    txtTitulo.setText(filme.getTitulo());
                    txtGenero.setText(filme.getGenero());
                    txtDuracao.setText(String.valueOf(filme.getDuracao()));
                    filmeDAO.atualizar(filme.getId(), filme);
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
    public void onReproduzir() {
        Filme selecionado = getFilmeSelecionado();
        if (selecionado != null) {
            selecionado.reproduzir();
            mostrarAlerta("Reproduzindo o filme ", selecionado.getTitulo());
        }
    }

    @FXML
    public void onPausar() {
        Filme selecionado = getFilmeSelecionado();
        if (selecionado != null) {
            selecionado.pausar();
            mostrarAlerta("Pausando o filme ", selecionado.getTitulo());
        }
    }

    @FXML
    public void onExibirInfo() {
        Filme selecionado = getFilmeSelecionado();
        if (selecionado != null) {
            selecionado.exibirInfo();
            String info = "Título: " + selecionado.getTitulo() +
                    "\nGênero: " + selecionado.getGenero() +
                    "\nDuração: " + selecionado.getDuracao() + " minutos";
            mostrarAlerta("Informações do Filme", info);
        }
    }

    @FXML
    public void onCadastrar() {
        try {
            String titulo = txtTitulo.getText();
            String genero = txtGenero.getText();
            int duracao = Integer.parseInt(txtDuracao.getText());

            Filme novoFilme = new Filme(titulo, genero, duracao);
            filmeDAO.inserir(novoFilme);
            listaFilmes.add(novoFilme);

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
            String titulo = txtTitulo.getText();
            String genero = txtGenero.getText();
            int duracao = Integer.parseInt(txtDuracao.getText());
            filme = new Filme(titulo, genero, duracao);
            return true;
        } catch (Exception e) {
            mostrarAlerta("Erro", "Preencha todos os campos corretamente!");
            return false;
        }
    }

    private void limparCampos() {
        txtTitulo.clear();
        txtGenero.clear();
        txtDuracao.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}