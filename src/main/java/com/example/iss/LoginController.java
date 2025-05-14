package com.example.iss;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import Service.Service;
import javafx.stage.Stage;
import org.w3c.dom.Node;


public class LoginController {
    private Service service;
    private List<Integer> locuriSelectate;
    private List<ToggleButton> locuriButoane;
    private RezervareController parentController; // referința la controllerul parinte

    @FXML
    private TextField textFieldNume;
    @FXML
    private TextField textFieldPrenume;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldParola;
    @FXML
    private TextField textFieldTelefon;
    @FXML
    private Button confirmaButton;
    @FXML
    private Button anulareButton;

    // se setează de HelloController
    public void setService(Service service) {
        this.service = service;
    }

    public void setParentController(RezervareController controller) {
        this.parentController = controller;
    }

    private Stage stage; // ADĂUGAT: referință la fereastra curentă

    public void setStage(Stage stage) { // ADĂUGAT: setter pentru stage
        this.stage = stage;
    }

    @FXML
    private void onConfirmaButtonClick() {
        try {
            // Datele introduse de utilizator
            String nume = textFieldNume.getText();
            String prenume = textFieldPrenume.getText();
            String email = textFieldEmail.getText();
            String parola = textFieldParola.getText();
            String telefon = textFieldTelefon.getText();

            // Validare minimală
            if (nume.isEmpty() || prenume.isEmpty() || email.isEmpty() || parola.isEmpty() || telefon.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Completați toate câmpurile!", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            service.addSpectatorUi(nume, prenume, email, parola, telefon);
            //stage.close();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "A apărut o eroare la adaugarea datelor personale!", ButtonType.OK);
            alert.showAndWait();

        }

    }

    @FXML
    private void onAnulareButtonClick() {}
}