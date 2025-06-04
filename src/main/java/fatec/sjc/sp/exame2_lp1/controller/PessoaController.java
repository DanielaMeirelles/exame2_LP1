package fatec.sjc.sp.exame2_lp1.controller;

import fatec.sjc.sp.exame2_lp1.classes.Pessoa;
import fatec.sjc.sp.exame2_lp1.dataBase.PessoaDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class PessoaController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtCpf;

    @FXML
    private TableView<Pessoa> tabelaPessoas;

    @FXML
    private TableColumn<Pessoa, String> colNome;

    @FXML
    private TableColumn<Pessoa, Integer> colIdade;

    @FXML
    private TableColumn<Pessoa, String> colCpf;

    @FXML
    private TableColumn<Pessoa, Void> colAcoes;

    private ObservableList<Pessoa> listaPessoas = FXCollections.observableArrayList();

    private Pessoa pessoa;

    private PessoaDAO pessoaDAO = new PessoaDAO();

    private Pessoa getPessoaSelecionada() {
        return tabelaPessoas.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        colAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(null));

        listaPessoas.addAll(pessoaDAO.listar());
        tabelaPessoas.setItems(listaPessoas);

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
                    Pessoa pessoa = getTableView().getItems().get(getIndex());
                    pessoaDAO.deletar(pessoa.getId());
                    listaPessoas.remove(pessoa);
                });

                btnEditar.setOnAction(e -> {
                    pessoa = getTableView().getItems().get(getIndex());
                    txtNome.setText(pessoa.getNome());
                    txtIdade.setText(String.valueOf(pessoa.getIdade()));
                    txtCpf.setText(pessoa.getCpf());
                    pessoaDAO.atualizar(pessoa.getId(), pessoa);
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
    public void onFalar() {
        Pessoa selecionada = getPessoaSelecionada();
        if (selecionada != null) {
            selecionada.falar();
            mostrarAlerta(selecionada.getNome(), " está dizendo olá!");
        } else {
            mostrarAlerta("Aviso", "Selecione uma pessoa na tabela!");
        }
    }

    @FXML
    public void onAndar() {
        Pessoa selecionada = getPessoaSelecionada();
        if (selecionada != null) {
            selecionada.andar();
            String msg = selecionada.getIdade() < 1
                    ? " ainda é muito jovem para andar."
                    : " está andando!";
            mostrarAlerta(selecionada.getNome(), msg);
        } else {
            mostrarAlerta("Aviso", "Selecione uma pessoa na tabela!");
        }
    }

    @FXML
    public void onDormir() {
        Pessoa selecionada = getPessoaSelecionada();
        if (selecionada != null) {
            selecionada.dormir();
            mostrarAlerta(selecionada.getNome(), " está dormindo!");
        } else {
            mostrarAlerta("Aviso", "Selecione uma pessoa na tabela!");
        }
    }


    @FXML
    public void onCadastrar() {
        try {
            String nome = txtNome.getText();
            int idade = Integer.parseInt(txtIdade.getText());
            String cpf = txtCpf.getText();

            Pessoa novaPessoa = new Pessoa(nome, idade, cpf);
            pessoaDAO.inserir(novaPessoa);
            listaPessoas.add(novaPessoa);

            limparCampos();
        } catch (Exception e) {
            mostrarAlerta("Erro", "Preencha todos os campos corretamente!");
        }
    }

    @FXML
    public void onLimpar() {
        limparCampos();
    }

    private boolean instanciarPessoa() {
        try {
            String nome = txtNome.getText();
            int idade = Integer.parseInt(txtIdade.getText());
            String cpf = txtCpf.getText();
            pessoa = new Pessoa(nome, idade, cpf);
            return true;
        } catch (Exception e) {
            mostrarAlerta("Erro", "Preencha todos os campos corretamente!");
            return false;
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtIdade.clear();
        txtCpf.clear();

    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
