
package history;

import java.io.Serializable;
import java.util.Date;

public class TimpData implements Serializable {

  private int ora;
  private int minute;
  private int secunde;
  private int anul;
  private int luna;
  private int ziua;
  private Date d;

  public TimpData(int ora, int minute, int secunde, int ziua, int luna, int anul) {
    this.ora = ora;
    this.minute = minute;
    this.secunde = secunde;
    this.ziua = ziua;
    this.luna = luna;
    this.anul = anul;
  }

  public TimpData() {
  }

  public String corectare(int i) {
    String s = i + "";
    String o = "0";
    if (i == 0)
      return o + s;
    if (i % 10 != 0 && i < 10)
      return o + s;
    else
      return s;
  }

  public TimpData timpScurs(TimpData t2, TimpData t1) {

    anul = 0;
    luna = 0;
    ziua = 0;
    if (t1.ora != 0 && t2.ora != 0)
      ora = t2.ora - t1.ora;
    else if (t1.ora == 0 && t2.ora != 0)
      ora = t2.ora;
    else if (t1.ora != 0 && t2.ora == 0)
      ora = 24 - t1.ora;
    if (ora == 0)
      minute = t2.minute - t1.minute;
    else {
      minute = (60 - t1.minute) + t2.minute;
      ora--;
    }
    if (minute > 59) {
      minute = minute % 60;
      ora++;
    }
    if (minute == 0)
      secunde = t2.secunde - t1.secunde;
    else {
      secunde = (60 - t1.secunde) + t2.secunde;
      minute--;
    }
    if (secunde > 59) {
      secunde = secunde % 60;
      minute++;
    }

    System.out.println("Timpul scurs: " + corectare(ora) + " : " + corectare(minute) + " : " + corectare(secunde));
    TimpData t = new TimpData(ora, minute, secunde, ziua, luna, anul);
    return t;
  }

  public int luna(String s) {
    switch (s) {
      case ("Jan"):
        return 1;
      case ("Feb"):
        return 2;
      case ("Mar"):
        return 3;
      case ("Apr"):
        return 4;
      case ("May"):
        return 5;
      case ("Jun"):
        return 6;
      case ("Jul"):
        return 7;
      case ("Aug"):
        return 8;
      case ("Sep"):
        return 9;
      case ("Oct"):
        return 10;
      case ("Nov"):
        return 11;
      case ("Dec"):
        return 12;
    }
    return 0;
  }

  //Wed Mar 14 00:12:19 EET 2018
  public TimpData timp() {

    d = new Date();
    String s = d.toString();
    String d1[] = s.split(" ");
    String dOra[] = d1[3].split(":");
    int ora = Integer.parseInt(dOra[0]);
    int minute = Integer.parseInt(dOra[1]);
    int secunde = Integer.parseInt(dOra[2]);
    int ziua = Integer.parseInt(d1[2]);
    int luna = luna(d1[1]);
    int anul = Integer.parseInt(d1[5]);
    TimpData t = new TimpData(ora, minute, secunde, ziua, luna, anul);
    return t;
  }

  public void setOra(int ora) {
    this.ora = ora;
  }

  public int getOra() {
    return this.ora;
  }

  public void setMinute(int minute) {
    this.minute = minute;
  }

  public int getMinute() {
    return this.minute;
  }

  public void setSecunde(int secunde) {
    this.secunde = secunde;
  }

  public int getSecunde() {
    return this.secunde;
  }

  public void setAnul(int anul) {
    this.anul = anul;
  }

  public int getAnul() {
    return this.anul;
  }

  public void setLuna(int luna) {
    this.luna = luna;
  }

  public int getLuna() {
    return this.luna;
  }

  public void setZiua(int ziua) {
    this.ziua = ziua;
  }

  public int getZiua() {
    return this.ziua;
  }

  @Override
  public boolean equals(Object d) {
    if (d instanceof Date) {
      TimpData td = (TimpData) d;
      if (this.ora == td.getOra() && this.minute == td.getMinute() && this.secunde == td.getSecunde() && this.ziua == td.getZiua() && this.luna == td.getLuna() && this.anul == td.getAnul())
        return true;
      else
        return false;
    } else
      return false;
  }

  @Override
  public String toString() {
    return " " + corectare(this.ziua) + "." + corectare(this.luna) + "." + this.anul + " ora " + corectare(this.ora) + " : " + corectare(this.minute) + " : " + corectare(this.secunde);
  }

}