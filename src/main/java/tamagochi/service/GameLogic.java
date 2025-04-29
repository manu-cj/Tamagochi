package tamagochi.service;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import tamagochi.model.Creature;

public class GameLogic {
    private final Creature creature;
    private Timeline stateDegradationTimeline;
    private Timeline damageTimeline;

    public GameLogic(Creature creature) {
        this.creature = creature;
    }

    public void startGame() {
        startStateDegradation();
        startDamageOverTime();
    }

    private void startStateDegradation() {
        stateDegradationTimeline = new Timeline(
            new KeyFrame(Duration.seconds(5), event -> creature.degradeStates())
        );
        stateDegradationTimeline.setCycleCount(Timeline.INDEFINITE);
        stateDegradationTimeline.play();
        
    }

    private void startDamageOverTime() {
        damageTimeline = new Timeline(
            new KeyFrame(Duration.seconds(5), event -> creature.applyDamageOverTime())
        );
        damageTimeline.setCycleCount(Timeline.INDEFINITE);
        damageTimeline.play();
    }

    

    public void stopGame() {
        if (stateDegradationTimeline != null) {
            stateDegradationTimeline.stop();
        }
        if (damageTimeline != null) {
            damageTimeline.stop();
        }
        System.out.println("Jeu arrêté.");
    }
}
