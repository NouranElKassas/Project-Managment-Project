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

public class PM_Project extends JFrame{

	private JFrame frmProjectManagment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PM_Project window = new PM_Project();
					window.frmProjectManagment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection conn= null;
	private JScrollPane scrollPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblStartDate;
	private JLabel lblEndDate;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblPhaseName;
	private JTextField textField_6;
	private JTextField textField_5;
	private JLabel lblPhaseName_1;
	private JButton btnUpdate;
	private JLabel lblPrePhase;
	private JTextField textField_8;
	private JTable table;
	
	public void refreshTable()
	{
		try{
			String query = "select * from Project_Managment_DB";
			PreparedStatement pst= conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public int SearchTable()
	{
		 int count1=0;
		try{
			String query = "select TaskID from Project_Managment_DB  where TaskID='"+textField_6.getText()+"'";
			PreparedStatement pst= conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				count1 =count1+1;
			}
			rs.close();
			pst.close();
		}
		
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
		return count1;
	}
	
	public String EndDateValue()
	{
		String value="";
		{ String s = textField_3.getText();
       // Scanner scan = new Scanner(System.in);
        //s = scan.nextLine();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");
        if (s.trim().length() != simpleDateFormat.toPattern().length())
        {
        	textField_2.setText(null);
        	textField_3.setText(null);
        	   return "0";
        }
        try
        {
        	//Scanner scan1 = new Scanner(System.in);
        	String y = textField_2.getText();
            int X = Integer.parseInt(y);
            		//scan.nextInt();
            DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
            Date date = simpleDateFormat.parse(s);
            Date addedDate1 = addDays(date, X);
            String A= df.format(addedDate1);
            textField_4.setText(A);
            return A;
            //System.out.println("date : "+simpleDateFormat.format(date));
        }
        catch (ParseException ex)
        {
        	JOptionPane.showMessageDialog(null,"Exception "+ex);
        }
     }
            
		return value;
	};
	  public static Date addDays(Date d, int days)
      {
    	  d.setTime(d.getTime() + days * 1000 * 60 * 60 * 24);
    	  return d;
       }
	  public static boolean isInteger(String s) {
		    try { 
		        Integer.parseInt(s); 
		    } catch(NumberFormatException e) { 
		        return false; 
		    } catch(NullPointerException e) {
		        return false;
		    }
		    // only got here if we didn't return false
		    return true;
		}
		public boolean isDigit(String s)
		{
			//String s = "abc123";
			for(char c : s.toCharArray())
			{
			    if(!(Character.isDigit(c)))
			    {
			        return false;
			    }
			}
			//return false;
			return true;
		}
	public PM_Project() {
		initialize();
		conn= main_connection.DB_Connector();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProjectManagment = new JFrame();
		frmProjectManagment.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmProjectManagment.getContentPane().setForeground(Color.WHITE);
		frmProjectManagment.setForeground(Color.WHITE);
		frmProjectManagment.setBackground(Color.WHITE);
		frmProjectManagment.setTitle("Project Managment");
		frmProjectManagment.setBounds(100, 100, 994, 612);
		frmProjectManagment.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton ShowData = new JButton("Show Data");
		ShowData.setBounds(41, 172, 166, 37);
		ShowData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					String query ="SELECT * FROM Project_Managment_DB";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		frmProjectManagment.getContentPane().setLayout(null);
		ShowData.setForeground(new Color(128, 0, 0));
		ShowData.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		frmProjectManagment.getContentPane().add(ShowData);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 220, 914, 319);
		frmProjectManagment.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSurrendersFocusOnKeystroke(true);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setForeground(Color.BLACK);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setBorder(null);
		table.setBackground(new Color(253, 245, 230));
		scrollPane.setViewportView(table);
		
		JLabel lblProjectName = new JLabel("Project Name");
		lblProjectName.setBounds(46, 16, 116, 37);
		lblProjectName.setForeground(new Color(128, 0, 0));
		lblProjectName.setFont(new Font("Buxton Sketch", Font.BOLD, 18));
		frmProjectManagment.getContentPane().add(lblProjectName);
		
		textField = new JTextField();
		textField.setBounds(150, 22, 138, 26);
		frmProjectManagment.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblTaskName = new JLabel("Task Name");
		lblTaskName.setBounds(46, 49, 116, 37);
		lblTaskName.setForeground(new Color(128, 0, 0));
		lblTaskName.setFont(new Font("Buxton Sketch", Font.BOLD, 18));
		frmProjectManagment.getContentPane().add(lblTaskName);
		
		JLabel lblDuration = new JLabel("Duration");
		lblDuration.setBounds(392, 52, 116, 37);
		lblDuration.setForeground(new Color(128, 0, 0));
		lblDuration.setFont(new Font("Buxton Sketch", Font.BOLD, 18));
		frmProjectManagment.getContentPane().add(lblDuration);
		
		textField_1 = new JTextField();
		textField_1.setBounds(150, 58, 138, 26);
		textField_1.setColumns(10);
		frmProjectManagment.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(483, 60, 123, 26);
		textField_2.setColumns(10);
		frmProjectManagment.getContentPane().add(textField_2);
		
		lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(392, 16, 116, 37);
		lblStartDate.setForeground(new Color(128, 0, 0));
		lblStartDate.setFont(new Font("Buxton Sketch", Font.BOLD, 18));
		frmProjectManagment.getContentPane().add(lblStartDate);
		
		lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(392, 89, 116, 37);
		lblEndDate.setForeground(new Color(128, 0, 0));
		lblEndDate.setFont(new Font("Buxton Sketch", Font.BOLD, 18));
		frmProjectManagment.getContentPane().add(lblEndDate);
		
		textField_3 = new JTextField();
		textField_3.setBounds(483, 22, 123, 26);
		textField_3.setColumns(10);
		frmProjectManagment.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(483, 100, 123, 26);
		textField_4.setEnabled(false);
		textField_4.setColumns(10);
		frmProjectManagment.getContentPane().add(textField_4);
		
		lblPhaseName = new JLabel("Task ID");
		lblPhaseName.setBounds(46, 91, 116, 37);
		lblPhaseName.setForeground(new Color(128, 0, 0));
		lblPhaseName.setFont(new Font("Buxton Sketch", Font.BOLD, 18));
		frmProjectManagment.getContentPane().add(lblPhaseName);
		
		textField_6 = new JTextField();
		textField_6.setBounds(150, 97, 138, 26);
		textField_6.setColumns(10);
		frmProjectManagment.getContentPane().add(textField_6);
		
		textField_5 = new JTextField();
		textField_5.setBounds(729, 58, 123, 59);
		textField_5.setColumns(10);
		frmProjectManagment.getContentPane().add(textField_5);
		
		lblPhaseName_1 = new JLabel("Description");
		lblPhaseName_1.setBounds(639, 52, 116, 37);
		lblPhaseName_1.setForeground(new Color(128, 0, 0));
		lblPhaseName_1.setFont(new Font("Buxton Sketch", Font.BOLD, 18));
		frmProjectManagment.getContentPane().add(lblPhaseName_1);
		
		JButton btnAddProject = new JButton("Add/Save ");
		btnAddProject.setBounds(234, 172, 166, 37);
		btnAddProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int x;
					String value= null;
					x=SearchTable();
					boolean Result;
					value=textField_6.getText();
					//checkers oc = new checkers();
					//Result =  oc.check(value);
					
					 try{
					      Integer.parseInt(value);
					      Result =true;
					}
					 catch (Exception  e2){
					      Result =false;
					}String value5=textField_2.getText();
					boolean resultA =isDigit(value5);
					if((Result== true)&&(resultA==true)){
					if(x!=1)
					{String query ="INSERT INTO Project_Managment_DB (ProjectName,TaskName,Duration,StartDate,EndDate,Description,TaskID,PreTask) VALUES (?,?,?,?,?,?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					String value1=EndDateValue();
					String result =textField_4.getText();
					boolean value2 = isInteger(result);
					if((value1!=""||value1!="0")&& value2==true)
					{
						textField_4.setText(value1);
					
					}
					else if(value1==""||value1=="0"){textField_4.setText(null); textField_2.setText(null);}
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4, textField_3.getText());
					pst.setString(5, textField_4.getText());
					pst.setString(6, textField_5.getText());
					pst.setString(7, textField_6.getText());
					pst.setString(8, textField_8.getText());
					//ResultSet rs = pst.executeQuery();
					pst.execute();
					pst.close();
					JOptionPane.showMessageDialog(null,"Data Saved");
					refreshTable();}
					else 
					{
						JOptionPane.showMessageDialog(null,"This data is entered before.");
					}
				}
					else 
					{
						JOptionPane.showMessageDialog(null,"There is sth wrong in the values entered pls re-check it.");
					}
			}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnAddProject.setForeground(new Color(128, 0, 0));
		btnAddProject.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		frmProjectManagment.getContentPane().add(btnAddProject);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(431, 172, 166, 37);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					int x;
					x= SearchTable();
					String value = null;
					boolean Result;
					value=textField_6.getText();
					//checkers oc = new checkers();
					//Result =  oc.check(value);
					//boolean XA;
					//JOptionPane.showMessageDialog(null,Result);
					try{
					      Integer.parseInt(value);
					      Result= true;}
					catch (Exception  e2){
					      Result =false;
					}
					
					if(Result==true){
					if(x!=0){String query ="DELETE FROM Project_Managment_DB WHERE TaskID='"+textField_6.getText()+"'";
					PreparedStatement pst = conn.prepareStatement(query);
					//ResultSet rs = pst.executeQuery();
					pst.execute();
					JOptionPane.showMessageDialog(null,"Data Deleted");
					//JOptionPane.showMessageDialog(null,value);
					
					refreshTable();
					pst.close();}
					else{JOptionPane.showMessageDialog(null,"Data is invalied");}
					}
					else{JOptionPane.showMessageDialog(null," TaskID entered contains characters or Null.");}
					}
                   
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setForeground(new Color(128, 0, 0));
		btnDelete.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		frmProjectManagment.getContentPane().add(btnDelete);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(623, 172, 166, 37);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					int x;
					x=SearchTable();
					
					if(x==1){
					String query=null;
					String query1=null;
					String query2=null;
					String query3=null;
					String query4=null;
					String query5=null;
					String query6=null;
					String query7=null;
					String query8=null;
					if(!textField.getText().isEmpty())
					{
						query =" Update  Project_Managment_DB set ProjectName='"+textField.getText()+"' where TaskID='"+textField_6.getText()+"';";
						PreparedStatement pst= conn.prepareStatement(query);
						pst.execute();
						pst.close();
						
					}
					
					if(!textField_1.getText().isEmpty())
					{
						
						query1 =" Update  Project_Managment_DB set TaskName='"+textField_1.getText()+"' where TaskID='"+textField_6.getText()+"';";
						PreparedStatement pst1= conn.prepareStatement(query1);
						pst1.executeUpdate();
						pst1.close();
						
					}
					
					if(!textField_2.getText().isEmpty())
					{
						query2 =" Update  Project_Managment_DB set Duration='"+textField_2.getText()+"' where TaskID='"+textField_6.getText()+"'; ";
						PreparedStatement pst2= conn.prepareStatement(query2);
						pst2.execute();
						pst2.close();
						
					}
					
					if(!textField_3.getText().isEmpty())
					{
					    query3 =" Update  Project_Managment_DB set StartDate='"+textField_3.getText()+"' where TaskID='"+textField_6.getText()+"' ";
					    PreparedStatement pst3= conn.prepareStatement(query3);
						pst3.execute();
						pst3.close();
						
					}
					
					if(!textField_4.getText().isEmpty())
					{
					    query4 =" Update  Project_Managment_DB set EndDate='"+textField_4.getText()+"' where TaskID='"+textField_6.getText()+"' ";
					    PreparedStatement pst4= conn.prepareStatement(query4);
						pst4.execute();
						pst4.close();
						
					}
					if(!textField_5.getText().isEmpty())
					{
						query5 =" Update  Project_Managment_DB set Description='"+textField_5.getText()+"' where TaskID='"+textField_6.getText()+"'";
						PreparedStatement pst5= conn.prepareStatement(query5);
						pst5.execute();
						pst5.close();
						
					}
					
					if(!textField_6.getText().isEmpty())
					{
				       query6 =" Update  Project_Managment_DB set TaskID='"+textField_6.getText()+"' where TaskID='"+textField_6.getText()+"'";
				       PreparedStatement pst6= conn.prepareStatement(query6);
						pst6.execute();
						pst6.close();
						
					}
					if(!textField_8.getText().isEmpty())
					{
					    query8 =" Update  Project_Managment_DB set PreTask='"+textField_8.getText()+"' where TaskID='"+textField_6.getText()+"' \n ";
					    PreparedStatement pst8= conn.prepareStatement(query8);
						pst8.execute();
						pst8.close();
					}
					JOptionPane.showMessageDialog(null,"Data Updated");
					refreshTable();
					}
					else{JOptionPane.showMessageDialog(null,"This Task ID is invalied");}
					
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}

			}
		});
		btnUpdate.setForeground(new Color(128, 0, 0));
		btnUpdate.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		frmProjectManagment.getContentPane().add(btnUpdate);
		
		lblPrePhase = new JLabel("Pre Task");
		lblPrePhase.setBounds(639, 16, 116, 37);
		lblPrePhase.setForeground(new Color(128, 0, 0));
		lblPrePhase.setFont(new Font("Buxton Sketch", Font.BOLD, 18));
		frmProjectManagment.getContentPane().add(lblPrePhase);
		
		textField_8 = new JTextField();
		textField_8.setBounds(729, 22, 123, 26);
		textField_8.setColumns(10);
		frmProjectManagment.getContentPane().add(textField_8);
		
		JLabel lblNewLabel = new JLabel(" *P.S:Only TaskID needed in search or delete the data.");
		lblNewLabel.setBounds(41, 132, 565, 29);
		lblNewLabel.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		lblNewLabel.setForeground(new Color(128, 0, 0));
		frmProjectManagment.getContentPane().add(lblNewLabel);
		
		
		JButton SearchData = new JButton("Search on Data");
		SearchData.setBounds(808, 172, 166, 37);
		SearchData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					int x;
					x=SearchTable();
					
					if(x==1){
					String query ="SELECT * FROM Project_Managment_DB  where TaskID='"+textField_6.getText()+"'";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					}
					else{JOptionPane.showMessageDialog(null,"This Task ID is invalied");}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		SearchData.setForeground(new Color(128, 0, 0));
		SearchData.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		frmProjectManagment.getContentPane().add(SearchData);
		
		JButton btnResources = new JButton("Resources");
		btnResources.setBounds(623, 128, 166, 33);
		btnResources.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Resources R=new Resources();
				R.setVisible(true);
			}
		});
		btnResources.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		btnResources.setForeground(new Color(128, 0, 0));
		frmProjectManagment.getContentPane().add(btnResources);
	}
}
