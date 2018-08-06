
package ui;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import history.Istoric;
import interfaceimpl.Comanda;
import parcareimpl.Masina;
import parcareimpl.NumarInmatriculare;
import parcareimpl.Parcare;
import serializareimpl.Serializare;

public class ParcareFrame extends JFrame implements ActionListener, Serializable {
  private JPanel mainPanel;

  private JTabbedPane tabbedPane;
  private JComponent component1;
  private JComponent component2;
  private JComponent component3;
  private JComponent component4;

  private JLabel labelLocuri;
  private JLabel labelGol;
  private JLabel labelTaxa;

  private JPanel panou1;
  private JPanel panou2;
  private JLabel labelNumeMasina;
  private JTextField txtNumeMasina;
  private JLabel labelCuloare;
  private JTextField txtCuloare;
  private JLabel labelNr;
  private JTextField txtNr;
  private JLabel labelNrUsi;
  private JTextField txtNrUsi;
  private JLabel labelSureIn;
  private JCheckBox sureIn;
  private ButonAdaugareMasina butonAdaugareActiune;
  //private JButton adaugareMasina;
  private JTextArea masinaIn;

  private JPanel panou3;
  private JComboBox masiniParcare;
  private JTextField cautaMasina;
  //private CautaText cautaMasina;
  private JCheckBox sureOut;
  private ButonScoateMasina butonScoateActiune;
  //private JButton remove;
  private JTextArea taxa;
  //private double taxaParcare;

  private JPanel panou4;
  private DefaultListModel l = new DefaultListModel();
  private JList listaMasini;
  private JScrollPane scroll;
  private JTextArea afisareDetalii;
  private ButonActiuneDetalii detaliiMasina;
  private ButonLink linkIstoric;

  private Parcare parcare;

  public ParcareFrame() {
    this.mainPanel = new JPanel();
    this.tabbedPane = new JTabbedPane();
    this.parcare = new Parcare("Masini", 35, 35, 0.2);
    //Serializare.writeToFile(parcare);
    this.parcare = Serializare.readFile();

    //dateIntrareTest();

    this.component1 = new JPanel();
    this.component2 = new JPanel();
    this.component3 = new JPanel();
    this.component4 = new JPanel();

    creareComponenta1();
    creareComponenta2();
    creareComponenta3();
    creareComponenta4();

    creareTabbedPane();

    crearePanelPrincipal();

    creareJFrame();
  }

  private void dateIntrareTest() {

    Masina masina1 = new Masina("Dacia", "Alb", new NumarInmatriculare("MM", 42, "ALM"), 0, 100, 4);
    Masina masina2 = new Masina("Lamborgini", "Albastru", new NumarInmatriculare("TM", 12, "213"), 0, 250, 2);
    Masina masina3 = new Masina("Opel", "Negru", new NumarInmatriculare("B", 34, "A63"), 0, 180, 4);
    Masina masina4 = new Masina("Ferrari", "Rosu", new NumarInmatriculare("BV", 38, "7A3"), 0, 180, 4);
    Masina masina5 = new Masina("Fiat", "Negru", new NumarInmatriculare("MM", 89, "COM"), 0, 180, 4);
    Masina masina6 = new Masina("Fiat", "Alb", new NumarInmatriculare("B", 49, "ARA"), 0, 180, 4);

    parcare.adaugare(masina1);
    parcare.adaugare(masina2);
    parcare.adaugare(masina3);
    parcare.adaugare(masina4);
    parcare.adaugare(masina5);
    parcare.adaugare(masina6);

    //    if (Serializare.readFile() != null) {
    //      for (Masina m : Serializare.readFile()) {
    //        parcare.adaugare(m);
    //      }
    //    }

  }

  private void crearePanelPrincipal() {
    mainPanel.add(tabbedPane);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
  }

  private void creareTabbedPane() {
    tabbedPane.addTab("Informatii generale", component1);
    tabbedPane.addTab("Parcare masina", component2);
    tabbedPane.addTab("Scoatere masina", component3);
    tabbedPane.addTab("Istoric parcare", component4);
  }

  private void creareComponenta1() {
    Border b = BorderFactory.createLineBorder(Color.BLACK);
    component1.setLayout(new GridBagLayout());
    this.labelLocuri = new JLabel("Locuri libere: " + parcare.getLocuriLibere() + ".");
    labelLocuri.setBorder(BorderFactory.createCompoundBorder(b, BorderFactory.createEmptyBorder(15, 15, 15, 15)));
    labelLocuri.setAlignmentX(CENTER_ALIGNMENT);
    this.labelGol = new JLabel("            ");
    this.labelTaxa = new JLabel("Taxa parcare pe ora: " + (parcare.getTaxa() * 60) + " ron.");
    labelTaxa.setBorder(BorderFactory.createCompoundBorder(b, BorderFactory.createEmptyBorder(15, 15, 15, 15)));
    labelTaxa.setAlignmentX(CENTER_ALIGNMENT);

    component1.add(labelLocuri);
    component1.add(labelGol);
    component1.add(labelTaxa);
  }

