import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JScrollBar;

public class windowbuilder {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JPanel panel_1;
	private JPanel panel;
	private JLabel lblNewLabel_2;
	private JLabel label;
	private JTextArea textArea_2;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JTextField textField_2;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JTextField textField_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowbuilder window = new windowbuilder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public windowbuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1116, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JButton btnNewButton = new JButton("\u8B80\u53D6\u6A94\u6848");
		final JFileChooser fc = new JFileChooser();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
							//panel_1.repaint();
							//panel.repaint();
							fc.setCurrentDirectory(new java.io.File("C:/Users/Lynn/workspace/NNHW1/data"));//data來源
			                fc.setDialogTitle("Open Data");
			                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			                if (fc.showOpenDialog(btnNewButton) == JFileChooser.APPROVE_OPTION)
			                {
			                	
			                }
				}
				
				/*public void actionPerformed(ActionEvent e) //openFile
				{
					fc.setCurrentDirectory(new java.io.File("C:/Users/lee chiting/workspace/neural network1"));//從哪個路徑底下開始找檔案
	                fc.setDialogTitle("請選擇要開啟的檔案");
	                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	                
	                if (fc.showOpenDialog(btnOpen) == JFileChooser.APPROVE_OPTION) //btnOpen建立button時自己建的參數名稱
	                {
	             
	                    //JOptionPane.showMessageDialog(null, fc.getSelectedFile().getAbsolutePath());//確定是否為這個文件
	                }
				}
			
			}*/
		});
		btnNewButton.setBounds(33, 500, 94, 39);
		frame.getContentPane().add(btnNewButton);
		JButton btnNewButton_1 = new JButton("\u57F7\u884C");
		final AlgoNN algonn = new AlgoNN();//連結演算法跟GUI的呼叫
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						algonn.run(fc.getSelectedFile().getAbsolutePath());
						algonn.algo(Double.parseDouble(textField_1.getText()),Double.parseDouble(textField.getText()));
						FileReader train = new FileReader("train.txt");
						BufferedReader br = new BufferedReader(train);
						String save = "";
						Color[] changeC = {
								Color.GREEN,
								Color.BLACK,
								Color.BLUE,
								Color.ORANGE,
								Color.RED,
						};
						
						Graphics gp1 = panel_1.getGraphics();
						gp1.setColor(Color.WHITE);
						gp1.fillRect(0, 0, panel_1.getWidth(), panel_1.getHeight());
						gp1.setColor(Color.BLACK);
						gp1.translate(panel_1.getWidth()/2,panel_1.getHeight()/2);
						gp1.drawLine(-panel_1.getWidth()/2,0, panel_1.getWidth()/2, 0);
						gp1.drawLine(0, -panel_1.getHeight()/2, 0, panel_1.getHeight()/2);
						
						while (br.ready()) 
						{
							String data = br.readLine();
							save += data+"\n";
							String[] token = data.split(" ");
							double x = Double.parseDouble(token[0])*20;
							double y = Double.parseDouble(token[1])*20;
							int classification = Integer.parseInt(token[2]);
				
							//gp1.translate(panel_1.getWidth()/2,panel_1.getHeight()/2);
							gp1.setColor(changeC[classification]);
							gp1.drawOval((int)x,(int)y, 3, 3);
						}
						textArea.setText(save);
						train.close();
						
						
						FileReader test = new FileReader("test.txt");
						BufferedReader tbr = new BufferedReader(test);
						String tsave = "";
						
						
						Graphics gp = panel.getGraphics();
						gp.setColor(Color.WHITE);
						gp.fillRect(0, 0, panel.getWidth(), panel.getHeight());
						gp.setColor(Color.BLACK);
						gp.translate(panel.getWidth()/2,panel.getHeight()/2);
						gp.drawLine(-panel.getWidth()/2,0, panel.getWidth()/2, 0);
						gp.drawLine(0, -panel.getHeight()/2, 0, panel.getHeight()/2);
						
						while (tbr.ready()) 
						{
							String data = tbr.readLine();
							tsave += data+"\n";
							String[] token = data.split(" ");
							double x = Double.parseDouble(token[0])*20;
							double y = Double.parseDouble(token[1])*20;
							int classification = Integer.parseInt(token[2]);
							//Graphics g = panel.getGraphics();
							//g.translate( panel.getWidth()/2,panel.getHeight()/2);
							gp.setColor(changeC[classification]);
							gp.drawOval((int)x,(int)y, 3, 3);
						}
						textArea_2.setText(tsave);
						test.close();
						
						
						FileReader result = new FileReader("result.txt");
						BufferedReader rr = new BufferedReader(result);
						String rsave = "";
						while (rr.ready()) 
						{
							String data = rr.readLine();
							rsave += data+"\n";
							String[] token = data.split(" ");
							double w0 = Double.parseDouble(token[0]);
							double w1 = Double.parseDouble(token[1]);
							double w2 = Double.parseDouble(token[2]);
							Graphics tg = panel.getGraphics();
							tg.translate( panel.getWidth()/2,panel.getHeight()/2);
							tg.setColor(Color.RED);
							tg.drawLine((int)-panel.getWidth()/2*20,
									  (int)(((w2-w0*(-panel.getWidth()/2)))/w1*20),
									  (int)panel.getWidth()/2*20,
									  (int)(((w2-w0*(panel.getWidth()/2)))/w1*20));
							Graphics g = panel_1.getGraphics();
							g.translate( panel_1.getWidth()/2,panel_1.getHeight()/2);
							g.setColor(Color.RED);
							
						
							g.drawLine((int)-panel.getWidth()/2*20,
									  (int)(((w2-w0*(-panel.getWidth()/2)))/w1*20),
									  (int)panel.getWidth()/2*20,
									  (int)(((w2-w0*(panel.getWidth()/2)))/w1*20));
						}
						textArea_1.setText(rsave);
						result.close();
						String transc = String.valueOf(algonn.get_traincount());
						textField_3.setText(transc);
						String testc = String.valueOf(algonn.get_testcount());
						textField_2.setText(testc);
						

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} //AlgoNN class
					//run - AlgoNN

			}
		});
		btnNewButton_1.setBounds(185, 500, 104, 39);
		frame.getContentPane().add(btnNewButton_1);
	
		
		textField = new JTextField("100");
		textField.setBounds(174, 404, 115, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField("0.5");
		textField_1.setBounds(174, 370, 115, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		panel_1 = new JPanel() {
			public void paint(Graphics g)
			{
				super.paint(g);
				System.out.print(getHeight()+"---"+getWidth());
				g.translate(getWidth()/2,getHeight()/2);
				g.drawLine(-getWidth()/2,0, getWidth()/2, 0);
				g.drawLine(0, -getHeight()/2, 0, getHeight()/2);
			}
		};
		panel_1.setForeground(Color.BLACK);
		panel_1.setBackground(UIManager.getColor("Button.highlight"));
		panel_1.setBounds(350, 87, 365, 438);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel = new JLabel(" \u5B78\u7FD2\u7387\uFF1A");
		lblNewLabel.setFont(new Font("標楷體", Font.BOLD, 18));
		lblNewLabel.setBounds(33, 370, 94, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" \u8FED\u4EE3\u6B21\u6578\uFF1A");
		lblNewLabel_1.setFont(new Font("標楷體", Font.BOLD, 18));
		lblNewLabel_1.setBounds(31, 404, 115, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		textArea_1 = new JTextArea();
		textArea_1.setForeground(UIManager.getColor("CheckBox.foreground"));
		textArea_1.setBounds(35, 318, 242, 39);
		frame.getContentPane().add(textArea_1);
		
		panel = new JPanel() {
			public void paint(Graphics g)
			{
				super.paint(g);
				System.out.print(getHeight()+"---"+getWidth());
				g.translate(getWidth()/2,getHeight()/2);
				g.drawLine(-getWidth()/2,0, getWidth()/2, 0);
				g.drawLine(0, -getHeight()/2, 0, getHeight()/2);
			}
		};
		panel.setForeground(Color.BLACK);
		panel.setBackground(Color.WHITE);
		panel.setBounds(725, 87, 365, 438);
		frame.getContentPane().add(panel);
		
		lblNewLabel_2 = new JLabel("\u8A13\u7DF4\u8CC7\u6599");
		lblNewLabel_2.setFont(new Font("標楷體", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(485, 38, 72, 24);
		frame.getContentPane().add(lblNewLabel_2);
		
		label = new JLabel("\u6E2C\u8A66\u8CC7\u6599");
		label.setFont(new Font("標楷體", Font.PLAIN, 18));
		label.setBounds(867, 38, 72, 24);
		frame.getContentPane().add(label);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(33, 202, 244, 82);
		frame.getContentPane().add(scrollPane_3);
		
		textArea_2 = new JTextArea();
		scrollPane_3.setViewportView(textArea_2);
		
		lblNewLabel_3 = new JLabel("\u9375\u7D50\u503C\uFF1A");
		lblNewLabel_3.setFont(new Font("標楷體", Font.BOLD, 18));
		lblNewLabel_3.setBounds(33, 295, 94, 24);
		frame.getContentPane().add(lblNewLabel_3);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(33, 87, 244, 82);
		frame.getContentPane().add(scrollPane_2);
		
		textArea = new JTextArea();
		scrollPane_2.setViewportView(textArea);
		textArea.setBackground(SystemColor.window);
		
		textField_2 = new JTextField();
		textField_2.setBounds(174, 435, 115, 24);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		label_1 = new JLabel("\u6E2C\u8A66\u8FA8\u8B58\u7387\uFF1A");
		label_1.setFont(new Font("標楷體", Font.BOLD, 18));
		label_1.setForeground(new Color(0, 0, 0));
		label_1.setBounds(33, 435, 115, 24);
		frame.getContentPane().add(label_1);
		
		label_2 = new JLabel("\u6E2C\u8A66\u8CC7\u6599\uFF1A");
		label_2.setFont(new Font("標楷體", Font.BOLD, 18));
		label_2.setBounds(35, 167, 108, 34);
		frame.getContentPane().add(label_2);
		
		label_3 = new JLabel("\u8A13\u7DF4\u8CC7\u6599\uFF1A");
		label_3.setFont(new Font("標楷體", Font.BOLD, 18));
		label_3.setBounds(35, 58, 108, 24);
		frame.getContentPane().add(label_3);
		
		label_4 = new JLabel("    \u55AE\u5C64\u611F\u77E5\u6A5F");
		label_4.setFont(new Font("標楷體", Font.BOLD, 25));
		label_4.setBounds(31, 10, 246, 52);
		frame.getContentPane().add(label_4);
		
		label_5 = new JLabel("\u8A13\u7DF4\u8FA8\u8B58\u7387\uFF1A");
		label_5.setFont(new Font("標楷體", Font.BOLD, 18));
		label_5.setBounds(33, 469, 115, 21);
		frame.getContentPane().add(label_5);
		
		textField_3 = new JTextField();
		textField_3.setBounds(174, 466, 115, 24);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		

	}
}
