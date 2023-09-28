package fr.aurelien.Creapict_project.Creatpict;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;
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

    /**
     * This method transfer values ".getText()" ​​from jtab to _tab.
     * @param jtab
     * @param x
     * @param y
     */
    public static void setTab(JLabel[][] jtab, int x, int y)
    {
        _tab[x][y] = jtab[x][y].getText();
    }


    /**
     * This method creates a file with predefined options.
     */
    public void writeFile()
    {
        File file = new File(_filePath, _fileName);

        // créer le fichier s'il n'existe pas
        
        try
        {
            if (!file.exists()) 
                file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for(int i = 0; i < _tab.length; i++)
            {
                if(i == 0)
                    bw.write("{\n");
                for(int j = 0; j < _tab[i].length; j++)
                {
                    if(j == 0)
                        bw.write("{");                    

                    if(j == _tab[i].length-1)
                    {
                        bw.write( _tab[i][j]+ "}");
                        continue;
                    }

                    bw.write( _tab[i][j] + ",");
                }
                if(i == _tab.length-1)
                {
                    bw.write("\n};");
                    continue;
                }
                bw.write(",\n");
            }
            bw.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
