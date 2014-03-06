package team2awesome.subsystem.gui;

import java.util.LinkedList;

import javax.servlet.annotation.WebServlet;

import team2awesome.subsystem.database.DataSet;
import team2awesome.subsystem.database.Datum;

/**
 * Servlet implementation class Server
 */
@WebServlet("/GUIDash")
public class GUIDash extends GUI
{

	private static LinkedList<DataSet[]> completed = new LinkedList<DataSet[]>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GUIDash() {
		// TODO LOT
	}

	/**
	 * @param done the done to set
	 */
	public static void addToCompleted(DataSet[] done) 
	{
		System.out.println("DONE ADDED!!");
		if(done==null)
		{
			System.out.println("DONE = NULL!!");
			return;
		}
		//TODO remove STALE
		completed.add(done);
	}
	
	private DataSet[][][] getUsersCompleted(String email)
	{
		LinkedList<LinkedList<DataSet[]>> monitors = new LinkedList<LinkedList<DataSet[]>>();
		LinkedList<DataSet[]> newMonitor;
		
		for(DataSet[] eachRule : completed)
		{	
			if(eachRule[0].getOwnerEmail().equals(email))
			{
				boolean added = false;
				for(LinkedList<DataSet[]> eachMonitor : monitors)
					if(eachRule[0].getMonitor().equals(eachMonitor.getFirst()[0].getMonitor()) && Math.abs((double)(eachRule[0].getTimeRan().getTime()-eachMonitor.getFirst()[0].getTimeRan().getTime()))<2000)
					{
						eachMonitor.add(eachRule);
						added = true;
						break;
					}
				if(!added)
				{
					newMonitor = new LinkedList<DataSet[]>();
					newMonitor.add(eachRule);
					monitors.add(newMonitor);
				}			
			}
		}
		DataSet[][][] allMonitors = new DataSet[monitors.size()][][];
		
		int i = 0;
		for(LinkedList<DataSet[]> eachMonitor : monitors)
		{
			int j = 0;
			allMonitors[i] = new DataSet[eachMonitor.size()][];
			for(DataSet[] eachRule : eachMonitor)
			{
				System.out.println("RULES =" + eachRule.length + " " + i + ":" + j);
				allMonitors[i][j++]=eachRule;
			}
			i++;
		}
		
		System.out.println("MONITORS =" + allMonitors.length);
		return allMonitors;
	}

	
	public String printWholePage(String email)
	{
		System.out.println("DISPLAY");
		return printAllMyMonitors(getUsersCompleted(email));
	}
	
	private String printAllMyMonitors(DataSet[][][] allMonitors)
	{

		StringBuilder page = new StringBuilder();

		page.append("<table border=\"1\">\n");
		
		int index=0;
		if(allMonitors==null)
			return "";
		
		for(int i = allMonitors.length-1; i>=0; i--)
		{
			page.append("<table border=\"1\">\n");
			page.append("\t<tr>\n\t\t<td colspan=\"2\">\n\t\t\t<h3>\n\t\t\t\tMonitor "+ ++index +": '"+allMonitors[i][0][0].getMonitor()+"' ran on "+allMonitors[i][0][0].getTimeRan().toString()+"\n\t\t\t</h3>\n\t\t</td>\n\t</tr>\n");
			page.append(printMyMonitor(allMonitors[i], index));
			page.append("</table>\n");
		}
		
		page.append("</table>\n");
		
		if(index==0)
			return "";
		return page.toString();
	}
	
	private String printMyMonitor(DataSet[][] eachMonitor, int i)
	{
		StringBuilder page = new StringBuilder();
		for(DataSet[] eachRule : eachMonitor)
			page.append(printMyRule(eachRule, i++));
		page.append("</table>\n");
		return page.toString();
	}
	
