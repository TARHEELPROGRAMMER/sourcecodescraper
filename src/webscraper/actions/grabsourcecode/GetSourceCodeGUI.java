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
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Joshua Powell I give permission for you to use this source code for
 * whatever purposes as long as I am credited with creating it inside the code.
 */
public class GetSourceCodeGUI extends JFrame
{

    static JFrame frame = new JFrame();
    JBackgroundPanel mainPanel = new JBackgroundPanel();
    JLabel descriptionLabel = new JLabel("Please Put The Link In The Text Box: ");
    JTextField userInputTextBox = new JTextField(20);
    JButton getSourceCodeButton = new JButton("Get Source Code");
    int isError = 0;

    public GetSourceCodeGUI()
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

        tempPanelOne.setBackground(new Color(0, 0, 0, 125));
        tempPanelTwo.setBackground(new Color(0, 0, 0, 125));

        tempPanelTwo.add(getSourceCodeButton);

        mainPanel.add(tempPanelOne, BorderLayout.CENTER);
        mainPanel.add(tempPanelTwo, BorderLayout.SOUTH);
    }

    private class JButtonListener implements ActionListener, GetWebSourceCode
    {

        public JButtonListener()
        {
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            String sourceCodeString = GetSourceCode(userInputTextBox.getText().trim());

            if (isError == 0)
            {
            new SourceToTextFileGUI(sourceCodeString);
            }
            else
            {
                new GetSourceCodeGUI();
            }
        }

        @Override
        public String GetSourceCode(String userInput)
        {
            String generate_URL = userInput;
            try
            {
                URL data = new URL(generate_URL);
                StringBuilder entireSourceCode = new StringBuilder();
                /**
                 * Proxy code start If you are working behind firewall uncomment
                 * below lines. Set your proxy server
                 */

                /* Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.0.202", 8080)); */
                /* HttpURLConnection con = (HttpURLConnection) data.openConnection(proxy); */
                /* Proxy code end */
                /* Open connection */
                /* comment below line in case of Proxy */
                URLConnection con = (URLConnection) data.openConnection();
                /* Read line by line */
                try ( /* Read webpage coontent */ BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())))
                {
                    /* Read line by line */
                    in.mark(1000000000);
                    while (in.readLine() != null)
                    {
                        entireSourceCode.append(in.readLine());
                    }
                }
                return entireSourceCode.toString();
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Please Make Sure You Have An Internet Connection And The Link You Entered Is A Proper URL And Try Again", "ERROR!", 1);
                isError = 1;
                return null;
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
