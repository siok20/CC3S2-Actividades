package cc3s2.towerdefense;

import java.util.*;

public class TowerDefenseGame {
    private int size = 5;
    private Random rd;
    private Map map;
    private Player player;
    private List<Wave> waves;

    public TowerDefenseGame(int size) {
        this.size = size;
        this.map = new Map(size);
        this.player = new Player();
        this.waves =  new ArrayList<>();
        this.rd = new Random();
    }
    
    public void getSize(int size){
        this.size = size;
    }
    
    public void placeTower(Tower tower, int x, int y){
        if(! map.isValid(x, y)) {
            System.out.println("Coordenadas invalidas"); return;
        }
        
        if(!map.isCellEmpty(x, y)){
            System.out.println("Celda ocupada"); return;
        }
        
        map.placeTower(tower, x, y);
    }
    
    public void startWave(){
        Wave wave = new Wave();
        
        for(int i=0; i<3; i++){
            wave.addEnemy(new Enemy('X', 15, 5));
        }
        
        waves.add(wave);
        wave.start();
    }
    
    public void updateGame(){
        map.clearEnemies();
        for( Wave wave: waves){
            for(Enemy enemy: wave.getEnemies()){
                int x = rd.nextInt(size);
                int y = rd.nextInt(size);
                
                if(map.isCellEmpty(x, y) || map.isEnemy(x, y)){
                    map.placeEnemy(enemy, x, y);
                }
                else{
                    player.reduceHealth(enemy.getDamage());
                    try{
                        Tower tower = player.findTower(x,y);
                        enemy.takeDamage(tower.getDamage());
                    }catch(NoSuchElementException e){
                        System.out.println(e);
                    }
                }
            }
        }
        
        for (Wave wave : waves) {
            for (Enemy enemy : wave.getEnemies()) {
                if (!enemy.isAlive()) {
                    player.increaseScore(10);
                }
            }
        }

        waves.removeIf(wave -> wave.getEnemies().stream().allMatch(enemy -> !enemy.isAlive()));
    }
    
    public void gameState(){
        System.out.println(map);
        System.out.println("Puntuacion: "+ player.getScore());
        System.out.println("Vida de la Base: "+ player.getBaseHealth());
    }
    
    public static void main(String[] args) {
        
        System.out.println("Juego Iniciado - Bienvenido");
        
        Scanner sc = new Scanner(System.in);
                
        int size = 0;
        
        do{
            System.out.println("Elija un tamaño de tablero valido");

            while(!sc.hasNextInt()){
                System.out.println("Elija un tamaño de tablero valido");
                sc.next();
            }
            
            size = sc.nextInt();
        }while(size<=0);
        TowerDefenseGame game = new TowerDefenseGame(size);

        while(true){
            try{
                System.out.println("1. Place Tower");
                System.out.println("2. Start Wave");
                System.out.println("3. Show Game State");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                
                
                int choice = sc.nextInt();
                
                switch(choice){
                    case 1 -> {
                        System.out.print("Enter tower symbol: ");
                        char symbol = sc.next().charAt(0);
                        System.out.print("Enter tower damage: ");
                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.next();  // Clear the invalid input
                            continue;
                        }
                        int damage = sc.nextInt();
                        System.out.print("Enter x position: ");
                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.next();  // Clear the invalid input
                            continue;
                        }
                        int x = sc.nextInt();
                        System.out.print("Enter y position: ");
                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.next();  // Clear the invalid input
                            continue;
                        }
                        int y = sc.nextInt();
                        
                        Tower tower = new Tower(symbol, damage, x, y);

                        game.placeTower(tower, x, y);
                        System.out.println("--");
                        game.player.addTower(tower);
                        break;
                    }
                        
                    case 2 -> {
                        game.startWave();
                        break;
                    }
                        
                    case 3 -> {
                        game.gameState();
                        break;
                    }
                        
                    case 4 -> {
                        System.exit(0);
                    }
                        
                    default -> System.out.println("Option not valid");
                }
                
                game.updateGame();
                
                if(game.player.getBaseHealth()<=0){
                    System.out.println("Jugador derrotado");
                    System.exit(0);
                }
            }
            catch(NoSuchElementException e) {
                System.out.println("Input error: " + e.getMessage());
                sc.nextLine();  // Clear the invalid input
            }
        }
    }
}
