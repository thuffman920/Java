package tshuffma.cd_directory.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * @author Tyler
 *
 */
public class CDMainGUI extends JFrame implements ActionListener {

  /**   */
  private static final long serialVersionUID = 1L;
  /** */
  private JRadioButton addSermon = new 
      JRadioButton("Add a Sermon to the database");
  /** */
  private JRadioButton searchFor = new 
      JRadioButton("Search the database");
  /** */
  private JRadioButton browse = new 
      JRadioButton("Initialize the database");
  /** */
  private JButton btnSubmit = new JButton("Submit");
  private int count;
  private String inputFile;
  private String searchResults;
  private String[] searchItems;
  private JLabel lblStoppingResults = new JLabel("");
  private CDInventory collection;
  private JCheckBox month = new JCheckBox("Month");
  private JCheckBox day = new JCheckBox("Day");
  private JCheckBox year = new JCheckBox("Year");
  private JCheckBox unknownSpeaker = new JCheckBox("Unknown Speaker");
  private JCheckBox knownSpeaker = new JCheckBox("Known Speaker");
  private JCheckBox multipleSpeakers = new JCheckBox("Multiple Possible Speakers");
  private JCheckBox bibleBook = new JCheckBox("Scriptures Book");
  private JCheckBox bibleChapter = new JCheckBox("Scriptures Chapter");
  private JCheckBox bibleVerses = new JCheckBox("Scriptures Verse(s)");
  private JCheckBox pullFromPast1 = new JCheckBox("Date From Search");
  private JCheckBox pullFromPast2 = new JCheckBox("Speaker From Search");
  private JCheckBox pullFromPast3 = new JCheckBox("Scripture From Search");
  private JTextField txtMonth = new JTextField("", 20);
  private JTextField txtDay = new JTextField("", 20);
  private JTextField txtYear = new JTextField("", 20);
  private JTextField speaker = new JTextField("", 20);
  private JButton btnSearch  = new JButton("Search");
  private JButton btnMain = new JButton("Main");
  private Container c = getContentPane();
  private JPanel main = new JPanel();
  private JButton btnText = new JButton("Export Text");
  private JButton btnExcel = new JButton("Highlight in Excel");;
  private JButton btnReset = new JButton("Reset");
  private JButton btnAdvanced = new JButton("Advanced Search");
  private JCheckBox date = new JCheckBox("Date");
  private JCheckBox cbSpeaker = new JCheckBox("Speaker");
  private JCheckBox bibleVerse = new JCheckBox("Bible Verse");
  private JRadioButton sundayEvening = new JRadioButton("Sunday Evening");
  private JRadioButton sundayMorning = new JRadioButton("Sunday Morning");
  private JRadioButton wednesday = new JRadioButton("Wednesday");
  private JTextArea txtSearchFor = new JTextArea(4, 20);
  private JPanel pnlSearchSermon = new JPanel(new GridLayout(6, 1));
  private JButton btnBrowse = new JButton("Browse");
  private JTextField txtBrowse = new JTextField("", 20);
  private JFrame parentFrame = new JFrame();
  private JButton btnBrowseSubmit = new JButton("Submit");
  private JTextField txtDate = new JTextField("", 20);
  private JTextField txtSpeaker = new JTextField("", 20);
  private JTextField txtBibleVerse = new JTextField("",20);
  private JTextArea txtSongs = new JTextArea(5, 20);
  private JCheckBox currentDate = new JCheckBox("Use Current Date");
  private JCheckBox specialEvent = new JCheckBox("Special Event");
  private JButton btnAddSubmit = new JButton("Add");

  /**
   * 
   */
  public CDMainGUI() {
    super("CD's Directory Menu");
    setSize(500,500);
    setLocation(100,100);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    count = 0;
    
    this.menu();
    this.addListeners();
    c.add(main);
    setVisible(true);
  }
  
