package team2awesome.subsystem.rule;

import java.util.LinkedList;

import team2awesome.subsystem.database.DBRule;
import team2awesome.subsystem.database.Datum;
import team2awesome.subsystem.gui.GUIStatic;
import team2awesome.subsystem.profile.Profile;

public class Rule extends RuleInfo{
	
	RuleDuration   duration;
	RuleDefinition definition;
	LinkedList<Datum[]> anamallyRegion = new LinkedList<Datum[]>();
	
	public Rule(String ownerEmail, String ruleName, String description, int ruleDuration, int ruleDefinition
			, String leftBoundFormula, String rightBoundFormula
		    , String premiseFormula, String booleanFormula, boolean isShared) 
	{
		super(ownerEmail, ruleName, description, ruleDuration,  ruleDefinition
				,leftBoundFormula, rightBoundFormula
				,premiseFormula,  booleanFormula, isShared, true); 
		
		//this.duration   = new RuleDuration(ruleDuration, leftBoundFormula, rightBoundFormula);
		//this.definition = new RuleDefinition(ruleDefinition, booleanFormula, premiseFormula);
		setScopeSettings(ruleDuration, leftBoundFormula, rightBoundFormula);
		setPatternSettings(ruleDefinition, booleanFormula, premiseFormula);
		
		
	}

	public static Rule get(String ownerEmail, String ruleName)
	{
		RuleInfo ri = DBRule.getRuleInfo(ownerEmail, ruleName);
		if(ri==null)
			return null;
		return new Rule(ri.ownerEmail, ri.name, ri.description, ri.durationType, ri.definitionType
				, ri.lboundFormula.getFormulaString(), ri.rboundFormula.getFormulaString()
				, ri.premiseFormula.getFormulaString(), ri.booleanFormula.getFormulaString()
				, ri.isShared);
	}
	private void setScopeSettings(int ruleDuration, String leftBoundFormula, String rightBoundFormula) 
	{
	
		/*
		 * <option value="null">Select a Duration</option>
		 * <option value="global">All readings</option>
		 * <option value="before_r">All Readings After L</option>
		 * <option value="after_l">All readings Before R</option>
		 * <option value="between">All Readings Between L and R</option>
		 * <option value="until">All Readings After L Until R</option>  <!-- not specified in SRS--> 
        */
		
		  
		Formula left = new Formula(leftBoundFormula);
		super.sensorSet.addAll(left.getSensorSet());
		Formula right = new Formula(rightBoundFormula);
		super.sensorSet.addAll(right.getSensorSet());
		
		System.out.println("DURATION:"+ruleDuration);
		switch (ruleDuration)
		{
			case 1 : this.duration = new ScopeGlobal(left, right); 	break;
			case 2 : this.duration = new ScopeBeforeR(left, right);	break;
			case 3 : this.duration = new ScopeAfterL(left, right);	break;
			case 4 : this.duration = new ScopeBetween(left, right);	break;
			case 5 : this.duration = new ScopeUntil(left, right);	break;
			case 0 : //error WAS NEVER SET
			default: ; 
		}
		
	}
	
	private void setPatternSettings(int ruleDefinition, String booleanFormula, String premiseFormula) 
	{
		/*
		 *	<option value="null">Select Rule Definition</option>
		 *  <option value="u">Universality</option>
		 *  <option value="a">Absense</option>
		 *  <option value="e">Existence</option>
		 *  <option value="r">Response</option>
        */
		Formula prem = new Formula(premiseFormula);
		super.addAllSensors(prem.getSensorSet());
		Formula bool = new Formula(booleanFormula);
		super.addAllSensors(bool.getSensorSet());
		
		switch (ruleDefinition)
		{
			case 1 : this.definition = new PatternUniversality(bool, prem); break;
			case 2 : this.definition = new PatternAbsense(bool, prem);		break;
			case 3 : this.definition = new PatternExistence(bool, prem);	break;
			case 4 : this.definition = new PatternResponse(bool, prem);		break;
			case 0 : //error WAS NEVER SET
			default: return; 
		}	
	}
	
