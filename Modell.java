import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import java.awt.*;

class Modell{
    private GUI gui;
    JLabel[][] ruter;
    private ArrayList<JLabel> slangekropp;
    public int score = 0;
    public int slangePosBredde = 1;
    public int slangePosLengde = 1;
    Rettning rettning = Rettning.SOR;

    
   //konstruktoer
    Modell (GUI gui) {
        this.gui = gui;
        ruter = gui.hentRuter();
        plasserSkattStart();
        slangekropp = new ArrayList<>();
    }

    
    public void plasserSkattStart(){
        for(int i = 0; i <10; i++){
            plasserSkatt();
        }
    }

    //metode for å plassere skatter på random plass
    public void plasserSkatt(){
        Random skattRandom = new Random();
        int rad = skattRandom.nextInt(12);
        int kolonne = skattRandom.nextInt(12);
        ruter[rad][kolonne].setText("$");
        gui.kallSkatt(rad, kolonne);
        
    }

    //metode for å fjerne skatt
    public boolean fjernskatt(int rad, int kol){     
        for(int c = 0; c < 12; c++){
            for(int b = 0; b <12; b++){
                if(ruter[rad][kol].getText().equals("$")){
                    ruter[rad][kol].setText(" ");
                    score++;
                    gui.leggTilLengde(score);
                    plasserSkatt();
                    slangekropp.add(0,ruter[rad][kol]);
                    
                    return true;
                } 
            }
        }
        return false;
    }

    //metode som flytter slangen
    public void flytt(Rettning rettning) throws Exception{
        if(Rettning.NORD.equals(rettning)) slangePosLengde--;
        if(Rettning.SOR.equals(rettning)) slangePosLengde++;
        if(Rettning.OST.equals(rettning)) slangePosBredde++;
        if(Rettning.VEST.equals(rettning)) slangePosBredde--;
 
        fjernskatt(slangePosLengde, slangePosBredde);  
        lagKropp(slangePosLengde, slangePosBredde, rettning);
 
    }

    //metode lager slangekropp
    public void lagKropp(int x, int y, Rettning rettning) throws Exception{
        try {
            if(Rettning.NORD.equals(rettning)) tegn(x,y);
            if(Rettning.SOR.equals(rettning)) tegn(x,y);
            if(Rettning.OST.equals(rettning)) tegn(x,y);
            if(Rettning.VEST.equals(rettning)) tegn(x,y);   
        } catch (Exception e) {
            System.exit(-1);

        }
    }

    //tegner slangekropp
    private void tegn(int x, int y){
        ruter[x][y].setBackground(Color.GREEN);
        ruter[x][y].setOpaque(true);
        slangekropp.add(0,ruter[x][y]);

        if(slangekropp.size()>1){
            slangekropp.get(slangekropp.size()-1).setBackground(Color.BLACK);
            slangekropp.remove(slangekropp.size()-1);
        }  
    }

    //metode for kollisjon mot vegg
    public boolean kollisjon(int x, int y){
        if (x <= ruter.length && y < 0 || x < 0 && y <= ruter.length  || x == ruter.length && y <= ruter.length || x <= ruter.length && y == ruter.length){ 
            System.exit(-1);
        }
        return false;
    }

    //metode for kollisjon med seg selv
    public void kollisjonMotHale(){
        for(int i = 1; i < slangekropp.size(); i++){
            if(slangekropp.get(0).getX() == slangekropp.get(i).getX() && slangekropp.get(0).getY() == slangekropp.get(i).getY()){
                System.exit(-1);
            
        }

    }
}

}
    