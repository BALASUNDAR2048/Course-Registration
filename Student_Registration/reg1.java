package Student_Registration;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class reg1 extends javax.swing.JFrame {

	private JFrame frame;
	private final JLabel lblNewLabel = new JLabel("STUDENT REGISTRATION SYSTEM");
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reg1 window = new reg1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public reg1() {
		initialize();
 		table_update();
	}
	



		Connection con1;
		PreparedStatement insert;

		private void  table_update() {
			int c;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con1= DriverManager.getConnection("jdbc:mysql://localhost:3307/balu1","root","Balu@020");
			insert =con1.prepareStatement("SELECT * FROM balu1.record");
		    ResultSet rs = insert.executeQuery();
		   ResultSetMetaData Rss = (ResultSetMetaData) rs.getMetaData();
		   c=Rss.getColumnCount();
		   
		   
		   DefaultTableModel Df=(DefaultTableModel)table.getModel();
		   Df.setRowCount(0);
		   
		   while(rs.next())
		   {
			   Vector v2 = new Vector();
			   
			   for(int a=1;a<=c;a++)
			   {
				   v2.add(rs.getString("id"));
				   v2.add(rs.getString("studentname"));
				   v2.add(rs.getString("mobile"));
				   v2.add(rs.getString("course"));

			   }
			   Df.addRow(v2);
		   }
		  
			}
			catch(ClassNotFoundException ex) {
				Logger.getLogger(reg1.class.getName()).log(Level.SEVERE,null,ex);
			}
			
			catch(SQLException ex) {
				Logger.getLogger(reg1.class.getName()).log(Level.SEVERE,null,ex);
			}	
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1010, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel.setBounds(238, 35, 529, 47);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "REGISTRATION", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 127, 447, 291);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Student name");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblNewLabel_1.setBounds(49, 59, 95, 21);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(183, 61, 211, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(183, 122, 211, 19);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(183, 193, 211, 19);
		panel.add(textField_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mobile");
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(49, 119, 83, 21);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Course");
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(49, 190, 83, 21);
		panel.add(lblNewLabel_1_2);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {

			Connection con1;
			PreparedStatement insert;

			
			public void actionPerformed(ActionEvent e) {
				String studentname=textField.getText();
				String mobile=textField_1.getText();
				String course=textField_2.getText();
			
				try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con1= DriverManager.getConnection("jdbc:mysql://localhost:3307/balu1","root","Balu@020");
			insert =con1.prepareStatement("insert into record(studentname,mobile,course)values(?,?,?)");
			insert.setString(1,studentname);
			insert.setString(2,mobile);
			insert.setString(3,course);
			insert.executeUpdate();
			JOptionPane.showMessageDialog(null, "       Data Recorded    ","Message",JOptionPane.PLAIN_MESSAGE);
	 		table_update();

			
			
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			textField.requestFocus();

				}
				catch(ClassNotFoundException ex) {
					Logger.getLogger(reg1.class.getName()).log(Level.SEVERE,null,ex);
				}
				
				catch(SQLException ex) {
					Logger.getLogger(reg1.class.getName()).log(Level.SEVERE,null,ex);
				}
			}
			});
		btnNewButton.setBounds(10, 260, 85, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 DefaultTableModel Df=(DefaultTableModel)table.getModel();
			      int selectedIndex=table.getSelectedRow();
				
			      
			      
			      try {
			    	  int id = Integer.parseInt(Df.getValueAt(selectedIndex, 0).toString());
			    	 
			    	  int dialogResult=JOptionPane.showConfirmDialog(null, "Do you want to delete the record ","Warning",JOptionPane.YES_NO_OPTION);
			    	  
			    	  if(dialogResult==JOptionPane.YES_OPTION)
			    	  {

							Class.forName("com.mysql.cj.jdbc.Driver");
							
							con1= DriverManager.getConnection("jdbc:mysql://localhost:3307/balu1","root","Balu@020");
							insert =con1.prepareStatement("delete from record where id=?");
							insert.setInt(1, id);
 
							insert.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "     Record deleted  ","Message",JOptionPane.PLAIN_MESSAGE);
					 		table_update();

							
							
							textField.setText("");
							textField_1.setText("");
							textField_2.setText("");
							textField.requestFocus();

			    	  }
			      }
			      catch(ClassNotFoundException ex) {
						Logger.getLogger(reg1.class.getName()).log(Level.SEVERE,null,ex);
					}
					
					catch(SQLException ex) {
						Logger.getLogger(reg1.class.getName()).log(Level.SEVERE,null,ex);
					}
			      
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnNewButton_1.setBounds(159, 260, 85, 21);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Edit");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			Connection con1;
			PreparedStatement insert;
			
			
			public void actionPerformed(ActionEvent e) {
				 DefaultTableModel Df=(DefaultTableModel)table.getModel();
			      int selectedIndex=table.getSelectedRow();
			    
			      try {
			    	  int id = Integer.parseInt(Df.getValueAt(selectedIndex, 0).toString());
			    	  String studentname=textField.getText();
						String mobile=textField_1.getText();
						String course=textField_2.getText(); 
						
						Class.forName("com.mysql.cj.jdbc.Driver");
						
						con1= DriverManager.getConnection("jdbc:mysql://localhost:3307/balu1","root","Balu@020");
						insert =con1.prepareStatement("update record set studentname=?,mobile=?, course=? where id=?");
						insert.setString(1,studentname);
						insert.setString(2,mobile);
						insert.setString(3,course);
						insert.setInt(4, id);

						insert.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "     Record updated  ","Message",JOptionPane.PLAIN_MESSAGE);
				 		table_update();

						
						
						textField.setText("");
						textField_1.setText("");
						textField_2.setText("");
						textField.requestFocus();

			      }
			      catch(ClassNotFoundException ex) {
						Logger.getLogger(reg1.class.getName()).log(Level.SEVERE,null,ex);
					}
					
					catch(SQLException ex) {
						Logger.getLogger(reg1.class.getName()).log(Level.SEVERE,null,ex);
					}
			      
			}
		});
		btnNewButton_1_1.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnNewButton_1_1.setBounds(305, 260, 85, 21);
		panel.add(btnNewButton_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(514, 127, 461, 278);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
	      DefaultTableModel Df=(DefaultTableModel)table.getModel();
	      int selectedIndex=table.getSelectedRow();
	      
	      
	      textField.setText(Df.getValueAt(selectedIndex, 1).toString());
	      textField_1.setText(Df.getValueAt(selectedIndex, 2).toString());
	      textField_2.setText(Df.getValueAt(selectedIndex, 3).toString());
	 
			}
		});
		scrollPane.setViewportView(table);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "studentname", "mobile", "course "
			}
		) {
			
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setMaxWidth(2147483646);
	}
}
