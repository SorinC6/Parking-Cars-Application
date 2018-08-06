
package ui;

import java.io.IOException;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;

import interfaceimpl.Comanda;
import parcareimpl.Parcare;

public class ButonActiuneDetalii extends JButton implements Comanda, Serializable {
  private JList lista;
  private JTextArea text;
  private Parcare parcare;

  public ButonActiuneDetalii(JList lista, JTextArea text, Parcare parcare) {
    this.lista = lista;
    this.text = text;
    this.parcare = parcare;
  }

  @Override
  public void executa() throws IOException {
    if (lista.getSelectedIndex() != -1) {
      text.setText(parcare.getData(lista.getSelectedValue() + ""));
    }
  }

}
