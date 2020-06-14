package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre Charbel Abi Rizk)
 * @version (14/6/2020)
 */
public class Controleur extends JPanel {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        add(donnee);
        donnee.addActionListener(new JButtonObserver());
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        boutons.add(push);  push.addActionListener(new JButtonObserver());
        boutons.add(add);   add.addActionListener(new JButtonObserver());
        boutons.add(sub);   sub.addActionListener(new JButtonObserver());
        boutons.add(mul);   mul.addActionListener(new JButtonObserver());
        boutons.add(div);   div.addActionListener(new JButtonObserver());
        boutons.add(clear); clear.addActionListener(new JButtonObserver());
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {
        if (pile.estPleine())
            push.setEnabled(false);
        else
            push.setEnabled(true);
        if (pile.taille() <= 1) {
            add.setEnabled(false);
            sub.setEnabled(false);
            mul.setEnabled(false);
            div.setEnabled(false);
        } else {
            add.setEnabled(true);
            sub.setEnabled(true);
            mul.setEnabled(true);
            div.setEnabled(true);
        }
    }

    private Integer operande() throws NumberFormatException {
        return Integer.parseInt(donnee.getText());
    }
    
    public class JButtonObserver implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String actionCommand = event.getActionCommand();
            if (actionCommand.equals("push")) {
                try {
                    pile.empiler(operande());
                } catch (PilePleineException e) {
                    e.printStackTrace();
                }
            } else if (actionCommand.equals("[]")) {
                while (!pile.estVide()) {
                    try {
                        pile.depiler();
                    } catch (PileVideException e) {
                        e.printStackTrace();
                    }
                }
            } else if (actionCommand.equals("+")) {
                int val1 = 0;
                int val2 = 0;
                try {
                    val1 = pile.depiler();
                    val2 = pile.depiler();
                } catch (PileVideException e) {
                    e.printStackTrace();
                }
                try {
                    pile.empiler(val2 + val1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (actionCommand.equals("-")) {
                int val1 = 0;
                int val2 = 0;
                try {
                    val1 = pile.depiler();
                    val2 = pile.depiler();
                } catch (PileVideException e) {
                    e.printStackTrace();
                }
                try {
                    pile.empiler(val2 - val1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (actionCommand.equals("*")) {
                int val1 = 0;
                int val2 = 0;
                try {
                    val1 = pile.depiler();
                    val2 = pile.depiler();
                } catch (PileVideException e) {
                    e.printStackTrace();
                }
                try {
                    pile.empiler(val2 * val1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (actionCommand.equals("/")) {
                int val1 = 0;
                int val2 = 0;
                int res = 0;
                boolean divisionZero = false;
                
                try {
                    val1 = pile.depiler();
                    val2 = pile.depiler();
                    if(val1 == 0) divisionZero=true;
                } catch (PileVideException e) {
                    e.printStackTrace();
                }

                try {
                    if (divisionZero) {
                        pile.empiler(null);               
                    } else
                        pile.empiler(val2/val1);
                } catch (PilePleineException ppe) {
                    ppe.printStackTrace();
                }

            }

            actualiserInterface();
        }
    }
}