  private void creareComponenta2() {
    panou1 = new JPanel();
    panou2 = new JPanel();

    component2.setLayout(new BoxLayout(component2, BoxLayout.Y_AXIS));
    panou1.setLayout(new GridLayout(6, 2));
    panou2.setLayout(new GridLayout(3, 0));

    labelNumeMasina = new JLabel("Nume masina: ");
    txtNumeMasina = new JTextField(15);
    labelCuloare = new JLabel("Culoare: ");
    txtCuloare = new JTextField(15);
    labelNr = new JLabel("Numar de inmatriculare: ");
    txtNr = new JTextField(15);
    labelNrUsi = new JLabel("Numar de usi: ");
    txtNrUsi = new JTextField();
    labelSureIn = new JLabel("Sunteti sigur ca vreti sa adaugati masina ?");
    sureIn = new JCheckBox();
    //adaugareMasina = new JButton();
    //adaugareMasina.setText("Intrare in parcare");
    masinaIn = new JTextArea(3, 5);

    //actiuneAdaugareMasina();
    butonAdaugareActiune = new ButonAdaugareMasina(txtNumeMasina, txtCuloare, txtNr, txtNrUsi, masinaIn, labelLocuri, l, parcare);
    butonAdaugareActiune.setText("Adauga masina");
    butonAdaugareActiune.addActionListener(this);

    panou1.add(labelNumeMasina);
    panou1.add(txtNumeMasina);
    panou1.add(labelCuloare);
    panou1.add(txtCuloare);
    panou1.add(labelNr);
    panou1.add(txtNr);
    panou1.add(labelNrUsi);
    panou1.add(txtNrUsi);
    panou1.add(labelSureIn);
    panou1.add(sureIn);
    //panou2.add(adaugareMasina);
    panou2.add(butonAdaugareActiune);
    panou2.add(new JLabel("   "));
    panou2.add(masinaIn);

    component2.add(panou1);
    component2.add(panou2);
  }

  //  private void actiuneAdaugareMasina() {
  //    adaugareMasina.addActionListener(new ActionListener() {
  //
  //      @Override
  //      public void actionPerformed(ActionEvent e) {
  //        if (sureIn.isSelected()) {
  //          String n = txtNr.getText();
  //          if (n.length() > 7) {
  //            String[] nrParcare = n.split(" ");
  //            nrInmatriculare = new NumarInmatriculare(nrParcare[0], Integer.parseInt(nrParcare[1]), nrParcare[2]);
  //          } else {
  //            String jud = txtNr.getText().substring(0, 2);
  //            int nr = Integer.parseInt(txtNr.getText().substring(2, 4));
  //            String seria = txtNr.getText().substring(4, txtNr.getText().length());
  //            nrInmatriculare = new NumarInmatriculare(jud, nr, seria);
  //          }
  //          masina = new Masina(txtNumeMasina.getText(), txtCuloare.getText(), nrInmatriculare, Integer.parseInt(txtNrUsi.getText()));
  //          txtNumeMasina.setText("");
  //          txtCuloare.setText("");
  //          txtNr.setText("");
  //          txtNrUsi.setText("");
  //          masinaIn.setEditable(false);
  //          if (parcare.existaMasina(nrInmatriculare)) {
  //            masinaIn.setText("Masina a fost parcata deja.");
  //          } else {
  //            masinaIn.setText("Ati adaugat masina: " + masina.getNume() + " " + masina.nrInmatriculare + ".\n");
  //            parcare.adaugare(masina);
  //            labelLocuri.setText("Locuri libere: " + parcare.getLocuriLibere() + ".");
  //            l.addElement(masina.getNume() + ", " + masina.getNumarInmatriculare());
  //            //System.out.println(masina.toString());
  //            //Arrays.sort(parcare.getNumereInmatriculArray());
  //          }
  //        }
  //
  //      }
  //    });
  //  }

  public void creareComponenta3() {
    panou3 = new JPanel();
    panou3.setLayout(new BoxLayout(panou3, BoxLayout.Y_AXIS));

    //Arrays.sort(parcare.getNumereInmatriculArray());
    masiniParcare = new JComboBox();
    masiniParcare.addItem("Cauta mai sus dupa numarul de inmatriculare al masinii");
    cautaMasina = new CautaText(parcare, masiniParcare);
    cautaMasina.setBounds(0, 5, 15, 2);
    cautaMasina.setToolTipText("Cauta dupa numar inmatriculare...");
    cautaMasina.addActionListener(this);
    sureOut = new JCheckBox();
    sureOut.setBounds(0, 7, 10, 2);
    sureOut.setText("Esti sigur ca vrei sa scoti masina din parcare?");
    taxa = new JTextArea(2, 20);
    taxa.setEditable(false);
    butonScoateActiune = new ButonScoateMasina(parcare, sureOut, masiniParcare, taxa, labelLocuri);
    butonScoateActiune.setBounds(0, 8, 7, 2);
    butonScoateActiune.setText("Iesire");
    butonScoateActiune.addActionListener(this);

    //actiuneCauta(masiniParcare);

    //actiuneRemove(masiniParcare);

    panou3.add(new JLabel("    "));
    panou3.add(cautaMasina);
    panou3.add(new JLabel("    "));
    panou3.add(masiniParcare);
    panou3.add(new JLabel("    "));
    panou3.add(sureOut);
    panou3.add(new JLabel("    "));
    panou3.add(butonScoateActiune);
    panou3.add(new JLabel("    "));
    panou3.add(taxa);

    component3.add(panou3);
  }

