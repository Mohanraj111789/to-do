package com.todo.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import com.todo.model.Todo;
import com.todo.dao.TodoappDAO;

public class TodoappGUI extends JFrame {
    private TodoappDAO todoappDAO;
    private JTable todoTable;
    private DefaultTableModel tableModel;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JCheckBox completedCheckbox;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton refreshButton;
    private JComboBox<String> filterComboBox;

    public TodoappGUI() {
        this.todoappDAO = new TodoappDAO();
        initializeComponents();
        setupLayout();
    }

    public void initializeComponents() {
        setTitle("Todo App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        String[] columns = { "ID", "Title", "Description", "Completed", "Created At", "Updated At" };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        todoTable = new JTable(tableModel);
        todoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todoTable.getSelectionModel().addListSelectionListener(
                (e) -> {
                    if (!e.getValueIsAdjusting()) {
                        // load the selected todo
                    }
                });

        titleField = new JTextField(20);
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        completedCheckbox = new JCheckBox("Completed");
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        refreshButton = new JButton("Refresh");

        String[] filterOptions = { "All", "Completed", "Incomplete" };
        filterComboBox = new JComboBox<>(filterOptions);
        filterComboBox.addActionListener(e -> {
            // filter the todos
        });

    }
    private void setupLayout()
    {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);


        gbc.gridx =0;
        gbc.gridy =0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(new JLabel("Title:"),gbc);

        gbc.gridx =1;
        // gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(titleField,gbc);

        // add(inputPanel,BorderLayout.CENTER);

        gbc.gridx =0;
        gbc.gridy =1;
        inputPanel.add(new JLabel("Description:"),gbc);

        gbc.gridx =1;
        inputPanel.add(descriptionArea,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(completedCheckbox,gbc);

        add(inputPanel,BorderLayout.NORTH);




    }

}