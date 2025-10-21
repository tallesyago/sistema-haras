package com.haras.view.pages;

import javax.swing.*;
import java.awt.*;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.swing.FontIcon;

public class AgendaView {
    
    public JPanel getContentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(248, 250, 252));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(new Color(248, 250, 252));

        FontIcon calendarIcon = FontIcon.of(FontAwesomeSolid.CALENDAR, 32, new Color(16, 185, 129));
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(calendarIcon);

        JLabel titleLabel = new JLabel("Agenda");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(31, 41, 55));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        headerPanel.add(iconLabel);
        headerPanel.add(titleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        FontIcon emptyIcon = FontIcon.of(FontAwesomeSolid.CALENDAR, 48, new Color(209, 213, 219));
        JLabel emptyIconLabel = new JLabel();
        emptyIconLabel.setIcon(emptyIcon);
        emptyIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descLabel = new JLabel("Gerencie eventos, consultas veterinárias e treinamentos");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        descLabel.setForeground(new Color(107, 114, 128));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel comingSoonLabel = new JLabel("Em desenvolvimento...");
        comingSoonLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        comingSoonLabel.setForeground(new Color(156, 163, 175));
        comingSoonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        comingSoonLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(emptyIconLabel);
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(descLabel);
        contentPanel.add(comingSoonLabel);
        contentPanel.add(Box.createVerticalGlue());

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(contentPanel, BorderLayout.CENTER);
        
        return panel;
    }
}
