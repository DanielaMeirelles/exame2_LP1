package fatec.sjc.sp.exame2_lp1.controller;

import fatec.sjc.sp.exame2_lp1.classes.Animal;
import fatec.sjc.sp.exame2_lp1.dataBase.AnimalDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

public class AnimalController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtEspecie;

    @FXML
    private TextField txtIdade;

    @FXML
    private TableView<Animal> tabelaAnimais;

    @FXML
    private TableColumn<Animal, String> colNome;

    @FXML
    private TableColumn<Animal, String> colEspecie;

    @FXML
    private TableColumn<Animal, Integer> colIdade;

    @FXML
    private TableColumn<Animal, Void> colAcoes;

    private ObservableList<Animal> listaAnimais = FXCollections.observableArrayList();

    private Animal animal;

    private AnimalDAO animalDAO = new AnimalDAO();

    private Animal getAnimalSelecionado() {
        return tabelaAnimais.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colAcoes.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(null));

        listaAnimais.addAll(animalDAO.listar());
        tabelaAnimais.setItems(listaAnimais);

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
                    Animal animal = getTableView().getItems().get(getIndex());
                    animalDAO.deletar(animal.getId());
                    listaAnimais.remove(animal);
                });

                btnEditar.setOnAction(e -> {
                    animal = getTableView().getItems().get(getIndex());
                    txtNome.setText(animal.getNome());
                    txtEspecie.setText(animal.getEspecie());
                    txtIdade.setText(String.valueOf(animal.getIdade()));
                    animalDAO.atualizar(animal.getId(), animal);
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
    public void onEmitirSom() {
        Animal selecionado = getAnimalSelecionado();
        if (selecionado != null) {
            selecionado.emitirSom();
            mostrarAlerta("Ação", selecionado.getNome() + "está emitindo som.");
        }
    }

    @FXML
    public void onCorrer() {
        Animal selecionado = getAnimalSelecionado();
        if (selecionado != null) {
            selecionado.correr();
            mostrarAlerta("Ação", selecionado.getNome() + " está correndo.");
        }
    }

    @FXML
    public void onMostrarInfo() {
        Animal selecionado = getAnimalSelecionado();
        if (selecionado != null) {
            selecionado.mostrarInfo();
            String info = "Nome: " + selecionado.getNome() +
                    "\nEspécie: " + selecionado.getEspecie() +
                    "\nIdade: " + selecionado.getIdade() + " anos.";
            mostrarAlerta("Informações do Animal", info);
        }
    }

    @FXML
    public void onCadastrar() {
        try {
            String nome = txtNome.getText();
            String especie = txtEspecie.getText();
            int idade = Integer.parseInt(txtIdade.getText());

            Animal novoAnimal = new Animal(nome, especie, idade);
            animalDAO.inserir(novoAnimal);
            listaAnimais.add(novoAnimal);

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
            String nome = txtNome.getText();
            String especie = txtEspecie.getText();
            int idade = Integer.parseInt(txtIdade.getText());
            animal = new Animal(nome, especie, idade);
            return true;
        } catch (Exception e) {
            mostrarAlerta("Erro", "Preencha todos os campos corretamente!");
            return false;
        }
    }

    private void limparCampos() {
        txtNome.clear();
        txtEspecie.clear();
        txtIdade.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
