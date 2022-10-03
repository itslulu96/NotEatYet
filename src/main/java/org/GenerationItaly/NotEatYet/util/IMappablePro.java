package org.GenerationItaly.NotEatYet.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//Metodi di default
//Metodi static
public interface IMappablePro {
	
	//Tutto quello che utilizzeremo qua dentro analizza la struttura
	//delle classi che implementano quest'interfaccia.
	//Il tutto senza conoscere nulla sulle classi che implementano quest'interfaccia

	default Map<String, String> toMap() {
		Map<String, String> ris = new HashMap<>();
		
		//Devo ricordarmi una cosa: qua dentro posso invocare i metodi
		//come se fossi dentro la classe che implementa quest'interfaccia
		//La classe ritornata sarà una che estende IMappablePro
		//Mi faccio restituire la classe dell'oggetto su cui viene invocato il metodo
		//Sono sicura che avrà almeno due tipi:
		// - Object
		// - IMappablePro
		// getClass restituisce la classe di runtime (mentre il programma è eseguito) dell'oggetto
		Class<? extends IMappablePro> classe = getClass();
		
		//Anche una classe è un oggetto. Una Class mi permette di ottenere informazioni
		//sulla struttura della classe.
		//Le classi che implementeranno quest'interfaccia avranno sempre tutti i getters
		//Dalla classe posso farmi ritornare tutti i suoi metodi
		//Me li salvo dentro un vettore Method che restituisce un vettore di metodi
		
		
		Method[] metodi = classe.getMethods();
		
		for (Method metodo : metodi) {
			String nomeMetodo = metodo.getName();
			
			//Voglio recuperare solamente i metodi getters
			//Ciò implica che se utilizziamo questa libreria
			//non dobbiamo aggiungere altri metodi che iniziano con is o con get
			if (nomeMetodo.startsWith("get") || nomeMetodo.startsWith("is")) {
				
				//Abbiamo bisogno del valore delle proprietà
				//Le recuperiamo dai getter
				//this è l'oggetto di runtime su cui viene indicato il toMap()
				//Il metodo vorrebbe dei parametri, ma i getter non hanno parametri
				//Primo parametro invoke: target su cui invocare il metodo
				//Secondo parametro: opzionale
				try {
					//Invoco il metodo, quello con la firma lunga
					//Invoke accetta sicuramente un parametro, cioé su cosa deve essere invocato il metodo
					//ossia sull'oggetto su cui deve essere indicato il toMap
					//Curiosità: invoke ha anche la possibilità di accettare dei parametri. Non sappiamo quanti
					//Possiamo passargliene quanti ne vogliamo
					//Noi non sappiamo cosa ritornerà nello specifico quel metodo
					//In teoria, invoke ritorna un Object
					//Tutto, anche gli int, i double, ecc. sono degli Object
					String valore = metodo.invoke(this).toString();
					
					//Ora che abbiamo il valore devo recuperare la chiave
					//I metodi getter possono iniziare con get o is
					//Con get devo togliere 3 lettere, con is 2
					
					int indicePartenza = nomeMetodo.startsWith("get") ? 3 : 2;
					
					String chiave = nomeMetodo.substring(indicePartenza).toLowerCase();
					
					ris.put(chiave, valore);
					
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				} 
			}
		}
		//sovrascrivo la classe con il nome semplice (senza package)
		ris.put("class", classe.getSimpleName());
		
		return ris;
	}
	
	//DA MAPPA A OGGETTO
	//L'oggetto deve essere già stato creato, perché viene invocato sull'oggetto
	//Il mio compito è sempre quello di caricare le proprietà dell'oggetto della mappa
	default void fromMap(Map<String, String> map) { 
		Class<? extends IMappablePro> classe = getClass();           
		
//		List<Method> metodi = Arrays.asList(classe.getMethods());
//		
//		metodi.addAll(Arrays.asList(classe.getSuperclass().getMethods()));
		
		Method[] metodi = classe.getMethods();
		
		for (Method metodo : metodi) {
			String nomeMetodo = metodo.getName();
			
			if (nomeMetodo.startsWith("set")) {
				String chiave = nomeMetodo.substring(3).toLowerCase();
				
				//arriva una mappa da parametro che arriva dal DB
				//La mappa avrà come chiave i nomi delle colonne e i relativi valori
				
				//Recupero dalla mappa che arriva da parametro
				//il valore associato alla proprietà dell'oggetto
				//che corrisponde alla chiave della mappa che corrisponde alla colonna del db
				String valore = map.get(chiave);
				
				//Se c'è un null nella cella del database non voglio errori
				//quindi skippo
				if (valore == null) continue;
				//Problema: ai setter serve un parametro, i parametri sono diversi
				//Devo caricare i valori della mappa dentro alle proprietà dell'oggetto utilizzando i setters
				//Ma i valori della mappa sono String, mentre i setter vogliono: interi, double, stringhe, ecc.
				//Devo quindi andare a fare dei parsing nel caso fosse uguale a...
				//Questo metodo mi restituisce un VETTORE di parametri
				//Di norma i setters hanno un parametro solo, quindi vado a prendere
				//quello alla posizione 0
				Class<?> tipoParametro = metodo.getParameterTypes()[0];
				
//				System.out.println(nomeMetodo);
//				System.out.println(tipoParametro);
				
				//Bisogna elencare per ogni tipo che necessita di un'operazione in più
				//che cosa fare
				try {
					if (tipoParametro.equals(int.class)) {
					//Dobbiamo invocare il metodo setter per poter caricare
					//il valore nell'oggetto
						metodo.invoke(this, Integer.parseInt(valore));
					
					} else if (tipoParametro.equals(double.class)) {
						metodo.invoke(this, Double.parseDouble(valore));
					} else if (tipoParametro.equals(boolean.class)) {
						metodo.invoke(this, valore.equals("1") || valore.equals("true"));
					} else if (tipoParametro.equals(long.class)) {
						metodo.invoke(this, Long.parseLong(valore));
					} else {
						//se è una stringa non devo parsare
						metodo.invoke(this, valore);
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			}
		}
		
	}
	
}
