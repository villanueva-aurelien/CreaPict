package fr.aurelien.Creapict_project.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettingPanel extends JPanel
{

    private JLabel _selectDir, _nameFile, _numberRow, _numberCol;
    private JTextField _file, _row, _col;
    private int _resultVerif;

    private SelectDirPanel _dirSelected;
    private String[] _tabSrStrings = new String[2];
    private Boolean _validateVerif = false;
    
    private GridBagConstraints _constraints = new GridBagConstraints();

    private String _valueA, _valueB;

    public SettingPanel()
    {
        this.setLayout(new GridLayout(5, 1));
        _selectDir = new JLabel("Select Dir");
        _nameFile = new JLabel("File name .txt");
        _numberRow = new JLabel("Number of Row");
        _numberCol = new JLabel("Number of Col");

        _file = new JTextField();
        _row = new JTextField();
        _col = new JTextField();

        JButton select = new JButton("...");

        select.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                _dirSelected = new SelectDirPanel();
            }
        });

        this.add(_selectDir);
        this.add(select);
        this.add(_nameFile);
        this.add(_file);
        
        this.add(_numberRow);
        this.add(_row);
        this.add(_numberCol);
        this.add(_col);
        
        
        JButton jbValidate = new JButton("OK");
        
        _constraints.fill = GridBagConstraints.HORIZONTAL;
        _constraints.ipady = 0;       //reset to default
        _constraints.weighty = 1.0;   //request any extra vertical space
        //c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        _constraints.insets = new Insets(10,0,0,0);  //top padding
        _constraints.gridx = 1;       //aligned with button 2
        //c.gridwidth = 2;   //2 columns wide
        _constraints.gridy = 8;       //third row
        this.add(jbValidate, _constraints);


        jbValidate.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                int a = 0;
                int b = 0;
                try
                {
                    if(!_row.getText().isEmpty())
                        a = Integer.parseInt(_row.getText());
                    if(!_col.getText().isEmpty())
                        b = Integer.parseInt(_col.getText());

                    _valueA = _row.getText();
                    _valueB = _col.getText();

                    if(isValadateNumber(_valueA) == false)
                    {
                        _resultVerif = 1;
                    }
                    if(isValadateNumber(_valueB) == false)
                    {
                        _resultVerif = _resultVerif+2;
                    }

                    if(checkResultVerif() == true)
                        return;
                    
                }
                catch (NumberFormatException ex)
                {
                    ex.printStackTrace();
                }  
                        
                convertNameAndPath();
                //ResultPanel r = new ResultPanel(a, b, _tabSrStrings); // Ancienne version avec des JTextField
                Result r = new Result(a, b, _tabSrStrings);
                updateMainApp(r.getPanel());
            }    
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(jbValidate, BorderLayout.WEST);
        this.add(buttonPanel);
    }
    
    private Boolean checkResultVerif()
    {
        String a = null;
        String b = null;

        if(_resultVerif == 3)
        {
            a = "- La donnée entrée pour les lignes n'est pas valide !";
            b = "- La donnée entrée pour les colonnes n'est pas valide !";
            _validateVerif = true;
        }
        if(_resultVerif == 2)
        {
            b = "- La donnée entrée pour les colonnes n'est pas valide !";
            _validateVerif = true;
        }
        if(_resultVerif == 1)
        {
            a = "- La donnée entrée pour les lignes n'est pas valide !";
            _validateVerif = true;
        }

        displayError(a, b);

        return _validateVerif;
    }

    private void convertNameAndPath()
    {
        _tabSrStrings[0] = _file.getText() + ".txt";
        _tabSrStrings[1] = _dirSelected.getFile().getPath();
    }

    private void displayError(String a, String b)
    {
        if(a == null && b == null)
            return;

        JDialog jd = new JDialog();
        jd.setLayout(new GridLayout(3, 1));
        JLabel jl = new JLabel();
        JLabel jl1 = new JLabel();

        JButton close = new JButton("Close");

        close.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                jd.dispose();
            }
        });

        jd.setLocationRelativeTo(null);
        jd.setBounds(900, 500, 400, 300);

        if(a != null && b == null)
        {
             jl.setText(a);
             jd.add(jl);
        }
        if(b != null && a == null)
        {
            jl1.setText(b);
            jd.add(jl1);
        }
        if(a != null && b != null)
        {
            jl.setText(a);
            jd.add(jl);
            jl1.setText(b);
            jd.add(jl1);
        }

        jd.add(close);
        jd.setVisible(true);
    }

    private boolean isValadateNumber(String number)
    {
        //String regExp = "^(0[0-9])$"; 
        String regExp =  "^[0-9]+$";
        return number.matches(regExp);
    }

    public void updateMainApp(JPanel panel)
    {
        MainWindow.updateJFrame(panel);
    }    
}
