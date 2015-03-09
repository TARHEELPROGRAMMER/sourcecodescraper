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

    static JFrame frame = new JFrame();
    JBackgroundPanel mainPanel = new JBackgroundPanel();
    JTextArea descriptionTextArea = new JTextArea(50, 50);
    JButton saveButton = new JButton("Save To Text File");
    JButton goBackButton = new JButton("Go Back To Home Screen");
    private final String webPageSourceCode;

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

    private void BuildMainPanel()
    {
        JPanel tempPanelOne = new JPanel();
        JPanel tempPanelTwo = new JPanel();

        tempPanelOne.setBackground(new Color(0, 0, 0, 125));
        tempPanelTwo.setBackground(new Color(0, 0, 0, 125));

        saveButton.addActionListener(new JButtonListener());
        goBackButton.addActionListener(new JButtonListener());

        descriptionTextArea.setText(webPageSourceCode);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(descriptionTextArea);

        tempPanelOne.add(scrollPane);

        scrollPane.setBackground(new Color(0, 0, 0, 125));
        descriptionTextArea.setBackground(Color.WHITE);
        descriptionTextArea.setForeground(Color.BLACK);
        tempPanelTwo.add(saveButton);
        tempPanelTwo.add(goBackButton);

        tempPanelTwo.setBackground(new Color(0, 0, 0, 125));

        mainPanel.add(tempPanelOne, BorderLayout.CENTER);
        mainPanel.add(tempPanelTwo, BorderLayout.SOUTH);
    }

    private void CloseThis()
    {
        frame.dispose();
    }

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
                new GetSourceCodeGUI();
                CloseThis();
            }
        }
    }

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
