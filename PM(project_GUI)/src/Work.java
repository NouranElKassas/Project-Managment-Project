import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Work extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton button;
	private JLabel lblTaskId;
	private JTextField textField_3;
	private JButton button_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Work frame = new Work();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public int SearchTable_id()
	{
		 int count1=0;
		try{
			String query = "select TaskID from Project_Managment_DB where TaskID='"+textField_3.getText()+"'";
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
	public int SearchTable()
	{
		 int count1=0;
		try{
			String query = "select ResourceName from Resources1  where ResourceName='"+textField.getText()+"'";
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
	public String TotalCost(String Cost,String Hours)
	{
		String Result=null;
		int v =Integer.parseInt(Cost);
		int h =Integer.parseInt(Hours);
		int value=h*v;
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append(value);
		Result = sb.toString();
		//Result.valueOf(value);
		return Result;
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
	/**
	 * Create the frame.
	 */
	Connection conn =null;
	public Work() {
		conn =main_connection.DB_Connector();
		setTitle("Work");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 335, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		lblName.setForeground(new Color(128, 0, 0));
		lblName.setBounds(24, 11, 67, 26);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(148, 11, 143, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNoOfHours = new JLabel("No. of Hours");
		lblNoOfHours.setForeground(new Color(128, 0, 0));
		lblNoOfHours.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		lblNoOfHours.setBounds(24, 48, 109, 26);
		contentPane.add(lblNoOfHours);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(148, 48, 143, 26);
		contentPane.add(textField_1);
		
		JLabel lblCost = new JLabel("Cost");
		lblCost.setForeground(new Color(128, 0, 0));
		lblCost.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		lblCost.setBounds(24, 85, 67, 26);
		contentPane.add(lblCost);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(148, 85, 143, 26);
		contentPane.add(textField_2);
		
		button = new JButton("Add/Save ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int x;
					x= SearchTable_id();
					String value=textField_2.getText();
					boolean resultA =isDigit(value);
					String value_2=textField_1.getText();
					boolean resultB =isDigit(value_2);
					if((x!=0)&&(resultA==true)&&(resultB==true))
					{String query ="INSERT INTO Resources1 (ResourceName,Hours,Cost,TaskID,TotalCost,ResourceType) VALUES (?,?,?,?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4,textField_3.getText());
					String value1=textField_1.getText();
					String value2=textField_2.getText();
					String result =TotalCost( value1,value2);
					if(result!=null){
					pst.setString(5,result);}
					pst.setString(6,"Work");
					//ResultSet rs = pst.executeQuery();
					pst.execute();
					pst.close();
					JOptionPane.showMessageDialog(null,"Data Saved");}
		             
					//refreshTable();
				
				else
				{
					JOptionPane.showMessageDialog(null,"There is sth wrong in the values entered pls re-check it.");
				}}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				

			}
		});
		button.setForeground(new Color(128, 0, 0));
		button.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		button.setBounds(34, 159, 109, 37);
		contentPane.add(button);
		
		lblTaskId = new JLabel("Task ID");
		lblTaskId.setForeground(new Color(128, 0, 0));
		lblTaskId.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		lblTaskId.setBounds(24, 122, 67, 26);
		contentPane.add(lblTaskId);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(148, 122, 143, 26);
		contentPane.add(textField_3);
		
		button_1 = new JButton("Delete");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					int x;
					x= SearchTable();
					String value = null;
					boolean Result;
					value=textField.getText();
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
					
					{
					if(x!=0){String query ="DELETE FROM Resources1 WHERE ResourceName='"+textField.getText()+"'";
					PreparedStatement pst = conn.prepareStatement(query);
					//ResultSet rs = pst.executeQuery();
					pst.execute();
					JOptionPane.showMessageDialog(null,"Data Deleted");
					//JOptionPane.showMessageDialog(null,value);
					pst.close();}
					else{JOptionPane.showMessageDialog(null,"Data is invalied");}
					}}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		button_1.setForeground(new Color(128, 0, 0));
		button_1.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		button_1.setBounds(158, 159, 133, 37);
		contentPane.add(button_1);
	}
}
