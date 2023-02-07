package Rooms;

public abstract class Room {
    private double square;
    private double height;
    private int numberOfSockets;
    private int numberOfLights;

   public Room (double square, double height, int numberOfSockets){
       this.square = square;
       this.height = height;
       this.numberOfSockets = numberOfSockets;
   }

    public void switchOffLight(){
        System.out.println("The light was switched off.");
    }

    public void switchOnLight(){
        System.out.println("The light was switched on.");
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setNumberOfSockets(int numberOfSockets) {
        this.numberOfSockets = numberOfSockets;
    }

    public int getNumberOfSockets() {
        return numberOfSockets;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public double getSquare() {
        return square;
    }

    public void setNumberOfLights(int numberOfLights) {
        this.numberOfLights = numberOfLights;
    }

    public int getNumberOfLights() {
        return numberOfLights;
    }
}
