import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GUI {
    private Kontroll kontroll;
    private JFrame vindu;
    private JPanel panel;
    private JButton slutt, hoyre, venstre, opp, ned;
    private JLabel lengde;
    int score;
    JLabel[][] ruter = new JLabel[12][12];
    JPanel ruteNett = new JPanel();

//KONSTRUKTOER
    GUI (Kontroll k) {
    kontroll = k;
        try {
            UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) { 
            System.exit(1); 
        }
        //Vinduet
        vindu = new JFrame("Slangespill");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        
        //Hovedtegneflaten
        panel = new JPanel();
        vindu.add(panel);

        //LEGGER TIL LENGDE
        lengde = new JLabel("Lengde: " + score);
        panel.add(lengde);

        //styre med TASTETRYKK FRA TASTATUR
        class KnappeTrykk implements KeyListener{
            @Override
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();
    
                if(key == KeyEvent.VK_LEFT){
                    kontroll.settRettning(Rettning.VEST);
                }
                if(key == KeyEvent.VK_RIGHT){
                    kontroll.settRettning(Rettning.OST);
                }
                if(key == KeyEvent.VK_UP){
                    kontroll.settRettning(Rettning.NORD);
                }
                if(key == KeyEvent.VK_DOWN){
                    kontroll.settRettning(Rettning.SOR);
                }
            }
    
            @Override
            public void keyTyped(KeyEvent e) { }
    
            @Override
            public void keyReleased(KeyEvent e) { }
        }

        //Trykknappen EXIT
        slutt = new JButton("Exit");
        class Stopper implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
                kontroll.avslutt();
            }
        }
        slutt.addActionListener(new Stopper());

        //Trykknappen HOYRE
        hoyre = new JButton("Hoyre");
        class Hoyre implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
                kontroll.settRettning(Rettning.OST);
            }
        }
        hoyre.addActionListener(new Hoyre());

        //Trykknappen VENSTRE
        venstre = new JButton("Venstre");
        class Venstre implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
                kontroll.settRettning(Rettning.VEST);
            }
        }
        venstre.addActionListener(new Venstre());

        //Trykknappen OPP
        opp = new JButton("Opp");
        class Opp implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
                kontroll.settRettning(Rettning.NORD);
            }
        }
        opp.addActionListener(new Opp());

        //Trykknappen NED
        ned = new JButton("Ned");
        class Ned implements ActionListener {
            @Override
            public void actionPerformed (ActionEvent e) {
                kontroll.settRettning(Rettning.SOR);
            }
        }
        ned.addActionListener(new Ned());
       
        //Legg alle komponentene på tegneflaten
        panel.add(ned);
        panel.add(opp);
        panel.add(venstre);
        panel.add(hoyre);
        panel.add(slutt);

        panel.setBackground(Color.BLACK);
        
        vindu.addKeyListener(new KnappeTrykk());

        //Klargjør GUI-vinduet
        vindu.pack(); 
        vindu.setSize(500,500);
        vindu.setLocationRelativeTo(null);
        vindu.setFocusable(true);
        vindu.setVisible(true);

        //LAGER RUTENETT
        ruteNett.setLayout(new GridLayout(12,12));
        for(int ruteLengde = 0; ruteLengde <12; ruteLengde++){
            for(int kolonneLengde = 0; kolonneLengde < 12; kolonneLengde++){
                JLabel rute = new JLabel(" ");
                rute.setPreferredSize(new Dimension(30, 30));
                rute.setHorizontalAlignment(JLabel.CENTER);
                rute.setVerticalAlignment(JLabel.CENTER);
                ruter[ruteLengde][kolonneLengde] = rute;
                ruteNett.add(rute);
            }
        }
        panel.add(ruteNett);
        ruteNett.setBackground(Color.BLACK);

    }

    //Kaller på skatt
    public void kallSkatt(int rad, int kolonne){
        ruter[rad][kolonne].setForeground(Color.RED);
        ruter[rad][kolonne].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 23));
        ruter[rad][kolonne].setText("$");
    }

    //Lengde på slangen
    public void leggTilLengde(int teller){
        lengde.setText("lengde " + teller);
    }
 

    public JLabel[][] hentRuter(){
        return ruter;
    }
}

