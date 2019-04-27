package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.List;
import java.util.Scanner;

public class Controller {

    @FXML
    private DatePicker birthday;

    @FXML
    private Label tooYoung;

    @FXML
    private Label enterBirthday;

    @FXML
    private Label usedMail;

    @FXML
    private CheckBox terms;

    @FXML
    private Button showPassword;

    @FXML
    private Button showPassword1;

    @FXML
    private Button hidePassword;

    @FXML
    private Button hidePassword1;

    @FXML
    private Button confirm;

    @FXML
    private TextField mail;

    @FXML
    private TextField name;

    @FXML
    private TextField city;

    @FXML
    private TextField country;

    @FXML
    private TextField visiblePassword;

    @FXML
    private TextField visiblePassword1;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password1;

    private LocalDate date = LocalDate.now();

    private int x = 0;
    private int y = 0;

    @FXML
    public void onButtonClicked(ActionEvent actionEvent) throws IOException {
        birthday.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.NONE, null, new BorderWidths(0))));
        mail.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.NONE, null, new BorderWidths(0))));
        name.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.NONE, null, new BorderWidths(0))));
        city.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.NONE, null, new BorderWidths(0))));
        country.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.NONE, null, new BorderWidths(0))));
        password.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.NONE, null, new BorderWidths(0))));
        password1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.NONE, null, new BorderWidths(0))));

        enterBirthday.setVisible(false);
        tooYoung.setVisible(false);
        usedMail.setVisible(false);

        boolean welcome = true;

        FileWriter writer = new FileWriter("UsersEmails.txt", true);
        File fl1 = new File("E:\\Java\\MailFx\\UsersEmails.txt");
        Scanner sc = new Scanner(fl1);

        List mails = new ArrayList<String>();

        while (sc.hasNextLine()) {
            mails.add(sc.nextLine());
        }

        if (birthday.getValue() == null) {
            welcome = false;
            birthday.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
            enterBirthday.setVisible(true);
        } else {
            if (date.getYear() - birthday.getValue().getYear() > 18) {
                tooYoung.setVisible(false);
            } else if (date.getYear() - birthday.getValue().getYear() == 18 && date.getMonthValue() > birthday.getValue().getMonthValue()) {
                tooYoung.setVisible(false);
            } else if (date.getYear() - birthday.getValue().getYear() >= 18 && date.getMonthValue() == birthday.getValue().getMonthValue()
                    && date.getDayOfMonth() >= birthday.getValue().getDayOfMonth()) {
                tooYoung.setVisible(false);
            } else {
                birthday.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
                welcome = false;
                tooYoung.setVisible(true);
            }
        }

        if (name.getText().isEmpty()) {
            name.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
            welcome = false;
        }

        if (city.getText().isEmpty()) {
            city.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
            welcome = false;
        }

        if (country.getText().isEmpty()) {
            country.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
            welcome = false;
        }

        if (password.getText().isEmpty()) {
            password.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
            welcome = false;
        }

        if (password1.getText().isEmpty()) {
            password1.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
            welcome = false;
        }

        if (!password1.getText().equals(password.getText())) {
            password1.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
            welcome = false;
        }

        if (mails.contains(mail.getText())) {
            welcome = false;
            usedMail.setVisible(true);
            mail.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        } else if (welcome == false) {
            //does nothing
        }else{
            mails.add(mail.getText());
            writer.write(mail.getText()+ "\n");
            writer.close();
        }

        if(welcome){
            Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
            confirmation.setTitle("Confirmation");
            confirmation.setContentText("Welcome" + name.getText());
            confirmation.showAndWait();
        }
        terms.setSelected(false);
        confirm.setDisable(true);

    }

    @FXML
    public void terms() {
        if(terms.isSelected()){
            confirm.setDisable(false);
        }else {
            confirm.setDisable(true);
        }
    }

    @FXML
    public void showHidePassword(ActionEvent e) {

        if(e.getSource() == showPassword && x % 2 == 0 ){
            password.setVisible(false);
            visiblePassword.setVisible(true);
            visiblePassword.setText(password.getText());
            showPassword.setVisible(false);
            hidePassword.setVisible(true);
            x++;
        }else if(e.getSource() == hidePassword && x % 2 !=0){
            password.setVisible(true);
            visiblePassword.setVisible(false);
            showPassword.setVisible(true);
            hidePassword.setVisible(false);
            x++;
        }

        if(e.getSource() == showPassword1 && y % 2 == 0){
            password1.setVisible(false);
            visiblePassword1.setVisible(true);
            visiblePassword1.setText(password1.getText());
            showPassword1.setVisible(false);
            hidePassword1.setVisible(true);
            y++;
        }else if(e.getSource() == hidePassword1 && y % 2 !=0){
            password1.setVisible(true);
            visiblePassword1.setVisible(false);
            showPassword1.setVisible(true);
            hidePassword1.setVisible(false);
            y++;
        }
    }
}
