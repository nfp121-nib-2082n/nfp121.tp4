package question3;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.Observable;
import java.util.Observer;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre Charbel Abi Rizk )
 * @version (14/6/2020)
 */
public class Vue extends JPanel implements Observer {

    private JLabel etatPile;
    private PileModele<Integer> pile;

    public Vue(PileModele<Integer> pile) {
        super();
        this.pile = pile;
        this.etatPile = new JLabel("entrez des nombres entiers");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        this.etatPile.setEnabled(false);
        add(this.etatPile);
        setBackground(Color.green);
        pile.addObserver(this);
        
    }

    public void update(Observable obs, Object arg) {
        etatPile.setText(pile.toString()); 
    }

}
