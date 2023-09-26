package fr.aurelien.Creapict_project.UI;

/*import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

public class SelectDirPanel
{
    private static JFrame _jf;
    private JPanel _jp;

    public SelectDirPanel()
    {
        _jf = new JFrame("Select Directory");
        _jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        _jf.setLocationRelativeTo(null);
        

        _jp = new JPanel();

        JScrollPane content = new JScrollPane(new JTree());
        content.setPreferredSize(new Dimension(100, 0));

        _jp.add(content);
        _jf.add(_jp);

        _jf.setVisible(true);
    }


    public JPanel getPanel()
    {
        return _jp;
    }
} */

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

    FileNameExtensionFilter filter = new FileNameExtensionFilter("File Java", "java");
    choose.addChoosableFileFilter(filter);

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
