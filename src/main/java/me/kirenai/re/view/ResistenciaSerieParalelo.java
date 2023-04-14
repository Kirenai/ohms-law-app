
package me.kirenai.re.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Kirenai
 */
public class ResistenciaSerieParalelo extends JFrame
{
    public ResistenciaSerieParalelo()
    {
        setTitle("Resistencia en Serie y Paralelo");
        setBounds(450,100,400,500);
        
        LaminaResistencia lamina = new LaminaResistencia();
        
        cerrarButton(lamina, "Cerrar", new Color(17,122,120), e -> dispose());
    }
    
    public void cerrarButton(Container c, String text, Color cB, ActionListener oyente)
    {
        JButton boton = new JButton(text);
        boton.setBounds(145, 380, 100, 25);
        boton.setBackground(cB);
        c.add(boton);
        add(c);
        boton.addActionListener(oyente);
    }
}

class LaminaResistencia extends JPanel
{
    public LaminaResistencia()
    {
        setLayout(null);
        setBackground(new Color(17, 70, 122));
        
        resistencia1 = new JTextField("0");
        resistencia2= new JTextField("0");
        resultadoSerie = new JTextField();
        resultadoParalelo = new JTextField();
        
        colocandoJTextField(resistencia1, new Color(108,122,137), 132, 70, 120, 20, new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                blockeaCaracteres(e);
            }
        });
        
        colocandoJTextField(resistencia2, new Color(108,122,137), 132, 100, 120, 20, new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                blockeaCaracteres(e);
            }
        });
        
        colocandoJTextField(resultadoSerie, new Color(108,122,137), 30, 250, 155, 30, new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                blockeaCaracteres(e);
            }
        });
        
        colocandoJTextField(resultadoParalelo, new Color(108,122,137), 200, 250, 155, 30, new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                blockeaCaracteres(e);
            }
        });  
        
        colocandoJLabel("Resistencia", Color.BLACK, 160, 30, 100, 25);
        colocandoJLabel("R1", Color.BLACK, 100, 70,25, 25);
        colocandoJLabel("R2", Color.BLACK, 100, 100, 25, 25);
        colocandoJLabel("Resultado Serie", Color.BLACK, 65, 230, 125, 25);
        colocandoJLabel("Resultado Paralelo", Color.BLACK, 225, 230, 250, 25);                  
        colocandoJLabelError(80, 300);
        
        creandoButton("Calcular Resistencia", new Color(17, 122,120), 30,350,160,25, e -> calculoEvento());
        
        creandoButton("Resetear", new Color(17, 122, 120), 205,350,150,25, e -> reset());
    }
    
    public void creandoButton(String texto, Color cB, int x, int y, int w, int h, ActionListener oyente)
    {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, w, h);
        boton.setBackground(cB);
        add(boton);
        boton.addActionListener(oyente);
    }
    
    public void colocandoJLabel(String text, Color cF, int x, int y, int w, int h)
    {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setForeground(cF);
        add(label);
    }
    
    public void colocandoJTextField(JTextComponent c, Color cB, int x, int y, int w, int h, KeyAdapter key)
    {       
        c.selectAll();
        c.setBounds(x, y, w, h);
        c.setBackground(cB);
        add(c);
        c.addKeyListener(key);    
    }
 
    public void calculoEvento()
    {
        double resSer1 = Double.parseDouble(resistencia1.getText());
        double resSer2 = Double.parseDouble(resistencia2.getText());

        if(resSer1>0 || resSer2>0)
        {
            double resistenciaTotalSerie = (resSer1+resSer2);
            double resistenciaTotalParalelo = (1/(1/resSer1+1/resSer2));

            String resultadoSerieTotal = Double.toString(resistenciaTotalSerie);
            String resultadoParaleloTotal = Double.toString(resistenciaTotalParalelo);

            resultadoSerie.setText(resultadoSerieTotal+" Ω");
            resultadoParalelo.setText(resultadoParaleloTotal+" Ω");
        }
    }
    
    public void reset()
    {
        resistencia1.setText("0");
        resistencia2.setText("0");
        resultadoSerie.setText("");
        resultadoParalelo.setText("");
        error.setText("");
    }
    
    public void colocandoJLabelError(int x,int y)
    {
        error = new JLabel();
        error.setBounds(x, y, 250, 25);
        add(error);
    }
    
    public void blockeaCaracteres(KeyEvent e)
    {
        char ca = e.getKeyChar();
        if(!((ca >= '0') && (ca <= '9') || (ca == KeyEvent.VK_BACK_SPACE) || (ca == KeyEvent.VK_DELETE) || (ca == KeyEvent.VK_PERIOD)))
        {
            error.setText("Ingresar solamente valores númericos");
            error.setForeground(Color.BLACK);
            e.consume();
        }          
        else
        {
           error.setText(""); 
        }
    }
    private JTextField resistencia1, resistencia2, resultadoSerie, resultadoParalelo;
    JLabel error;
}
