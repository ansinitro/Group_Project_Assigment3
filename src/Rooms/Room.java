package Rooms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public abstract class Room {
    private double square;
    private double height;

   public Room (double square, double height){
       this.square = square;
       this.height = height;
   }

    public void switchOffLight(){
        System.out.println("The light was switched off.");
    }

    public void switchOnLight(){
        System.out.println("The light was switched on.");
    }

    public double getHeight() {
        return height;
    }

    public double getSquare() {
        return square;
    }

    }

