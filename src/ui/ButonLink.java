/*
 * Copyright (c) 2018 SSI Schaefer Noell GmbH
 *
 * $Header: $
 */

package ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;

import interfaceimpl.Comanda;
import parcareimpl.Parcare;

public class ButonLink extends JButton implements Comanda {
  private Parcare parcare;

  public ButonLink(Parcare parcare) {
    this.parcare = parcare;
  }

  @Override
  public void executa() throws IOException {
    parcare.toHTML();
    File f = new File("D:\\java\\exercitii\\Masini\\situatie.html");
    try {
      Desktop.getDesktop().open(f);
    } catch (Exception i) {
      i.printStackTrace();
    }

  }

}
