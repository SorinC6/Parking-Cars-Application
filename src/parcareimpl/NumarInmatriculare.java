
package parcareimpl;

import java.io.Serializable;

public class NumarInmatriculare implements Serializable {

  private String judet;
  private int numar;
  private String serie;

  public NumarInmatriculare(String jud, int nr, String ser) {
    this.judet = jud;
    this.numar = nr;
    this.serie = ser;
  }

  public NumarInmatriculare() {
  }

  public void setJudet(String newJudet) {
    this.judet = newJudet;
  }

  public String getJudet() {
    return this.judet;
  }

  public static boolean verificaJudet(String judet) {
    if (judet.length() == 2) {
      if (Character.isUpperCase(judet.charAt(0)) && Character.isUpperCase(judet.charAt(1)))
        return true;
      else
        return false;
    }
    return false;
  }

  public void setNumar(int newNumar) {
    this.numar = newNumar;
  }

  public int getNumar() {
    return this.numar;
  }

  public void setSerie(String newSerie) {
    this.serie = newSerie;
  }

  public String getSerie() {
    return this.serie;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((judet == null) ? 0 : judet.hashCode());
    result = prime * result + numar;
    result = prime * result + ((serie == null) ? 0 : serie.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    NumarInmatriculare other = (NumarInmatriculare) obj;
    if (judet == null) {
      if (other.judet != null)
        return false;
    } else if (!judet.equals(other.judet))
      return false;
    if (numar != other.numar)
      return false;
    if (serie == null) {
      if (other.serie != null)
        return false;
    } else if (!serie.equals(other.serie))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return this.judet + " " + this.numar + " " + this.serie;
  }
}