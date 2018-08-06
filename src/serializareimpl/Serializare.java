
package serializareimpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import parcareimpl.Masina;
import parcareimpl.Parcare;

public class Serializare {
  //private final String fisier = "fisier.txt";
  static ArrayList<Masina> masini = new ArrayList<>();

  //  public static void writeToFile(Masina m) {
  //
  //    try {
  //      masini.add(m);
  //      FileOutputStream fisier = new FileOutputStream("fisier.txt");
  //      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fisier);
  //      objectOutputStream.writeObject(masini);
  //      objectOutputStream.flush();
  //      objectOutputStream.close();
  //      fisier.close();
  //      System.out.println("Masina " + m.getNume() + " " + m.getNumarInmatriculare() + " a fost serializata!");
  //    } catch (IOException e) {
  //      e.printStackTrace();
  //    }
  //
  //  }
  public static void writeToFile(Parcare p) {

    try {
      //masini.add(m);
      FileOutputStream fisier = new FileOutputStream("fisier.txt");
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fisier);
      objectOutputStream.writeObject(p);
      objectOutputStream.flush();
      objectOutputStream.close();
      fisier.close();
      System.out.println("Parcarea " + p.getId() + " a fost serializata!");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  //  public static ArrayList<Masina> readFile() {
  //
  //    try {
  //      FileInputStream f = new FileInputStream("fisier.txt");
  //      ObjectInputStream ois = new ObjectInputStream(f);
  //      ArrayList<Masina> masini = (ArrayList<Masina>) ois.readObject();
  //
  //      //System.out.println(masini);
  //      ois.close();
  //      f.close();
  //      return masini;
  //
  //    } catch (FileNotFoundException e) {
  //      e.printStackTrace();
  //    } catch (IOException e) {
  //      e.printStackTrace();
  //    } catch (ClassNotFoundException e) {
  //      e.printStackTrace();
  //    }
  //    return null;
  //
  //  }
  public static Parcare readFile() {

    try {
      FileInputStream f = new FileInputStream("fisier.txt");
      ObjectInputStream ois = new ObjectInputStream(f);
      Parcare p = (Parcare) ois.readObject();

      //System.out.println(masini);
      ois.close();
      f.close();
      return p;

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;

  }

}
