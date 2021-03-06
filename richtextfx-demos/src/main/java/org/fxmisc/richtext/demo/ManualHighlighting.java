package org.fxmisc.richtext.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import org.fxmisc.richtext.StyleClassedTextArea;

public class ManualHighlighting extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private final StyleClassedTextArea area = new StyleClassedTextArea();
    {
        area.setWrapText(true);
    }

    @Override
    public void start(Stage primaryStage) {
        HBox panel = new HBox();
        Button red = createColorButton(Color.RED, "red");
        Button green = createColorButton(Color.GREEN, "green");
        Button blue = createColorButton(Color.BLUE, "blue");
        Button bold = createBoldButton("bold");
        panel.getChildren().addAll(red, green, blue, bold);

        VBox vbox = new VBox();
        VBox.setVgrow(area, Priority.ALWAYS);
        vbox.getChildren().addAll(panel, area);

        Scene scene = new Scene(vbox, 600, 400);
        scene.getStylesheets().add(ManualHighlighting.class.getResource("manual-highlighting.css").toExternalForm());
        primaryStage.setScene(scene);
        area.requestFocus();
        primaryStage.setTitle("Manual Highlighting Demo");
        primaryStage.show();
    }

    private Button createBoldButton(String styleClass) {
        Button button = new Button("B");
        button.styleProperty().set("-fx-font-weight: bold;");
        setPushHandler(button, styleClass);
        return button;
    }

    private Button createColorButton(Color color, String styleClass) {
        Rectangle rect = new Rectangle(20, 20, color);
        Button button = new Button(null, rect);
        setPushHandler(button, styleClass);
        return button;
    }

    private void setPushHandler(Button button, String styleClass) {
        button.onActionProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                IndexRange range = area.getSelection();
                area.setStyleClass(range.getStart(), range.getEnd(), styleClass);
                area.requestFocus();
            }
        });
    }

}
