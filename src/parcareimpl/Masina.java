
package parcareimpl;

import java.io.Serializable;

public class Masina implements Serializable {

  private String nume;
  private String culoare;
  public NumarInmatriculare nrInmatriculare;
  private int viteza = 0;
  private int vitezaMaxima;
  private int nrUsi;

  public Masina(String nume, String cul, NumarInmatriculare nrInmatriculare, int viteza, int vitezaMaxima, int nrUsi) {

    this.nume = nume;
    this.culoare = cul;
    this.nrInmatriculare = nrInmatriculare;
    this.viteza = viteza;
    this.vitezaMaxima = vitezaMaxima;
    this.nrUsi = nrUsi;
  }

  public Masina(String nume, String cul, NumarInmatriculare nrInmatriculare, int nrUsi) {

    this.nume = nume;
    this.culoare = cul;
    this.nrInmatriculare = nrInmatriculare;
    this.nrUsi = nrUsi;
  }

  public Masina() {
  }

  public void accelereaza(int vitezaAccelerata) {
    if (vitezaAccelerata > this.vitezaMaxima)
      System.out.println("Viteza depaseste viteza masinii.");
    else
      this.viteza = vitezaAccelerata;

    //setViteza(vitezaOptima);
  }

  public void franeaza() {
    setViteza(0);
  }

  public void setNume(String num) {
    this.nume = num;
  }

  public String getNume() {
    return this.nume;
  }

  public void setCuloare(String cul) {
    this.culoare = cul;
  }

  public String getCuloare() {
    return this.culoare;
  }

  public void setNumarInmatriculare(NumarInmatriculare numarInmatriculare) {
    this.nrInmatriculare = numarInmatriculare;
  }

  public NumarInmatriculare getNumarInmatriculare() {
    return this.nrInmatriculare;
  }

  public void setViteza(int viteza) {
    this.viteza = viteza;
  }

  public int getViteza() {
    return this.viteza;
  }

  public void setVitezaMaxima(int vitezaMax) {
    this.vitezaMaxima = vitezaMax;
  }

  public int getVitezaMaxima() {
    return this.vitezaMaxima;
  }

  public void setNrUsi(int nrUsi) {
    this.nrUsi = nrUsi;
  }

  public int getNrUsi() {
    return this.nrUsi;
  }

  @Override
  public boolean equals(Object id) {
    if (id instanceof Masina) {
      Masina masinuta = (Masina) id;
      if (this.nume.equals(masinuta.getNume()) && this.culoare.equals(masinuta.getCuloare()) && this.nrInmatriculare.equals(masinuta.getNumarInmatriculare()) && this.viteza == masinuta.getViteza() && this.vitezaMaxima == masinuta.getVitezaMaxima() && this.nrUsi == masinuta.getNrUsi())
        return true;
      else
        return false;
    }
    return false;
  }

  @Override
  public String toString() {
    return " Nume: " + this.nume + ";\n Culoare: " + this.culoare + ";\n Nr inmatriculare: " + this.nrInmatriculare /*+ "\n viteza: " + this.viteza + "\n viteza maxima: " + this.vitezaMaxima */ + ";\n Nr usi: " + this.nrUsi + ";";
  }
}