package memoryManger;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class input2 extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField segmentName;
	private JTextField segmentSize;
	private JTextField processName;
	public static output frame2;  
	public static Boolean Visable=false; 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					input2 frame = new input2();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param "Input 2" 
	 */
	public input2(){
		setTitle("Enter Process Segments:"); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 703);
		contentPane = new JPanel();
		contentPane.setForeground(SystemColor.inactiveCaption);
		contentPane.setBackground(UIManager.getColor("CheckBox.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 29, 587, 271);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				
				String name=(String) model.getValueAt(table.getSelectedRow(),0);
				String size=model.getValueAt(table.getSelectedRow(),1).toString();
			
				segmentName.setText(name);
				segmentSize.setText(size);
			
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"SegmentName", "SegmentSize","Base"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class,Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("SegmentName");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(38, 336, 141, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblSegmentsize = new JLabel("SegmentSize");
		lblSegmentsize.setFont(new Font("Arial", Font.BOLD, 18));
		lblSegmentsize.setBounds(38, 381, 141, 35);
		contentPane.add(lblSegmentsize);
		
		JLabel lblProcessname = new JLabel("ProcessName");
		lblProcessname.setFont(new Font("Arial", Font.BOLD, 18));
		lblProcessname.setBounds(38, 468, 141, 35);
		contentPane.add(lblProcessname);
		
		segmentName = new JTextField();
		segmentName.setBounds(248, 346, 141, 24);
		contentPane.add(segmentName);
		segmentName.setColumns(10);
		
		segmentSize = new JTextField();
		segmentSize.setColumns(10);
		segmentSize.setBounds(248, 391, 141, 24);
		contentPane.add(segmentSize);
		
		processName = new JTextField();
		processName.setColumns(10);
		processName.setBounds(248, 478, 141, 24);
		contentPane.add(processName);
		
		JButton add = new JButton("ADD");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	          DefaultTableModel model=(DefaultTableModel)table.getModel();
				
				try {
					if(Integer.parseInt(segmentSize.getText())<=0){
						JOptionPane.showMessageDialog(null, "Please Enter Valid Size For The Segment ");
					}else {
					model.addRow(new Object[] {segmentName.getText(),Integer.parseInt(segmentSize.getText())});
					segmentName.setText("");
					segmentSize.setText("");
			
				}
				}catch(Exception e1){
						JOptionPane.showMessageDialog(null, "Please Enter Valid Size For The Segment");
					}
				
				
			
			}
		});
		add.setFont(new Font("Tahoma", Font.ITALIC, 15));
		add.setBounds(492, 345, 126, 35);
		contentPane.add(add);
		
		JButton update = new JButton("UPDATE");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				
				try{
					if(table.getSelectedRowCount()==1) {
						if(Integer.parseInt(segmentSize.getText())<=0){
							JOptionPane.showMessageDialog(null, "Please Enter Valid Size For The Segment ");
						
						
			
					
				         }else {
				
					        String name=segmentName.getText();
					        String size=segmentSize.getText();
		
					
					model.setValueAt(name, table.getSelectedRow(), 0);
					model.setValueAt(Integer.parseInt(size), table.getSelectedRow(), 1);
					
					
				}
				}else {
					if(table.getRowCount()==0){
						JOptionPane.showMessageDialog(null, "Table is Empty");
					}else {
						JOptionPane.showMessageDialog(null, "Please select single row");
					}
				}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Please Enter Valid Size For The Segment");
				}
			}
		});
		update.setFont(new Font("Tahoma", Font.ITALIC, 15));
		update.setBounds(492, 390, 126, 35);
		contentPane.add(update);
		
		JButton delete = new JButton("DELETE");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	           DefaultTableModel model=(DefaultTableModel)table.getModel();
				
				if(table.getSelectedRowCount()==1) {
					model.removeRow(table.getSelectedRow());
				}else {
					if(table.getRowCount()==0){
						JOptionPane.showMessageDialog(null, "Table is Empty");
					}else {
						JOptionPane.showMessageDialog(null, "Please select single row");
					}
				}
			}
		});
		delete.setFont(new Font("Tahoma", Font.ITALIC, 15));
		delete.setBounds(492, 435, 126, 35);
		contentPane.add(delete);
		
		JButton deleteAll = new JButton("DeleteAll");
		deleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				if (table.getRowCount() > 0) {
				    for (int i = table.getRowCount() - 1; i > -1; i--) {
				    	model.removeRow(i);
				    }
				}
				    else
				    {
				    	JOptionPane.showMessageDialog(null, "Table is Empty");
				    }
			}
		});
		deleteAll.setFont(new Font("Tahoma", Font.ITALIC, 15));
		deleteAll.setBounds(492, 482, 126, 35);
		contentPane.add(deleteAll);
		
		JButton firstFit = new JButton("FirstFit");
		
		firstFit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 
			
				
				
				
				
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				Vector segments=new Vector();
				segments=model.getDataVector();
				int  segmentsCount=model.getRowCount();
				String ProcessName=processName.getText();

				
				Input.event1.setProcessName(ProcessName);
				Input.event1.setSegmentsCount(segmentsCount);
				Input.event1.addSegments(segments);
				Input.event1.firstFit();
				for(int i=0;i<Input.event1.baseVector.size();i++)
				{
					model.setValueAt(Input.event1.baseVector.get(i), i, 2);
				}
				Input.event1.clearBase();
				Input.event1.clearSegment();
				
                 Input.frame1.setVisible(false);
                 
                frame2=new output();   
                frame2.setLocation(850, 90);
				frame2.setVisible(true); 

		    
				Visable=true;
				
				
				
				
			}
		});
		firstFit.setFont(new Font("Traditional Arabic", Font.BOLD | Font.ITALIC, 24));
		firstFit.setForeground(new Color(139, 0, 0));
		firstFit.setBounds(127, 566, 141, 46);
		contentPane.add(firstFit);
		
		JButton bestFit = new JButton("BestFit");
		bestFit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				Vector segments=new Vector();
				segments=model.getDataVector();
				int  segmentsCount=model.getRowCount();
				String ProcessName=processName.getText();
				
				Input.event1.setProcessName(ProcessName);
				Input.event1.setSegmentsCount(segmentsCount);
				Input.event1.addSegments(segments);
				Input.event1.bestFit();
				for(int i=0;i<Input.event1.baseVector.size();i++)
				{
					model.setValueAt(Input.event1.baseVector.get(i), i, 2);
				}
				Input.event1.clearBase();
				Input.event1.clearSegment();
				
                 Input.frame1.setVisible(false);
                 frame2=new output();
				frame2.setVisible(true);
		        frame2.setLocation(850, 90);

				Visable=true;
				
				
			}
		});
		bestFit.setForeground(new Color(139, 0, 0));
		bestFit.setFont(new Font("Traditional Arabic", Font.BOLD | Font.ITALIC, 24));
		bestFit.setBounds(379, 566, 141, 46);
		contentPane.add(bestFit);
		
		
		
	}
}
