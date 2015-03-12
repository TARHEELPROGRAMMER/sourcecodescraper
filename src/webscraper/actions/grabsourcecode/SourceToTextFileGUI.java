/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webscraper.actions.grabsourcecode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import static webscraper.actions.grabsourcecode.GetSourceCodeGUI.frame;

/**
 *
 * @author Joshua Powell I give permission for you to use this source code for
 * whatever purposes as long as I am credited with creating it inside the code.
 */
public class SourceToTextFileGUI extends JFrame
{
    //Declaring Class Level Objects
    static JFrame frame = new JFrame();
    JBackgroundPanel mainPanel = new JBackgroundPanel();
    JTextArea descriptionTextArea = new JTextArea(50, 50);
    JButton saveButton = new JButton("Save To Text File");
    JButton goBackButton = new JButton("Go Back To Home Screen");
    private final String webPageSourceCode;
    
    //Constructor Built With Swing Components And The Source Code String Being Passed To The Constructor
    public SourceToTextFileGUI(String SourceCode)
    {
        webPageSourceCode = SourceCode;

        frame.setTitle("Web Source Code Scraper");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        BuildMainPanel();

        frame.add(mainPanel);

        frame.setLocation(500,
                100);

        frame.setResizable(false);

        frame.setSize(700, 900);

        frame.setVisible(true);
    }

    //Method To Build The Main Panel
    private void BuildMainPanel()
    {
        //Temp Panels To Hold Swing Components
        JPanel tempPanelOne = new JPanel();
        JPanel tempPanelTwo = new JPanel();
        
        //Creating a scrollPane To Hold descriptionTextArea
        JScrollPane scrollPane = new JScrollPane(descriptionTextArea);
        
        //Cosmetic Additions To Swing Components
        tempPanelOne.setBackground(new Color(0, 0, 0, 125));
        tempPanelTwo.setBackground(new Color(0, 0, 0, 125));
        descriptionTextArea.setText(webPageSourceCode);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setLineWrap(true);
        scrollPane.setBackground(new Color(0, 0, 0, 125));
        descriptionTextArea.setBackground(Color.WHITE);
        descriptionTextArea.setForeground(Color.BLACK);
        
        //Creating The Action Listener For saveButton And goBackButton
        saveButton.addActionListener(new JButtonListener());
        goBackButton.addActionListener(new JButtonListener());

        //Adding Panels To The Main Panel
        tempPanelOne.add(scrollPane);

        tempPanelTwo.add(saveButton);
        tempPanelTwo.add(goBackButton);

        tempPanelTwo.setBackground(new Color(0, 0, 0, 125));

        mainPanel.add(tempPanelOne, BorderLayout.CENTER);
        mainPanel.add(tempPanelTwo, BorderLayout.SOUTH);
    }

    //This Method When Called Will Close frame
    private void CloseThis()
    {
        frame.dispose();
    }

    //Action Listener Class For saveButton And goBackButton
    private class JButtonListener implements ActionListener
    {

        public JButtonListener()
        {
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (saveButton == e.getSource())
            {
                //This Block Of Code Is Opening A JFileChooser And Allowing The User To Save The Source Code String To A
                //Text File.
                JFileChooser saver = new JFileChooser("./");
                FileFilter filter = new FileNameExtensionFilter("Text File","txt");
                saver.setFileFilter(filter);
                int returnVal = saver.showSaveDialog(frame);
                File file = saver.getSelectedFile();
                BufferedWriter writer = null;
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    try
                    {
                        writer = new BufferedWriter(new FileWriter(file));
                        writer.write(descriptionTextArea.getText());
                        writer.close();
                        JOptionPane.showMessageDialog(frame, "The Message was Saved Successfully!",
                                "Success!", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex)
                    {
                        JOptionPane.showMessageDialog(frame, "The Text could not be Saved!",
                                "Error!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }

            if (goBackButton == e.getSource())
            {
                //This Block Of Code Will Call The Main Screen GUI And Close SourceToTextFileGUI
                new GetSourceCodeGUI();
                CloseThis();
            }
        }
    }

    //This Class Extends The JPanel Class And Adds A Background To The JPanel So The User Can Call This Class And Have
    //A Background For The JPanel Already.
    public class JBackgroundPanel extends JPanel
    {

        private BufferedImage img;
        private BorderLayout borderLayout = new BorderLayout();

        public JBackgroundPanel()
        {
            // load the background image
            try
            {
                this.setLayout(borderLayout);
                img = ImageIO.read(new File("BlackAbstract.PNG"));
            } catch (IOException e)
            {
            }
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            // paint the background image and scale it to fill the entire space
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
