package Rooms;

public class LivingRoom extends Room {
    private int numberOfBed;

    public LivingRoom(double square, double height){
        super(square, height);
    }

    public LivingRoom(double square, double height, int numberOfBed){
        super(square, height);
        this.numberOfBed = numberOfBed;
    }

    public int getNumberOfBed() {
        return numberOfBed;
    }

    public void settlement(){

    }
}
