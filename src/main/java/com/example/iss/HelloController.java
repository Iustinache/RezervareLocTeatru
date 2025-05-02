package com.example.iss;

import Domain.Loc;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Service.Service;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HelloController {
    @FXML
    private ToggleButton l1;
    @FXML
    private ToggleButton l2;
    @FXML
    private ToggleButton l3;
    @FXML
    private ToggleButton l4;
    @FXML
    private ToggleButton l5;
    @FXML
    private ToggleButton l6;
    @FXML
    private ToggleButton l7;
    @FXML
    private ToggleButton l8;
    @FXML
    private ToggleButton l9;
    @FXML
    private ToggleButton l10;
    @FXML
    private ToggleButton l11;
    @FXML
    private ToggleButton l12;
    @FXML
    private ToggleButton l13;
    @FXML
    private ToggleButton l14;
    @FXML
    private ToggleButton l15;
    @FXML
    private ToggleButton l16;
    @FXML
    private ToggleButton l17;
    @FXML
    private ToggleButton l18;
    @FXML
    private ToggleButton l19;
    @FXML
    private ToggleButton l20;
    @FXML
    private ToggleButton l21;
    @FXML
    private ToggleButton l22;
    @FXML
    private ToggleButton l23;
    @FXML
    private ToggleButton l24;
    @FXML
    private ToggleButton l25;
    @FXML
    private ToggleButton l26;
    @FXML
    private ToggleButton l27;
    @FXML
    private ToggleButton l28;
    @FXML
    private ToggleButton l29;
    @FXML
    private ToggleButton l30;
    @FXML
    private ToggleButton l31;
    @FXML
    private ToggleButton l32;
    @FXML
    private ToggleButton l33;
    @FXML
    private ToggleButton l34;
    @FXML
    private ToggleButton l35;
    @FXML
    private ToggleButton l36;
    @FXML
    private ToggleButton l37;
    @FXML
    private ToggleButton l38;
    @FXML
    private ToggleButton l39;
    @FXML
    private ToggleButton l40;
    @FXML
    private ToggleButton l41;
    @FXML
    private ToggleButton l42;
    @FXML
    private ToggleButton l43;
    @FXML
    private ToggleButton l44;
    @FXML
    private ToggleButton l45;
    @FXML
    private ToggleButton l46;
    @FXML
    private ToggleButton l47;
    @FXML
    private ToggleButton l48;
    @FXML
    private ToggleButton l49;
    @FXML
    private ToggleButton l50;
    @FXML
    private ToggleButton l51;
    @FXML
    private ToggleButton l52;
    @FXML
    private ToggleButton l53;
    @FXML
    private ToggleButton l54;
    @FXML
    private ToggleButton l55;
    @FXML
    private ToggleButton l56;
    @FXML
    private ToggleButton l57;
    @FXML
    private ToggleButton l58;
    @FXML
    private ToggleButton l59;
    @FXML
    private ToggleButton l60;
    @FXML
    private ToggleButton l61;
    @FXML
    private ToggleButton l62;
    @FXML
    private ToggleButton l63;
    @FXML
    private ToggleButton l64;
    @FXML
    private ToggleButton l65;
    @FXML
    private ToggleButton l66;
    @FXML
    private ToggleButton l67;
    @FXML
    private ToggleButton l68;
    @FXML
    private ToggleButton l69;
    @FXML
    private ToggleButton l70;
    @FXML
    private ToggleButton l71;
    @FXML
    private ToggleButton l72;
    @FXML
    private ToggleButton l73;
    @FXML
    private ToggleButton l74;
    @FXML
    private ToggleButton l75;
    @FXML
    private ToggleButton l76;
    @FXML
    private ToggleButton l77;
    @FXML
    private ToggleButton l78;
    @FXML
    private ToggleButton l79;
    @FXML
    private ToggleButton l80;
    @FXML
    private ToggleButton l81;
    @FXML
    private ToggleButton l82;
    @FXML
    private ToggleButton l83;
    @FXML
    private ToggleButton l84;
    @FXML
    private ToggleButton l85;
    @FXML
    private ToggleButton l86;
    @FXML
    private ToggleButton l87;
    @FXML
    private ToggleButton l88;
    @FXML
    private ToggleButton l89;
    @FXML
    private ToggleButton l90;
    @FXML
    private ToggleButton l91;
    @FXML
    private ToggleButton l92;
    @FXML
    private ToggleButton l93;
    @FXML
    private ToggleButton l94;
    @FXML
    private ToggleButton l95;
    @FXML
    private ToggleButton l96;
    @FXML
    private ToggleButton l97;
    @FXML
    private ToggleButton l98;
    @FXML
    private ToggleButton l99;
    @FXML
    private ToggleButton l100;
    @FXML
    private ToggleButton l101;
    @FXML
    private ToggleButton l102;
    @FXML
    private ToggleButton l103;
    @FXML
    private ToggleButton l104;
    @FXML
    private ToggleButton l105;
    @FXML
    private ToggleButton l106;
    @FXML
    private ToggleButton l107;
    @FXML
    private ToggleButton l108;
    @FXML
    private ToggleButton l109;
    @FXML
    private ToggleButton l110;
    @FXML
    private ToggleButton l111;
    @FXML
    private ToggleButton l112;
    @FXML
    private ToggleButton l113;
    @FXML
    private ToggleButton l114;
    @FXML
    private ToggleButton l115;
    @FXML
    private ToggleButton l116;
    @FXML
    private ToggleButton l117;
    @FXML
    private ToggleButton l118;
    @FXML
    private ToggleButton l119;
    @FXML
    private ToggleButton l120;
    @FXML
    private ToggleButton l121;
    @FXML
    private ToggleButton l122;
    @FXML
    private ToggleButton l123;
    @FXML
    private ToggleButton l124;
    @FXML
    private ToggleButton l125;
    @FXML
    private ToggleButton l126;
    @FXML
    private ToggleButton l127;
    @FXML
    private ToggleButton l128;
    @FXML
    private ToggleButton l129;
    @FXML
    private ToggleButton l130;
    @FXML
    private ToggleButton l131;
    @FXML
    private ToggleButton l132;
    @FXML
    private ToggleButton l133;
    @FXML
    private ToggleButton l134;
    @FXML
    private ToggleButton l135;
    @FXML
    private ToggleButton l136;
    @FXML
    private ToggleButton l137;
    @FXML
    private ToggleButton l138;
    @FXML
    private ToggleButton l139;
    @FXML
    private ToggleButton l140;
    @FXML
    private ToggleButton l141;
    @FXML
    private ToggleButton l142;
    @FXML
    private ToggleButton l143;
    @FXML
    private ToggleButton l144;
    @FXML
    private ToggleButton l145;
    @FXML
    private ToggleButton l146;
    @FXML
    private ToggleButton l147;
    @FXML
    private ToggleButton l148;
    @FXML
    private ToggleButton l149;
    @FXML
    private ToggleButton l150;
    @FXML
    private ToggleButton l151;
    @FXML
    private ToggleButton l152;
    @FXML
    private ToggleButton l153;
    @FXML
    private ToggleButton l154;
    @FXML
    private ToggleButton l155;
    @FXML
    private ToggleButton l156;
    @FXML
    private ToggleButton l157;
    @FXML
    private ToggleButton l158;
    @FXML
    private ToggleButton l159;
    @FXML
    private ToggleButton l160;
    @FXML
    private ToggleButton l161;
    @FXML
    private ToggleButton l162;
    @FXML
    private ToggleButton l163;
    @FXML
    private ToggleButton l164;
    @FXML
    private ToggleButton l165;
    @FXML
    private ToggleButton l166;
    @FXML
    private ToggleButton l167;
    @FXML
    private ToggleButton l168;
    @FXML
    private ToggleButton l169;
    @FXML
    private ToggleButton l170;
    @FXML
    private ToggleButton l171;
    @FXML
    private ToggleButton l172;
    @FXML
    private ToggleButton l173;
    @FXML
    private ToggleButton l174;
    @FXML
    private ToggleButton l175;
    @FXML
    private ToggleButton l176;
    @FXML
    private ToggleButton l177;
    @FXML
    private ToggleButton l178;
    @FXML
    private ToggleButton l179;
    @FXML
    private ToggleButton l180;
    @FXML
    private ToggleButton l181;
    @FXML
    private ToggleButton l182;
    @FXML
    private ToggleButton l183;
    @FXML
    private ToggleButton l184;
    @FXML
    private ToggleButton l185;
    @FXML
    private ToggleButton l186;
    @FXML
    private ToggleButton l187;
    @FXML
    private ToggleButton l188;
    @FXML
    private ToggleButton l189;
    @FXML
    private ToggleButton l190;
    @FXML
    private ToggleButton l191;
    @FXML
    private ToggleButton l192;
    @FXML
    private ToggleButton l193;
    @FXML
    private ToggleButton l194;
    @FXML
    private ToggleButton l195;
    @FXML
    private ToggleButton l196;
    @FXML
    private ToggleButton l197;
    @FXML
    private ToggleButton l198;
    @FXML
    private ToggleButton l199;
    @FXML
    private ToggleButton l200;
    @FXML
    private ToggleButton l201;
    @FXML
    private ToggleButton l202;
    @FXML
    private ToggleButton l203;
    @FXML
    private ToggleButton l204;
    @FXML
    private ToggleButton l205;
    @FXML
    private ToggleButton l206;
    @FXML
    private ToggleButton l207;
    @FXML
    private ToggleButton l208;
    @FXML
    private ToggleButton l209;
    @FXML
    private ToggleButton l210;
    @FXML
    private ToggleButton l211;
    @FXML
    private ToggleButton l212;
    @FXML
    private ToggleButton l213;
    @FXML
    private ToggleButton l214;
    @FXML
    private ToggleButton l215;
    @FXML
    private ToggleButton l216;
    @FXML
    private ToggleButton l217;
    @FXML
    private ToggleButton l218;
    @FXML
    private ToggleButton l219;
    @FXML
    private ToggleButton l220;

    @FXML
    private Button butonRezervare;


    private Service service;
    private List<ToggleButton> locuriButoane = new ArrayList<>();

    @FXML
    public void initialize() throws NoSuchFieldException, IllegalAccessException {
        for (int i = 1; i <= 220; i++) {
            ToggleButton buton = (ToggleButton) getClass().getDeclaredField("l" + i).get(this);
            locuriButoane.add(buton);
        }
    }


    public void setService(Service service){
        this.service = service;
        try {
            initialize();
            initLocuri();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private void initLocuri() throws Exception {
        List<Loc> locuri = service.getSala();

        for (int i = 0; i < locuri.size(); i++) {
            Loc loc = locuri.get(i);
            ToggleButton btn = locuriButoane.get(i); // folosim butonul deja existent

            btn.setUserData(loc.getId()); // păstrăm ID-ul locului în buton

            if (loc.getStare()) {
                btn.setStyle("-fx-background-color: red;");
                btn.setDisable(true);
            }

//            btn.setOnAction(e -> {
//                if (btn.isSelected()) {
//                    btn.setStyle("-fx-background-color: green;");
//                } else {
//                    btn.setStyle("");
//                }
//            });
        }
    }


    //"Locul cu numărul " + loc.getNumar() + " de la loja " + loc.getLoja() + " este deja ocupat."

    @FXML
    protected void onButonRezervareClick() {
        try {
            handleRezervaAction();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleRezervaAction() throws IOException {
        try {
            // Selectăm locurile bifate (ToggleButton selectate)
            List<Integer> locuriSelectate = locuriButoane.stream()
                    .filter(ToggleButton::isSelected)
                    .map(b -> (Integer) b.getUserData())
                    .collect(Collectors.toList());

            if (locuriSelectate.isEmpty()) {
                // Nu ai selectat nimic -> mesaj de avertizare
                Alert alert = new Alert(Alert.AlertType.WARNING, "Selectați cel puțin un loc!", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            // Încarcăm rezervare-view.fxml

            FXMLLoader loader = new FXMLLoader(getClass().getResource("rezervare-view.fxml"));
            Parent root = loader.load();

            // Controllerul pentru fereastra de rezervare
            RezervareController rezController = loader.getController();
            rezController.setService(service);

            // Trimitem locurile selectate către RezervareController
            rezController.setLocuriSelectate(locuriSelectate, locuriButoane);

            // Setăm referința la fereastra principală ca să putem reveni
            rezController.setParentController(this);

            // Deschidem noua fereastră
            Stage stage = new Stage();
            stage.setTitle("Date personale spectator");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}