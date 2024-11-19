package cc3s2.towerdefense;
public class Map {
    private int size;
    private char[][] grid;

    public Map(int size){
        this.size = size;
        this.grid = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void placeTower(Tower tower, int x, int y) {
        this.grid[x][y] = tower.getSymbol();
    }

    public void placeEnemy(Enemy enemy, int x, int y) {
        this.grid[x][y] = enemy.getSymbol();
    }
    
    public boolean isCellEmpty(int x, int y) {
        return this.grid[x][y] == ' ';
    }
    
    public boolean isEnemy(int x, int y) {
        return this.grid[x][y] == 'X';
    }

    public void clearCell(int x, int y) {
        this.grid[x][y] = ' ';
    }
    
    public boolean isValid(int x, int y){
        return x >= 0 && x < size && y >= 0 && y < size;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid) {
            for (char cell : row) {
                sb.append("[").append(cell).append("]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
