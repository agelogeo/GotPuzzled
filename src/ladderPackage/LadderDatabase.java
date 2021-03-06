package ladderPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import puzzlePackage.PuzzleData;



/** LadderDatabase contains all the Ladder database and important methods.
 * @param ladders ArrayList that contains all ladders 
 * @param loadsave save/load class
 * @param ladderNames Only for JList-GUI use.
 * 
 */

public class LadderDatabase {
	private ArrayList<LadderChallenge> ladders;
	private LoadSaveLadders loadsave;
	private ArrayList<String> ladderNames; 

	public LadderDatabase() {
		super();
		this.ladders = new ArrayList<LadderChallenge>();
		this.loadsave = new LoadSaveLadders(ladders);
		;
		this.ladderNames = new ArrayList<String>();
	}

	public void createLadder(String name, ArrayList<PuzzleData> puzzles) {
		for (LadderChallenge l : ladders) {
			if (l.getName() == name) {
				System.out.println("This Ladder already exists!");
			}
		}
		ladders.add(new LadderChallenge(name, puzzles.size(), puzzles));

	}

	//String updater for arrays used on LadderChallenge
	public void UpdateLadderNamesArrayList() {
		for (int i = 0; i < ladders.size(); i++) {
			ladderNames.add(ladders.get(i).getName());
		}

	}
	
	/**LadderChallenge Loader. PopUp window for users to choose the ladder they want to import.
	 * 
	 * @param file
	 * @return ladder
	 */
	public static LadderChallenge loadLadder(File file) {
		LadderChallenge ladder = null;
		try {
			FileInputStream fileIn = new FileInputStream(file.getAbsolutePath());
			ObjectInputStream in = new ObjectInputStream(fileIn);

			ladder = (LadderChallenge) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			System.out.println("");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("");

		}

		System.out.println("path complete - desirialized");
		return ladder;

	}
	
	/**LadderChallenge Importer. With this class the user is able to import user-made LadderChallenges.
	 * 
	 * @param p
	 * @param ladders
	 * @param laddersNames
	 */
	public void importLadder(LadderChallenge p,
			ArrayList<LadderChallenge> ladders, ArrayList<String> laddersNames) {
		boolean flag = true;
		for (LadderChallenge ladder : ladders)
			if (ladder.getName().equals(p.getName())) {
				System.out.println("This Puzzle already exists");
				flag = false;
				break;
			}
		if (flag) {
			ladders.add(p);
			laddersNames.add(p.getName());
			loadsave.save(ladders);
			System.out.println("Ladder Desirialized");

		}

	}
	
	/**LadderChallenge Exporter. With this class the user is able to export user-made LadderChallenges.
	 * @param pname
	 */
	public void exportLadder(String pname) {
		LadderChallenge ladderToExport = null;
		for (LadderChallenge l : ladders) {
			if (l.getName() == pname) {
				ladderToExport = l;
				String filename = pname + ".lad";
				try {
					;
					FileOutputStream fos = new FileOutputStream(
							System.getProperty("user.home") + "\\Desktop\\"
									+ filename);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(ladderToExport);
					oos.close();
					System.out.println("Ladder Serialised");
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	
	//Getters&Setters
	public LoadSaveLadders getLoadsave() {
		return loadsave;
	}

	public ArrayList<String> getLadderNames() {
		return ladderNames;
	}

	public void setLadders(ArrayList<LadderChallenge> ladders) {
		this.ladders = ladders;
	}

	public ArrayList<LadderChallenge> getLadders() {
		return ladders;
	}

}
