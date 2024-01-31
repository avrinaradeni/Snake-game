import javax.swing.JLabel;

public class Kontroll {
    GUI gui;
    private Modell modell;
    private Thread traad;
    private Rettning rettning = Rettning.SOR;
    private JLabel[][] ruter;

//konstruktoer
    public Kontroll() {
        gui = new GUI(this);
        modell = new Modell(gui);
        ruter = gui.hentRuter();
        traad = new Thread(new Teller());
        traad.start();
    }

//avslutt metode
    void avslutt () {
        System.exit(0);
    }
    
//En indre klasse for traad 
    class Teller implements Runnable {
        @Override
        public void run(){
            while(true){
                try {
                    Thread.sleep(280);
                } catch (Exception e) {
                    System.exit(-1);
                }
                try {
                    modell.flytt(rettning);
                    modell.kollisjon(modell.slangePosLengde, modell.slangePosBredde);
                } catch (Exception e) {
                    System.exit(-1);
                }
                }
            }
        }

//setter rettning ved hjelp av rettning klassen
    public void settRettning(Rettning r){
        this.rettning = r;
    }

}