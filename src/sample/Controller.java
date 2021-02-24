package sample;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
public class Controller implements Initializable{
    private BigDecimal wartosc;                     // BigDecimal pozwala na używanie metod add, subtrack, multiply, zero
    private String znak;                            // znak to wybrany symbol przycisku
    private boolean czyNumer;                       // czyNumer informuje nas czy wciśnięty przez nas znak jest numerem czy nie
    @FXML
    private TextField display;                      // wyświetla tekst w polu kalkulatora
    public Controller(){                            // Konstruktor
        this.wartosc = BigDecimal.ZERO;             // 0
        this.znak = "";
        this.czyNumer = false;
    }
    @FXML
    public void Przycisk(ActionEvent evt){          // metoda przycisk informuje program o czynności jaką ma podjąć przy naciśnięci przycisku
        Button button = (Button)evt.getSource();
        String buttonText = button.getText();
        if (buttonText.equals("C")){                // informuje program o tym co ma zrobić przy naciśnięci "C"
            if (buttonText.equals("C")){            // ponowne naciśnięcie przycisku ustawia wartość na 0
                wartosc = BigDecimal.ZERO;          // ustawia wartość na 0
            }
            znak = "";
            czyNumer = false;
            display.setText("0");                   // wyświetla "0" w polu kalkulatora
            return;
        }
        if (buttonText.matches("[0-9\\.]")){    // informuje program o tym co ma zrobić przy naciśnięci numerów oraz kropki
            if (!czyNumer) {
                czyNumer = true;
                display.clear();
            }
            display.appendText(buttonText);           // wyświetla dodany tekst (naciśnięcie 2 i 4 da nam 24 w polu kalkulatora)
            return;
        }
        if (buttonText.matches("[+-/*]")) {     // informuje program o tym co ma zrobić przy naciśnięciu +,-,*,/
            wartosc = new BigDecimal(display.getText());
            znak = buttonText;                        // ustawiamy wartość znaku na taki znak który nacisneliśmy
            czyNumer = false;
            return;
        }
        if (buttonText.equals("=")){                  // informuje program o tym co ma zrobić przy naciśnięci znaku "="
            BigDecimal wynik = czyNumer ? new BigDecimal(display.getText()) : wartosc;
            wartosc = wylicz(znak, wartosc, wynik);   // poprzez wybrany znak (+,-,*,/) program wylicza wynik
            display.setText(wartosc.toString());
            czyNumer = false;
            return;
        }
    }
    static BigDecimal wylicz(String symbol, BigDecimal wartosc, BigDecimal wynik) {     // Metoda "wylicz" która mówi aplikacji co ma zrobić przy naciśnięci symboli: +,-,*,/
        switch (symbol){
            case "+":                                                                   //  Dodawanie
                return wartosc.add(wynik);
            case "-":                                                                   //  Odejmowanie
                return wartosc.subtract(wynik);
            case "*":                                                                   //  Mnożenie
                return wartosc.multiply(wynik);
            case "/":                                                                   //  Dzielenie
                return wartosc.divide(wynik);
        }
        return wynik;                                                                   // Zwracamy wynik
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){}}