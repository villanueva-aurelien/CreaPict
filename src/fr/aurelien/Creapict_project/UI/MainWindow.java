package fr.aurelien.Creapict_project.UI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.*;

public class MainWindow
{
    private static JFrame _jf;
    private static JPanel _currentPanel;
    private static int _sizeX = 1000;
    private static int _sizeY = 1000;

    public MainWindow()
    {
        _jf = new JFrame("Main Menu");
        //_jf.setResizable(false);
        _jf.setSize(_sizeX, _sizeY);

        _jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        _jf.setLocationRelativeTo(null);

        _jf.addWindowListener(new WindowAdapter()
       {
           @Override
           public void windowClosing(WindowEvent e)
           {
               int option = JOptionPane.showConfirmDialog(
                       _jf,
                       "Êtes-vous sûr de vouloir fermer la fenêtre ?",
                       "Confirmation",
                       JOptionPane.YES_NO_OPTION);

               if(option == JOptionPane.YES_OPTION)
               {
                   // mettre ici la commande a executé avant fermeture!
                   //_jf.dispose();
                   System.exit(0);
               }
           }
       });

       _jf.setVisible(true);

       //SelectDirPanel dp = new SelectDirPanel();
       SettingPanel settingP = new SettingPanel();
       //ResultPanel settingP = new ResultPanel(25, 25);
       _jf.add(settingP);//.getPanel());
       _currentPanel = settingP;//.getPanel();
       _jf.pack();
    }

    /**
     * This method update Jpanel
     * Remove last Jpanel and add new Jpanel
     * Repaint and pack JFrame
     * @param j
     */
    public static void updateJFrame(JPanel j)
    {
        if(j == null)
            return;
        
        _jf.remove(_currentPanel);

        _jf.add(j);
        _currentPanel = j;
        _jf.repaint();
        _jf.pack();
    }

}
