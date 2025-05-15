

package com.example.iss;

import Domain.Spectator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
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
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldParola;
    @FXML
    private Button creareCont;
    @FXML
    private Button confirmare;

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
    private void onConfirmaButtonClick() {
        handleConfirmare();
    }

    @FXML
    private void onCreareContButtonClick() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Controllerul pentru fereastra de rezervare
        LoginController logController = loader.getController();
        logController.setService(service);

        // Setăm referința la fereastra principală ca să putem reveni
        logController.setParentController(this);

        // Deschidem noua fereastră
        Stage stage = new Stage();
        stage.setTitle("Date personale spectator");
        stage.setScene(new Scene(root));
        logController.setStage(stage);
        stage.show();
    }

    // Apelată când apăsăm pe butonul Confirmă din rezervare-view.fxml
//    @FXML
//    private void handleConfirmare() {
//        try {
//            // Datele introduse de utilizator
//            String email = textFieldEmail.getText();
//            String parola = textFieldParola.getText();
//
//            // Validare minimală
//            if (email.isEmpty() || parola.isEmpty()) {
//                Alert alert = new Alert(Alert.AlertType.WARNING, "Completați toate câmpurile!", ButtonType.OK);
//                alert.showAndWait();
//                return;
//            }
//
//            // Efectuăm rezervarea prin service
//            int[] locuriArray = locuriSelectate.stream().mapToInt(i -> i).toArray();
//
//            if (service.verificareLogin(email, parola)) {
//                service.efectuareRezervare(email, parola, locuriArray);
//            } else{
//                throw new Exception("Cont inexistent! Trebuie sa va creati cont.");
//            }
//
//            // După rezervare, facem butoanele roșii și dezactivate
//            for (ToggleButton btn : locuriButoane) {
//                if (btn.isSelected()) {
//                    btn.setStyle("-fx-background-color: red; -fx-border-color: grey");
//                    btn.setDisable(true);
//                }
//            }
//            stage.close();
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Alert alert = new Alert(Alert.AlertType.ERROR, "A apărut o eroare la rezervare!", ButtonType.OK);
//            alert.showAndWait();
//        }
//    }
    @FXML
    private void handleConfirmare() {
        try {
            // Datele introduse de utilizator
            String email = textFieldEmail.getText();
            String parola = textFieldParola.getText();

            // Validare minimală
            if (email.isEmpty() || parola.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Completați toate câmpurile!", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            // Efectuăm rezervarea prin service
            int[] locuriArray = locuriSelectate.stream().mapToInt(i -> i).toArray();

            if (service.verificareLogin(email, parola)) {
                service.efectuareRezervare(email, parola, locuriArray);
            } else {
                throw new Exception("Cont inexistent! Trebuie sa va creati cont.");
            }

            // După rezervare, facem butoanele roșii și dezactivate
            for (ToggleButton btn : locuriButoane) {
                if (btn.isSelected()) {
                    btn.setStyle("-fx-background-color: red; -fx-border-color: grey");
                    btn.setDisable(true);
                }
            }

            double costTotal = service.calculeazaCost(locuriArray);
            Alert succesAlert = new Alert(Alert.AlertType.INFORMATION,
                    "Rezervarea a avut loc!\nCostul va fi " + costTotal + " lei.\nVă așteptăm cu drag!",
                    ButtonType.OK);
            succesAlert.showAndWait();

            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

}








