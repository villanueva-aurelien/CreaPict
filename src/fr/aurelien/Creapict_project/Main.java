package fr.aurelien.Creapict_project;

import javax.swing.SwingUtilities;

import fr.aurelien.Creapict_project.UI.MainWindow;

public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                MainWindow mw = new MainWindow();

            }
        });
    }
}