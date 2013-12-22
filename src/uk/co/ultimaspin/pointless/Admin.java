package uk.co.ultimaspin.pointless;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 30/11/2013
 * Time: 20:45
 * To change this template use File | Settings | File Templates.
 */
public class Admin {

    final TextField textField = new TextField();
    final PointlessApplication app;

    public Admin(PointlessApplication app) {
        this.app = app;

        // Admin console
        final Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(500);
        slider.setValue(300);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(100);
        slider.setMinorTickCount(50);

        final Slider delaySlide = new Slider();
        delaySlide.setMin(0);
        delaySlide.setMax(10);
        delaySlide.setValue(3);
        delaySlide.setShowTickLabels(true);
        delaySlide.setShowTickMarks(true);
        delaySlide.setMajorTickUnit(1);
//        delaySlide.setMinorTickCount(50);

        final Button startButton = new Button("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (validateText()) {
                    double target = Double.parseDouble(textField.getText());

                    Admin.this.app.animate(target, 501 - slider.getValue(), delaySlide.getValue());
                    startButton.setDisable(true);
                } else {
                    // TODO How to alert this error
                }
            }
        });

        Button resetButton = new Button("Reset");
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Admin.this.app.reset();
                startButton.setDisable(false);
            }
        });

        Button bigger = new Button("+");
        bigger.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Admin.this.app.bigger();
            }
        });

        Button smaller = new Button("-");
        smaller.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Admin.this.app.smaller();
            }
        });


        // Admin console
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(0, 10, 0, 10));

        grid.add(new Label("Score: "), 0, 0);
        grid.add(textField, 1, 0);
        grid.add(startButton, 2, 0);
        grid.add(resetButton, 3, 0);

        grid.add(new Label("Speed: "), 0, 1);
        GridPane.setConstraints(slider, 1, 1, 3, 1);
        grid.add(slider, 1, 1);

        grid.add(new Label("Delay: "), 0, 2);
        GridPane.setConstraints(delaySlide, 1, 1, 3, 1);
        grid.add(delaySlide, 1, 2);

        grid.add(new Label("Height: "), 0, 3);
        grid.add(smaller, 1, 3);
        grid.add(bigger, 2, 3);


        Button wider = new Button("+");
        wider.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Admin.this.app.wider();
            }
        });

        Button narrower = new Button("-");
        narrower.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Admin.this.app.narrower();
            }
        });

        grid.add(new Label("Width: "), 0, 4);
        grid.add(narrower, 1, 4);
        grid.add(wider, 2, 4);


        FlowPane flowPaneContainer = new FlowPane();
        flowPaneContainer.getChildren().addAll(grid);
        flowPaneContainer.setAlignment(Pos.CENTER);

        Stage adminStage = new Stage();
        adminStage.setTitle("Quiz Master Control Panel");
        adminStage.setScene(new Scene(flowPaneContainer, 400, 300));
        adminStage.sizeToScene();
        adminStage.show();

    }

    private boolean validateText() {

        String input = textField.getText();

        try {
            double val = Double.parseDouble(input);
            return 0 <= val && val <= 100; // TODO Move hard-coded numbers
        } catch (Exception e) {
            return false;
        }
    }

}
