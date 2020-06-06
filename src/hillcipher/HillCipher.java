/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hillcipher;

import static hillcipher.Hill.choice;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author 20102
 */
public class HillCipher extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button encrypt_btn = new Button("Encrypt", new ImageView(new Image("hillcipher/usb.png")));
        Button Decrept_btn = new Button("Decrypt", new ImageView(new Image("hillcipher/decrypt.png")));
        encrypt_btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                TextField plain_txt_tf = new TextField();
                TextField codeKey_txt_tf = new TextField();
                Label plain_lbl = new Label("Enter Plain txt :");
                Label key_lbl = new Label("Enter key matrix txt :");
                CheckBox sugguestBox = new CheckBox("Suggest a Key");

                Button encrypt = new Button("ENCRYPT");
                Label en_out = new Label("");

                sugguestBox.setOnAction((event3) -> {
                    if (!sugguestBox.isSelected()) {
                        codeKey_txt_tf.setText("");
                    } else {
                        String[] suggestions = {"GYBNQKURP", "HILLMAGIC"};
                        codeKey_txt_tf.setText(suggestions[(int) Math.random() < 0.5 ? 0 : 1]);
                    }
                });

                encrypt.setOnAction((event2) -> {

                    Hill obj = new Hill();
                    choice = 1;
                    String line = plain_txt_tf.getText();
                    String key = codeKey_txt_tf.getText();
                    double sq = Math.sqrt(key.length());
                    if (sq != (long) sq) {
                        en_out.setText("Cannot Form a square matrix");
                    } else {
                        int size = (int) sq;
                        if (obj.check(key, size)) {
                            obj.cofact(obj.km, size);
                            en_out.setText(obj.performDivision(line, size));
                        }
                    }

                });

                HBox row1 = new HBox(15, plain_lbl, plain_txt_tf);
                HBox row2 = new HBox(15, key_lbl, codeKey_txt_tf, sugguestBox);
                row1.setAlignment(Pos.CENTER);
                row2.setAlignment(Pos.CENTER);
                encrypt.setAlignment(Pos.CENTER);
                en_out.setAlignment(Pos.CENTER);
                VBox main = new VBox(15, row1, row2, encrypt, en_out);
                main.setAlignment(Pos.CENTER);

                Scene s = new Scene(main, 500, 500);
                Stage st = new Stage();
                st.setScene(s);
                st.setTitle("Encrypt");
                st.show();
            }
        });
        Decrept_btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                TextField cipher_txt_tf = new TextField();
                TextField codeKey_txt_tf = new TextField();
                Label plain_lbl = new Label("Enter Cipher txt :");
                Label key_lbl = new Label("Enter key matrix txt :");
                CheckBox sugguestBox = new CheckBox("Suggest a Key");

                Button encrypt = new Button("DECRYPT");
                Label en_out = new Label("");

                sugguestBox.setOnAction((event3) -> {
                    if (!sugguestBox.isSelected()) {
                        codeKey_txt_tf.setText("");
                    } else {
                        String[] suggestions = {"GYBNQKURP", "HILLMAGIC"};
                        codeKey_txt_tf.setText(suggestions[(int) Math.random() < 0.5 ? 0 : 1]);
                    }
                });

                encrypt.setOnAction((event2) -> {

                    Hill obj = new Hill();
                    choice = 2;
                    String line = cipher_txt_tf.getText();
                    String key = codeKey_txt_tf.getText();
                    double sq = Math.sqrt(key.length());
                    if (sq != (long) sq) {
                        en_out.setText("Cannot Form a square matrix");
                    } else {
                        int size = (int) sq;
                        if (obj.check(key, size)) {
                            obj.cofact(obj.km, size);
                            en_out.setText(obj.performDivision(line, size));
                        }
                    }

                });

                HBox row1 = new HBox(15, plain_lbl, cipher_txt_tf);
                HBox row2 = new HBox(15, key_lbl, codeKey_txt_tf, sugguestBox);
                row1.setAlignment(Pos.CENTER);
                row2.setAlignment(Pos.CENTER);
                encrypt.setAlignment(Pos.CENTER);
                en_out.setAlignment(Pos.CENTER);
                VBox main = new VBox(15, row1, row2, encrypt, en_out);
                main.setAlignment(Pos.CENTER);

                Scene s = new Scene(main, 500, 500);
                Stage st = new Stage();
                st.setScene(s);
                st.setTitle("Decrypt");
                st.show();

            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(new HBox(10, encrypt_btn, Decrept_btn));

        Scene scene = new Scene(root, 500, 500);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
