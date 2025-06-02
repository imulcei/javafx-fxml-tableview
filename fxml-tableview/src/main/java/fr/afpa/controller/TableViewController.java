package fr.afpa.controller;

import fr.afpa.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TableViewController {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField city;
    @FXML
    private Button saveButton;
    @FXML
    private Button canceButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, String> cityColumn;

    private ObservableList<Person> persons = FXCollections.observableArrayList();

    // afficher une liste de personnes
    // ajouter une nouvelle personne grâce à "sauver" + textfield
    // supprimer une personne du tableau
    // clear la saisie utilisateur
    @FXML
    public void initialize() {
        persons.add(new Person("Dupont", "Michel", "Paris"));
        persons.add(new Person("Smith", "John", "New-York"));
        persons.add(new Person("Silva", "Rodrigo", "Mexico"));
        tableView.setItems(persons);
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().getFirstName());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().getLastName());
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().getCity());
    }

    @FXML
    private void save(ActionEvent event) {
        String firstNameValue = firstName.getText();
        String lastNameValue = lastName.getText();
        String cityValue = city.getText();
        if (firstNameValue.isEmpty() || lastNameValue.isEmpty() || cityValue.isEmpty()) {
            System.out.println("Merci de remplir tous les champs.");
            return;
        }
        Person newPerson = new Person(lastNameValue, firstNameValue, cityValue);
        persons.add(newPerson);
        firstName.clear();
        lastName.clear();
        city.clear();
    }

    @FXML
    private void cancel(ActionEvent event) {
        firstName.clear();
        lastName.clear();
        city.clear();
    }

    @FXML
    private void delete(ActionEvent event) {
        Person selectedPerson = tableView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            persons.remove(selectedPerson);
        } else {
            System.out.println("Aucune personne sélectionnée.");
        }
    }
}
