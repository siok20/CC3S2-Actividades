package cc3s2.towerdefense;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Player{
    private int score;
    private List<Tower> towers;
    private int baseHealth;

    public Player() {
        this.score = 0;
        this.baseHealth = 100;
        this.towers = new ArrayList<>();
    }

    public int getScore() {
        return score;
    }
    
    public void increaseScore(int pts){
        this.score += pts;
    }

    public int getBaseHealth() {
        return baseHealth;
    }
    
    public void reduceHealth(int damage){
        this.baseHealth -= damage;
    }
    
    public void addTower(Tower tower){
        towers.add(tower);
    }
    
    public Tower findTower(int x, int y){
        for(Tower tower: towers){
            if(tower.getX() == x && tower.getY() == y){
                return tower;
            }
        }
        
        throw new NoSuchElementException("No se encontró una torre en las coordenadas (" + x + ", " + y + ")");
    }
    
}