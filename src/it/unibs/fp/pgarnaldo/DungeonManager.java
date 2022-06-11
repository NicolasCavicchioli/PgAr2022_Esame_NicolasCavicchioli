package it.unibs.fp.pgarnaldo;

import java.io.IOException;
import java.util.HashSet;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import it.unibs.fp.mylib.EstrazioniCasuali;
import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.pgarnaldo.entities.Monster;
import it.unibs.fp.pgarnaldo.entities.Player;
import it.unibs.fp.pgarnaldo.oggetti.Arma;
import it.unibs.fp.pgarnaldo.oggetti.Oggetto;
import it.unibs.fp.pgarnaldo.oggetti.TipoOggetto;
import it.unibs.fp.pgarnaldo.tiles.MapLegend;
import it.unibs.fp.pgarnaldo.tiles.Mappable;
import it.unibs.fp.pgarnaldo.xml.XMLParser;

public class DungeonManager {
	
	private DungeonFloor[] floors;
	private int currentLevel = 0;
	private Player player;
	private HashSet<Oggetto> oggetti = new HashSet<Oggetto>();
	
	public void inizializzaOggetti() {
		oggetti.add(new Arma("Spada", 10));
		oggetti.add(new Oggetto("pozione", TipoOggetto.POZIONE));
		oggetti.add(new Oggetto("Scudo", TipoOggetto.SCUDO));
	}
	
	public Oggetto estraiOggettoDiTipo(TipoOggetto tipo) {
		return oggetti.stream()
		.filter(o -> o.tipo==tipo)
		// tra due oggetti ne sceglie uno in maniera casuale,
		// fino a quando non ne rimane uno solo
		.min((a,b) -> (int)(EstrazioniCasuali.estraiDouble()-0.5)).get();
	}
	
	
	public DungeonManager(String playerName, String filePath, String[] files) {
		player = new Player("O", playerName);
		floors = new DungeonFloor[files.length];
		for (int i = 0; i < files.length; i++) {
			floors[i] = readFloor(filePath+files[i]);
		}
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public DungeonFloor readFloor(String file) {
		final DungeonFloor df;
		try {
			XMLParser xmlp = new XMLParser(file);
			df = new DungeonFloor(xmlp.getHeight(), xmlp.getWidth());
			
			xmlp.forBiEnumerated((i,j,e)-> {
				String s = e.getTextContent();
				Mappable to = MapLegend.fromIcon(s);
				
				if (to==null) df.setItem(i, j, new Mappable.Empty());
				else  df.setItem(i,j,to);
					
			});
			
			
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
			return null;
		}
		
		return df;
	}
	
	public void printFloor() {
		System.out.printf("piano %d:%n", currentLevel);
		String floorString = floors[currentLevel].toString(player.x, player.y);
		String obbiettivo = "Obbiettivo:\n- Raggiungere la cima della torre\n- Salvare la principessa";
		Main.parallelPrintln(floorString, obbiettivo);
	}
	
	public DungeonFloor currentFloor() {
		return floors[currentLevel];
	}
	
	public void movePlayer(int dx, int dy) {
		int x=player.x, y=player.y;
		try {
			Mappable to = currentFloor().get(y+dy, x+dx);
			if (to.getData().canWalk) {
				player.move(dx, dy);
				to.onPlayerSteppingOver(this);
				fightTurn();
			}
			
		} catch(ArrayIndexOutOfBoundsException e) {}
	}
	
	public Mappable getAtPlayerPos() {
		return currentFloor().get(player.y, player.x);
	}
	
	public void playerTurn() {
		char action = InputDati.leggiUpperChar("scegli l'azione (WASDEM)", "WASDEM");
		switch(action) {
		case 'W': movePlayer( 0,  1); break;
		case 'A': movePlayer(-1,  0); break;
		case 'S': movePlayer( 0, -1); break;
		case 'D': movePlayer( 1,  0); break;
		case 'E':
			Mappable map = getAtPlayerPos();
			if (map==null || map.getData().canInteract==false) break;
			map.onInteractionWithPlayer(this);
			break;
		case 'M':
			secondaryMenu();
			break;
		}
	}
	
	public void secondaryMenu() {
		char action = InputDati.leggiUpperChar("scegli l'azione (AI)", "AI");
		switch(action) {
		case 'A': throw new Main.ExitProgram();
		case 'I': player.mostraInventario(); break;
		}
	}
	
	public void playerFoundObject(Oggetto o) {
		System.out.println("hai trovato "+o);
		InputDati.enterToContinue("enter to continue");
		player.inventario.add(o);
	}
	
	public void fightTurn() {
		Mappable map = getAtPlayerPos();
		if (map instanceof Monster == false) return;
		Monster monster = (Monster)map;
		while (player.isAlive() && monster.isAlive()) {
			chosePlayerFightAction(monster);
		}
		
	}
	public void chosePlayerFightAction(Monster monster) {
		double danno;
		monster.prendiDanno(danno=player.calcolaDanno());
		System.out.printf("hai inferto %f di danno%n", danno);
		if (monster.isAlive()) {
			player.prendiDanno( danno=monster.calcolaDanno() );
			System.out.printf("il mostro ti ha inferto %f di danno%n", danno);
		}
	}
	
	
	public void clearAtPlayerPos() {
		currentFloor().set(player.y, player.x, new Mappable.Empty());
	}
	
	
	public void sendPlayerUpstairs() {
		currentLevel++;
	}
	public void sendPlayerDownstairs() {
		currentLevel--;
	}
	
}
