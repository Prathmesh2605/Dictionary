package com.example.dictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    Label meaningLabel = new Label("meaning");
    Button searchButton = new Button("Search");
    TextField wordTextField = new TextField();

    ListView<String> suggestionList;
    DictionaryUsingHashMap dictionaryUsingHashMap = new DictionaryUsingHashMap();
    Pane createContent(){
        Pane root = new Pane();

        root.setPrefSize(400,600);


        searchButton.setTranslateX(250);
        searchButton.setTranslateY(50);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //meaningLabel.setText();
               // wordTextField.getText();
                String word = wordTextField.getText();
                if(word.isBlank() || word.isEmpty()){
                    meaningLabel.setText("Please enter a word");
                    meaningLabel.setTextFill(Color.RED);

                }
                else{
                    String meaning = dictionaryUsingHashMap.getMeaning(word);
                    meaningLabel.setText(meaning);
                    meaningLabel.setTextFill(Color.BLACK);
                }
            }
        });


        wordTextField.setTranslateX(50);
        wordTextField.setTranslateY(50);


        meaningLabel.setTranslateX(50);
        meaningLabel.setTranslateY(100);

        suggestionList = new ListView<>();
        suggestionList.setTranslateX(20);
        suggestionList.setTranslateY(200);

        suggestionList.setMinSize(300,20);
        suggestionList.setMaxSize(300,50);

        String [] wordList = {"Prathmesh", "Rahul", "Angad", "Vivek"};
        suggestionList.getItems().addAll(wordList);
        suggestionList.setOrientation(Orientation.HORIZONTAL);

        suggestionList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectedWord = suggestionList.getSelectionModel().getSelectedItem();
                meaningLabel.setText(selectedWord);
            }
        });


        root.getChildren().addAll(searchButton,wordTextField,meaningLabel,suggestionList);
        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
       // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Dictionary");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}