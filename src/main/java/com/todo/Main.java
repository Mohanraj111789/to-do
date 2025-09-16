package com.todo;
import com.todo.util.DatabaseConnection;
import java.sql.Connection;
    public class Main {
    public static void main(String[] args) {
        DatabaseConnection db_Connection = new DatabaseConnection();
        try{
            Connection cn = db_Connection.getDBConnection();
            System.out.println("✅Database Connection Successful");
        }
        catch(Exception e){
            System.out.println("❌Database Connection Failed: " + e.getMessage());
        }
    }
}
