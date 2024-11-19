package cc3s2.towerdefense;

public class Tower{
    private char symbol;
    private int damage;
    private int x;
    private int y;

    public Tower(char symbol, int damage, int x, int y) {
        this.symbol = symbol;
        this.damage = damage;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getDamage() {
        return damage;
    }
    
}