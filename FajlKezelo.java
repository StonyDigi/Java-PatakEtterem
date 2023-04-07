package patakEtterem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class FajlKezelo {
	
	@SuppressWarnings("unused")
	private static String fejlec = "";
	
	public List<Etel> fajlBeolvas(String fajlNev){
		List<Etel> Etelek = new ArrayList<Etel>();
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(fajlNev), "UTF-8"));
			fejlec = br.readLine();
			
			while (br.ready()) {
				String sor = br.readLine();
				String[] sorAdatok = sor.split(";");
				 
				Etel ujEtel = new Etel(
						sorAdatok[0],
						sorAdatok[1],
						Byte.parseByte(sorAdatok[2]),
						Integer.parseInt(sorAdatok[3]),
						Integer.parseInt(sorAdatok[4]));
						Etelek.add(ujEtel);	
			}
			br.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return Etelek;	
	}
	
	public void rendeles90FelettiFajlbaIr(List<Etel> Etelek) {
		try {
			FileOutputStream fs = new FileOutputStream("rendeles.csv", false);
			OutputStreamWriter out = new OutputStreamWriter(fs, "UTF-8");
			out.write(fejlec);
			out.write("\n");
			for (Etel etel : Etelek) {
				if(etel.getEladottMennyiseg()>90) {
					out.write(etel.getAzonosito());
					out.write(";");
					out.write(etel.getNev());
					out.write(";");
					out.write(etel.getKategoriaId().toString());
					out.write(";");
					out.write(etel.getEgysegar().toString());
					out.write(";");
					out.write(etel.getEladottMennyiseg().toString());
					out.write("\n");
				}
			}
			out.close();
			fs.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