  /**
   * 
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (!browse.isSelected() && e.getSource() == btnSubmit && inputFile == null) {
      lblStoppingResults.setText("You need to initializes the database file");
    } else if (browse.isSelected() && e.getSource() == btnSubmit && count == 0){
      count++;
      this.browsePanel();
      try {
        collection = new CDInventory(this.inputFile);
      } catch (Exception g) {
        
      }
    } else {
      if (addSermon.isSelected() && e.getSource() == btnSubmit && count == 0) {
        count++;
        this.addPanel();
      } else if (searchFor.isSelected() && e.getSource() == btnSubmit && count == 0) {
        count++;
        this.searchPanel();
      } else {
      }
    }
    if (e.getSource() == btnSearch) {
      String knownParts = "";
      if (date.isSelected()){
        knownParts = knownParts + "Date\t";
      }
      if (cbSpeaker.isSelected()) {
        knownParts = knownParts + "Speaker\t";
      }
      if (bibleVerse.isSelected()){
        knownParts = knownParts + "Bible Verse";
      }
      searchItems = collection.setSearchFor(txtSearchFor.getText(), knownParts);
      collection.setDate("");
      collection.setBibleVerse("");
      collection.setSpeaker("");
      String service = "";
      if (sundayMorning.isSelected()) {
        service = sundayMorning.getText();
        searchResults = collection.searchFor(searchItems, service, knownParts);
      } else if (sundayEvening.isSelected()) {
        service = sundayEvening.getText();
        searchResults = collection.searchFor(searchItems, service, knownParts);
      } else if (wednesday.isSelected()) {
        service = wednesday.getText() + " Evening";
        searchResults = collection.searchFor(searchItems, service, knownParts);
      } else {
        searchResults = collection.searchAll(searchItems, knownParts);
      }
      lblStoppingResults.setText(searchResults);
      JPanel pnlToManyResults = new JPanel(new GridLayout(1, 4));
      pnlToManyResults.add(btnText);
      pnlToManyResults.add(btnExcel);
      pnlToManyResults.add(btnReset);
      pnlToManyResults.add(btnAdvanced);
      pnlSearchSermon.add(pnlToManyResults);
      c.add(pnlSearchSermon, BorderLayout.CENTER);
      setVisible(true);
    }
    if (e.getSource() == btnMain) {
      this.menu();
    }
    if (e.getSource() == btnReset) {
      txtSearchFor.setText("");
      cbSpeaker.setSelected(false);
      date.setSelected(false);
      bibleVerse.setSelected(false);
      sundayMorning.setSelected(false);
      sundayEvening.setSelected(false);
      wednesday.setSelected(false);
    }
    if (e.getSource() == btnText){
      this.sendToText(searchResults);
    }
    if (e.getSource() == btnExcel){
      this.sendToExcel(searchResults);
    }
    if (e.getSource() == btnAdvanced) {
      this.advancedSearchPanel();
    }
    if (e.getSource() == btnBrowse) {
      JFileChooser file = new JFileChooser();
      file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
      file.setCurrentDirectory(new java.io.File("."));
      int opt = file.showSaveDialog(parentFrame);
      if (opt == JFileChooser.APPROVE_OPTION) {
        File fileToSave = file.getSelectedFile();
        txtBrowse.setText(fileToSave.getAbsolutePath().toString());
      }
    }
    if (e.getSource() == btnBrowseSubmit) {
      this.setPathName();
      this.menu();
    }
    if (currentDate.isSelected()) {
      txtDate.setEnabled(false);
    } else {
      txtDate.setEnabled(true);
    }
    if (e.getSource() == btnAddSubmit && !specialEvent.isSelected()) {
      collection.setDate(txtDate.getText());
      collection.setSpeaker(txtSpeaker.getText());
      collection.setBibleVerse(txtBibleVerse.getText());
      collection.setSongs(txtSongs.getText());
      String button = "";
      if (sundayMorning.isSelected()) {
        button = sundayMorning.getText();
      } else {
        button = sundayEvening.getText();
      }
      String sheetName = "";
      Calendar sermonDate = Calendar.getInstance();
      String date = txtDate.getText();
      int year = Integer.parseInt(date.substring(6, date.length()));
      int day = Integer.parseInt(date.substring(3, 5));
      int month = Integer.parseInt(date.substring(0,2));
      sermonDate.set(year, month - 1, day);
      try {
        if (sermonDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
          sheetName = sheetName + "Sunday " + button;
        } else if (sermonDate.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
          sheetName = sheetName + "Wednesday " + button;
        } else {
          throw new IllegalArgumentException();
        }
      } catch (IllegalArgumentException f) {
        lblStoppingResults.setText("Sermon Not Added");
      }
      this.addSermonTo(sheetName);
    } else if (e.getSource() == btnAddSubmit && specialEvent.isSelected()) {
      String sheetName = "Special Event";
      this.addSermonTo(sheetName);
    }
  }
  
  private void reset() {
    c.setVisible(false);
    c.setVisible(true);
  }
  
  private void menu() {
    ButtonGroup mainProcess = new ButtonGroup();
    JLabel mainSelection = new JLabel("What do you want?");
    addSermon.setSelected(false);
    searchFor.setSelected(false);
    browse.setSelected(false);
    mainProcess.add(addSermon);
    mainProcess.add(searchFor);
    mainProcess.add(browse);
    JPanel mainPanel = new JPanel(new GridLayout(5, 1));
    mainPanel.add(mainSelection);
    mainPanel.add(addSermon);
    mainPanel.add(searchFor);
    mainPanel.add(browse);
    mainPanel.add(lblStoppingResults);
    mainPanel.add(btnSubmit);
    main.add(mainPanel);
    c.removeAll();
    c.add(main);
    this.reset();
  }
  
  private void addListeners() {
    btnSearch.addActionListener(this);
    btnSubmit.addActionListener(this);
    btnMain.addActionListener(this);
    btnSearch.addActionListener(this);
    btnText.addActionListener(this);
    btnExcel.addActionListener(this);
    btnAdvanced.addActionListener(this);
    btnReset.addActionListener(this);
    btnBrowse.addActionListener(this);
    btnBrowseSubmit.addActionListener(this);
    currentDate.addActionListener(this);
  }
  
  private void addPanel() {
    try {
      collection = new CDInventory(inputFile);
    } catch (IOException e) {
      e.getMessage();
    } catch (NullPointerException e) {
      lblStoppingResults.setText("No file was added");
    }
    JLabel lblDate = new JLabel("Date:");
    JLabel lblSpeaker = new JLabel("Speaker:");
    JLabel lblBibleVerse = new JLabel("Bible Verse:");
    ButtonGroup addSermonButton = new ButtonGroup();
    addSermonButton.add(sundayMorning);
    addSermonButton.add(sundayEvening);
    sundayEvening.setSelected(false);
    sundayMorning.setSelected(false);
    JLabel lblSongs = new JLabel("Songs:");
    txtSongs.setLineWrap(true);
    
    JPanel pnlAddSermon = new JPanel(new GridLayout(5, 1));
    JPanel pnlAddSermon1 = new JPanel(new GridLayout(2, 2));
    JPanel pnlAddSermon2 = new JPanel(new GridLayout(3, 2));
    JPanel pnlAddSermon3 = new JPanel(new GridLayout(1, 2));
    JPanel pnlAddSermon4 = new JPanel();
    JPanel pnlAddSermon5 = new JPanel();
    pnlAddSermon1.add(lblDate);
    pnlAddSermon1.add(txtDate);
    pnlAddSermon1.add(currentDate);
    pnlAddSermon1.add(specialEvent);
    pnlAddSermon2.add(sundayMorning);
    pnlAddSermon2.add(sundayEvening);
    pnlAddSermon2.add(lblSpeaker);
    pnlAddSermon2.add(txtSpeaker);
    pnlAddSermon2.add(lblBibleVerse);
    pnlAddSermon2.add(txtBibleVerse);
    pnlAddSermon3.add(lblSongs);
    pnlAddSermon3.add(txtSongs);
    pnlAddSermon4.add(lblStoppingResults);
    pnlAddSermon4.add(btnSubmit);
    pnlAddSermon5.add(btnMain);
    pnlAddSermon.add(pnlAddSermon1);
    pnlAddSermon.add(pnlAddSermon2);
    pnlAddSermon.add(pnlAddSermon3);
    pnlAddSermon.add(pnlAddSermon4);
    pnlAddSermon.add(pnlAddSermon5);
    c.removeAll();
    c.add(pnlAddSermon, BorderLayout.NORTH);
    this.reset();
  }
  
  private void searchPanel() {
    JLabel lblSearchFor = new JLabel("Search for:");
    ButtonGroup searchSermonButton = new ButtonGroup();
    searchSermonButton.add(sundayMorning);
    searchSermonButton.add(sundayEvening);
    searchSermonButton.add(wednesday);
    JPanel pnlSearchSermon1 = new JPanel(new GridLayout(1, 2));
    JPanel pnlSearchSermon2 = new JPanel(new GridLayout(2, 3));
    JPanel pnlSearchSermon3 = new JPanel();
    JPanel pnlSearchSermon4 = new JPanel();
    pnlSearchSermon1.add(lblSearchFor);
    pnlSearchSermon1.add(txtSearchFor);
    pnlSearchSermon2.add(date);
    pnlSearchSermon2.add(speaker);
    pnlSearchSermon2.add(bibleVerse);
    pnlSearchSermon2.add(sundayMorning);
    pnlSearchSermon2.add(sundayEvening);
    pnlSearchSermon2.add(wednesday);
    pnlSearchSermon3.add(lblStoppingResults);
    pnlSearchSermon3.add(btnSearch);
    pnlSearchSermon4.add(btnMain);
    pnlSearchSermon.add(pnlSearchSermon1);
    pnlSearchSermon.add(pnlSearchSermon2);
    pnlSearchSermon.add(pnlSearchSermon3);
    pnlSearchSermon.add(pnlSearchSermon4);
    c.removeAll();
    c.add(pnlSearchSermon, BorderLayout.CENTER);
  }
  
  private void browsePanel() {
    JLabel lblBrowse = new JLabel("Browse:");    
    JPanel pnlBrowse = new JPanel(new GridLayout(2, 1));
    JPanel pnlBrowse1 = new JPanel(new GridLayout(1, 3));
    JPanel pnlBrowse2 = new JPanel();
    pnlBrowse1.add(lblBrowse);
    pnlBrowse1.add(txtBrowse);
    pnlBrowse1.add(btnBrowse);
    pnlBrowse2.add(btnBrowseSubmit);
    pnlBrowse.add(pnlBrowse1);
    pnlBrowse.add(pnlBrowse2);
    c.removeAll();
    c.add(pnlBrowse, BorderLayout.NORTH);
    this.reset();
  }
  
  private void advancedSearchPanel() {
    JPanel pnlAdditionalSearch = new JPanel(new GridLayout(5, 1));
    JPanel pnlAdditionalSearch1 = new JPanel(new GridLayout(1, 3));
    JPanel pnlAdditionalSearch2 = new JPanel(new GridLayout(1, 3));
    JPanel pnlAdditionalSearch3 = new JPanel(new GridLayout(1, 3));
    JPanel pnlAdditionalSearch4 = new JPanel();
    pnlAdditionalSearch1.add(day);
    pnlAdditionalSearch1.add(month);
    pnlAdditionalSearch1.add(year);
    pnlAdditionalSearch2.add(unknownSpeaker);
    pnlAdditionalSearch2.add(knownSpeaker);
    pnlAdditionalSearch2.add(multipleSpeakers);
    pnlAdditionalSearch3.add(bibleBook);
    pnlAdditionalSearch3.add(bibleChapter);
    pnlAdditionalSearch3.add(bibleVerses);
    pnlAdditionalSearch4.add(btnSubmit);
    pnlAdditionalSearch4.add(btnSearch);
    pnlAdditionalSearch4.add(btnMain);
    pnlAdditionalSearch.add(pnlAdditionalSearch1);
    pnlAdditionalSearch.add(pnlAdditionalSearch2);
    pnlAdditionalSearch.add(pnlAdditionalSearch3);
    pnlAdditionalSearch.add(pnlAdditionalSearch4);
    c.removeAll();
    c.add(pnlAdditionalSearch);
    this.reset();
  }
  
  private void sendToExcel(String searchResults) {
    
    
  }

  private void sendToText(String searchResults) {
    PrintStream outputStream = null;
    try{
      outputStream = new PrintStream(new File("Search_Results.txt"));
      outputStream.println(searchResults);
    } catch(FileNotFoundException s) {
      lblStoppingResults.setText(s.getMessage());
    }
  }
  
  private void setPathName() {
    File inputTextFileName = new File("File_Location.txt");
    BufferedReader inputFileReader = null;
    FileReader fileReader = null;
    PrintStream outputTextFileName = null;
    FileWriter fileWriter = null;
    try {
      fileReader = new FileReader(inputTextFileName);
      inputFileReader = new BufferedReader(fileReader);
      if (inputFileReader.readLine() != null) {
        fileWriter = new FileWriter(inputTextFileName);
        fileWriter.write("");
        fileWriter.append(this.txtBrowse.getText());
      }else{
        outputTextFileName = new PrintStream(inputTextFileName);
        outputTextFileName.println(this.txtBrowse.getText());
        outputTextFileName.close();
      }
      fileReader.close();
      fileWriter.close();
    } catch (FileNotFoundException d) {
      try{
        inputTextFileName.createNewFile();
        outputTextFileName = new PrintStream(inputTextFileName);
        outputTextFileName.println(this.txtBrowse.getText());
      } catch (FileNotFoundException w) {
        w.getMessage();
      } catch (IOException f) {
        f.getMessage();
      }
      outputTextFileName.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.inputFile = this.txtBrowse.getText();
  }
  
  private void addSermonTo(String sheetName) {
    boolean added = false;
    boolean added1 = false;
    try{
      added = collection.writeExcel(sheetName, 0);
      added1 = collection.writeText(sheetName);
      if (added && added1) {
        lblStoppingResults.setText("Sermon added."); 
        txtDate.setText("");
        txtBibleVerse.setText("");
        txtSpeaker.setText("");
        txtSongs.setText("");
        sundayMorning.setSelected(false);
        sundayEvening.setSelected(false);
      } else {
        lblStoppingResults.setText("Sermon not added.");
      }
    } catch (IOException d) {
      lblStoppingResults.setText(d.getMessage());
    }
  }
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    new CDMainGUI();

  }
}