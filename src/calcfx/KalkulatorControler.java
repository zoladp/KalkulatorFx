package calcfx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class KalkulatorControler {
    int klikniecie = 0;
    public static ArrayList<String> listaOperatorowAryt = new ArrayList<String>();
    public static ArrayList<Integer> listaLiczb = new ArrayList<Integer>();
//    public static String currentText;
//    public static TextField currentField;
    public static int result;
    public static double resultDziel;

    @FXML
    private TextField resultField;
    @FXML
    private Button resetBtn;
    @FXML
    private Button procentBtn;
    @FXML
    private Button cyfra9;
    @FXML
    private Button cyfra8;
    @FXML
    private Button cyfra7;
    @FXML
    private Button cyfra6;
    @FXML
    private Button cyfra5;
    @FXML
    private Button cyfra4;
    @FXML
    private Button cyfra3;
    @FXML
    private Button cyfra2;
    @FXML
    private Button cyfra1;
    @FXML
    private Button equalsBtn;
    @FXML
    private Button wylaczBtn;

    @FXML
    public void initialize() {
        resetBtn.setOnAction(e -> {
            resultField.setText("0");
            listaLiczb.clear();
            listaOperatorowAryt.clear();
        });
        equalsBtn.setOnAction(e -> showWynik());
        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("TO jest handler");
                resultField.setText("" + 7);
            }
        };
    }

    public void cyfraClick(ActionEvent e) {
        String clickedButtonValue = ((Button) e.getSource()).getText();
        System.out.print(clickedButtonValue);
        String currentText = getResultField().getText();
        Pattern liczby = Pattern.compile("^[1-9]$");
        Pattern znakiarytmetyczne = Pattern.compile("[%-+]");
        Matcher matcherLiczb = liczby.matcher(clickedButtonValue);
        if (currentText.equals("0") && matcherLiczb.find()) resultField.setText(clickedButtonValue);
        else if (!currentText.equals("0")) resultField.setText(currentText + clickedButtonValue);
    }

    public void showWynik() throws NumberFormatException {
        String tekstResultField = resultField.getText().trim();
        List<String> listaLiczbTmp = Arrays.asList(tekstResultField.split("[-+*/÷×%]"));
        for (String liczba : listaLiczbTmp) listaLiczb.add(Integer.valueOf(liczba));
        List<String> listaOperatorowTmp = Arrays.asList(tekstResultField.split("[0-9]"));
        for (String operator : listaOperatorowTmp) if (!operator.isEmpty()) listaOperatorowAryt.add(operator);
        //System.out.println("Lista liczba:" + listaLiczb);
        //System.out.println(listaOperatorowAryt);
        System.out.print("\ndzialań: " + listaOperatorowAryt.size());
        wykonOperacje();
        //System.out.println("Liczba klikniec: " + klikniecie);
        if (listaOperatorowAryt.size() == 1) {
            resultField.setText(String.valueOf(result));
            listaLiczb.clear();
            listaOperatorowAryt.clear();
            ++klikniecie;
        }
    }

    public static void wykonOperacje() {
//		result = 0;
        for (int i = 0; i < 1; i++) {
            System.out.print(" ("+listaOperatorowAryt.get(i)+") ");
            switch (listaOperatorowAryt.get(i)) {
                case "+":
                    System.out.print("Dodawanie");
                    if (listaLiczb.size() > 1 && listaOperatorowAryt.size() > 0) {
                        result = listaLiczb.get(i) + listaLiczb.get(i + 1);
                        System.out.println("\nwynik: " + result);
                    } else System.out.println("ERROR");
                    break;
                case "-":
                    System.out.print("Odejmowanie");
                    result = listaLiczb.get(i) - listaLiczb.get(i + 1);
                    System.out.println("\nwynik: " + result);
                    break;
                case "×":
                    System.out.print("Mnożenie");
                    result = listaLiczb.get(i) * listaLiczb.get(i + 1);
                    System.out.println("\nwynik: " + result);
                    break;
                case "÷":
                    System.out.print("Dzielenie");
                    if((double)listaLiczb.get(i) == 0) break;
                    resultDziel = (double)listaLiczb.get(i) / (double)listaLiczb.get(i + 1);
                    System.out.println("\nwynik: " + resultDziel);
                    break;
                default: System.out.println("Operacja nie może być wykonana");
            }
        }
    }

    public TextField getResultField() {
        return this.resultField;
    }

    public static void zamknijKalkulator() {
        Platform.exit();
        System.exit(0);
    }

    public void wylaczBtn() {
        zamknijKalkulator();
    }
}
