package fr.aurelien.Creapict_project.Creatpict;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextField;

public class CreateModel
{
    private static String[][] _tab;
    private String[] _tabStrings;
    private String _fileName, _filePath;

    public CreateModel(int indexA, int indexB, String fileName, String file)
    {
        _tab = new String[indexA][indexB];
        _fileName = fileName;
        _filePath = file;
    }

    private void applicateValue()
    {
        
    }

    public static void displayResult(int i, int j)
    {
        /* for(int i = 0; i < _tab.length; i++)
        {
            for(int j = 0; j < _tab[i].length; j++)
            {
                System.out.println(_tab[i][j]);
            }
        } */
        System.out.println(_tab[i][j]);
            
    }

    public static void setTab(JTextField[][] jtab, int x, int y)
    {
        _tab[x][y] = jtab[x][y].getText();
    }

    private void writeFile()
    {
        File file = new File(_filePath, _fileName);

        // créer le fichier s'il n'existe pas
        
        try
        {
            if (!file.exists()) 
                file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for(int i = 0; i < _tab.length-1; i++)
            {
                for(int j = 0; j < _tab[i].length-1; j++)
                {
                    if(j == _tab[i].length-2)
                        continue;

                    bw.write("{{"+ _tab[i][j] + ',');
                    bw.close();
                }

                bw.write("},\n");
                bw.close();
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
