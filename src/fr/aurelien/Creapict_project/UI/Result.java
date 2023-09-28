package fr.aurelien.Creapict_project.UI;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import fr.aurelien.Creapict_project.Creatpict.CreateModel;

public class Result
{
    private JPanel _complet;
    private JPanel _left;
    private JPanel _center;
    private JPanel _uper;
    private JPanel _lower;

    private String[] _tabStrings;
    private int _sizeX;
    private int _sizeY;
    private int _width = 15;
    private int _height = 15;
    private JLabel [][] _jTab;
    private JLabel[] _jlTab;
    private Color[] _color = {null, Color.BLACK, Color.WHITE, Color.YELLOW, Color.RED, Color.BLUE };
    private Color _defaultColor = null;
    private String _filePath, _fileName;
    private String _indexColor;

    private GridBagConstraints _constraints = new GridBagConstraints();

    public Result(int a, int b, String[] tabStrings)
    {
        _sizeX = a;
        _sizeY = b;
        _tabStrings = tabStrings;
        _fileName = _tabStrings[0];
        _filePath = _tabStrings[1];
        _jTab = new JLabel[_sizeX][_sizeY];
        _jlTab = new JLabel[_color.length];

        _complet = new JPanel(new BorderLayout());
        
        _left = new JPanel();
        _left.setLayout(new GridLayout(3, 2));

        _uper = new JPanel();
        _uper.setLayout(new FlowLayout());

        _uper.setLayout(new GridLayout(1, 6));

        _lower= new JPanel();
        _lower.setLayout(new BorderLayout());

        _complet.add(_uper, BorderLayout.NORTH);
        _complet.add(_lower, BorderLayout.SOUTH);
        
        
        
        
        createJlabelColor();

        _center = new JPanel();
        _center.setLayout(new GridLayout(_sizeX, _sizeY));
        _complet.add(_center, BorderLayout.CENTER);

        JButton jbValidate = new JButton("OK");
        
        _constraints.fill = GridBagConstraints.HORIZONTAL;
        _constraints.ipady = 0;       //reset to default
        _constraints.weighty = 1.0;   //request any extra vertical space
        //c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        _constraints.insets = new Insets(10,0,0,0);  //top padding
        _constraints.gridx = 1;       //aligned with button 2
        //c.gridwidth = 2;   //2 columns wide
        _constraints.gridy = 1;       //third row
        _lower.add(jbValidate, BorderLayout.CENTER);
        
        jbValidate.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {               
                CreateModel c = new CreateModel(_sizeX, _sizeY, _fileName, _filePath);
                
                for(int i = 0; i < _sizeX; i++)            
                {
                    for(int j = 0; j < _sizeY; j++)
                    {
                        if(_jTab[i][j] == null)
                            continue;

                        CreateModel.setTab(_jTab,i,j);
                    }
                }
                c.writeFile();
                updateMainApp(new SettingPanel());
            }   
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(jbValidate, BorderLayout.CENTER);
        _lower.add(buttonPanel);
        createGrid();

        _complet.repaint();
        _complet.updateUI();
    }

    public void createGrid()
    {
        int indexX = 0;
        int indexY = 0;

        for( int i = 0; i < _sizeX; i++)
        {
            for(int j = 0; j < _sizeY; j++)
            {
                _jTab[i][j] = new JLabel("0");
                _jTab[i][j].setPreferredSize(new Dimension(_width, _height));
                _jTab[i][j].setOpaque(true); // Permet d'appliquer la couleur de fond
                _jTab[i][j].setBackground(_defaultColor);
                _jTab[i][j].setBorder(new LineBorder(Color.BLACK));
                _jTab[i][j].setVisible(true);

                _center.add(_jTab[i][j]);

                indexX = indexX+1;

                _jTab[i][j].addMouseListener(new MouseListener()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        JLabel sourceLabel = (JLabel) e.getSource();
                        if(SwingUtilities.isLeftMouseButton(e))
                        {
                            sourceLabel.setBackground(_defaultColor);
                            sourceLabel.setText(_indexColor);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                       
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                       
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        
                    }
                });
            }

            indexY = indexY+1;
            indexX = 0;
        }
    }

    private void createJlabelColor()
    {
        int index = 0;
        String text = "0";
        for(int i = 0; i < _jlTab.length; i++)
        {
            _jlTab[i] = new JLabel(text);
            _jlTab[i].setPreferredSize(new Dimension(20, 20));

            if(i != 0)
            {
                _jlTab[i].setBackground(_color[i]);
            }
            else
            {
                _jlTab[i].setBackground(null);
            }
            if(_color[i] == Color.BLACK || _color[i] == Color.BLUE)
            {
                _jlTab[i].setForeground(Color.white);
            }
            _jlTab[i].setOpaque(true);
            _uper.add(_jlTab[i]);

            index = index + 1;
            text = Integer.toString(index);

            _jlTab[i].addMouseListener(new MouseListener()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    JLabel sourceLabel = (JLabel) e.getSource();
                    if(SwingUtilities.isLeftMouseButton(e))
                    {
                        _defaultColor = sourceLabel.getBackground();
                        _indexColor = sourceLabel.getText();
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    
                }
            });
        }
    }
    
    public void updateMainApp(JPanel panel)
    {
        MainWindow.updateJFrame(panel);
    }    

    public JPanel getPanel()
    {
        return _complet;
    }
}
