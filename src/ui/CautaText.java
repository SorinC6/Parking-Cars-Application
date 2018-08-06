
package ui;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import interfaceimpl.Comanda;
import parcareimpl.NumarInmatriculare;
import parcareimpl.Parcare;

public class CautaText extends JTextField implements Comanda {
  private Parcare parcare;
  private JComboBox masiniParcare;

  public CautaText(Parcare parcare, JComboBox s) {
    this.parcare = parcare;
    this.masiniParcare = s;
  }

  @Override
  public void executa() {
    masiniParcare.removeAllItems();
    if (this.getText().isEmpty()) {
      for (NumarInmatriculare nr : parcare.getNumereInmatriculareArray()) {
        masiniParcare.addItem(nr);
      }
    } else {
      NumarInmatriculare[] string = parcare.cautaNumereInmatriculare(this.getText());
      for (NumarInmatriculare str : string) {
        masiniParcare.addItem(str);
      }
    }

  }

}