  //  private void actiuneRemove(JComboBox s) {
  //    remove.addActionListener(new ActionListener() {
  //
  //      @Override
  //      public void actionPerformed(ActionEvent e) {
  //        if (sureOut.isSelected()) {
  //          if (!s.getSelectedItem().equals("Cauta mai sus dupa numarul de inmatriculare al masinii")) {
  //            NumarInmatriculare n = (NumarInmatriculare) s.getSelectedItem();
  //            masina = parcare.extrageMasina(n);
  //            parcare.scoate(masina);
  //            taxaParcare = parcare.calculTaxa();
  //            taxa.setText("Masina " + masina.getNume() + " " + masina.getNumarInmatriculare() + " a fost scoasa.\nAveti de achitat " + taxaParcare + " ron.");
  //            labelLocuri.setText("Locuri libere: " + parcare.getLocuriLibere() + ".");
  //            s.removeItem(n);
  //          }
  //        }
  //      }
  //    });
  //  }

  //  private void actiuneCauta(JComboBox s) {
  //
  //    cautaMasina.addActionListener(new ActionListener() {
  //
  //      @Override
  //      public void actionPerformed(ActionEvent e) {
  //        s.removeAllItems();
  //        if (cautaMasina.getText().isEmpty()) {
  //          for (NumarInmatriculare nr : parcare.getNumereInmatriculareArray()) {
  //            s.addItem(nr);
  //          }
  //        } else {
  //          NumarInmatriculare[] string = parcare.cautaNumereInmatriculare(cautaMasina.getText());
  //          for (NumarInmatriculare str : string) {
  //            s.addItem(str);
  //          }
  //        }
  //      }
  //    });
  //  }

  public void creareComponenta4() {

    panou4 = new JPanel();
    panou4.setLayout(new BoxLayout(panou4, BoxLayout.PAGE_AXIS));

    for (Istoric i : parcare.getIstoric()) {
      Masina m = i.getMasina();
      l.addElement(m.getNume() + ", " + m.getNumarInmatriculare());
    }
    listaMasini = new JList(l);
    scroll = new JScrollPane(listaMasini);

    afisareDetalii = new JTextArea(8, 30);
    afisareDetalii.setEditable(false);

    detaliiMasina = new ButonActiuneDetalii(listaMasini, afisareDetalii, parcare);
    detaliiMasina.setText("Detalii masina selectata");
    detaliiMasina.addActionListener(this);
    //actiuneDetalii();

    linkIstoric = new ButonLink(parcare);
    linkIstoric.setText("Link istoric");
    linkIstoric.addActionListener(this);
    //actiuneLink();

    panou4.add(scroll);
    panou4.add(new JLabel("   "));
    panou4.add(detaliiMasina);
    panou4.add(new JLabel("   "));
    panou4.add(afisareDetalii);
    panou4.add(new JLabel("   "));
    panou4.add(linkIstoric);

    component4.add(panou4);

  }

  //  private void actiuneDetalii() {
  //    detaliiMasina.addActionListener(new ActionListener() {
  //
  //      @Override
  //      public void actionPerformed(ActionEvent e) {
  //        if (listaMasini.getSelectedIndex() != -1) {
  //          afisareDetalii.setText(parcare.getData(listaMasini.getSelectedValue() + ""));
  //        }
  //
  //      }
  //    });
  //  }
  //
  //  private void actiuneLink() {
  //    linkIstoric.addActionListener(new ActionListener() {
  //
  //      @Override
  //      public void actionPerformed(ActionEvent e) {
  //
  //        parcare.toHTML();
  //        File f = new File("D:\\java\\exercitii\\Masini\\situatie.html");
  //        try {
  //          Desktop.getDesktop().open(f);
  //        } catch (Exception i) {
  //          i.printStackTrace();
  //        }
  //
  //      }
  //    });
  //  }

  private void creareJFrame() {
    super.add(mainPanel);
    super.setTitle("Parcare");
    super.setSize(500, 500);
    super.setVisible(true);
    super.setLocationRelativeTo(null);
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {

    ParcareFrame p = new ParcareFrame();

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    try {
      ((Comanda) e.getSource()).executa();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }

}
