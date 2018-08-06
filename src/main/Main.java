
package main;

import java.util.Scanner;

import parcareimpl.Masina;
import parcareimpl.NumarInmatriculare;
import parcareimpl.Parcare;
import ui.ParcareFrame;

public class Main {

  private static Scanner scan = new Scanner(System.in);
  private static int varianta = 0;

  public static void main(String[] args) {

    /*NumarInmatriculare nrInmatriculare3=new NumarInmatriculare();
    
    System.out.println("Introduceti judetul, numarul si seria de inmatriculare: ");
    String judetul=scan.nextLine();
    if(NumarInmatriculare.verificaJudet(judetul))
    	continue;
    else
    	break;
    int numarul=scan.nextInt();
    String seria=scan.nextLine();*/

    Masina[] masini = new Masina[6];

    NumarInmatriculare nrInmatriculare1 = new NumarInmatriculare("MM", 42, "ALM");
    NumarInmatriculare nrInmatriculare2 = new NumarInmatriculare("TM", 12, "213");
    NumarInmatriculare nrInmatriculare3 = new NumarInmatriculare("B", 34, "A63");
    NumarInmatriculare nrInmatriculare4 = new NumarInmatriculare("BV", 38, "7A3");
    NumarInmatriculare nrInmatriculare5 = new NumarInmatriculare("MM", 14, "87G");
    NumarInmatriculare nrInmatriculare6 = new NumarInmatriculare("B", 49, "ARA");

    Masina masina1 = new Masina("Dacia", "Alb", nrInmatriculare1, 0, 100, 4);
    Masina masina2 = new Masina("Lamborgini", "Albastru", nrInmatriculare2, 0, 250, 2);
    Masina masina3 = new Masina("Opel", "Negru", nrInmatriculare3, 0, 180, 4);
    Masina masina4 = new Masina("Ferrari", "Rosu", nrInmatriculare4, 0, 180, 4);
    Masina masina5 = new Masina("Fiat", "Negru", nrInmatriculare5, 0, 180, 4);
    Masina masina6 = new Masina("Fiat", "Alb", nrInmatriculare6, 0, 180, 4);

    masini[0] = masina1;
    masini[1] = masina2;
    masini[2] = masina3;
    masini[3] = masina4;
    masini[4] = masina5;
    masini[5] = masina6;

    /*for (int i=0; i<masini.length; i++){
    	System.out.println(masini[i]);
    }*/
    //System.out.println(nrInmatriculare1+" "+nrInmatriculare2);

    /*masina1.accelereaza(20);
    System.out.print("Cat accelereaza viteza? ");
    int nrViteza=scan.nextInt();
    masina1.accelereaza(nrViteza);
    System.out.println(masina1);*/

    Parcare parcare = new Parcare("1B", 35, 35, 0.2);
    parcare.adaugare(masina1);
    parcare.adaugare(masina2);
    parcare.adaugare(masina3);
    parcare.adaugare(masina4);
    parcare.adaugare(masina5);
    parcare.adaugare(masina6);

    // MeniuParcare m = new MeniuParcare(parcare);

    //m.meniu();
    //parcare.toHTML();

    ParcareFrame p = new ParcareFrame();

  }
}