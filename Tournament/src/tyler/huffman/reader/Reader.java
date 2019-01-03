package tyler.huffman.reader;

import javax.management.InstanceNotFoundException;

import tyler.huffman.team.Team;

public class Reader {

	private String dataFile;
	
	private Team[] teams;
	
	private int[] indexAlph = {0,19,35,63,75,83,94,106,116,129,133,138,155,185,215,225,237,238,245,286,304,323,331,348,349,-1};
	
	public Reader() {
		
	}

	public Team[] getTeams(){
		return this.teams;
	}
	
	public int[] getIndex() {
		return this.indexAlph;
	}
	
	public Team getTeam(String index) throws InstanceNotFoundException {
		int letter = (int)(Character.toLowerCase(index.charAt(0))) - 97;
		int start = indexAlph[letter];
		int end = indexAlph[letter + 1];
		for (int i = start; i < end; i++)
			if (teams[i].getName().equalsIgnoreCase(index))
				return teams[i];
		throw new InstanceNotFoundException("No such team");
	}
	
	public static void main(String[] args) {
		Reader reader = new Reader();
		System.out.println("" + reader.getIndex().length);
	}
}
