package ui;
import java.util.*;

import parcareimpl.Masina;
import parcareimpl.NumarInmatriculare;
import parcareimpl.Parcare;

public class MeniuParcare{
	
	private Scanner scan=new Scanner(System.in);
	private int varianta;
	private Parcare p;
	private Masina m=new Masina();
	private NumarInmatriculare nr;
	
	public MeniuParcare(Parcare p){
		this.p=p;
	}
	
	public NumarInmatriculare definireNrInmatriculare(){
		System.out.print("			Prescurtare judet(ex.BV, TM): ");
		String judet=scan.next();
		System.out.println();
		System.out.print("			Numar(ex.10, 24): ");
		int numar=scan.nextInt();
		System.out.println();
		System.out.print("			Nr. serie(ex. 123, FIN): ");
		String serie=scan.next();
		System.out.println();
		this.nr=new NumarInmatriculare(judet,numar,serie);
		return nr;
	}
	
	public Masina definireMasina(){
		System.out.println("Introduceti datele masinii:");
		System.out.print("Nume masinia: ");
		String nume=scan.next();
		System.out.println();
		System.out.print("Culoarea masinii: ");
		String culoare=scan.next();
		System.out.println();
		System.out.println("Numar de inmatriculare: ");
		definireNrInmatriculare();
		System.out.println();
		System.out.print("Viteza masinii: ");
		int viteza=scan.nextInt();
		System.out.println();
		System.out.print("Viteza maxima a masinii: ");
		int vitezaMaxima=scan.nextInt();
		System.out.println();
		System.out.print("Numarul de usi: ");
		int nrUsi=scan.nextInt();
		System.out.println();
		this.m=new Masina(nume,culoare,nr,viteza,vitezaMaxima,nrUsi);
		return m;
		
	}
	
	public void meniu(){
		
		while (true){
			afisareMeniu();
			
			varianta=scan.nextInt();
			switch(varianta){
				case 1: 
					System.out.println(" Taxa pe ora: "+p.getTaxa()*60+" ron");
					break;
				case 2:
					System.out.println(" Mai sunt "+p.getLocuriLibere()+" locuri de parcare");
					break;
				case 3:
					definireMasina();
					p.adaugare(m);
					System.out.print("Ati adaugat masina: \n"+m);
					break;
					
				case 4:
					definireNrInmatriculare();
					if (p.existaMasina(this.nr)){
						m=p.extrageMasina(this.nr);
						p.scoate(this.m);
						double taxa=p.calculTaxa();
						System.out.printf(" Aveti de achitat: %.2f ron",taxa);
						System.out.println();
					}
					else
						System.out.println("Nu aveti masina parcata aici.");
					break;
					
				case 5:
					definireNrInmatriculare();
					if (p.existaMasina(this.nr)){
						m=p.extrageMasina(this.nr);
						System.out.print(this.m);
					}
					else
						System.out.println(p.getMasini().isEmpty() ? "Parcarea este goala.":"Nu ati parcat masina aici.");
	
					break;
				case 6:
					detaliiParcare();
					break;
				
				case 7:
					System.exit(1);
				
				default:
					System.out.println("Nu exista aceasta varianta");
			}
		}
	}
	
	public void detaliiParcare(){
		System.out.print("Ati accesat detaliile parcarii, introduceti codul: ");
		String cod=scan.next();
		System.out.println();
		if (cod.equals(p.getId())){
			p.toFile();
			p.toHTML();
			System.out.println(p.getIstoric());
		}
		else
			System.out.println("Nu puteti accesa detaliile parcarii.");
	}
	
	public void setParcare(Parcare p){
		this.p=p;
	}
	
	public Parcare getParcare(){
		return p;
	}
	
	@Override
	public boolean equals(Object i){
		if (i instanceof MeniuParcare){
			MeniuParcare m=(MeniuParcare)i;
			if ((this.p).equals(m.getParcare()))
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public void afisareMeniu(){
		System.out.println("1.Taxa parcare/h");
		System.out.println("2.Locuri libere");
		System.out.println("3.Parcare");
		System.out.println("4.Scoate masina");
		System.out.println("5.Detalii masina");
		System.out.println("6.Afisare istoric parcare");
		System.out.println("7.Iesire din meniu");
	}
	
	
}