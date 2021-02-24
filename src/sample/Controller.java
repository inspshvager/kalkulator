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
    private BigDecimal left;
    private String selected;
    private boolean number;
    @FXML
    private TextField display;
    public Controller(){
        this.left = BigDecimal.ZERO;
        this.selected = "";
        this.number = false;
    }
    @FXML
    public void Button(ActionEvent evt){
        Button button = (Button)evt.getSource();
        String buttonText = button.getText();
        if (buttonText.equals("C")){
            if (buttonText.equals("C")){
                left = BigDecimal.ZERO;
            }
            selected = "";
            number = false;
            display.setText("0");
            return;
        }
        if (buttonText.matches("[0-9\\.]")){
            if (!number) {
                number = true;
                display.clear();
            }
            display.appendText(buttonText);
            return;
        }
        if (buttonText.matches("[+-/*]")) {
            left = new BigDecimal(display.getText());
            selected = buttonText;
            number = false;
            return;
        }
        if (buttonText.equals("=")){
            BigDecimal right = number ? new BigDecimal(display.getText()) : left;
            left = calculate(selected, left, right);
            display.setText(left.toString());
            number = false;
            return;
        }
    }
    static BigDecimal calculate(String symbol, BigDecimal left, BigDecimal right) {
        switch (symbol){
            case "+":
                return left.add(right);
            case "-":
                return left.subtract(right);
            case "*":
                return left.multiply(right);
            case "/":
                return left.divide(right);
            default:
        }
            return right;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){}
}