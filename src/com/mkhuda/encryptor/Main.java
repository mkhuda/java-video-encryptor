package com.mkhuda.encryptor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.TextArea;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.awt.Button;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import net.miginfocom.swing.MigLayout;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Window.Type;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Main extends JFrame{

	private JPanel contentPane;
	static String source, out, code;
	private static JTextField textField;
	private JTextField sourceText;
	private static JTextField textField_1;
	private JButton btnChoose;
	private static JTextField txtEncryptionCode;
	private JLabel lblmkhuda;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
//					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setResizable(false);
		setTitle("AES MP4 Encryption v.1.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("100px"),
				ColumnSpec.decode("171dlu:grow"),},
			new RowSpec[] {
				RowSpec.decode("40dlu"),
				RowSpec.decode("23px"),
				RowSpec.decode("23px"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
				
				btnChoose = new JButton("Choose .mp4");
				contentPane.add(btnChoose, "1, 2, left, center");
				
				btnChoose.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ae) {
	        JFileChooser fileChooser = new JFileChooser();
	        FileNameExtensionFilter filter = new FileNameExtensionFilter(
	                "Video MP4", "mp4");
	            fileChooser.setFileFilter(filter);
	        int returnValue = fileChooser.showOpenDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
	          File selectedFile = fileChooser.getSelectedFile();
	          String selected = selectedFile.getAbsolutePath();
	          String outputFile = selectedFile.getParent();
	          textField.setText(selected);
	          textField_1.setText(outputFile+"output.mp4");
	        }
	      }
	    });
				
				textField = new JTextField();
				contentPane.add(textField, "2, 2, left, center");
				textField.setColumns(25);
				
				textField_1 = new JTextField();
				contentPane.add(textField_1, "2, 3, left, center");
				textField_1.setColumns(25);
				
				txtEncryptionCode = new JTextField();
				txtEncryptionCode.setToolTipText("Passphase (Code)");
				
				contentPane.add(txtEncryptionCode, "2, 4, left, center");
				txtEncryptionCode.setColumns(25);
			
				
				JButton button = new JButton("Convert");
				button.setVerticalAlignment(SwingConstants.BOTTOM);
				contentPane.add(button, "2, 5, center, center");
				
				lblmkhuda = DefaultComponentFactory.getInstance().createTitle("@mkhuda (2015)");
				contentPane.add(lblmkhuda, "1, 10");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					encrypt();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		
	}
	
	public static void encrypt() throws Exception {
		source = textField.getText();
		out = textField_1.getText();
		code = txtEncryptionCode.getText();
		String bonjour = new String(code);
		if(!(bonjour.length() == 16)) {
			JOptionPane.showMessageDialog(null, "Code must 16 length character");
		} else {
			JOptionPane.showMessageDialog(null, "Converting: "+source+" => "+out);
		    final byte[] buf = new byte[8192];
		    final Cipher c = Cipher.getInstance("AES/CFB/NoPadding");
		    c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(bonjour.getBytes(), "AES"), new IvParameterSpec(new byte[16]));
		    final InputStream is = new FileInputStream(source);
		    final OutputStream os = new CipherOutputStream(new FileOutputStream(out), c);
		    while (true) {
		        int n = is.read(buf);
		        if (n == -1) break;
		        os.write(buf, 0, n);
		        
		    }
		    os.close(); is.close();
		    JOptionPane.showMessageDialog(null, "Success");
		}
	
		
	}

}
