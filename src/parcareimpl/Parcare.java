
package parcareimpl;

import java.io.File;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

import history.Istoric;
import history.TimpData;
import xmlparser.XMLParser;

public class Parcare implements Serializable {

  private static final long serialVersionUID = 1L;
  private String id;
  private int capacitate = 5;
  private Masina masina;
  private int capacitateMaxima;
  private double taxa;
  protected ArrayList<Masina> masini = new ArrayList<Masina>();
  private TimpData dataIntrare = new TimpData();
  private TimpData dataIesire = new TimpData();
  private TimpData timp;
  private TimpData t = new TimpData();
  protected ArrayList<Istoric> istoric = new ArrayList<Istoric>();
  protected ArrayList<NumarInmatriculare> numereInmatriculare;

  public Parcare(String id, int capacitate, int locuriLibere, double taxa) {

    this.id = id;
    this.capacitate = capacitate;
    this.capacitateMaxima = locuriLibere;
    this.taxa = taxa;
    //setTaxa(50);
    XMLParser xmlParser = new XMLParser();
    xmlParser.getCapacitateFromXml(this);

  }

  public Parcare() {

  }

  public void adaugare(Masina m) {
    if (masini.size() < capacitate) {
      masini.add(m);
      this.dataIntrare = t.timp();
      System.out.println(dataIntrare);
      this.capacitateMaxima--;
      this.dataIesire = new TimpData();
      Istoric ist1 = new Istoric(m, dataIntrare, dataIesire);
      istoric.add(ist1);
    } else if (masini.contains(m)) {
      dataIntrare = null;
      System.out.println(" Masina a fost adaugata deja.");
    } else {
      dataIntrare = null;
      System.out.println("Parcare plina !");
    }
  }

  public void scoate(Masina m) {
    if (masini.contains(m)) {
      masini.remove(m);
      this.dataIesire = t.timp();
      System.out.println(dataIesire);
      this.capacitateMaxima++;
      for (int i = 0; i < istoric.size(); i++) {
        if (istoric.get(i).getMasina().equals(m))
          istoric.remove(istoric.get(i));
      }
      Istoric ist2 = new Istoric(m, dataIntrare, dataIesire);
      istoric.add(ist2);

    } else {
      dataIesire = null;
      System.out.println("Masina nu se afla in parcare.");
    }
  }

  public double calculTaxa() {
    double taxaFinala = taxa;
    this.timp = t.timpScurs(dataIesire, dataIntrare);
    if (timp.getMinute() > 0)
      taxaFinala = taxaFinala * timp.getMinute();
    else if (timp.getOra() > 0)
      taxaFinala = taxaFinala * timp.getOra() * 60;
    else if (timp.getZiua() > 0)
      taxaFinala = taxaFinala * timp.getZiua() * 60 * 24;
    else
      taxaFinala = 0;
    return taxaFinala;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return this.id;
  }

  public void setCapacitate(int capacitate) {
    this.capacitate = capacitate;
  }

  public int getCapacitate() {
    return this.capacitate;
  }

  public void setMasina(Masina masina) {
    this.masina = masina;
  }

  public Masina getMasina() {
    return this.masina;
  }

  public void setLocuriLibere(int locuriLibere) {
    this.capacitateMaxima = locuriLibere;
  }

  public int getLocuriLibere() {
    return this.capacitateMaxima;
  }

  public void setTaxa(double taxa) {
    this.taxa = taxa;
  }

  public double getTaxa() {
    return this.taxa;
  }

  public ArrayList<Masina> getMasini() {
    return this.masini;
  }

  public Masina extrageMasina(NumarInmatriculare nr) {
    for (int i = 0; i < masini.size(); i++) {
      if ((masini.get(i)).getNumarInmatriculare().equals(nr)) {
        return masini.get(i);
      }
    }
    return new Masina();
  }

  public boolean existaMasina(NumarInmatriculare nr) {
    for (int i = 0; i < masini.size(); i++) {
      if ((masini.get(i)).getNumarInmatriculare().equals(nr))
        return true;
    }
    return false;
  }

  public void afisareArray() {
    for (int i = 0; i < this.masini.size(); i++) {
      System.out.print(masini.get(i) + " ");
    }
  }

  public ArrayList<Istoric> getIstoric() {
    return this.istoric;
  }

  public ArrayList<NumarInmatriculare> getNumereInmatriculare() {
    for (int i = 0; i < this.masini.size(); i++) {
      numereInmatriculare.add(masini.get(i).nrInmatriculare);
    }
    return numereInmatriculare;
  }

  public NumarInmatriculare[] getNumereInmatriculareArray() {
    NumarInmatriculare[] numere = new NumarInmatriculare[masini.size()];
    for (int i = 0; i < this.masini.size(); i++) {
      numere[i] = masini.get(i).nrInmatriculare;
    }
    return numere;
  }

