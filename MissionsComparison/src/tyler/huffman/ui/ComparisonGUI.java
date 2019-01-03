package tyler.huffman.ui;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * @author Tyler Huffman
<applet code="ComparisonGUI" width="200" height="200">
</applet>
 */
public class ComparisonGUI extends JFrame implements ActionListener {

	/**	 */
	private static final long serialVersionUID = 1L;

	private JTextField firstText;
	private JTextField secondText;
	
	private JLabel firstResult;
	private JLabel secondResult;
	private JLabel firstFileResult;
	private JLabel secondFileResult;
	
	private JTextArea firstList;
	private JTextArea secondList;
	
	private JScrollPane listScroller1;
	private JScrollPane listScroller2;
	
	private JButton submitButton;
	private JButton fileChooserButton1;
	private JButton fileChooserButton2;
	
	public ComparisonGUI() {
		
		JFrame jfrm = new JFrame();
		jfrm.setSize(1070, 700);
		jfrm.setLayout(new GridLayout(3, 1));
		jfrm.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// This creates the result panel
		JPanel panel = new JPanel(new GridLayout(4, 1));
		JPanel panel1 = new JPanel(new GridLayout(1, 2));
		JPanel panel2 = new JPanel(new GridLayout(1, 2));
		JPanel panel3 = new JPanel(new GridLayout(1, 4));
		JPanel panel4 = new JPanel(new GridLayout(1, 4));
		JPanel panel5 = new JPanel(new GridLayout(1, 4));
		
		// This creates the labels
		JLabel firstFile = new JLabel("First File");
		firstResult = new JLabel("");
		JLabel secondFile = new JLabel("Second File");
		secondResult = new JLabel("");
		firstFileResult = new JLabel("");
		secondFileResult = new JLabel("");
		panel1.add(firstFileResult);
		panel1.add(secondFileResult);
		
		// This creates the text fields 
		firstText = new JTextField(15);
		secondText = new JTextField(15);
		
		// This creates the text areas
		firstList = new JTextArea();
		secondList = new JTextArea();
		listScroller1 = new JScrollPane(firstList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listScroller1.setBounds(5, 5, 5, 5);
		listScroller2 = new JScrollPane(secondList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listScroller2.setBounds(5, 5, 5, 5);
		panel2.add(listScroller1);
		panel2.add(listScroller2);
		listScroller1.setVisible(false);
		listScroller2.setVisible(false);
		
		Border border1 = BorderFactory.createLineBorder(Color.BLACK);
		firstList.setBorder(BorderFactory.createCompoundBorder(border1, 
		            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		Border border2 = BorderFactory.createLineBorder(Color.BLACK);
		secondList.setBorder(BorderFactory.createCompoundBorder(border2, 
		            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		// This creates the submit button
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		fileChooserButton1 = new JButton("File...");
		fileChooserButton1.addActionListener(this);
		fileChooserButton2 = new JButton("File...");
		fileChooserButton2.addActionListener(this);
		
		panel3.add(firstFile);
		panel3.add(firstText);
		panel3.add(fileChooserButton1);
		panel3.add(firstResult);
		panel4.add(secondFile);
		panel4.add(secondText);
		panel4.add(fileChooserButton2);
		panel4.add(secondResult);
		panel5.add(submitButton);
		panel5.add(new JLabel());
		panel5.add(new JLabel());
		panel5.add(new JLabel());
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		jfrm.add(panel);
		jfrm.add(panel1);
		jfrm.add(panel2);
		jfrm.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource().equals(submitButton)) {
			firstList.setVisible(false);
			secondList.setVisible(false);
			listScroller1.setVisible(false);
			listScroller2.setVisible(false);
			firstFileResult.setVisible(false);
			secondFileResult.setVisible(false);
			if (secondText.getText().equals("") && firstText.getText().equals("")) {
				firstResult.setText("First file is empty.");
				secondResult.setText("Second file is empty.");
			} else if (secondText.getText().equals(""))
				secondResult.setText("Second file is empty.");
			else if (firstText.getText().equals(""))
				firstResult.setText("First file is empty");
			else {
				firstResult.setText("                                     ");
				secondResult.setText("                                     ");
				try {
					ComparisonUI ui = new ComparisonUI(firstText.getText(), secondText.getText());
					String filename1 = firstText.getText().substring(firstText.getText().lastIndexOf("\\") + 1);
					String filename2 = secondText.getText().substring(secondText.getText().lastIndexOf("\\") + 1);
					firstFileResult.setText("First File Results: " + filename1);
					secondFileResult.setText("Second File Results: " + filename2);
					firstList.setText(ui.toString(ui.getFirstDifference()));
					secondList.setText(ui.toString(ui.getSecondDifference()));
					if (ui.getMax() == 1) {
						listScroller1.setVerticalScrollBar(listScroller2.getVerticalScrollBar());
						listScroller1.setHorizontalScrollBar(listScroller2.getHorizontalScrollBar());
					} else {
						listScroller2.setVerticalScrollBar(listScroller1.getVerticalScrollBar());
						listScroller2.setHorizontalScrollBar(listScroller1.getHorizontalScrollBar());
					}
					firstList.setVisible(true);
					secondList.setVisible(true);
					listScroller1.setVisible(true);
					listScroller2.setVisible(true);
					firstFileResult.setVisible(true);
					secondFileResult.setVisible(true);
				} catch (FileNotFoundException g) {
					g.printStackTrace();
					if (g.getMessage().indexOf(firstText.getText()) != -1)
						firstResult.setText(g.getMessage());
					else
						secondResult.setText(g.getMessage());
				}
			}
		} else if (ae.getSource().equals(fileChooserButton2)) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int result = chooser.showOpenDialog(ComparisonGUI.this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();
				secondText.setText(selectedFile.getAbsolutePath());
			}
		} else if (ae.getSource().equals(fileChooserButton1)) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int result = chooser.showOpenDialog(ComparisonGUI.this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();
				firstText.setText(selectedFile.getAbsolutePath());
			}
		}
	}
	
	public static void main(String[] args) {
		new ComparisonGUI();
	}
}