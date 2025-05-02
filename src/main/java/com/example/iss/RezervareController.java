package com.example.iss;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import Service.Service;
import javafx.stage.Stage;
import org.w3c.dom.Node;


public class RezervareController {
    private Service service;
    private List<Integer> locuriSelectate;
    private List<ToggleButton> locuriButoane;
    private HelloController parentController; // referința la controllerul principal
    @FXML
    private TextField textFieldNume;
    @FXML
    private TextField textFieldPrenume;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldTelefon;
    @FXML
    private Button confirmaButton;

    // se setează de HelloController
    public void setService(Service service) {
        this.service = service;
    }

    public void setLocuriSelectate(List<Integer> locuriSelectate, List<ToggleButton> locuriButoane) {
        this.locuriSelectate = locuriSelectate;
        this.locuriButoane = locuriButoane;
    }

    public void setParentController(HelloController controller) {
        this.parentController = controller;
    }

    private Stage stage; // ADĂUGAT: referință la fereastra curentă

    public void setStage(Stage stage) { // ADĂUGAT: setter pentru stage
        this.stage = stage;
    }

    @FXML
    private void onConfirmaButtonClick(){
        handleConfirmare();
    }

    // Apelată când apăsăm pe butonul Confirmă din rezervare-view.fxml
    @FXML
    private void handleConfirmare() {
        try {
            // Datele introduse de utilizator
            String nume = textFieldNume.getText();
            String prenume = textFieldPrenume.getText();
            String email = textFieldEmail.getText();
            String telefon = textFieldTelefon.getText();

            // Validare minimală
            if (nume.isEmpty() || prenume.isEmpty() || email.isEmpty() || telefon.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Completați toate câmpurile!", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            // Efectuăm rezervarea prin service
            int[] locuriArray = locuriSelectate.stream().mapToInt(i -> i).toArray();
            service.efectuareRezervare(nume, prenume, email, telefon, locuriArray);

//            textFieldNume.clear();
//            textFieldPrenume.clear();
//            textFieldEmail.clear();
//            textFieldTelefon.clear();

            // După rezervare, facem butoanele roșii și dezactivate
            for (ToggleButton btn : locuriButoane) {
                if (btn.isSelected()) {
                    btn.setStyle("-fx-background-color: red;");
                    btn.setDisable(true);
                }
            }
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "A apărut o eroare la rezervare!", ButtonType.OK);
            alert.showAndWait();
        }
    }
}