  public ArrayList<NumarInmatriculare> cautaNumere(String t) {
    NumarInmatriculare[] numereInmatriculare = getNumereInmatriculareArray();
    ArrayList<NumarInmatriculare> arrayList = new ArrayList<>();
    ArrayList<NumarInmatriculare> arrayList0 = new ArrayList<>();
    for (NumarInmatriculare nr : numereInmatriculare) {
      if (t.length() > 0 && t.length() <= nr.toString().length() && t.toUpperCase().equals(nr.toString().toUpperCase().substring(0, t.length()))) {
        arrayList.add(nr);
      } else {
        arrayList0.add(nr);
      }
    }
    if (arrayList.size() == 0) {
      return arrayList0;
    } else
      return arrayList;

  }

  public NumarInmatriculare[] cautaNumereInmatriculare(String text) {
    ArrayList<NumarInmatriculare> arrayList = cautaNumere(text);
    NumarInmatriculare[] string = new NumarInmatriculare[arrayList.size()];
    for (int i = 0; i < arrayList.size(); i++) {
      string[i] = arrayList.get(i);
    }
    return string;
  }

  public String getData(String text) {

    String[] nume = text.split(", ");
    for (Istoric i : istoric) {
      if (i.getMasina().getNume().equals(nume[0]) && i.getMasina().getNumarInmatriculare().toString().equals(nume[1])) {
        return i.toString();
      }
    }
    return null;
  }

  public void toFile() {

    File file = new File("info.txt");
    try {
      PrintWriter pw = new PrintWriter(file);

      for (int i = 0; i < istoric.size(); i++) {
        pw.println(istoric.get(i).getMasina() + "\n");
        pw.println("Timp de intrare: " + istoric.get(i).getTimpIntrare());
        pw.println("Timp de iesire: " + istoric.get(i).getTimpIesire());

      }
      pw.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void toHTML() {
    File file = new File("situatie.html");
    try {
      PrintWriter pw = new PrintWriter(file);

      pw.println("<html>");
      pw.println("<head> <title> Istoric parcare </title>" + "<style> body { background-image: url('fundal.gif'); }" + "th,td {text-align: center; padding: 8px;}" + "th{background-color: #4CAF50; color: white;}" + "td{background-color: white} </style> </head>");
      pw.println("<body> <h1 style='text-align:center; background-color:MediumSeaGreen; font-family:verdana; font-size:110%; color:white;'> Tabel istoric parcare </h1> <br> <br> <table align='center', border=\"1\">");
      //pw.println("<th> </th>");
      pw.println("<tr>");
      pw.println("<th>" + "Nume" + "</td>");
      pw.println("<th>" + "Culoare" + "</td>");
      pw.println("<th>" + "Numar de inmatriculare" + "</td>");
      pw.println("<th>" + "Viteza" + "</td>");
      pw.println("<th>" + "Viteza maxima" + "</td>");
      pw.println("<th>" + "Numar de usi" + "</td>");
      pw.println("<th>" + "Data parcarii" + "</td>");
      pw.println("<th>" + "Data iesirii din parcare" + "</td>");
      pw.println("</tr>");
      for (int i = 0; i < istoric.size(); i++) {
        pw.println("<tr>");
        pw.println("<td>" + istoric.get(i).getMasina().getNume() + "</td>");
        pw.println("<td>" + istoric.get(i).getMasina().getCuloare() + "</td>");
        //pw.println("<td>"+System.out.printf("  %s  ",istoric.get(i).getMasina().getNumarInmatriculare())+"</td>");
        //pw.println("<td>"+String.format("|   \t	\t\t\t\t%s\t   |", istoric.get(i).getMasina().getNumarInmatriculare())+"</td>");
        //pw.println("<td>"+String.format("|%-40s|", istoric.get(i).getMasina().getNumarInmatriculare())+"</td>");
        pw.println("<td>" + istoric.get(i).getMasina().getNumarInmatriculare() + "</td>");
        pw.println("<td>" + istoric.get(i).getMasina().getViteza() + "</td>");
        pw.println("<td>" + istoric.get(i).getMasina().getVitezaMaxima() + "</td>");
        pw.println("<td>" + istoric.get(i).getMasina().getNrUsi() + "</td>");
        pw.println("<td>" + istoric.get(i).getTimpIntrare() + "</td>");
        pw.println("<td>" + istoric.get(i).getTimpIesire() + "</td>");
        pw.println("</tr>");
      }

      pw.println("</table>");
      pw.println("</html>");

      pw.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean equals(Object p) {

    if (p instanceof Parcare) {
      Parcare masinaNr = (Parcare) p;
      if (this.id.equals(masinaNr.getId()) && this.capacitate == masinaNr.getCapacitate() && this.masina.equals(masinaNr.getMasina()) && this.capacitateMaxima == masinaNr.getLocuriLibere() && this.taxa == masinaNr.getTaxa())
        return true;
      else
        return false;
    }
    return false;
  }

  @Override
  public String toString() {
    return "id: " + this.id + "; capacitate: " + this.capacitate + "; masina\n " + masini + "; locuri libere: " + this.capacitateMaxima + "; taxa/ora: " + this.taxa * 60;
  }
}