	private String printMyRule(DataSet[] eachRule, int i)
	{
		StringBuilder page = new StringBuilder();
		page.append("\t<tr>\n\t\t<td colspan=\"2\">\n\t\t\t<h4>\n\t\t\t\tRule '"+ eachRule[0].getRule() +"':\n\t\t\t</h4>\n\t\t\t<p>\n\t\t\t\t"+eachRule[0].getNDL()+"\n\t\t\t</p>\n\t\t</td>\n\t</tr>\n");
		page.append("\t<tr>\n");
		page.append(printRuleGraph(eachRule, i));
		page.append(printRuleTable(eachRule));
		page.append("\t</tr>\n");
		return page.toString();
	}
	
	private String printRuleGraph(DataSet[] eachRule, int i)
	{
		StringBuilder page = new StringBuilder();
		page.append("\t\t<td>\n");
		page.append(createGraph(eachRule, i));
		page.append("\t\t</td>\n");
		return page.toString();	
	}
	
	private String printRuleTable(DataSet[] eachRule)
	{
		StringBuilder page = new StringBuilder();
		page.append("\t\t<td><div style =\"height:450px;width:550px; overflow:auto;\">\n");

		page.append(printTableData(eachRule));
		page.append("\t\t</div></td>\n");
		return page.toString();		
	}
	
	private String printTableData(DataSet[] eachRule) 
	{
		StringBuilder page = new StringBuilder();
		
		for(DataSet eachDataSet : eachRule)
		{	
			for(Datum eachDatum : eachDataSet.getDatums())
			{
				page.append(eachDatum.print()+"<br/>\n");
			}
		}
		
		return page.toString();
	}

