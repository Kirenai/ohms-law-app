
package me.kirenai.re.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Kirenai
 */
public class LeyOhm extends JFrame
{
    public LeyOhm()
    {           
        setBounds(450,100,400,500);
        setTitle("Ley de Ohm");
        
        LaminaOhm lamina = new LaminaOhm();
        
        cerrarButton(lamina, "Cerrar", new Color(17, 122, 120), e -> dispose());
    }
    
    public void cerrarButton(Container c, String t, Color cB, ActionListener oyente)
    {
        JButton cerrar = new JButton(t);
        cerrar.setBackground(cB);
        cerrar.setBounds(250, 400, 100, 25);
        c.add(cerrar);
        add(c);
        cerrar.addActionListener(oyente);
    }
}

class LaminaOhm extends JPanel
{
    public LaminaOhm()
    {
        setLayout(null);
        setBackground(new Color(17, 70, 122));
        
        v = new JTextField("0");
        c = new JTextField("0");
        r = new JTextField("0");
        
        setJTextField(v, new Color(36, 37, 38), new Color(108,122,137), 120, 40, new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                blockeaCaracteres(e);
            }
        });
        
        setJTextField(c, new Color(36, 37, 38), new Color(108,122,137), 120, 70, new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                blockeaCaracteres(e);
            }
        });
        
        setJTextField(r, new Color(36, 37, 38), new Color(108,122,137), 120, 100, new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                blockeaCaracteres(e);
            }
        });              
        
        colocandoJLabel("Voltaje :", Color.BLACK, 30, 40);
        colocandoJLabel("Corriente :", Color.BLACK, 30, 70);
        colocandoJLabel("Resistencia :", Color.BLACK, 30, 100);
        
        colocandoJLabelError(80, 200);
        
        setButton("Calcular", new Color(17, 122, 120), 30, 400, this::calcular);
        
        setButton("Resetear", new Color(17, 122, 120), 140, 400, e -> resetear());
    }   
    
    public void setButton(String titulo, Color cB, int x, int y, ActionListener oyente)
    {
        JButton boton = new JButton(titulo);
        boton.setBounds(x, y, 100, 25);
        boton.setBackground(cB);
        add(boton);
        boton.addActionListener(oyente);
    }
    public void calcular(ActionEvent e)
    {
        double voltaje = Double.parseDouble(v.getText());
        double corriente = Double.parseDouble(c.getText());
        double resistencia = Double.parseDouble(r.getText());

        if(voltaje > 0 && corriente > 0)
        {              
            String res = Double.toString(voltaje/corriente);
            r.setText(res+" Ω");
        }
        else if(corriente > 0 && resistencia >0)
        {
            String volt = Double.toString(corriente * resistencia);
            v.setText(volt+" V");
        }
        else if(voltaje > 0 && resistencia > 0)
        {
            String corri = Double.toString(voltaje / resistencia);
            c.setText(corri+" A");
        }      
    }
    public void resetear()
    {
        v.setText("0");
        c.setText("0");
        r.setText("0");
        error.setText("");
    }
    
    public void colocandoJLabel(String texto, Color cF, int x, int y)
    {
        JLabel label = new JLabel(texto);
        label.setForeground(cF);
        label.setBounds(x, y, 80, 20);
        add(label);
    }
    
    public void colocandoJLabelError(int x,int y)
    {
        error = new JLabel();
        error.setBounds(x, y, 250, 25);
        add(error);
    }
    
    public void setJTextField(JTextComponent jtc, Color cF, Color cB, int x, int y, KeyAdapter key )
    {       
        jtc.selectAll();
        jtc.setForeground(cF);
        jtc.setBackground(cB);
        jtc.setBounds(x, y, 200, 20);
        add(jtc);
        jtc.addKeyListener(key);
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
    private JTextField v, c, r;
    private JLabel error;
}



