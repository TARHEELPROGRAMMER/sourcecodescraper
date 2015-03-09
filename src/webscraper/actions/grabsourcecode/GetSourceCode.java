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
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Joshua Powell
 * I give permission for you to use this source code for whatever purposes as long as I am credited with creating it inside
 * the code.
 */
public class GetSourceCode extends JFrame
{
    static JFrame frame = new JFrame();
    JBackgroundPanel mainPanel = new JBackgroundPanel();
    JLabel descriptionLabel = new JLabel("Please Put The Link In The Text Box: ");
    JTextField userInputTextBox = new JTextField(20);
    JButton getSourceCodeButton = new JButton("Get Source Code");
    
    public GetSourceCode()
    {
        frame.setTitle("Web Source Code Scraper");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        BuildMainPanel();

        frame.add(mainPanel);

        frame.setLocation(700,
                200);

        frame.setResizable(false);
        
        frame.pack();

        frame.setVisible(true);
    }

    private void BuildMainPanel()
    {
        JPanel tempPanelOne = new JPanel();
        JPanel tempPanelTwo = new JPanel();
        
        getSourceCodeButton.addActionListener(new JButtonListener());
        
        getSourceCodeButton.setBackground(Color.WHITE);
        descriptionLabel.setForeground(Color.WHITE);
        
        tempPanelOne.add(descriptionLabel);
        tempPanelOne.add(userInputTextBox);
        
        tempPanelOne.setBackground(new Color(0,0,0,125));
        tempPanelTwo.setBackground(new Color(0,0,0,125));
        
        tempPanelTwo.add(getSourceCodeButton);
        
        mainPanel.add(tempPanelOne, BorderLayout.CENTER);
        mainPanel.add(tempPanelTwo, BorderLayout.SOUTH);
    }

    private class JButtonListener implements ActionListener
    {

        public JButtonListener()
        {
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            
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
                e.printStackTrace();
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
