package tamagochi.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.util.Duration;
import tamagochi.model.Creature;
import tamagochi.service.GameLogic;

public class PrimaryController {
    @FXML
    private Label messageLabel;

    @FXML
    private Label healthLabel;

    @FXML
    private Label hungerLabel;

    @FXML
    private Label staminaLabel;

    @FXML
    private Label happinessLabel;

    @FXML
    private Button btnSleep;

    @FXML
    private javafx.scene.image.ImageView creatureImage;


    private Creature creature;
  

    public PrimaryController() {
        // Create an instance of Creature
        creature = new Creature("Lubullule");
        GameLogic gameLogic = new GameLogic(creature);
       
        gameLogic.startGame();
    }

    // Method to update the Labels in real-time
    public void updateLabel() {

        String message = creature.getMessage();
        messageLabel.setText(message);

        Image image = new Image(getClass().getResource("/sprites/lubullule_smile.png").toExternalForm());

        if (creature.getHappiness() < 20 || creature.getHealth() < 20) {
            image = new Image(getClass().getResource("/sprites/lubullule_sad.png").toExternalForm());
           
        }
        if (creature.getHunger() > 80) {
            image = new Image(getClass().getResource("/sprites/lubullule_hungry.png").toExternalForm());
        }
        if (creature.getStamina() < 20) {
            image = new Image(getClass().getResource("/sprites/lubullule_fatigued.png").toExternalForm());
        }

        creatureImage.setImage(image);
        
        // Update health
        int health = creature.getHealth();
        String healthText = health + "%";
        healthLabel.setText(healthText);
        if (health > 75) {
            healthLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        } else if (health > 50) {
            healthLabel.setStyle("-fx-text-fill: #DAA520; -fx-font-weight: bold;");
        } else if (health > 25) {
            healthLabel.setStyle("-fx-text-fill: orange; -fx-font-weight: bold;");
        } else {
            healthLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        }

        // Update hunger
        float hunger = Math.round(creature.getHunger() * 100.0f) / 100.0f;
        String hungerText = hunger + "%";
        hungerLabel.setText(hungerText);
        if (hunger > 75) {
            hungerLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        } else if (hunger > 50) {
            hungerLabel.setStyle("-fx-text-fill: orange; -fx-font-weight: bold;");
        } else if (hunger > 25) {
            hungerLabel.setStyle("-fx-text-fill: #DAA520; -fx-font-weight: bold;"); // Use a less bright yellow (Goldenrod)
        } else {
            hungerLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        }

        // Update stamina
        int stamina = creature.getStamina();
        String staminaText = stamina + "%";
        staminaLabel.setText(staminaText);
        if (stamina > 75) {
            staminaLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        } else if (stamina > 50) {
            staminaLabel.setStyle("-fx-text-fill: #DAA520; -fx-font-weight: bold;");
        } else if (stamina > 25) {
            staminaLabel.setStyle("-fx-text-fill: orange; -fx-font-weight: bold;");
        } else {
            staminaLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        }

        // Update happiness
        int happiness = creature.getHappiness();
        String happinessText = happiness + "%";
        happinessLabel.setText(happinessText);
        if (happiness > 75) {
            happinessLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        } else if (happiness > 50) {
            happinessLabel.setStyle("-fx-text-fill: #DAA520; -fx-font-weight: bold;");
        } else if (happiness > 25) {
            happinessLabel.setStyle("-fx-text-fill: orange; -fx-font-weight: bold;");
        } else {
            happinessLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
        }
    }

    // Initialization method to start updating from the beginning
    @FXML
    public void initialize() {
       
        // Immediately update the labels
        updateLabel();

        Timeline messageTimeline = new Timeline(
            new KeyFrame(Duration.seconds(3), event -> messageLabel.setText(""))
        );
        messageTimeline.play();

        // Use a Timeline to regularly update the labels
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), event -> {
                updateLabel();
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE); // The Timeline repeats indefinitely
        timeline.play(); 
        
    }

    @FXML
    public void handleEat() {
        creature.eat();
    }

    @FXML
    public void handlePlay() {
        creature.play();
    }

    @FXML
    public void handleSleepOrWake() {
        if ("Dormir".equals(btnSleep.getText())) {
            creature.sleep(); 
            btnSleep.setText("Réveiller");
        } else {
            creature.wake(); 
            btnSleep.setText("Dormir");
            messageLabel.setText(creature.getName() + " is not happy to have been woken up.");
        }
    }
}
