package tamagochi.model;



public class Creature {
    private String name;
    private int age;
    private int health;
    private float hunger;
    private int happiness;
    private int stamina;
    private boolean sleep;
    private boolean isFatigued;
    private boolean isHungry;
    private boolean isSad;
    private boolean isDead;
    private boolean damageThreadStarted;
    private boolean stateThreadStarted;
    private String message;


    public Creature(String name) {
        this.name = name;
        this.age = 0;
        this.health = 100;
        this.hunger = 0;
        this.happiness = 100;
        this.stamina = 100;
        this.sleep = false;
        this.isFatigued = false;
        this.isHungry = false;
        this.isSad = false;
        this.isDead = false;
        this.damageThreadStarted = false;
        this.stateThreadStarted = false;
        this.message = "";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getHealth() {
        return health;
    }

    public float getHunger() {
        return hunger;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getStamina() {
        return stamina;
    }

    public boolean isSleeping() {
        return sleep;
    }

    public boolean isFatigued() {
        return isFatigued;
    }

    public boolean isHungry() {
        return isHungry;
    }

    public boolean isSad() {
        return isSad;
    }


    public boolean isDead() {
        return isDead;
    }
    public String getMessage() {
        return message;
    }

    public void eat() {
        if (hunger == 0) {
            System.out.println(name + " n'a pas faim !");
        }
        hunger = Math.max(0, hunger - 20);
        happiness = Math.min(100, happiness + 5);
        health = Math.min(100, health + 15);
        stamina = Math.min(100, stamina + 20);
        message = name + " a mangé et se sent mieux !";
        updateState();
    }


    public void play() {
        happiness = Math.min(100, happiness + 15);
        hunger = Math.min(100, hunger + 8);
        stamina = Math.max(0, stamina - 10);
        message = name + " a joué";
        updateState();
        if (stamina == 0) {
            message = name + " est trop fatigué pour jouer davantage.";
        }
    }

    // sleep 10 seconds for regen stamina
    public void sleep() {
        if (sleep) {
            message = name + " dort déjà.";
            return;
        }
        sleep = true;
        new Thread(() -> {
            while (sleep) {
                try {
                    Thread.sleep(1000);
                    stamina = Math.min(100, stamina + 1);
                    health = Math.min(100, health + 1);
                    hunger = Math.min(100, hunger + 0.6f);
                    if (!sleep) {
                        message = name + " n'est pas content d'avoir été réveiller.";
                    }

                    message = name + " est entrain de dormir...";

                    updateState();
                    if (stamina >= 100) {
                        sleep = false;
                        message = name + " s'est bien reposé.";
                        updateState();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    message = "Le sommeil de " + name + " a été interrompu";
                    System.out.println("Le sommeil de " + name + " a été interrompu : " + e.getMessage());
                }
            }
        }).start();
    }

    public void wake() {
        if (sleep) {
            sleep = false;
            happiness = Math.max(0, happiness - 10);
            updateState();
            
        } else {
            message = name + " n'est pas en train de dormir.";
        }
    }

    public void updateState() {
        System.out.println("État actuel : faim=" + hunger + ", énergie=" + stamina + ", bonheur=" + happiness);
    
        if (health == 0 && !isDead) {
            isDead = true;
            message = name + " est mort.";
        }

        if (!isDead && !stateThreadStarted) {
            degradeStates();
            stateThreadStarted = true;
        }
    
        if (!isDead && !damageThreadStarted) {
            applyDamageOverTime();
            damageThreadStarted = true;
        }

    }

    public void degradeStates() {
        if (!isDead) {
            happiness = Math.max(0, happiness - 1);
            hunger = Math.min(100, hunger + 1);
            stamina = Math.max(0, stamina - 1);
    
            // Update critical states
            isSad = happiness < 20;
            isHungry = hunger > 80;
            isFatigued = stamina < 20;
    
            System.out.println(name + " -> faim=" + hunger + ", énergie=" + stamina + ", bonheur=" + happiness + ", health=" + health);
        }
    }
    
    //apply damage for each states
    public void applyDamageOverTime() {
        if (!isDead) {
            if (isSad) {
                health = Math.max(0, health - 1);
                message = name + " perd de la santé à cause de la tristesse.";
            }
            if (isHungry) {
                health = Math.max(0, health - 1);
                message = name + " perd de la santé à cause de la faim.";
            }
            if (isFatigued) {
                health = Math.max(0, health - 1);
                message = name + " perd de la santé à cause de la fatigue.";
            }
    
            if (health == 0) {
                isDead = true;
                message = name + " est mort à cause des dégâts cumulés.";
            }
        }
    }
 
}
