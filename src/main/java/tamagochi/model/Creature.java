package tamagochi.model;

public class Creature {
    private String name;
    private int age;
    private int health;
    private int hunger;
    private int happiness;
    private int stamina;
    private boolean sleep;
    private boolean isFatigued;
    private boolean isHungry;
    private boolean isSad;
    private boolean isHunger;
    private boolean isDead;
    private boolean damageThreadStarted;
    private boolean etatThreadStarted;
    


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
        this.isHunger = false;
        this.isDead = false;
        this.damageThreadStarted = false;
        this.etatThreadStarted = false;
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

    public int getHunger() {
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

    public boolean isHunger() {
        return isHunger;
    }

    public boolean isDead() {
        return isDead;
    }

    public void eat() {
        if (hunger == 0) {
            System.out.println(name + " n'a pas faim !");
        }
        hunger = Math.max(0, hunger - 20);
        happiness = Math.min(100, happiness + 5);
        health = Math.min(100, health + 15);
        stamina = Math.min(100, stamina + 20);
        System.out.println(name + " a mangé et se sent mieux !");
    }


    public void play() {
        happiness = Math.min(100, stamina + 15);
        hunger = Math.min(100, hunger + 10);
        stamina = Math.max(0, stamina - 10);
        if (stamina == 0) {
            System.out.println(name + " est trop fatigué pour jouer davantage.");
        }
    }

    // sleep 10 second for regen stamina
    public void sleep() {
        sleep = true;
        new Thread(() -> {
            while (sleep) {
            try {
                Thread.sleep(1000); 
                stamina = Math.min(100, stamina + 10); 
                health = Math.min(100, health + 1);
                hunger = Math.min(100, hunger + 3);
                if (stamina >= 100) {
                    sleep = false;
                    System.out.println(name + " s'est bien reposé.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Le sommeil de " + name + " a été interrompu.");
            }
            }
        }).start();
    }

    public void wake() {
        if (sleep) {
            sleep = false;
            happiness = Math.max(0, happiness - 10);
            System.out.println(name + " s'est réveillé.");
        } else {
            System.out.println(name + " n'est pas en train de dormir.");
        }
    }

    public void updateState() {
        System.out.println("État actuel : faim=" + hunger + ", énergie=" + stamina + ", bonheur=" + happiness);
    
        
    
        if (health == 0 && !isDead) {
            isDead = true;
            System.out.println(name + " est mort.");
        }

        if (!isDead && !etatThreadStarted) {
            changeEtatOverTime();
            etatThreadStarted = true;
        }
    
        if (!isDead && !damageThreadStarted) {
            startDamageOverTime();
            damageThreadStarted = true;
        }

    }

    private void changeEtatOverTime() {
        new Thread( () -> {
            while (!isDead) {
                try {
                    Thread.sleep(5000);

                    happiness = Math.max(0, -1);
                    hunger = Math.max(0, -1);
                    stamina = Math.max(0, -1);
                    
                    isSad = happiness < 20;
                    isHungry = hunger > 80;
                    isFatigued = stamina < 20;                   
    
                    if (health == 0) {
                        isDead = true;
                        System.out.println(name + " est mort à cause des dégâts cumulés.");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Le mécanisme de changement d'états sur la durée a été interrompu.");
                }
            }
        });
    }
    
    private void startDamageOverTime() {
        new Thread(() -> {
            while (!isDead) {
                try {
                    Thread.sleep(5000);
    
                    if (isSad) {
                        health = Math.max(0, health - 2);
                        System.out.println(name + " perd de la santé à cause de la tristesse.");
                    }
                    if (isHungry) {
                        health = Math.max(0, health - 3);
                        System.out.println(name + " perd de la santé à cause de la faim.");
                    }
                    if (isFatigued) {
                        health = Math.max(0, health - 1);
                        System.out.println(name + " perd de la santé à cause de la fatigue.");
                    }
    
                    if (health == 0) {
                        isDead = true;
                        System.out.println(name + " est mort à cause des dégâts cumulés.");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Le mécanisme de dégâts sur la durée a été interrompu.");
                }
            }
        }).start();
    }
 
}
