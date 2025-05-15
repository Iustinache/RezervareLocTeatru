

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


public class AnulareController {
    private Service service;

    private HelloController parentController; // referința la controllerul principal

    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldParola;
    @FXML
    private Button confirmare;

    // se setează de HelloController
    public void setService(Service service) {
        this.service = service;
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
        handleAnulare();
    }

    @FXML
    private void handleAnulare() {
        try {
            String email = textFieldEmail.getText();
            String parola = textFieldParola.getText();

            if (email.isEmpty() || parola.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Completați toate câmpurile!", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            List<Integer> locuriAnulate;

            if (service.verificareLogin(email, parola)) {
                locuriAnulate = service.anuleazaToateRezervarile(email, parola);
            } else {
                throw new Exception("Cont inexistent! Verificați emailul sau parola.");
            }

            // 🔥 Accesăm locuriButoane prin parentController
            for (ToggleButton btn : parentController.getLocuriButoane()) {
                Integer idLoc = (Integer) btn.getUserData();
                if (locuriAnulate.contains(idLoc)) {
                    btn.setStyle("-fx-background-color: lightgreen; -fx-border-color: grey");
                    btn.setDisable(false);
                    btn.setSelected(false);
                }
            }

            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }



}








