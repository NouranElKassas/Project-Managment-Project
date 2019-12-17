import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Materials extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton button_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Materials frame = new Materials();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	public int SearchTable_id()
	{
		 int count1=0;
		try{
			String query = "select TaskID from Project_Managment_DB where TaskID='"+textField_2.getText()+"'";
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
	
	/**
	 * Create the frame.
	 */
	Connection conn =null;
	public Materials() {
		conn =main_connection.DB_Connector();
		setTitle("Materials");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 263, 207);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Name");
		label.setForeground(new Color(128, 0, 0));
		label.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		label.setBounds(10, 11, 67, 26);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(75, 11, 143, 26);
		contentPane.add(textField);
		
		JLabel label_1 = new JLabel("Cost");
		label_1.setForeground(new Color(128, 0, 0));
		label_1.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		label_1.setBounds(10, 48, 67, 26);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(75, 52, 143, 26);
		contentPane.add(textField_1);
		
		JLabel label_2 = new JLabel("Task ID");
		label_2.setForeground(new Color(128, 0, 0));
		label_2.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		label_2.setBounds(10, 89, 67, 26);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(75, 93, 143, 26);
		contentPane.add(textField_2);
		
		JButton button = new JButton("Add/Save ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
					int x;
					x= SearchTable_id();
					String value=textField_1.getText();
					boolean result =isDigit(value);
					
					if((x!=0)&&(result==true))
					{
					String query ="INSERT INTO Resources1 (ResourceName,Cost,TaskID,Hours,TotalCost,ResourceType) VALUES (?,?,?,?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
					pst.setString(4, "0");
					pst.setString(5, textField_1.getText());
					pst.setString(6,"Materials");
					//ResultSet rs = pst.executeQuery();
					pst.execute();
					pst.close();
					JOptionPane.showMessageDialog(null,"Data Saved");
					//refreshTable();
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
		button.setForeground(new Color(128, 0, 0));
		button.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		button.setBounds(10, 126, 107, 37);
		contentPane.add(button);
		
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
			
		}});
		button_1.setForeground(new Color(128, 0, 0));
		button_1.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		button_1.setBounds(119, 126, 118, 37);
		contentPane.add(button_1);
	}

}
