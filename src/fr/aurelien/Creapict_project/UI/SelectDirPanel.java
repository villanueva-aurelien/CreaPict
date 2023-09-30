package fr.aurelien.Creapict_project.UI;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class SelectDirPanel 
{
  private File _file;
  public SelectDirPanel()
  {
    JFileChooser choose = new JFileChooser(
        FileSystemView
        .getFileSystemView()
        .getHomeDirectory()
    );

    choose.setDialogTitle("Select Directory Only");
    choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    //FileNameExtensionFilter filter = new FileNameExtensionFilter(null);
    choose.addChoosableFileFilter(null);

    // Ouvrez le fichier
    //int res = choose.showOpenDialog(null);
    // Enregistrez le fichier
    int res = choose.showSaveDialog(null);

    if (res == JFileChooser.APPROVE_OPTION)
    {
      _file = choose.getSelectedFile();
      //System.out.println(file.getAbsolutePath());
    }
  }

  public File getFile()
  {
    return _file;
  }
}
