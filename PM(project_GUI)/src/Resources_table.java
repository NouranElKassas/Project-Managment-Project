import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.lang.reflect.Executable;
import java.awt.event.ActionEvent;

import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Color;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Dialog.ModalExclusionType;
public class Resources_table extends JFrame {

	
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resources_table frame = new Resources_table();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn =null;
	private JButton btnShowData;
	/**
	 * Create the frame.
	 */
	public Resources_table() {
		setBackground(new Color(255, 255, 224));
		setFont(new Font("Aharoni", Font.PLAIN, 12));
		setForeground(new Color(255, 255, 224));
		setType(Type.POPUP);
		conn =main_connection.DB_Connector();
		setTitle("Resources Table");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 619, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 583, 265);
		scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		btnShowData = new JButton("Show Data");
		btnShowData.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		btnShowData.setForeground(new Color(128, 0, 0));
		btnShowData.setBounds(182, 11, 126, 34);
		btnShowData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String query ="SELECT * FROM Resources1";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnShowData);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, scrollPane, table, btnShowData}));
	}
}
