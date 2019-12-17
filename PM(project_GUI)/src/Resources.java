import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Window.Type;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Resources extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resources frame = new Resources();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Resources() {
		setFont(new Font("Buxton Sketch", Font.BOLD, 18));
		setType(Type.POPUP);
		setTitle("Resources");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 353, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Materials");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Materials M =new Materials();
				M.setVisible(true);
			}
		});
		btnNewButton.setForeground(new Color(128, 0, 0));
		btnNewButton.setFont(new Font("Buxton Sketch", Font.BOLD, 18));
		btnNewButton.setBounds(63, 24, 192, 34);
		contentPane.add(btnNewButton);
		
		JButton btnWork = new JButton("Work");
		btnWork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Work W =new Work();
				W.setVisible(true);
			}
		});
		btnWork.setForeground(new Color(128, 0, 0));
		btnWork.setFont(new Font("Buxton Sketch", Font.BOLD, 19));
		btnWork.setBounds(63, 86, 192, 34);
		contentPane.add(btnWork);
		
		JButton btnShowResourcesTable = new JButton("Show Resources Table");
		btnShowResourcesTable.setForeground(new Color(128, 0, 0));
		btnShowResourcesTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Resources_table rt = new Resources_table();
				rt.setVisible(true);
			}
		});
		btnShowResourcesTable.setFont(new Font("Buxton Sketch", Font.BOLD, 17));
		btnShowResourcesTable.setBounds(63, 141, 192, 34);
		contentPane.add(btnShowResourcesTable);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, btnNewButton, btnWork}));
	}

}
