package team2awesome.subsystem.rule;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import team2awesome.subsystem.database.Datum;
import team2awesome.subsystem.sensor.Sensor;


public class Formula 
{
	private Set<String> sensorSet = new HashSet<String>();
	
	private boolean staticResult;
	private boolean staticFormula=false;
	
	private int offset;
	
	private String formulaString; 
	private String errMsg;
	
	private EqTriple[][] usableEquations;
	
	public Formula(String formulaString) 
	{
		System.out.println(formulaString);
		if(validateFormula(formulaString))
			this.formulaString = formulaString;
	}
	
	public Formula(boolean value) 
	{
		staticResult=value;
		staticFormula = true;
	}
	

	public boolean checkDatumAgaistFormula(Datum [] allSlice) throws Exception
	{
		if(staticFormula)
			return staticResult;
		
		if(usableEquations.length<1)
		{
			this.errMsg = "No usable formulas found for this Rule";
			throw new Exception();
		}
		boolean [] usableResults = new boolean[usableEquations.length]; 
		
		int i = 0;
		for(EqTriple[] or : usableEquations)
		{
			usableResults[i] = true;
			for(EqTriple and : or)
			{
				if(!and.check(allSlice))
				{
					usableResults[i] = false;
					break;
				}
			}
			if(usableResults[i])
				return true;
			i++;
		}
		return false;
	}

	public String getFormula()
	{
		return this.formulaString;
	}
	
	private boolean validateFormula(String formulaString) 
	{
		try 
		{
			usableEquations = tokenizeFormula(formulaString);
			if(usableEquations==null)
				throw new Exception();
		} 
		catch (Exception e) 
		{	
			if(errMsg==null)
				e.printStackTrace();
			else
				System.out.print(errMsg);
			return false;
		}
		if(!setVaildSensors() || usableEquations==null)
			return false;
		return true;
	}

	private boolean setVaildSensors() 
	{
		//finds sesnsors and checks to ensure they exits..
		//Ensure they only repeat NEVER CHANGE!!
			//ELSE CRAZY LOGIC NEEDED
		boolean err = false;
		
		for(EqTriple[] row : usableEquations)
			for(EqTriple elem : row) 
				if(elem.sensorFieldUnit==null)
				{
					addErrMsg("Must have a Sensor specified in the formula");  //no sensor found
					err = true;
				}
				else 
				{	//TODO!!!!!!!!!!
					if(!Sensor.exists(elem.sensorFieldUnit[0][0]))//, elem.sensorFieldUnit[0][1], elem.sensorFieldUnit[0][2]))
					{	
						addErrMsg("Sensor '"+elem.sensorFieldUnit[0]+"' is not found in database");  //no sensor found
						err = true;
					}
				}
		return !err;
	}

	/**
	 * Seperate each element:
	 *  number(int or float)   = 1 element
	 *  EQoperator (=, >=, ...)= 1 element
	 *  sensor.field@unit	   = 1 element
	 *  parses and KEEPS ORDER
	 *  example:
	 *  I:"sensor.field@unit<80ANDsensor.field@unit>60"
	 *  O:{"sensor.field@unit", "<", "80", "AND", "sensor.field@unit", ">", "60"}
	 *  
	 *  place sensor first ALWAYS and manipulate opperator to fit
	 * @param formula
	 * @return
	 * @throws Exception 
	 */
	private EqTriple[][] tokenizeFormula(String formula) 
	{
		if(formula==null || formula.length()<1)
		{
			this.addErrMsg("Formula not does not exist or is blank");
			return null;
		}
		
		String [][] equations = formulaSplit(formula);
		usableEquations = new EqTriple[equations.length][];
		System.out.println("tokenizeFormula\n\tusableEquations.length= "+usableEquations.length);
		int i = 0;
		for(String[] row : equations)
		{
			int j = 0;
			usableEquations[i] = new EqTriple[row.length];
			for(String elem : row)
			{
				usableEquations[i][j] = new EqTriple(elem);
				
				System.out.println("tokenizeFormula\n\tEqTriple Exists");
				if(usableEquations[i][j].errMsg!=null)
				{
					this.addErrMsg("Error in segment '"+elem+"': "+usableEquations[i][j].errMsg);
				}
				else if(usableEquations[i][j].sensorFieldUnit==null)
				{
					this.addErrMsg("Error parsing segment '"+elem+"': unknown");
				}
				else
				{
					for(String [] each : usableEquations[i][j].sensorFieldUnit)
					this.addSensor(each);
				}
				j++;
			}
			i++;
		}
		return usableEquations;
	}

