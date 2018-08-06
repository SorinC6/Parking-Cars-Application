
package history;

import java.io.Serializable;

import parcareimpl.Masina;

public class Istoric implements Serializable {

  private Masina masina;
  private TimpData timpIntrare;
  private TimpData timpIesire;

  public Istoric(Masina m, TimpData dataIntrare, TimpData dataIesire) {
    this.masina = m;
    this.timpIntrare = dataIntrare;
    this.timpIesire = dataIesire;
  }

  public Masina getMasina() {
    return this.masina;
  }

  public TimpData getTimpIntrare() {
    return this.timpIntrare;
  }

  public TimpData getTimpIesire() {
    return this.timpIesire;
  }

  @Override
  public String toString() {

    return this.masina + "\n Data de intrare: " + this.timpIntrare + "\n Data de iesire: " + this.timpIesire + "\n";
  }
}