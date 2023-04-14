
package me.kirenai.re.view;

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Kirenai
 */
public class AbriendoMarcos extends JFrame
{
    public AbriendoMarcos()
    {
        setLayout(null);
        
        setTitle("Menu Principal");
        setBounds(600, 100, 400, 400);
        
        leyOhm = new JButton("Ley de Ohm");
        serieParalelo = new JButton("Calcular Resistencia");
        
        leyOhm.setBounds(30, 30, 130, 25);
        serieParalelo.setBounds(175, 30, 180, 25);
        
        leyOhm.addActionListener(new AbrirMarcos());
        serieParalelo.addActionListener(new AbrirMarcos());
        
        add(leyOhm);
        add(serieParalelo);
    }
    private class AbrirMarcos implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(e.getSource()==leyOhm)
            {
                LeyOhm marco = new LeyOhm();
                marco.setVisible(true);
            }
            else if(e.getSource()==serieParalelo)
            {
                ResistenciaSerieParalelo marco2 = new ResistenciaSerieParalelo();
                marco2.setVisible(true);
            }
        }      
    }
    private JButton leyOhm, serieParalelo;
}