	private String[][] formulaSplit(String formulaString) {
		String [] or = formulaString.split("OR");
		String [][] matrix = new String[or.length][];
		int i=0;
		for(String elem : or)
		{	
			matrix[i]=elem.split("AND");
			i++;
		}
		
		for(String[] j : matrix)
			for(String k : j)
				System.out.println("formulaSplit\n\telem: "+k);
		return matrix;
	}

	public String getErrMsg() {
		return errMsg;
	}
	
	public void addErrMsg(String msg) 
	{
		if(errMsg==null)
			errMsg = ":<br/>.          "+msg+"<br/>";
		else
			errMsg+= ".          "+msg+"<br/>";
	}
	/*---begin private methods---*/

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the formulaUsable
	 */
	public EqTriple[][] getFormulaUsable() {
		return usableEquations;
	}

	/**
	 * @param formulaUsable the formulaUsable to set
	 */
	public void setFormulaUsable(EqTriple[][] formulaUsable) {
		this.usableEquations = formulaUsable;
	}

	private class EqTriple  
	{
		String [][] sensorFieldUnit;
		int operatorType; //index of operators
		double value;
		final String[] operators =		{"<=", ">=", "==", "!=", "<>", "=", "<", ">"};
		
		//idx of not operators
		final int[] operatorsNOT	= 	{7, 6, 3, 2, 2,	3, 1, 0};
		
		String errMsg = null;
		String valueStr = null;
		
		public EqTriple(String eq)
		{
			try
			{
				System.out.println(eq);
				int i = 0;
				String [] split = null;
				
				for(String op : operators)
				{
					if((split = eq.split(op)).length==2)
						break;
					i++;
				}
				if(i==operators.length)
				{	
					addErrMsg("Expecting exp|operator|exp, found: "+eq);
					throw new Exception();//NOT VALID
				}
				operatorType=i;
				String sensorANDfieldANDunit1 = null;
				String sensorANDfieldANDunit2 = null;
				//if left has sensor in it
				if((split[0].split("@")).length==2)
				{
					sensorANDfieldANDunit1 = split[0];
					
					if(split[1].split("@").length==2)
						sensorANDfieldANDunit2 =split[1]; //if double sensor eq
					else
					{
						sensorANDfieldANDunit2 = null;
						valueStr = split[1];
					}
				}
				//if right has sensor in it
				else if((split[1].split("@")).length==2)
				{	//SWAP
					sensorANDfieldANDunit1 = split[1];
					valueStr = split[0];
					operatorType=operatorsNOT[operatorType];
				}
				else
				{
					addErrMsg("Expecting sensorname.fieldname@unit, or a RealNumber found: "+ split[0] + " " + split[1]);
					throw new Exception();
				}
				
				if(sensorANDfieldANDunit2==null)  //must be:
				{
					sensorFieldUnit = new String[][]{processSensorString(sensorANDfieldANDunit1)};
					value=Double.parseDouble(valueStr);
				}
				else
				{
					sensorFieldUnit = new String[][]{processSensorString(sensorANDfieldANDunit1),processSensorString(sensorANDfieldANDunit2)};
					if(sensorFieldUnit[0][0].equals(sensorFieldUnit[1][0]) && sensorFieldUnit[0][1].equals(sensorFieldUnit[1][1]) && sensorFieldUnit[0][2].equals(sensorFieldUnit[1][2]))
					{
							addErrMsg("Expecting comparison against 2 sensors@fields, found comparison against the same sensor twice : "+ sensorANDfieldANDunit1 + " " + sensorANDfieldANDunit2);
							throw new Exception();
					}
				}
				
				
			}
			catch (NumberFormatException e)
			{
				sensorFieldUnit = null;
				addErrMsg("Expecting decimal number, found: " + valueStr);
				System.out.println(errMsg);
				e.printStackTrace();
			}
			catch (Exception e)
			{
				sensorFieldUnit = null;
				if(errMsg == null)
					e.printStackTrace();
				System.out.println(errMsg);
			}
			
		}
		