	/**
	 * expected to recieve an array of all sensors' datum needed, at one time point.. 
	 * @param slice
	 * @return
	 */
	public Datum[][] executeRuleCheck(Datum[] slice)
	{
		boolean add = false;
		try 
		{
			if(!duration.inScope)
				if(duration.checkInScope(slice))
				{	
					setSliceFlag(slice, 1);//scope start
					if(anamallyRegion.size()==0)
						add = true;
					else
						System.err.println("anamallyRegion not empty for new");
				}
			if(duration.inScope)
			{
				add = true;
				if(definition.check(slice))
					if(duration.isCompleted())
						setSliceFlag(slice, 4);
					else
						setSliceFlag(slice, 8);;
				if(!duration.isCompleted() && duration.checkIfCompleted(slice))
				{
					setSliceFlag(slice, 2); //scope end
					if(add)
						anamallyRegion.add(slice);
					thisFromStartPotientialsAreAnomally();
				}
				else if(add)
					anamallyRegion.add(slice);
				if(definition.anomallyOccur && duration.isCompleted())
				{	//package anomally region!
					Datum [][] anamally = anamallyRegion.toArray(new Datum[anamallyRegion.size()][]);
					anamallyRegion.clear();
					definition.anomallyOccur = false;
					return anamally;
				}
				return null;
			}
			else
				return new Datum[][]{slice};
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}

	//simple flag ALL
	private void setSliceFlag(Datum[] slice, int flag) 
	{
		//TODO add scope flags on scopes
		//TODO add bool flags on bool..?
		for(Datum each : slice)
			each.addFlag(flag);
	}

	/**
	 * 
	 */
	private void thisFromStartPotientialsAreAnomally() 
	{
		for(Datum[] slice : anamallyRegion)
			for(Datum each : slice)
				if((each.getFlag() & 8)!=0)
					each.setFlag(each.getFlag()-4);
	}
	
	LinkedList<Datum[]> thisFromStartPotientialsAreNOTAnomally() 
	{
		for(Datum[] slice : anamallyRegion)
			for(Datum each : slice)
				if((each.getFlag() & 8)!=0)
					each.setFlag(each.getFlag()-8);
		return anamallyRegion;
	}
	
	private static String[][] getAllRules(String ownerEmail)
	{
		String[][] rules1 = DBRule.getOwnedRuleArray(ownerEmail);
		String[][] rules2 = DBRule.getSharedRuleArray(ownerEmail);
		String[][] rules = new String [(rules1!=null ? rules1.length : 0)+(rules2!=null ? rules2.length : 0)][];
		if(rules.length==0)
			return null;
		int i = 0;
		if(rules1!=null)
		{
			for(String[] each : rules1)
				rules[i++]=each;
		}
		if(rules2!=null)
		{
			for(String[] each : rules2)
				rules[i++]=each;
		}
		if(rules.length==0)
			return null;
		return rules;
	}
	
	public static String printRuleArray(String id)
	{	
		String email = GUIStatic.getEmailFromID(id);
		
		String[][] rules = getAllRules(email);
		
		if(rules==null)
			return "";
		
		StringBuilder output = new StringBuilder();
	
		int i = 1;
		for(String[] rule : rules) 
		{
			if(rule.length==9)
				output.append("\trules["+ (i++) +"] = [\""+
	   				rule[0]+"\", \""+
	   				rule[1]+"\", "+
	   				rule[2]+", \""+
	   				rule[3]+"\", \""+
	   				rule[4]+"\", "+
	   				rule[5]+", \""+
	   				rule[6]+"\", \""+
	   				rule[7]+"\", "+
	   				rule[8]+"];\n");
		}
		if(output.length()!=0)
			return output.toString();
		return "";
	}	
}
