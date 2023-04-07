package patakEtterem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class App {

	private JFrame frameEtelek;
	private JTable tableEtelek;
	private JButton btnLegdargabbDesszert;
	private JButton btnHetiosszbevetel;
	private JButton btntEtelekSzazalekosan;
	private JButton btnFajlbairas;
	private JLabel lblOsszbevetel;
	private JButton btnFajlBeolvas;
	private JButton btnHibasAdatTorol;
	private JButton btnKilep;
	private JScrollPane scrollPane;
	private List<Etel> Etelek;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frameEtelek.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameEtelek = new JFrame();
		frameEtelek.setBounds(100, 100, 1051, 689);
		frameEtelek.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameEtelek.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 24, 988, 217);
		frameEtelek.getContentPane().add(scrollPane);
		
		tableEtelek = new JTable();
		scrollPane.setViewportView(tableEtelek);
		
		btnFajlBeolvas = new JButton("F\u00E1jlbeolvas");
		btnFajlBeolvas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FajlKezelo fkObj = new FajlKezelo();
				String fajlNev = "patak.csv";
				Etelek = fkObj.fajlBeolvas(fajlNev);
				
				DefaultTableModel tableEtelekModel = new DefaultTableModel (new Object[] {
						"Azonosító",
						"Név",
						"Kategória",
						"Egységár (Ft)",
						"Eladott mennyiség"},0);
				for (Etel etel : Etelek) {
					tableEtelekModel.addRow(new Object[] {
							etel.getAzonosito(),
							etel.getNev(),
							etel.getKategoriaSzoveg(),
							etel.getEgysegar(),
							etel.getEladottMennyiseg()
							});	
				}
				tableEtelek.setModel(tableEtelekModel);
				DefaultTableCellRenderer Kozepreigazitas = new DefaultTableCellRenderer();
				Kozepreigazitas.setHorizontalAlignment(JLabel.CENTER);
				for (int i = 0; i < tableEtelekModel.getColumnCount(); i++) {
					tableEtelek.getColumnModel().getColumn(i).setCellRenderer(Kozepreigazitas);	
				}
				btnFajlBeolvas.setEnabled(false);
				btnHibasAdatTorol.setEnabled(true);
			}
		});
		btnFajlBeolvas.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFajlBeolvas.setBounds(807, 280, 203, 42);
		frameEtelek.getContentPane().add(btnFajlBeolvas);
		
		btnHibasAdatTorol = new JButton("Hib\u00E1s adat t\u00F6rl\u00E9se");
		btnHibasAdatTorol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int hibasIndex = -1;
				for (int i = 0; i < Etelek.size(); i++) {
					if(Etelek.get(i).getAzonosito().length() !=10) {
						hibasIndex = i;
					}
				}
				Etelek.remove(hibasIndex);
				((DefaultTableModel)tableEtelek.getModel()).removeRow(hibasIndex);
				
				btnHibasAdatTorol.setEnabled(false);
				btnLegdargabbDesszert.setEnabled(true);
				btnHetiosszbevetel.setEnabled(true);
				btntEtelekSzazalekosan.setEnabled(true);
				btnFajlbairas.setEnabled(true);
			}
		});
		btnHibasAdatTorol.setEnabled(false);
		btnHibasAdatTorol.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHibasAdatTorol.setBounds(807, 347, 203, 42);
		frameEtelek.getContentPane().add(btnHibasAdatTorol);
		
		btnKilep = new JButton("KIL\u00C9P");
		btnKilep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnKilep.setForeground(Color.WHITE);
		btnKilep.setBackground(Color.RED);
		btnKilep.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnKilep.setBounds(807, 551, 203, 42);
		frameEtelek.getContentPane().add(btnKilep);
		
		btnLegdargabbDesszert = new JButton("Legdr\u00E1g\u00E1bb desszert");
		btnLegdargabbDesszert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Etel legDragabbDesszert = Etelek.stream()
						.filter(c->c.getKategoriaId()==3)
						.max(Comparator.comparing(Etel::getEgysegar))
						.orElseThrow(NoSuchElementException::new);
				JOptionPane.showMessageDialog(frameEtelek, 
						"Legdrágább desszert neve / egységára: "+legDragabbDesszert.getNev() + "/"+legDragabbDesszert.getEgysegar()+ " Ft",
						"Legdrágább desszert neve és egységára",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnLegdargabbDesszert.setEnabled(false);
		btnLegdargabbDesszert.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLegdargabbDesszert.setBounds(24, 280, 232, 42);
		frameEtelek.getContentPane().add(btnLegdargabbDesszert);
		
		btnHetiosszbevetel = new JButton("Heti \u00F6sszbev\u00E9tel");
		btnHetiosszbevetel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer osszBevetel = 0;
				for (Etel etel : Etelek) {
					osszBevetel += etel.getEgysegar()*etel.getEladottMennyiseg();
					
				}
				lblOsszbevetel.setVisible(true);
				lblOsszbevetel.setText(osszBevetel.toString() + " Ft");
			}
		});
		btnHetiosszbevetel.setEnabled(false);
		btnHetiosszbevetel.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHetiosszbevetel.setBounds(24, 347, 232, 42);
		frameEtelek.getContentPane().add(btnHetiosszbevetel);
		
		btntEtelekSzazalekosan = new JButton("\u00C9telek %-osan");
		btntEtelekSzazalekosan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer osszRendelesDb = 0;
				Integer foetelRendelesDb = 0;
				Integer levesRendelesDb = 0;
				Integer desszertRendelesDb = 0;
				for (Etel etel : Etelek) {
					if(etel.getKategoriaId() == 1) {
						levesRendelesDb += etel.getEladottMennyiseg();
					} else if(etel.getKategoriaId() == 2) {
						foetelRendelesDb += etel.getEladottMennyiseg();
					} else if(etel.getKategoriaId() == 3) {
						desszertRendelesDb += etel.getEladottMennyiseg();
					}
					osszRendelesDb +=etel.getEladottMennyiseg();
				}
				Double levesSzazalek = (double)levesRendelesDb/osszRendelesDb*100;
				Double foEtelSzazalek = (double)foetelRendelesDb/osszRendelesDb*100;
				Double desszertSzazalek = (double)desszertRendelesDb/osszRendelesDb*100;
				
				DecimalFormat df = new DecimalFormat("0.0#");
				
				JOptionPane.showMessageDialog(frameEtelek, 
						"Leves: "+df.format(levesSzazalek) + " % \n"+
						"Fõétel: "+df.format(foEtelSzazalek) + " % "+
						"Desszert: "+df.format(desszertSzazalek) + " % ",
						"Rendelt ételek százalékos aránya",
						JOptionPane.PLAIN_MESSAGE
						);
			}
		});
		btntEtelekSzazalekosan.setEnabled(false);
		btntEtelekSzazalekosan.setFont(new Font("Tahoma", Font.BOLD, 16));
		btntEtelekSzazalekosan.setBounds(24, 431, 232, 42);
		frameEtelek.getContentPane().add(btntEtelekSzazalekosan);
		
		btnFajlbairas = new JButton("F\u00E1jlba\u00EDr\u00E1s");
		btnFajlbairas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FajlKezelo fkObj = new FajlKezelo();
				fkObj.rendeles90FelettiFajlbaIr(Etelek);
				JOptionPane.showMessageDialog(frameEtelek, 
						"Sikeres mûvelet: létrejött a rendeles.csv",
						"Fájl létrehozva!!!",
						JOptionPane.INFORMATION_MESSAGE
						);		
			}
		});
		btnFajlbairas.setEnabled(false);
		btnFajlbairas.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnFajlbairas.setBounds(24, 493, 232, 42);
		frameEtelek.getContentPane().add(btnFajlbairas);
		
		lblOsszbevetel = new JLabel("");
		lblOsszbevetel.setForeground(Color.BLACK);
		lblOsszbevetel.setHorizontalAlignment(SwingConstants.CENTER);
		lblOsszbevetel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOsszbevetel.setEnabled(false);
		lblOsszbevetel.setOpaque(true);
		lblOsszbevetel.setVisible(false);
		lblOsszbevetel.setBackground(Color.GREEN);
		lblOsszbevetel.setBounds(266, 347, 239, 42);
		frameEtelek.getContentPane().add(lblOsszbevetel);
	}
}
