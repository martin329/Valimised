package com.valimised;

public class Kandidaat {
	public int id;
	public int piirkond;
	public int erakond;
	public String eesnimi;
	public String perenimi;
	
	@Override
	public String toString() {
		return "Kandidaat [id=" + id + ", piirkond=" + piirkond + ", erakond="
				+ erakond + ", eesnimi=" + eesnimi + ", perenimi=" + perenimi
				+ "]";
	}

	public Kandidaat(int id, int piirkond, int erakond, String eesnimi,
			String perenimi) {
		super();
		this.id = id;
		this.piirkond = piirkond;
		this.erakond = erakond;
		this.eesnimi = eesnimi;
		this.perenimi = perenimi;
	}
	
	

}