	private String createGraph(DataSet[] eachRule, int i)//(String newMonitorid,String MonitorChartid, String Data)
	{
		/*
		<div id="chartContainer">FusionCharts will load here!</div>          
	    <script type="text/javascript"><!--     
	      FusionCharts.printManager.enabled(true);

	      var myChart = new FusionCharts( "FusionCharts/Column3D.swf", 
	        "myChartId", "400", "300", "0", "0" );
	      myChart.setDataURL("Data.xml");
	      myChart.render("chartContainer");
	                    
	      FusionCharts.addEventListener ( 
	          FusionChartsEvents.PrintReadyStateChange , 
	          function (identifier, parameter) {
	            if(parameter.ready){ 
	               alert("Chart is now ready for printing.");
	               document.getElementById('printButton').disabled = false;
	            }
	        });
	    // -->     
	    </script>        
	    <input type="button" onclick="FusionCharts.printManager.managedPrint()"
	        value="Managed Print" disabled="disabled" id="printButton" >
	  </body> 
	  */
		StringBuilder page = new StringBuilder();
		page.append("\t\t\t<div id=" +"\"monitor_"+i+"\""+ ">Graph Failed to Load :(</div>\n");  
		page.append("\t\t\t<script type=\"text/javascript\">\n");
		page.append("\t\t\t\tvar myChart_"+i+"= new FusionCharts(\""  + "MSLine"+ "\"" +", "+ "\"monitorID_"+i + "\"" +", "+ "\"550\""+", "+ "\"450\"" +", "+ "\"0\"" +");\n"); 	      
		page.append("\t\t\t\tmyChart_"+i + ".setXMLData(\n\n"  + printGraphData(eachRule) +  "\n\n\t\t\t\t" +");\n");
		page.append("\t\t\t\tmyChart_"+i + ".render(\"monitor_"+i+"\");\n");
		page.append("\t\t\t</script>\n");
				
		return page.toString();
	}
	
	
	private String printGraphData(DataSet[] eachRule)
	{
		int i;
		StringBuilder page = new StringBuilder();
		page.append("\t\t\t\"<chart caption='"+eachRule[0].getRule()+"' numdivlines='4' showValues='0' numVDivLines='22' anchorRadius='2' labelDisplay='rotate' slantLabels='0' lineThickness='4' palette='5' connectNullData='0' xtLabelManagement='0'>\"+\n\n");
		
		StringBuilder categories = new StringBuilder();
		StringBuilder sensorData = new StringBuilder();
		
		
		StringBuilder[] anomally   = new StringBuilder[eachRule.length];
		StringBuilder[] scopeSet   = new StringBuilder[eachRule.length];
		StringBuilder[] scopeStart = new StringBuilder[eachRule.length];
		
		for(i = 0; i < eachRule.length; i++)
		{
			anomally[i]   = new StringBuilder();
			scopeSet[i]   = new StringBuilder();
			scopeStart[i] = new StringBuilder();
			anomally[i].append("\t\t\t\t\"<dataset seriesName='Anomalys for "+eachRule[i].getSensorName()[0]+i+"' color='FF0000' anchorSides='0' anchorRadius='6' anchorBgColor='FF0000' anchorBorderColor='FFFFFF'>\"+\n");
			scopeSet[i].append("\t\t\t\t\"<dataset seriesName='ScopeSet for "+eachRule[i].getSensorName()[0]+i+"' color='FFFF00' anchorSides='0' anchorRadius='6' anchorBgColor='FFFF00' anchorBorderColor='FFFFFF'>\"+\n");
			scopeStart[i].append("\t\t\t\t\"<dataset seriesName='ScopeStart for "+eachRule[i].getSensorName()[0]+i+"' color='FF6600' anchorSides='0' anchorRadius='6' anchorBgColor='FF6600' anchorBorderColor='FFFFFF'>\"+\n");
		}
		
		i=0;  
		boolean firstStream = true;
		for(DataSet eachStream : eachRule)
		{
			boolean firstDatum = true;
			for(Datum datum : eachStream.getDatums())
			{	
				if(firstStream)
					categories.append("\t\t\t\t\t\"<category label='"+datum.getTime().getTimeShort()+"' />\"+\n");
				
				if(firstDatum)
				{
					sensorData.append("\t\t\t\t\"<dataset seriesname= '"+datum.getSensorName()+""+datum.getFieldName()+""+datum.getUnit()+"'>\"+\n");
					firstDatum = false;
				}
				else
					sensorData.append("\t\t\t\t\t\"<set value='"+datum.getReading()+"' />\"+\n");
				
				int flag = datum.getFlag();
				
				if((flag & 4)!=0) //anomally!!
					anomally[i].append("\t\t\t\t\t\"<set value='"+datum.getReading()+"' />\"+\n");
				else
					anomally[i].append("\t\t\t\t\t\"<set value='' />\"+\n");
				
				if((flag & 2)!=0) //scopeSet!!
					scopeSet[i].append("\t\t\t\t\t\"<set value='"+datum.getReading()+"' />\"+\n");
				else
					scopeSet[i].append("\t\t\t\t\t\"<set value='' />\"+\n");
				
				if((flag & 1)!=0) //scopeStart!!
					scopeStart[i].append("\t\t\t\t\t\"<set value='"+datum.getReading()+"' />\"+\n");
				else
					scopeStart[i].append("\t\t\t\t\t\"<set value='' />\"+\n");
			}
			firstStream=false;
			sensorData.append("\t\t\t\t\"</dataset>\"+\n\n");
			anomally[i].append("\t\t\t\t\"</dataset>\"+\n\n");
			scopeSet[i].append("\t\t\t\t\"</dataset>\"+\n\n");
			scopeStart[i].append("\t\t\t\t\"</dataset>\"+\n\n");
			i++;
		}
		
		/*
			<trendlines>
				<line startValue='50' color='91C728' displayValue='Max' showOnTop='1'/>
			</trendlines>*/
		
		page.append("\t\t\t\t\"<categories>\"+\n");
		page.append(categories.toString());
		page.append("\t\t\t\t\"</categories>\"+\n\n");
		page.append(sensorData.toString());
		
		for(i = 0; i < 1/*eachRule.length*/; i++) //TODO
		{
			page.append(scopeStart[i].toString());
			page.append(scopeSet[i].toString());
			page.append(anomally[i].toString());
		}
		page.append("\t\t\t\"</chart>\"\n");
		
		return page.toString();
	}
}
