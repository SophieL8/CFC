// Carbon Footprint Calculator; formula taken from Alexandra Shimo-Barry, author of “The Environment Equation”
/*
https://clevercarbon.io/quiz/
https://clevercarbon.io/how-to-calculate-your-carbon-footprint/
Low footprint < 15,999 pounds per year. 16,000-22,000 is considered average. Under 6,000 is considered very low. Over 22,000 is high
*/

// add default option for each dropdown. then later, add error message if default option is still chosen
// add graphical feature

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Point2D;
import javafx.scene.chart.NumberAxis;
import javafx.geometry.Insets;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CarbonPrintCalculator extends Application {

   private ComboBox commute = new ComboBox();
   private ComboBox typeOfCar = new ComboBox();
   private Slider flyHours = new Slider(0, 120, 3);
   private ComboBox diet = new ComboBox();
   private ComboBox energy = new ComboBox();
   private TextField flyHoursLabel = new TextField();
   private Button cpCalculate = new Button("Calculate");
   private Button rCalculate = new Button("Get Comparison");
   private TextField tfTotalFootprint = new TextField();   
   private TextField tfcomparison = new TextField();   


  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
      Line line = new Line(100, 10, 10, 110);

      commute.getItems().addAll("Walking or Biking", "Car", "Public Transporation/Other methods");
      typeOfCar.getItems().addAll("Electric", "Other", "Don't own a car");
      diet.getItems().addAll("Plants", "Equal amounts of veggies and meat", "Meat");
      energy.getItems().addAll("100% renewable", "Some are renewable", "100% non renewable");

       // Create UI
       GridPane gridPane = new GridPane();
       gridPane.setHgap(5);
       gridPane.setVgap(5);
       gridPane.add(new Label("How do you normally commute?: "), 0, 0);
       gridPane.add(commute, 1, 0);
       gridPane.add(new Label("What type of car do you own?: "), 0, 1);
       gridPane.add(typeOfCar, 1, 1);
       gridPane.add(new Label("Hours flown in the last year: :"), 0, 2);
       gridPane.add(flyHours, 1, 2);
       gridPane.add(new Label("What do you normally eat?: "), 0, 3);
       gridPane.add(diet, 1, 3);
       gridPane.add(new Label("Energy at your home is:"), 0, 4);
       gridPane.add(energy, 1, 4);
       gridPane.add(tfTotalFootprint, 1, 5);
       gridPane.add(new Label("Total Carbon Footprint:"), 0, 5);
       gridPane.add(cpCalculate, 1, 6);
       gridPane.add(rCalculate, 1, 9);
       
       gridPane.add(new Label("Compared to the average US carbon" + "\n"
                                 + "footprint, your carbon footprint is:"), 0, 9);
       gridPane.add(tfcomparison, 1, 10);

       tfcomparison.setEditable(false);
       tfTotalFootprint.setEditable(false);
       
       // Set properties for slider
        flyHours.setShowTickMarks(true);
        flyHours.setShowTickLabels(true);
        flyHours.setMajorTickUnit(60);

        Label label = new Label();
        Popup popup = new Popup();
        popup.getContent().add(label);

        double offset = 10 ;

        flyHours.setOnMouseMoved(e -> {
            NumberAxis axis = (NumberAxis) flyHours.lookup(".axis");
            Point2D locationInAxis = axis.sceneToLocal(e.getSceneX(), e.getSceneY());
            double mouseX = locationInAxis.getX() ;
            double value = axis.getValueForDisplay(mouseX).doubleValue() ;
            if (value >= flyHours.getMin() && value <= flyHours.getMax()) {
                label.setText(String.format("Value: %.1f", value));
            } else {
                label.setText("Value: ---");
            }
            popup.setAnchorX(e.getScreenX());
            popup.setAnchorY(e.getScreenY() + offset);
        });

        flyHours.setOnMouseEntered(e -> popup.show(flyHours, e.getScreenX(), e.getScreenY() + offset));
        flyHours.setOnMouseExited(e -> popup.hide());
        
        Alert a = new Alert(AlertType.NONE);
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                a.setAlertType(AlertType.ERROR);
                a.show();
            }
        };
   
       // Set properties for UI
      
       gridPane.setAlignment(Pos.CENTER);
       
       if (commute!=null)
         cpCalculate.setOnAction(e -> calculateCarbonPrint());
         rCalculate.setOnAction(e -> calculateR());
       }
       
       cpCalculate.setOnAction(event);
       rCalculate.setOnAction(event);

       // Create a scene and place it in the stage
       Scene scene = new Scene(gridPane, 400, 250);
       primaryStage.setTitle("Carbon Print Calculator"); // Set title
       primaryStage.setScene(scene); // Place the scene in the stage
       primaryStage.show(); // Display the stage
  }
  
  private void calculateCarbonPrint() {
    // Get values from text fields
    String myCommute = (String)commute.getValue();
    String mytypeOfCar = (String)typeOfCar.getValue();
    double myflyHours = (Double)flyHours.getValue();
    String myDiet = (String)diet.getValue();
    String myEnergy = (String)energy.getValue();

    CarbonPrint print = new CarbonPrint(myCommute, mytypeOfCar, myflyHours, myDiet, myEnergy);

    tfTotalFootprint.setText(String.format("%.2f", print.getTotalFootprint()));
  }

  private void calculateR() {
    // Get values from text fields
    String myCommute = (String)commute.getValue();
    String mytypeOfCar = (String)typeOfCar.getValue();
    double myflyHours = (Double)flyHours.getValue();
    String myDiet = (String)diet.getValue();
    String myEnergy = (String)energy.getValue();
    
      CarbonPrint print = new CarbonPrint(myCommute, mytypeOfCar, myflyHours, myDiet, myEnergy);
    
      Double a = new Double(print.getTotalFootprint());
      
         if (a < 16.0) {
             tfcomparison.setText("Less than Average");
             tfcomparison.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
         }
          
         else if (a > 16.0 && a < 22.0) {
             tfcomparison.setText("Average");
             tfcomparison.setStyle("-fx-text-fill: orange; -fx-font-size: 16px;");
         }
         
         else if (a > 22.0) {
             tfcomparison.setText("Greater than Average");
             tfcomparison.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
         }
    }
  }
