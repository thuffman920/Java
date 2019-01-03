package tyler.huffman.team;

public class Team {

	private int[] teams;
	
	private int[] homePTS;
	
	private int[] oppPTS;
	
	private String name;
	
	private double[] stats;
	
	public Team(String name, String statistics) {
		this.name = name;
		String[] stats = statistics.split(",");
		this.stats = new double[stats.length];
		for (int i = 0; i < stats.length; i++)
			this.stats[i] = Double.parseDouble(stats[i]);
	}

	public double[] getStats() {
		return stats;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getWin(int team) {
		int[] list = {0, 0};
		int count = 0;
		int a = 0;
		while (count < teams.length) {
			if (teams[count] == team) {
				list[a] = count;
				a++;
			}
			count++;
		}
		if (list[0] == teams.length)
			return 0;
		int diff1 = Math.abs(homePTS[list[0]] - oppPTS[list[0]]);
		int diff2 = Math.abs(homePTS[list[1]] - oppPTS[list[1]]);
		double factor1 = 1.0 * (list[0] + 1) / (5.0 * (teams.length - list[0]));
		double factor2 = 1.0 * (list[1] + 1) / (5.0 * (teams.length - list[1]));
		double power1 = Math.signum(homePTS[list[0]] - oppPTS[list[0]]) * 0.0025 * factor1 * Math.pow(diff1, 2);
		double power2 = Math.signum(homePTS[list[1]] - oppPTS[list[1]]) * 0.0025 * factor2 * Math.pow(diff2, 2);
		if (list[1] == 0)
			return power1;
		return (power1 + power2) / 2.0;
	}
	
	public int[] getHome() {
		return homePTS;
	}
	
	public int[] getOpp() {
		return oppPTS;
	}
	
	public int[] getTeams() {
		return teams;
	}
	
	public void setUpSchedule(String line) {
		String[] data = line.split(",");
		teams = new int[(int)(data.length / 3)];
		homePTS = new int[(int)(data.length / 3)];
		oppPTS = new int[(int)(data.length / 3)];
		int count = 0;
		for (int i = 0; i < data.length; i += 3) {
			teams[count] = Integer.parseInt(data[i]);
			homePTS[count] = Integer.parseInt("" + data[i + 1]);
			oppPTS[count] = Integer.parseInt("" + data[i + 2]);
			count++;
		}
	}
}
