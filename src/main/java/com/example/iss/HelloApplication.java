package com.example.iss;

import Domain.Loc;
import Domain.Rezervare;
import Domain.Spectator;
import Repository.*;
import Service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        // creezi repo-urile (presupunem RepoMemory momentan)
        IRepository<Spectator> repoSpec = new SQLSpectatorRepository();
        IRepository<Loc> repoLoc = new SQLLocRepository();
        IRepository<Rezervare> repoRez = new SQLRezervareRepository();

        // creezi service-ul
        Service service = new Service(repoSpec, repoLoc, repoRez);

        LocalTime oraCurenta = LocalTime.now();
        if (oraCurenta.isAfter(LocalTime.of(6, 0))) {
            try {
                service.resetLocuriOdataPeZi();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        HelloController controller = fxmlLoader.getController();
        controller.setService(service);

        stage.setTitle("Rezervări Spectacol");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}