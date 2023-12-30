package chatroom.project4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ServerGUI extends Application {

    private TextArea textArea = new TextArea();
    private Network connection = createServer();

    public static void main(String[] args) {
        launch(args);
    }

    private Parent createContent() {
        textArea.setEditable(false);
        textArea.setFont(new Font("Century Gothic", 16));
        textArea.setPrefHeight(500);
        textArea.setWrapText(true);
        textArea.setStyle("-fx-control-inner-background: #e8e5e4");
        textArea.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        TextField textField = new TextField();
        textField.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        textField.setFont(new Font("Century Gothic", 16));
        textField.setPadding(new Insets(10, 0, 0, 0));
        textField.setOnAction(event -> {
            String userMessage = "SERVER: ";
            userMessage += textField.getText();
            textField.clear();

            textArea.appendText(userMessage + "\n");

            try {
                connection.send(userMessage);
            } catch (Exception e) {
                textArea.appendText("Failed to send\n");
            }
        });

        VBox vBox = new VBox(15, textArea, textField);
        vBox.setPrefSize(400, 300);
        return vBox;
    }

    @Override
    public void init() throws Exception {
        connection.startConnection();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SERVER");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
        primaryStage.setWidth(400);
        primaryStage.setHeight(600);
    }

    @Override
    public void stop() throws Exception {
        connection.closeConnection();
    }

    private ServerNetwork createServer() {
        return new ServerNetwork(8691, data -> {
            Platform.runLater(() -> {
                textArea.appendText(data.toString() + "\n");
            });
        });
    }
}
