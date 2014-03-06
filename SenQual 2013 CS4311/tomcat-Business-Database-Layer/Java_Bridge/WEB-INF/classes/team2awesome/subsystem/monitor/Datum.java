package team2awesome.subsystem.monitor;

public class Datum {

	private int flag = 0;
	
	public Datum() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean setFlag(int flag)
	{
		this.flag = flag;
		return true;
	}
	
	public int getFlag()
	{
		return this.flag;
	}

}
