package com.example.demo.repository;

import com.example.demo.model.Todo;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoRepository {

    private final DataSource dataSource;

    public TodoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Todo> findTodos(String statusFilter) {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT id, title, description, status FROM todos";
        if (statusFilter != null) {
            sql += " WHERE status = ?";
        }

        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (statusFilter != null) {
                stmt.setString(1, statusFilter);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                todos.add(new Todo(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return todos;
    }

    public Todo findTodosById(Integer id) {
        String sql = "SELECT id, title, description, status FROM todos WHERE id = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return (new Todo(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String createTodo(Todo todo){
        String sql = "INSERT INTO todos (title, description, status) VALUES (?, ?, ?)";
        try (Connection conn = dataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getDescription());
            stmt.setString(3, todo.getStatus());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                todo.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Todo created";
    }
}
