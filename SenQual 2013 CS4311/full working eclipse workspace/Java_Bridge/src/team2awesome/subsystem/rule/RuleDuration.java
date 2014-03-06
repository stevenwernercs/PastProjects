package team2awesome.subsystem.rule;

import team2awesome.subsystem.database.Datum;

public abstract class RuleDuration {

	protected boolean nests;
	
	protected boolean inScope = false;
	private boolean completed = false;
	
	protected boolean doesReset;
	
	int [] offset = new int[3];
	
	Formula left;
	Formula right;
	
	public RuleDuration(Formula left, Formula right) 
	{
		this.left = left;
		this.right = right;
	}	
	 
	public abstract boolean checkScope(Datum[] allSlice) throws Exception;
	public abstract boolean checkCompleted(Datum[] allSlice) throws Exception;
	
	public boolean checkInScope(Datum[] allSlice) throws Exception
	{
		if(inScope)
			return true;
		return inScope = checkScope(allSlice);
	}
	
	public boolean checkIfCompleted(Datum[] allSlice) throws Exception
	{
		if(completed)
			return true;
		if((completed = checkCompleted(allSlice)) && doesReset)
		{
			reset();
			return true;
		}
		return completed;
	}

	private void reset() 
	{
		inScope = false;
		completed = false;
	}

	/**
	 * @return the completed
	 */
	public boolean isCompleted() {
		return completed;
	}
}
