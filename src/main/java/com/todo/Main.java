package com.todo;
import com.todo.gui.TodoappGUI;
import com.todo.util.DatabaseConnection;
import java.sql.Connection;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
    public class Main {
    public static void main(String[] args) {
        DatabaseConnection db_Connection = new DatabaseConnection();
        try{
            Connection cn = db_Connection.getDBConnection();
            System.out.println("✅Database Connection Successful");
        }
        catch(Exception e){
            System.out.println("❌Database Connection Failed: " + e.getMessage());
            System.exit(1);
        }
        try{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e){
           System.err.println("❌Failed to set Look and Feel: " + e.getMessage());
        }

        SwingUtilities.invokeLater(//create thread
            () -> {
                try{
                TodoappGUI todoappGUI = new TodoappGUI();
                todoappGUI.setVisible(true);
                }
                catch(Exception e){
                    System.err.println("❌Failed to create GUI: " + e.getLocalizedMessage());
                }
            }
        );
    }
}
