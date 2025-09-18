package com.todo.dao;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import com.todo.model.Todo;
import com.todo.util.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TodoappDAO {
    private Todo getTodoRow(ResultSet rs) throws SQLException
    {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String description = rs.getString("description");
        boolean completed = rs.getBoolean("completed");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
        Todo todo = new Todo(id, title, description, completed, createdAt, updatedAt);
        return todo;
        
    }
    public List<Todo> getAllTodos() throws SQLException
    {
        List<Todo> todos = new ArrayList<>();
        try(Connection conn =new DatabaseConnection().getDBConnection()){
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM todos ORDER BY created_at DESC");
        ResultSet rs = stmt.executeQuery();
        
        {
            while(rs.next())
            {
                todos.add(getTodoRow(rs));
            }
                
            }

        }
        return todos;
    }
    
}
