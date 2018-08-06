
package ui;

import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import interfaceimpl.Comanda;
import parcareimpl.Masina;
import parcareimpl.NumarInmatriculare;
import parcareimpl.Parcare;
import serializareimpl.Serializare;

public class ButonScoateMasina extends JButton implements Comanda, Serializable {
  private Parcare parcare;
  private JCheckBox sureOut;
  private JComboBox s;
  private JTextArea taxa;
  private JLabel labelLocuri;

  public ButonScoateMasina(Parcare parcare, JCheckBox sureOut, JComboBox s, JTextArea taxa, JLabel labelLocuri) {
    this.parcare = parcare;
    this.sureOut = sureOut;
    this.s = s;
    this.taxa = taxa;
    this.labelLocuri = labelLocuri;
  }

  @Override
  public void executa() {
    if (sureOut.isSelected()) {
      if (!s.getSelectedItem().equals("Cauta mai sus dupa numarul de inmatriculare al masinii")) {
        NumarInmatriculare n = (NumarInmatriculare) s.getSelectedItem();
        Masina masina = parcare.extrageMasina(n);
        parcare.scoate(masina);
        Double taxaParcare = parcare.calculTaxa();
        Serializare.writeToFile(parcare);
        taxa.setText("Masina " + masina.getNume() + " " + masina.getNumarInmatriculare() + " a fost scoasa.\nAveti de achitat " + taxaParcare + " ron.");
        labelLocuri.setText("Locuri libere: " + parcare.getLocuriLibere() + ".");
        s.removeItem(n);
      }
    }

  }

}
