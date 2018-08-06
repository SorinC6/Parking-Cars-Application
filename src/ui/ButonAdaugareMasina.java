
package ui;

import java.io.IOException;
import java.io.Serializable;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import interfaceimpl.Comanda;
import parcareimpl.Masina;
import parcareimpl.NumarInmatriculare;
import parcareimpl.Parcare;
import serializareimpl.Serializare;

public class ButonAdaugareMasina extends JButton implements Comanda, Serializable {
  private static final long serialVersionUID = 1L;
  private JTextField nume;
  private JTextField culoare;
  private JTextField nr;
  private JTextField usi;
  private JTextArea displayMasina;
  private Parcare parcare;

  private JLabel labelLocuri;
  private DefaultListModel l;

  public ButonAdaugareMasina(JTextField nume, JTextField culoare, JTextField nr, JTextField usi, JTextArea displayMasina, JLabel labelLocuri, DefaultListModel l, Parcare parcare) {
    this.nume = nume;
    this.culoare = culoare;
    this.nr = nr;
    this.usi = usi;
    this.displayMasina = displayMasina;
    this.labelLocuri = labelLocuri;
    this.l = l;
    this.parcare = parcare;
  }

  public NumarInmatriculare definireNr(String n) {
    if (n.length() > 7) {
      String[] nrParcare = n.split(" ");
      return new NumarInmatriculare(nrParcare[0], Integer.parseInt(nrParcare[1]), nrParcare[2]);
    } else if (n.length() == 7) {
      String jud = n.substring(0, 2);
      int numar = Integer.parseInt(n.substring(2, 4));
      String seria = n.substring(4, n.length());
      return new NumarInmatriculare(jud, numar, seria);
    }
    return null;
  }

  @Override
  public void executa() throws IOException {
    String n = nr.getText();
    NumarInmatriculare nrInmatriculare = definireNr(n);
    if (nrInmatriculare != null) {
      Masina masina = new Masina(nume.getText(), culoare.getText(), nrInmatriculare, Integer.parseInt(usi.getText()));
      nume.setText("");
      culoare.setText("");
      nr.setText("");
      usi.setText("");
      displayMasina.setEditable(false);
      if (parcare.existaMasina(nrInmatriculare)) {
        displayMasina.setText("Masina a fost parcata deja.");
      } else {
        displayMasina.setText("Ati adaugat masina: " + masina.getNume() + " " + masina.nrInmatriculare + ".\n");
        parcare.adaugare(masina);

        Serializare.writeToFile(parcare);
        parcare = Serializare.readFile();

        labelLocuri.setText("Locuri libere: " + parcare.getLocuriLibere() + ".");
        l.addElement(masina.getNume() + ", " + masina.getNumarInmatriculare());

      }
    }

  }

}