		private String[] processSensorString(String sensorANDfieldANDunit) throws Exception 
		{
			String [] process = new String [3];
			if(sensorANDfieldANDunit==null)
				return null;
			String[] split;
			int before= sensorANDfieldANDunit.length();
			int after = (sensorANDfieldANDunit=sensorANDfieldANDunit.replace("NOT", "")).length();
			if(before>after)
			{
				if(((before-after)/3)%2==1)
					operatorType=operatorsNOT[operatorType];
			}
		
			if((split=sensorANDfieldANDunit.split("@")).length==2)
			{
				process[0]=split[0];
				process[2]=split[1];
			}
			else
			{
				addErrMsg("Expecting sensorname.fieldname@unit, found: " + sensorANDfieldANDunit);
				throw new Exception();
			}
			
			if((split=process[0].split("\\.")).length==2)
			{
				process[0]=split[0];
				process[1]=split[1];
			}
			else
			{
				addErrMsg("Expecting sensorname.fieldname, found: " + process[0]);
				throw new Exception();
			}
			
			if(Sensor.get(process[0])==null)
			{
				addErrMsg("Expected sensor '"+process[0]+"' does not exists");
				throw new Exception();
			}
			else
			{
				//TODO ensure this doesnt duplicate uncorrectly
				return process;
			}
			
		}

		public boolean check(Datum [] allSlice)
		{
			//assume only need 2 max!!!!
			double [] sensorReading = getNeedSlice(allSlice);
			double compareValue;
			if(sensorReading.length==2)
				compareValue = sensorReading[1];
			else
				compareValue = this.value;
			switch (this.operatorType)
			{ //{"<=", ">=", "==", "!=", "<>", "=", "<", ">"};
				case 0:	return sensorReading[0] <=  compareValue;  
				case 1:	return sensorReading[0] >=  compareValue;	
				case 2:
				case 5: return sensorReading[0] ==  compareValue;
				case 3: 
				case 4:	return sensorReading[0] !=  compareValue;
				case 6: return sensorReading[0] <   compareValue;
				case 7: return sensorReading[0] >   compareValue;
			}
			return false;
		}
		
		private double[] getNeedSlice(Datum[] allSlice) 
		{
			int i = 0;
			double [] result = new double[sensorFieldUnit.length];
			for(String [] eqSensorInfo : this.sensorFieldUnit)
				for(Datum datum : allSlice)
					if(datum.getSensorName().equals(eqSensorInfo[0]) && datum.getFieldName().equals(eqSensorInfo[1]) && datum.getUnit().equals(eqSensorInfo[2]))
					{
						result[i++]=datum.getReading();
						break;
					}
			return result;
			
		}

		public void addErrMsg(String msg) 
		{
			if(errMsg==null)
				errMsg = ":<br/>.          "+msg+"<br/>";
			else
				errMsg+= ".          "+msg+"<br/>";
		}
	}
	
	public String getFormulaString() {
		return formulaString;
	}

	/**
	 * @param sensorSets the sensorSets to set
	 */
	public boolean addSensor(String[] sensor)
	{
		return this.getSensorSet().add(sensor[0]+"."+sensor[1]+"@"+sensor[2]);
	}

	/**
	 * @return the sensorSet
	 */
	public Set<String> getSensorSet() {
		return sensorSet;
	}
}
