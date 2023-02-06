package Rooms;

public abstract class Room {
    private double square;
    private double height;

    public void switchOffLight(){
        System.out.println("The light was switched off.");
    }

    public void switchOnLight(){
        System.out.println("The light was switched on.");
    }


}
