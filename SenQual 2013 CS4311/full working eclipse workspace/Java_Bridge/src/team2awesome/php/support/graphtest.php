
<!DOCTYPE html>
<!-- saved from url=(0043)http://getbootstrap.com/examples/jumbotron/ -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Team2Awesome">
    <link rel="shortcut icon" href="http://getbootstrap.com/docs-assets/ico/favicon.png">
    <script>
    function check_tel(id)
    {
    	number = document.getElementById(id).value;

    	if(number == "")
    		return true;

    	numArray = number.match(/\d/g);

    	number = numArray.join("");
    	
    	document.getElementById(id).value = number;
    	if(number.length!=10)
    	{
    		alert("Phone number must be 10 digits");
    		return false;
    	}
    	return true;
    	
    }
   	function check_email(id)
   	{
    	email = document.getElementById(id).value;
		
		var name   = email.split("@");
		
		if(name.length!=2 || name[0].length<=1)
		{
			alert("The e-mail address format is not valid. Please reenter.");
			return false;
		}
		var domain = name[1].split(".");
    	
    	if(domain.length<2 || domain[0].length<=1 || domain[1].length<=1)
    	{
			alert("The e-mail address format is not valid. Please reenter.");
			return false;
		}
   		return true;
   	}
    </script>
      <title>Dashboard Page</title>

    <!-- Bootstrap core CSS -->
    <link href="http://getbootstrap.com/dist/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="http://getbootstrap.com/examples/jumbotron/jumbotron.css" rel="stylesheet">
    
    	<style type="text/css"></style></head>

  <body style="">
  
    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
         <!--  <a class="navbar-brand" href="#">Project name</a>  -->
          <ul class="nav navbar-nav">
            <li><a href="../main/index.php">Home</a></li>
            <li class="active"><a href="../main/dashboard.php">Dashboard</a></li>
            <li><a href="../main/monitor.php">Monitor</a></li>
            <li><a href="../main/rule.php">Rules</a></li>
            <li><a href="../main/sensors.php">Sensors</a></li>
            <li><a href="../main/profile.php">Profile</a></li>
          </ul>
        </div>
        <div class="navbar-collapse collapse">
       		<form class="navbar-form navbar-right" role="form" action="../main/index.php" method="post">
       			<input type="hidden" name="logout" value="logout"/>
            	<button type="submit" class="btn btn-success">log out</button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </div>
    
<script type="text/javascript" src="../support/FusionCharts/FusionCharts.js">
</script>
<style>
div.scroll
{
overflow:scroll;
}
</style>
</head>
<body>


<!-- Main jumbotron for a primary marketing message or call to action -->
    
    <div class="jumbotron">
      <div class="container">
Your monitored data:
	</div>
	</div>
	<div class="container">
	<table border="1">
	<tr>
		<td colspan="2">
			<h3>
				Monitor 1: 'EastCoast' ran on Mon Dec 09 19:59:39 GMT-07:00 2013
			</h3>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<h4>
				Rule 'nyc_always_over_40':
			</h4>
			<p>
				null
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<div id="monitor_1">Graph Failed to Load :(</div>
			<script type="text/javascript">
				FusionCharts.printManager.enabled(true);
				var myChart_1= new FusionCharts("MSLine", "monitorID_1", "550", "450", "0");
				myChart_1.setXMLData(

			"<chart caption='nyc_always_over_40' numdivlines='4' showValues='0' numVDivLines='22' anchorRadius='2' labelDisplay='rotate' slantLabels='0' lineThickness='4' palette='5' connectNullData='0' xtLabelManagement='0'>"+

				"<categories>"+
					"<category label='2013/11/21 0:0' />"+
					"<category label='2013/11/21 0:15' />"+
					"<category label='2013/11/21 0:30' />"+
					"<category label='2013/11/21 0:45' />"+
					"<category label='2013/11/21 1:0' />"+
					"<category label='2013/11/21 1:15' />"+
					"<category label='2013/11/21 1:30' />"+
					"<category label='2013/11/21 1:45' />"+
					"<category label='2013/11/21 2:0' />"+
					"<category label='2013/11/21 2:15' />"+
					"<category label='2013/11/21 2:30' />"+
					"<category label='2013/11/21 2:45' />"+
					"<category label='2013/11/21 3:0' />"+
					"<category label='2013/11/21 3:15' />"+
					"<category label='2013/11/21 3:30' />"+
					"<category label='2013/11/21 3:45' />"+
					"<category label='2013/11/21 4:0' />"+
					"<category label='2013/11/21 4:15' />"+
					"<category label='2013/11/21 4:30' />"+
					"<category label='2013/11/21 4:45' />"+
					"<category label='2013/11/21 5:0' />"+
					"<category label='2013/11/21 5:15' />"+
					"<category label='2013/11/21 5:30' />"+
					"<category label='2013/11/21 5:45' />"+
					"<category label='2013/11/21 6:0' />"+
					"<category label='2013/11/21 6:15' />"+
					"<category label='2013/11/21 6:30' />"+
					"<category label='2013/11/21 6:45' />"+
					"<category label='2013/11/21 7:0' />"+
					"<category label='2013/11/21 7:15' />"+
					"<category label='2013/11/21 7:30' />"+
					"<category label='2013/11/21 7:45' />"+
					"<category label='2013/11/21 8:0' />"+
					"<category label='2013/11/21 8:15' />"+
					"<category label='2013/11/21 8:30' />"+
					"<category label='2013/11/21 8:45' />"+
					"<category label='2013/11/21 9:0' />"+
					"<category label='2013/11/21 9:15' />"+
					"<category label='2013/11/21 9:30' />"+
					"<category label='2013/11/21 9:45' />"+
					"<category label='2013/11/21 10:0' />"+
					"<category label='2013/11/21 10:15' />"+
					"<category label='2013/11/21 10:30' />"+
					"<category label='2013/11/21 10:45' />"+
					"<category label='2013/11/21 11:0' />"+
					"<category label='2013/11/21 11:15' />"+
					"<category label='2013/11/21 11:30' />"+
					"<category label='2013/11/21 11:45' />"+
					"<category label='2013/11/21 12:0' />"+
					"<category label='2013/11/21 12:15' />"+
					"<category label='2013/11/21 12:30' />"+
					"<category label='2013/11/21 12:45' />"+
					"<category label='2013/11/21 13:0' />"+
					"<category label='2013/11/21 13:15' />"+
					"<category label='2013/11/21 13:30' />"+
					"<category label='2013/11/21 13:45' />"+
					"<category label='2013/11/21 14:0' />"+
					"<category label='2013/11/21 14:15' />"+
					"<category label='2013/11/21 14:30' />"+
					"<category label='2013/11/21 14:45' />"+
					"<category label='2013/11/21 15:0' />"+
					"<category label='2013/11/21 15:15' />"+
					"<category label='2013/11/21 15:30' />"+
					"<category label='2013/11/21 15:45' />"+
					"<category label='2013/11/21 16:0' />"+
					"<category label='2013/11/21 16:15' />"+
					"<category label='2013/11/21 16:30' />"+
					"<category label='2013/11/21 16:45' />"+
					"<category label='2013/11/21 17:0' />"+
					"<category label='2013/11/21 17:15' />"+
					"<category label='2013/11/21 17:30' />"+
					"<category label='2013/11/21 17:45' />"+
					"<category label='2013/11/21 18:0' />"+
					"<category label='2013/11/21 18:15' />"+
					"<category label='2013/11/21 18:30' />"+
					"<category label='2013/11/21 18:45' />"+
					"<category label='2013/11/21 19:0' />"+
					"<category label='2013/11/21 19:15' />"+
					"<category label='2013/11/21 19:30' />"+
					"<category label='2013/11/21 19:45' />"+
					"<category label='2013/11/21 20:0' />"+
					"<category label='2013/11/21 20:15' />"+
					"<category label='2013/11/21 20:30' />"+
					"<category label='2013/11/21 20:45' />"+
					"<category label='2013/11/21 21:0' />"+
					"<category label='2013/11/21 21:15' />"+
					"<category label='2013/11/21 21:30' />"+
					"<category label='2013/11/21 21:45' />"+
					"<category label='2013/11/21 22:0' />"+
					"<category label='2013/11/21 22:15' />"+
					"<category label='2013/11/21 22:30' />"+
					"<category label='2013/11/21 22:45' />"+
					"<category label='2013/11/21 23:0' />"+
					"<category label='2013/11/21 23:15' />"+
					"<category label='2013/11/21 23:30' />"+
					"<category label='2013/11/21 23:45' />"+
					"<category label='2013/11/22 0:0' />"+
					"<category label='2013/11/22 0:15' />"+
					"<category label='2013/11/22 0:30' />"+
					"<category label='2013/11/22 0:45' />"+
					"<category label='2013/11/22 1:0' />"+
					"<category label='2013/11/22 1:15' />"+
					"<category label='2013/11/22 1:30' />"+
					"<category label='2013/11/22 1:45' />"+
					"<category label='2013/11/22 2:0' />"+
					"<category label='2013/11/22 2:15' />"+
					"<category label='2013/11/22 2:30' />"+
					"<category label='2013/11/22 2:45' />"+
					"<category label='2013/11/22 3:0' />"+
					"<category label='2013/11/22 3:15' />"+
					"<category label='2013/11/22 3:30' />"+
					"<category label='2013/11/22 3:45' />"+
					"<category label='2013/11/22 4:0' />"+
					"<category label='2013/11/22 4:15' />"+
					"<category label='2013/11/22 4:30' />"+
					"<category label='2013/11/22 4:45' />"+
					"<category label='2013/11/22 5:0' />"+
					"<category label='2013/11/22 5:15' />"+
					"<category label='2013/11/22 5:30' />"+
					"<category label='2013/11/22 5:45' />"+
					"<category label='2013/11/22 6:0' />"+
					"<category label='2013/11/22 6:15' />"+
					"<category label='2013/11/22 6:30' />"+
					"<category label='2013/11/22 6:45' />"+
					"<category label='2013/11/22 7:0' />"+
					"<category label='2013/11/22 7:15' />"+
					"<category label='2013/11/22 7:30' />"+
					"<category label='2013/11/22 7:45' />"+
					"<category label='2013/11/22 8:0' />"+
					"<category label='2013/11/22 8:15' />"+
					"<category label='2013/11/22 8:30' />"+
					"<category label='2013/11/22 8:45' />"+
					"<category label='2013/11/22 9:0' />"+
					"<category label='2013/11/22 9:15' />"+
					"<category label='2013/11/22 9:30' />"+
					"<category label='2013/11/22 9:45' />"+
					"<category label='2013/11/22 10:0' />"+
					"<category label='2013/11/22 10:15' />"+
					"<category label='2013/11/22 10:30' />"+
					"<category label='2013/11/22 10:45' />"+
					"<category label='2013/11/22 11:0' />"+
					"<category label='2013/11/22 11:15' />"+
					"<category label='2013/11/22 11:30' />"+
					"<category label='2013/11/22 11:45' />"+
					"<category label='2013/11/22 12:0' />"+
					"<category label='2013/11/22 12:15' />"+
					"<category label='2013/11/22 12:30' />"+
					"<category label='2013/11/22 12:45' />"+
					"<category label='2013/11/22 13:0' />"+
					"<category label='2013/11/22 13:15' />"+
					"<category label='2013/11/22 13:30' />"+
					"<category label='2013/11/22 13:45' />"+
					"<category label='2013/11/22 14:0' />"+
					"<category label='2013/11/22 14:15' />"+
					"<category label='2013/11/22 14:30' />"+
					"<category label='2013/11/22 14:45' />"+
					"<category label='2013/11/22 15:0' />"+
					"<category label='2013/11/22 15:15' />"+
					"<category label='2013/11/22 15:30' />"+
					"<category label='2013/11/22 15:45' />"+
					"<category label='2013/11/22 16:0' />"+
					"<category label='2013/11/22 16:15' />"+
					"<category label='2013/11/22 16:30' />"+
					"<category label='2013/11/22 16:45' />"+
					"<category label='2013/11/22 17:0' />"+
					"<category label='2013/11/22 17:15' />"+
					"<category label='2013/11/22 17:30' />"+
					"<category label='2013/11/22 17:45' />"+
					"<category label='2013/11/22 18:0' />"+
					"<category label='2013/11/22 18:15' />"+
					"<category label='2013/11/22 18:30' />"+
					"<category label='2013/11/22 18:45' />"+
					"<category label='2013/11/22 19:0' />"+
					"<category label='2013/11/22 19:15' />"+
					"<category label='2013/11/22 19:30' />"+
					"<category label='2013/11/22 19:45' />"+
					"<category label='2013/11/22 20:0' />"+
					"<category label='2013/11/22 20:15' />"+
					"<category label='2013/11/22 20:30' />"+
					"<category label='2013/11/22 20:45' />"+
					"<category label='2013/11/22 21:0' />"+
					"<category label='2013/11/22 21:15' />"+
					"<category label='2013/11/22 21:30' />"+
					"<category label='2013/11/22 21:45' />"+
					"<category label='2013/11/22 22:0' />"+
					"<category label='2013/11/22 22:15' />"+
					"<category label='2013/11/22 22:30' />"+
					"<category label='2013/11/22 22:45' />"+
					"<category label='2013/11/22 23:0' />"+
					"<category label='2013/11/22 23:15' />"+
					"<category label='2013/11/22 23:30' />"+
					"<category label='2013/11/22 23:45' />"+
					"<category label='2013/11/23 0:0' />"+
					"<category label='2013/11/23 0:15' />"+
					"<category label='2013/11/23 0:30' />"+
					"<category label='2013/11/23 0:45' />"+
					"<category label='2013/11/23 1:0' />"+
					"<category label='2013/11/23 1:15' />"+
					"<category label='2013/11/23 1:30' />"+
					"<category label='2013/11/23 1:45' />"+
					"<category label='2013/11/23 2:0' />"+
					"<category label='2013/11/23 2:15' />"+
					"<category label='2013/11/23 2:30' />"+
					"<category label='2013/11/23 2:45' />"+
					"<category label='2013/11/23 3:0' />"+
					"<category label='2013/11/23 3:15' />"+
					"<category label='2013/11/23 3:30' />"+
					"<category label='2013/11/23 3:45' />"+
					"<category label='2013/11/23 4:0' />"+
					"<category label='2013/11/23 4:15' />"+
					"<category label='2013/11/23 4:30' />"+
					"<category label='2013/11/23 4:45' />"+
					"<category label='2013/11/23 5:0' />"+
					"<category label='2013/11/23 5:15' />"+
					"<category label='2013/11/23 5:30' />"+
					"<category label='2013/11/23 5:45' />"+
					"<category label='2013/11/23 6:0' />"+
					"<category label='2013/11/23 6:15' />"+
					"<category label='2013/11/23 6:30' />"+
					"<category label='2013/11/23 6:45' />"+
					"<category label='2013/11/23 7:0' />"+
					"<category label='2013/11/23 7:15' />"+
					"<category label='2013/11/23 7:30' />"+
					"<category label='2013/11/23 7:45' />"+
					"<category label='2013/11/23 8:0' />"+
					"<category label='2013/11/23 8:15' />"+
					"<category label='2013/11/23 8:30' />"+
					"<category label='2013/11/23 8:45' />"+
					"<category label='2013/11/23 9:0' />"+
					"<category label='2013/11/23 9:15' />"+
					"<category label='2013/11/23 9:30' />"+
					"<category label='2013/11/23 9:45' />"+
					"<category label='2013/11/23 10:0' />"+
					"<category label='2013/11/23 10:15' />"+
					"<category label='2013/11/23 10:30' />"+
					"<category label='2013/11/23 10:45' />"+
					"<category label='2013/11/23 11:0' />"+
					"<category label='2013/11/23 11:15' />"+
					"<category label='2013/11/23 11:30' />"+
					"<category label='2013/11/23 11:45' />"+
					"<category label='2013/11/23 12:0' />"+
					"<category label='2013/11/23 12:15' />"+
					"<category label='2013/11/23 12:30' />"+
					"<category label='2013/11/23 12:45' />"+
					"<category label='2013/11/23 13:0' />"+
					"<category label='2013/11/23 13:15' />"+
					"<category label='2013/11/23 13:30' />"+
					"<category label='2013/11/23 13:45' />"+
					"<category label='2013/11/23 14:0' />"+
					"<category label='2013/11/23 14:15' />"+
					"<category label='2013/11/23 14:30' />"+
					"<category label='2013/11/23 14:45' />"+
					"<category label='2013/11/23 15:0' />"+
					"<category label='2013/11/23 15:15' />"+
					"<category label='2013/11/23 15:30' />"+
					"<category label='2013/11/23 15:45' />"+
					"<category label='2013/11/23 16:0' />"+
					"<category label='2013/11/23 16:15' />"+
					"<category label='2013/11/23 16:30' />"+
					"<category label='2013/11/23 16:45' />"+
					"<category label='2013/11/23 17:0' />"+
					"<category label='2013/11/23 17:15' />"+
					"<category label='2013/11/23 17:30' />"+
					"<category label='2013/11/23 17:45' />"+
					"<category label='2013/11/23 18:0' />"+
					"<category label='2013/11/23 18:15' />"+
					"<category label='2013/11/23 18:30' />"+
					"<category label='2013/11/23 18:45' />"+
					"<category label='2013/11/23 19:0' />"+
					"<category label='2013/11/23 19:15' />"+
					"<category label='2013/11/23 19:30' />"+
					"<category label='2013/11/23 19:45' />"+
					"<category label='2013/11/23 20:0' />"+
					"<category label='2013/11/23 20:15' />"+
					"<category label='2013/11/23 20:30' />"+
					"<category label='2013/11/23 20:45' />"+
					"<category label='2013/11/23 21:0' />"+
					"<category label='2013/11/23 21:15' />"+
					"<category label='2013/11/23 21:30' />"+
					"<category label='2013/11/23 21:45' />"+
					"<category label='2013/11/23 22:0' />"+
					"<category label='2013/11/23 22:15' />"+
					"<category label='2013/11/23 22:30' />"+
					"<category label='2013/11/23 22:45' />"+
					"<category label='2013/11/23 23:0' />"+
					"<category label='2013/11/23 23:15' />"+
					"<category label='2013/11/23 23:30' />"+
				"</categories>"+

				"<dataset seriesname= 'NYC_temptemperatureF'>"+
					"<set value='55.35332665504479' />"+
					"<set value='55.88676859307207' />"+
					"<set value='58.60790666783789' />"+
					"<set value='55.655950936096936' />"+
					"<set value='59.59612198037519' />"+
					"<set value='59.96602859766651' />"+
					"<set value='55.72336528709729' />"+
					"<set value='61.05225764481259' />"+
					"<set value='60.578465099074634' />"+
					"<set value='58.557937050194965' />"+
					"<set value='61.23676749148111' />"+
					"<set value='62.65603154439712' />"+
					"<set value='61.43039277812871' />"+
					"<set value='63.27922459060872' />"+
					"<set value='60.61693101222938' />"+
					"<set value='65.91483463526995' />"+
					"<set value='61.39760927089647' />"+
					"<set value='62.726885109615225' />"+
					"<set value='64.13462639399717' />"+
					"<set value='66.4358303923037' />"+
					"<set value='65.8554155487067' />"+
					"<set value='65.96145551382334' />"+
					"<set value='68.66132993908977' />"+
					"<set value='70.02499797414949' />"+
					"<set value='72.22117065943455' />"+
					"<set value='68.12375714074905' />"+
					"<set value='67.69507456796855' />"+
					"<set value='75.49138998369273' />"+
					"<set value='74.44039821906183' />"+
					"<set value='74.54796038610718' />"+
					"<set value='73.81439438363806' />"+
					"<set value='78.04644373800464' />"+
					"<set value='75.56233935481873' />"+
					"<set value='75.51982664884562' />"+
					"<set value='77.80516353365107' />"+
					"<set value='78.24254333816256' />"+
					"<set value='77.4177513837929' />"+
					"<set value='78.52129911291685' />"+
					"<set value='78.90526211741854' />"+
					"<set value='79.91553624461089' />"+
					"<set value='78.56507356998637' />"+
					"<set value='78.40124423487845' />"+
					"<set value='80.96844125870196' />"+
					"<set value='80.5227386287156' />"+
					"<set value='81.48072149523378' />"+
					"<set value='83.41709810827516' />"+
					"<set value='79.86152870335889' />"+
					"<set value='83.60110143701878' />"+
					"<set value='82.43522500080338' />"+
					"<set value='81.50344264062193' />"+
					"<set value='83.07819493453337' />"+
					"<set value='80.80966502487007' />"+
					"<set value='82.61918733565123' />"+
					"<set value='81.4411046736189' />"+
					"<set value='83.37788422282216' />"+
					"<set value='80.16616249047712' />"+
					"<set value='81.8615966533952' />"+
					"<set value='82.82424340514318' />"+
					"<set value='79.24019702401222' />"+
					"<set value='77.63965823387582' />"+
					"<set value='76.7178502409578' />"+
					"<set value='76.85302946910903' />"+
					"<set value='81.06296350824444' />"+
					"<set value='76.21938093030286' />"+
					"<set value='77.79594415666534' />"+
					"<set value='77.01592561284835' />"+
					"<set value='78.32646327483431' />"+
					"<set value='74.94442840306193' />"+
					"<set value='71.93660988447898' />"+
					"<set value='74.66821872063288' />"+
					"<set value='74.39670279351392' />"+
					"<set value='70.14976724042245' />"+
					"<set value='68.2667337597439' />"+
					"<set value='69.36232222620701' />"+
					"<set value='72.24734013117602' />"+
					"<set value='67.36089205852336' />"+
					"<set value='68.20515703753992' />"+
					"<set value='67.54043860604642' />"+
					"<set value='66.14273336532361' />"+
					"<set value='64.85265876865205' />"+
					"<set value='63.21590709518841' />"+
					"<set value='65.86144198151698' />"+
					"<set value='66.24366751265703' />"+
					"<set value='58.87712787346256' />"+
					"<set value='60.47605381032127' />"+
					"<set value='62.00129096670372' />"+
					"<set value='60.17258277948272' />"+
					"<set value='60.96076595962905' />"+
					"<set value='60.65370213930335' />"+
					"<set value='61.23991700783537' />"+
					"<set value='57.80656861318459' />"+
					"<set value='59.473039037763996' />"+
					"<set value='58.676133960625435' />"+
					"<set value='56.52734373518079' />"+
					"<set value='57.95401673739921' />"+
					"<set value='46.80279173112797' />"+
					"<set value='49.964539310275356' />"+
					"<set value='45.188440002169195' />"+
					"<set value='47.00354843238851' />"+
					"<set value='45.9960545597062' />"+
					"<set value='46.01533179993261' />"+
					"<set value='46.82834073977021' />"+
					"<set value='49.068229955255894' />"+
					"<set value='48.93167817454136' />"+
					"<set value='51.26023904441951' />"+
					"<set value='49.73125819743935' />"+
					"<set value='47.61904796003375' />"+
					"<set value='49.33667366285652' />"+
					"<set value='52.04350823162927' />"+
					"<set value='52.55666871839088' />"+
					"<set value='50.08934830778113' />"+
					"<set value='53.047999968789874' />"+
					"<set value='52.150810417676325' />"+
					"<set value='51.58488210155089' />"+
					"<set value='53.333280561075085' />"+
					"<set value='57.79999005962861' />"+
					"<set value='55.16039641271969' />"+
					"<set value='57.909923063035535' />"+
					"<set value='56.51482969448804' />"+
					"<set value='58.85610121443278' />"+
					"<set value='59.46646684724755' />"+
					"<set value='58.62744821180624' />"+
					"<set value='58.314033510552306' />"+
					"<set value='62.48993914603548' />"+
					"<set value='61.345181534253385' />"+
					"<set value='61.38836891912881' />"+
					"<set value='63.47169359305606' />"+
					"<set value='66.27837518833365' />"+
					"<set value='64.52442105241323' />"+
					"<set value='68.72485084623037' />"+
					"<set value='64.98769498746559' />"+
					"<set value='68.53376864484832' />"+
					"<set value='70.46541436501596' />"+
					"<set value='69.49767065261457' />"+
					"<set value='69.73817601038053' />"+
					"<set value='68.9728091031675' />"+
					"<set value='71.96766456732202' />"+
					"<set value='69.66254643866051' />"+
					"<set value='71.88323747440757' />"+
					"<set value='73.89632611514955' />"+
					"<set value='73.76824737576675' />"+
					"<set value='73.30800751836219' />"+
					"<set value='70.1996414545025' />"+
					"<set value='71.73755155076228' />"+
					"<set value='72.52250981641146' />"+
					"<set value='72.57864094464028' />"+
					"<set value='72.39203306644227' />"+
					"<set value='73.82577222144504' />"+
					"<set value='69.92545986386781' />"+
					"<set value='71.66512146617723' />"+
					"<set value='71.511604289643' />"+
					"<set value='70.60804929289009' />"+
					"<set value='71.7616886989402' />"+
					"<set value='70.51630175510411' />"+
					"<set value='71.84279573932943' />"+
					"<set value='70.37369967062011' />"+
					"<set value='67.35650172814664' />"+
					"<set value='68.30877887862876' />"+
					"<set value='69.0065897062605' />"+
					"<set value='65.73451370735268' />"+
					"<set value='68.24201974369086' />"+
					"<set value='64.99575966438606' />"+
					"<set value='66.57316307121887' />"+
					"<set value='62.72819876613348' />"+
					"<set value='61.84934777703776' />"+
					"<set value='61.44968736238275' />"+
					"<set value='64.17712349299798' />"+
					"<set value='58.433281688649345' />"+
					"<set value='60.6914574806209' />"+
					"<set value='59.811960872247084' />"+
					"<set value='59.985229858528555' />"+
					"<set value='55.41065214872579' />"+
					"<set value='54.81119435929719' />"+
					"<set value='55.66814869548466' />"+
					"<set value='58.3793870797098' />"+
					"<set value='55.12006843676623' />"+
					"<set value='55.2347730672026' />"+
					"<set value='52.57837064906752' />"+
					"<set value='51.88678332033058' />"+
					"<set value='52.69927874047655' />"+
					"<set value='48.79326718161194' />"+
					"<set value='52.30996908829546' />"+
					"<set value='49.71430245508917' />"+
					"<set value='47.13891510214991' />"+
					"<set value='49.95291612235913' />"+
					"<set value='50.843227855755046' />"+
					"<set value='48.725790873263136' />"+
					"<set value='48.93800884422313' />"+
					"<set value='48.22957610449535' />"+
					"<set value='45.85070828634284' />"+
					"<set value='46.338384151928054' />"+
					"<set value='38.28883561918634' />"+
					"<set value='39.90878302430756' />"+
					"<set value='36.16559480778097' />"+
					"<set value='36.6971144445938' />"+
					"<set value='37.778384685155004' />"+
					"<set value='38.735264078073804' />"+
					"<set value='39.80459284691027' />"+
					"<set value='40.13595500055241' />"+
					"<set value='40.512894798522815' />"+
					"<set value='41.5348439040161' />"+
					"<set value='37.530082641877144' />"+
					"<set value='39.09415214607816' />"+
					"<set value='41.56284193487178' />"+
					"<set value='43.53825742808295' />"+
					"<set value='42.22696246960422' />"+
					"<set value='42.71784055313975' />"+
					"<set value='45.464429334602144' />"+
					"<set value='44.866366407492286' />"+
					"<set value='41.36696570935227' />"+
					"<set value='47.02346983842182' />"+
					"<set value='46.933361439379794' />"+
					"<set value='49.09865866945415' />"+
					"<set value='45.919611088350656' />"+
					"<set value='48.94435704242689' />"+
					"<set value='51.337012632945815' />"+
					"<set value='50.51085466550742' />"+
					"<set value='49.98292789827751' />"+
					"<set value='51.12324205538961' />"+
					"<set value='52.990730097307555' />"+
					"<set value='52.360106573269114' />"+
					"<set value='51.97557441954038' />"+
					"<set value='57.54647658162918' />"+
					"<set value='58.68469524142753' />"+
					"<set value='56.882268949771486' />"+
					"<set value='55.775777327553016' />"+
					"<set value='59.72967227732599' />"+
					"<set value='60.84477048340524' />"+
					"<set value='59.85803843443443' />"+
					"<set value='57.89264861914029' />"+
					"<set value='59.185134859346554' />"+
					"<set value='59.38407564413357' />"+
					"<set value='58.703387630356005' />"+
					"<set value='59.611877049727205' />"+
					"<set value='64.32824367113838' />"+
					"<set value='63.61168097329532' />"+
					"<set value='63.04467450601922' />"+
					"<set value='61.070562063049024' />"+
					"<set value='63.813075161421054' />"+
					"<set value='60.53075803228425' />"+
					"<set value='60.65007528930808' />"+
					"<set value='62.38503231963749' />"+
					"<set value='63.52702044199238' />"+
					"<set value='63.48078902227522' />"+
					"<set value='64.40656018703424' />"+
					"<set value='60.280881502407894' />"+
					"<set value='61.119884767004436' />"+
					"<set value='60.35699219079085' />"+
					"<set value='62.75550218723758' />"+
					"<set value='62.15613067106441' />"+
					"<set value='59.994979108956855' />"+
					"<set value='58.57494858911869' />"+
					"<set value='56.630146789289256' />"+
					"<set value='58.14638215523384' />"+
					"<set value='54.14798700691236' />"+
					"<set value='54.53950651842607' />"+
					"<set value='55.47457720214446' />"+
					"<set value='55.55504565639541' />"+
					"<set value='51.10391259114387' />"+
					"<set value='53.62437276835741' />"+
					"<set value='53.36645222853531' />"+
					"<set value='53.962173379932196' />"+
					"<set value='52.47315244244943' />"+
					"<set value='47.57234834992542' />"+
					"<set value='48.61465682788362' />"+
					"<set value='48.43211750957647' />"+
					"<set value='45.32468743844631' />"+
					"<set value='44.78593409088903' />"+
					"<set value='46.45808385992249' />"+
					"<set value='47.50311237656596' />"+
					"<set value='42.65752756681862' />"+
					"<set value='45.193980617761' />"+
					"<set value='44.65303014778023' />"+
					"<set value='42.06096124623911' />"+
					"<set value='42.740875937340114' />"+
					"<set value='40.252201282727725' />"+
					"<set value='40.80414193326806' />"+
					"<set value='40.774274542715986' />"+
					"<set value='39.98898191101637' />"+
					"<set value='40.16368495550618' />"+
					"<set value='38.26748039055655' />"+
					"<set value='37.50015064982384' />"+
					"<set value='39.338343702660524' />"+
					"<set value='39.59004648514917' />"+
					"<set value='39.33408862366394' />"+
					"<set value='35.956815292778124' />"+
				"</dataset>"+

				"<dataset seriesName='ScopeStart for NYC_temp0' color='FF6600' anchorSides='0' anchorRadius='6' anchorBgColor='FF6600' anchorBorderColor='FFFFFF'>"+
					"<set value='56.78284611101935' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
				"</dataset>"+

				"<dataset seriesName='ScopeSet for NYC_temp0' color='FFFF00' anchorSides='0' anchorRadius='6' anchorBgColor='FFFF00' anchorBorderColor='FFFFFF'>"+
					"<set value='56.78284611101935' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
				"</dataset>"+

				"<dataset seriesName='Anomalys for NYC_temp0' color='FF0000' anchorSides='0' anchorRadius='6' anchorBgColor='FF0000' anchorBorderColor='FFFFFF'>"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='38.28883561918634' />"+
					"<set value='39.90878302430756' />"+
					"<set value='36.16559480778097' />"+
					"<set value='36.6971144445938' />"+
					"<set value='37.778384685155004' />"+
					"<set value='38.735264078073804' />"+
					"<set value='39.80459284691027' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='37.530082641877144' />"+
					"<set value='39.09415214607816' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='' />"+
					"<set value='39.98898191101637' />"+
					"<set value='' />"+
					"<set value='38.26748039055655' />"+
					"<set value='37.50015064982384' />"+
					"<set value='39.338343702660524' />"+
					"<set value='39.59004648514917' />"+
					"<set value='39.33408862366394' />"+
					"<set value='35.956815292778124' />"+
				"</dataset>"+

			"</chart>"


				);
				myChart_1.render("monitor_1");
				FusionCharts.addEventListener ( 
				          FusionChartsEvents.PrintReadyStateChange , 
				          function (identifier, parameter) {
				            if(parameter.ready){ 
				               alert("Chart is now ready for printing.");
				               document.getElementById('printButton').disabled = false;
				            }
				        });
			</script>
			<br/>
			<input type="button" onclick="FusionCharts.printManager.managedPrint()"
        		value="Managed Print"  id="printButton" /> 
		</td>
		<td><div style ="height:450px;width:550px; overflow:auto;">
NYC_temp.temperature@F | 2013/11/21 0:0 | 3 | 56.78284611101935<br/>
NYC_temp.temperature@F | 2013/11/21 0:15 | 0 | 55.35332665504479<br/>
NYC_temp.temperature@F | 2013/11/21 0:30 | 0 | 55.88676859307207<br/>
NYC_temp.temperature@F | 2013/11/21 0:45 | 0 | 58.60790666783789<br/>
NYC_temp.temperature@F | 2013/11/21 1:0 | 0 | 55.655950936096936<br/>
NYC_temp.temperature@F | 2013/11/21 1:15 | 0 | 59.59612198037519<br/>
NYC_temp.temperature@F | 2013/11/21 1:30 | 0 | 59.96602859766651<br/>
NYC_temp.temperature@F | 2013/11/21 1:45 | 0 | 55.72336528709729<br/>
NYC_temp.temperature@F | 2013/11/21 2:0 | 0 | 61.05225764481259<br/>
NYC_temp.temperature@F | 2013/11/21 2:15 | 0 | 60.578465099074634<br/>
NYC_temp.temperature@F | 2013/11/21 2:30 | 0 | 58.557937050194965<br/>
NYC_temp.temperature@F | 2013/11/21 2:45 | 0 | 61.23676749148111<br/>
NYC_temp.temperature@F | 2013/11/21 3:0 | 0 | 62.65603154439712<br/>
NYC_temp.temperature@F | 2013/11/21 3:15 | 0 | 61.43039277812871<br/>
NYC_temp.temperature@F | 2013/11/21 3:30 | 0 | 63.27922459060872<br/>
NYC_temp.temperature@F | 2013/11/21 3:45 | 0 | 60.61693101222938<br/>
NYC_temp.temperature@F | 2013/11/21 4:0 | 0 | 65.91483463526995<br/>
NYC_temp.temperature@F | 2013/11/21 4:15 | 0 | 61.39760927089647<br/>
NYC_temp.temperature@F | 2013/11/21 4:30 | 0 | 62.726885109615225<br/>
NYC_temp.temperature@F | 2013/11/21 4:45 | 0 | 64.13462639399717<br/>
NYC_temp.temperature@F | 2013/11/21 5:0 | 0 | 66.4358303923037<br/>
NYC_temp.temperature@F | 2013/11/21 5:15 | 0 | 65.8554155487067<br/>
NYC_temp.temperature@F | 2013/11/21 5:30 | 0 | 65.96145551382334<br/>
NYC_temp.temperature@F | 2013/11/21 5:45 | 0 | 68.66132993908977<br/>
NYC_temp.temperature@F | 2013/11/21 6:0 | 0 | 70.02499797414949<br/>
NYC_temp.temperature@F | 2013/11/21 6:15 | 0 | 72.22117065943455<br/>
NYC_temp.temperature@F | 2013/11/21 6:30 | 0 | 68.12375714074905<br/>
NYC_temp.temperature@F | 2013/11/21 6:45 | 0 | 67.69507456796855<br/>
NYC_temp.temperature@F | 2013/11/21 7:0 | 0 | 75.49138998369273<br/>
NYC_temp.temperature@F | 2013/11/21 7:15 | 0 | 74.44039821906183<br/>
NYC_temp.temperature@F | 2013/11/21 7:30 | 0 | 74.54796038610718<br/>
NYC_temp.temperature@F | 2013/11/21 7:45 | 0 | 73.81439438363806<br/>
NYC_temp.temperature@F | 2013/11/21 8:0 | 0 | 78.04644373800464<br/>
NYC_temp.temperature@F | 2013/11/21 8:15 | 0 | 75.56233935481873<br/>
NYC_temp.temperature@F | 2013/11/21 8:30 | 0 | 75.51982664884562<br/>
NYC_temp.temperature@F | 2013/11/21 8:45 | 0 | 77.80516353365107<br/>
NYC_temp.temperature@F | 2013/11/21 9:0 | 0 | 78.24254333816256<br/>
NYC_temp.temperature@F | 2013/11/21 9:15 | 0 | 77.4177513837929<br/>
NYC_temp.temperature@F | 2013/11/21 9:30 | 0 | 78.52129911291685<br/>
NYC_temp.temperature@F | 2013/11/21 9:45 | 0 | 78.90526211741854<br/>
NYC_temp.temperature@F | 2013/11/21 10:0 | 0 | 79.91553624461089<br/>
NYC_temp.temperature@F | 2013/11/21 10:15 | 0 | 78.56507356998637<br/>
NYC_temp.temperature@F | 2013/11/21 10:30 | 0 | 78.40124423487845<br/>
NYC_temp.temperature@F | 2013/11/21 10:45 | 0 | 80.96844125870196<br/>
NYC_temp.temperature@F | 2013/11/21 11:0 | 0 | 80.5227386287156<br/>
NYC_temp.temperature@F | 2013/11/21 11:15 | 0 | 81.48072149523378<br/>
NYC_temp.temperature@F | 2013/11/21 11:30 | 0 | 83.41709810827516<br/>
NYC_temp.temperature@F | 2013/11/21 11:45 | 0 | 79.86152870335889<br/>
NYC_temp.temperature@F | 2013/11/21 12:0 | 0 | 83.60110143701878<br/>
NYC_temp.temperature@F | 2013/11/21 12:15 | 0 | 82.43522500080338<br/>
NYC_temp.temperature@F | 2013/11/21 12:30 | 0 | 81.50344264062193<br/>
NYC_temp.temperature@F | 2013/11/21 12:45 | 0 | 83.07819493453337<br/>
NYC_temp.temperature@F | 2013/11/21 13:0 | 0 | 80.80966502487007<br/>
NYC_temp.temperature@F | 2013/11/21 13:15 | 0 | 82.61918733565123<br/>
NYC_temp.temperature@F | 2013/11/21 13:30 | 0 | 81.4411046736189<br/>
NYC_temp.temperature@F | 2013/11/21 13:45 | 0 | 83.37788422282216<br/>
NYC_temp.temperature@F | 2013/11/21 14:0 | 0 | 80.16616249047712<br/>
NYC_temp.temperature@F | 2013/11/21 14:15 | 0 | 81.8615966533952<br/>
NYC_temp.temperature@F | 2013/11/21 14:30 | 0 | 82.82424340514318<br/>
NYC_temp.temperature@F | 2013/11/21 14:45 | 0 | 79.24019702401222<br/>
NYC_temp.temperature@F | 2013/11/21 15:0 | 0 | 77.63965823387582<br/>
NYC_temp.temperature@F | 2013/11/21 15:15 | 0 | 76.7178502409578<br/>
NYC_temp.temperature@F | 2013/11/21 15:30 | 0 | 76.85302946910903<br/>
NYC_temp.temperature@F | 2013/11/21 15:45 | 0 | 81.06296350824444<br/>
NYC_temp.temperature@F | 2013/11/21 16:0 | 0 | 76.21938093030286<br/>
NYC_temp.temperature@F | 2013/11/21 16:15 | 0 | 77.79594415666534<br/>
NYC_temp.temperature@F | 2013/11/21 16:30 | 0 | 77.01592561284835<br/>
NYC_temp.temperature@F | 2013/11/21 16:45 | 0 | 78.32646327483431<br/>
NYC_temp.temperature@F | 2013/11/21 17:0 | 0 | 74.94442840306193<br/>
NYC_temp.temperature@F | 2013/11/21 17:15 | 0 | 71.93660988447898<br/>
NYC_temp.temperature@F | 2013/11/21 17:30 | 0 | 74.66821872063288<br/>
NYC_temp.temperature@F | 2013/11/21 17:45 | 0 | 74.39670279351392<br/>
NYC_temp.temperature@F | 2013/11/21 18:0 | 0 | 70.14976724042245<br/>
NYC_temp.temperature@F | 2013/11/21 18:15 | 0 | 68.2667337597439<br/>
NYC_temp.temperature@F | 2013/11/21 18:30 | 0 | 69.36232222620701<br/>
NYC_temp.temperature@F | 2013/11/21 18:45 | 0 | 72.24734013117602<br/>
NYC_temp.temperature@F | 2013/11/21 19:0 | 0 | 67.36089205852336<br/>
NYC_temp.temperature@F | 2013/11/21 19:15 | 0 | 68.20515703753992<br/>
NYC_temp.temperature@F | 2013/11/21 19:30 | 0 | 67.54043860604642<br/>
NYC_temp.temperature@F | 2013/11/21 19:45 | 0 | 66.14273336532361<br/>
NYC_temp.temperature@F | 2013/11/21 20:0 | 0 | 64.85265876865205<br/>
NYC_temp.temperature@F | 2013/11/21 20:15 | 0 | 63.21590709518841<br/>
NYC_temp.temperature@F | 2013/11/21 20:30 | 0 | 65.86144198151698<br/>
NYC_temp.temperature@F | 2013/11/21 20:45 | 0 | 66.24366751265703<br/>
NYC_temp.temperature@F | 2013/11/21 21:0 | 0 | 58.87712787346256<br/>
NYC_temp.temperature@F | 2013/11/21 21:15 | 0 | 60.47605381032127<br/>
NYC_temp.temperature@F | 2013/11/21 21:30 | 0 | 62.00129096670372<br/>
NYC_temp.temperature@F | 2013/11/21 21:45 | 0 | 60.17258277948272<br/>
NYC_temp.temperature@F | 2013/11/21 22:0 | 0 | 60.96076595962905<br/>
NYC_temp.temperature@F | 2013/11/21 22:15 | 0 | 60.65370213930335<br/>
NYC_temp.temperature@F | 2013/11/21 22:30 | 0 | 61.23991700783537<br/>
NYC_temp.temperature@F | 2013/11/21 22:45 | 0 | 57.80656861318459<br/>
NYC_temp.temperature@F | 2013/11/21 23:0 | 0 | 59.473039037763996<br/>
NYC_temp.temperature@F | 2013/11/21 23:15 | 0 | 58.676133960625435<br/>
NYC_temp.temperature@F | 2013/11/21 23:30 | 0 | 56.52734373518079<br/>
NYC_temp.temperature@F | 2013/11/21 23:45 | 0 | 57.95401673739921<br/>
NYC_temp.temperature@F | 2013/11/22 0:0 | 0 | 46.80279173112797<br/>
NYC_temp.temperature@F | 2013/11/22 0:15 | 0 | 49.964539310275356<br/>
NYC_temp.temperature@F | 2013/11/22 0:30 | 0 | 45.188440002169195<br/>
NYC_temp.temperature@F | 2013/11/22 0:45 | 0 | 47.00354843238851<br/>
NYC_temp.temperature@F | 2013/11/22 1:0 | 0 | 45.9960545597062<br/>
NYC_temp.temperature@F | 2013/11/22 1:15 | 0 | 46.01533179993261<br/>
NYC_temp.temperature@F | 2013/11/22 1:30 | 0 | 46.82834073977021<br/>
NYC_temp.temperature@F | 2013/11/22 1:45 | 0 | 49.068229955255894<br/>
NYC_temp.temperature@F | 2013/11/22 2:0 | 0 | 48.93167817454136<br/>
NYC_temp.temperature@F | 2013/11/22 2:15 | 0 | 51.26023904441951<br/>
NYC_temp.temperature@F | 2013/11/22 2:30 | 0 | 49.73125819743935<br/>
NYC_temp.temperature@F | 2013/11/22 2:45 | 0 | 47.61904796003375<br/>
NYC_temp.temperature@F | 2013/11/22 3:0 | 0 | 49.33667366285652<br/>
NYC_temp.temperature@F | 2013/11/22 3:15 | 0 | 52.04350823162927<br/>
NYC_temp.temperature@F | 2013/11/22 3:30 | 0 | 52.55666871839088<br/>
NYC_temp.temperature@F | 2013/11/22 3:45 | 0 | 50.08934830778113<br/>
NYC_temp.temperature@F | 2013/11/22 4:0 | 0 | 53.047999968789874<br/>
NYC_temp.temperature@F | 2013/11/22 4:15 | 0 | 52.150810417676325<br/>
NYC_temp.temperature@F | 2013/11/22 4:30 | 0 | 51.58488210155089<br/>
NYC_temp.temperature@F | 2013/11/22 4:45 | 0 | 53.333280561075085<br/>
NYC_temp.temperature@F | 2013/11/22 5:0 | 0 | 57.79999005962861<br/>
NYC_temp.temperature@F | 2013/11/22 5:15 | 0 | 55.16039641271969<br/>
NYC_temp.temperature@F | 2013/11/22 5:30 | 0 | 57.909923063035535<br/>
NYC_temp.temperature@F | 2013/11/22 5:45 | 0 | 56.51482969448804<br/>
NYC_temp.temperature@F | 2013/11/22 6:0 | 0 | 58.85610121443278<br/>
NYC_temp.temperature@F | 2013/11/22 6:15 | 0 | 59.46646684724755<br/>
NYC_temp.temperature@F | 2013/11/22 6:30 | 0 | 58.62744821180624<br/>
NYC_temp.temperature@F | 2013/11/22 6:45 | 0 | 58.314033510552306<br/>
NYC_temp.temperature@F | 2013/11/22 7:0 | 0 | 62.48993914603548<br/>
NYC_temp.temperature@F | 2013/11/22 7:15 | 0 | 61.345181534253385<br/>
NYC_temp.temperature@F | 2013/11/22 7:30 | 0 | 61.38836891912881<br/>
NYC_temp.temperature@F | 2013/11/22 7:45 | 0 | 63.47169359305606<br/>
NYC_temp.temperature@F | 2013/11/22 8:0 | 0 | 66.27837518833365<br/>
NYC_temp.temperature@F | 2013/11/22 8:15 | 0 | 64.52442105241323<br/>
NYC_temp.temperature@F | 2013/11/22 8:30 | 0 | 68.72485084623037<br/>
NYC_temp.temperature@F | 2013/11/22 8:45 | 0 | 64.98769498746559<br/>
NYC_temp.temperature@F | 2013/11/22 9:0 | 0 | 68.53376864484832<br/>
NYC_temp.temperature@F | 2013/11/22 9:15 | 0 | 70.46541436501596<br/>
NYC_temp.temperature@F | 2013/11/22 9:30 | 0 | 69.49767065261457<br/>
NYC_temp.temperature@F | 2013/11/22 9:45 | 0 | 69.73817601038053<br/>
NYC_temp.temperature@F | 2013/11/22 10:0 | 0 | 68.9728091031675<br/>
NYC_temp.temperature@F | 2013/11/22 10:15 | 0 | 71.96766456732202<br/>
NYC_temp.temperature@F | 2013/11/22 10:30 | 0 | 69.66254643866051<br/>
NYC_temp.temperature@F | 2013/11/22 10:45 | 0 | 71.88323747440757<br/>
NYC_temp.temperature@F | 2013/11/22 11:0 | 0 | 73.89632611514955<br/>
NYC_temp.temperature@F | 2013/11/22 11:15 | 0 | 73.76824737576675<br/>
NYC_temp.temperature@F | 2013/11/22 11:30 | 0 | 73.30800751836219<br/>
NYC_temp.temperature@F | 2013/11/22 11:45 | 0 | 70.1996414545025<br/>
NYC_temp.temperature@F | 2013/11/22 12:0 | 0 | 71.73755155076228<br/>
NYC_temp.temperature@F | 2013/11/22 12:15 | 0 | 72.52250981641146<br/>
NYC_temp.temperature@F | 2013/11/22 12:30 | 0 | 72.57864094464028<br/>
NYC_temp.temperature@F | 2013/11/22 12:45 | 0 | 72.39203306644227<br/>
NYC_temp.temperature@F | 2013/11/22 13:0 | 0 | 73.82577222144504<br/>
NYC_temp.temperature@F | 2013/11/22 13:15 | 0 | 69.92545986386781<br/>
NYC_temp.temperature@F | 2013/11/22 13:30 | 0 | 71.66512146617723<br/>
NYC_temp.temperature@F | 2013/11/22 13:45 | 0 | 71.511604289643<br/>
NYC_temp.temperature@F | 2013/11/22 14:0 | 0 | 70.60804929289009<br/>
NYC_temp.temperature@F | 2013/11/22 14:15 | 0 | 71.7616886989402<br/>
NYC_temp.temperature@F | 2013/11/22 14:30 | 0 | 70.51630175510411<br/>
NYC_temp.temperature@F | 2013/11/22 14:45 | 0 | 71.84279573932943<br/>
NYC_temp.temperature@F | 2013/11/22 15:0 | 0 | 70.37369967062011<br/>
NYC_temp.temperature@F | 2013/11/22 15:15 | 0 | 67.35650172814664<br/>
NYC_temp.temperature@F | 2013/11/22 15:30 | 0 | 68.30877887862876<br/>
NYC_temp.temperature@F | 2013/11/22 15:45 | 0 | 69.0065897062605<br/>
NYC_temp.temperature@F | 2013/11/22 16:0 | 0 | 65.73451370735268<br/>
NYC_temp.temperature@F | 2013/11/22 16:15 | 0 | 68.24201974369086<br/>
NYC_temp.temperature@F | 2013/11/22 16:30 | 0 | 64.99575966438606<br/>
NYC_temp.temperature@F | 2013/11/22 16:45 | 0 | 66.57316307121887<br/>
NYC_temp.temperature@F | 2013/11/22 17:0 | 0 | 62.72819876613348<br/>
NYC_temp.temperature@F | 2013/11/22 17:15 | 0 | 61.84934777703776<br/>
NYC_temp.temperature@F | 2013/11/22 17:30 | 0 | 61.44968736238275<br/>
NYC_temp.temperature@F | 2013/11/22 17:45 | 0 | 64.17712349299798<br/>
NYC_temp.temperature@F | 2013/11/22 18:0 | 0 | 58.433281688649345<br/>
NYC_temp.temperature@F | 2013/11/22 18:15 | 0 | 60.6914574806209<br/>
NYC_temp.temperature@F | 2013/11/22 18:30 | 0 | 59.811960872247084<br/>
NYC_temp.temperature@F | 2013/11/22 18:45 | 0 | 59.985229858528555<br/>
NYC_temp.temperature@F | 2013/11/22 19:0 | 0 | 55.41065214872579<br/>
NYC_temp.temperature@F | 2013/11/22 19:15 | 0 | 54.81119435929719<br/>
NYC_temp.temperature@F | 2013/11/22 19:30 | 0 | 55.66814869548466<br/>
NYC_temp.temperature@F | 2013/11/22 19:45 | 0 | 58.3793870797098<br/>
NYC_temp.temperature@F | 2013/11/22 20:0 | 0 | 55.12006843676623<br/>
NYC_temp.temperature@F | 2013/11/22 20:15 | 0 | 55.2347730672026<br/>
NYC_temp.temperature@F | 2013/11/22 20:30 | 0 | 52.57837064906752<br/>
NYC_temp.temperature@F | 2013/11/22 20:45 | 0 | 51.88678332033058<br/>
NYC_temp.temperature@F | 2013/11/22 21:0 | 0 | 52.69927874047655<br/>
NYC_temp.temperature@F | 2013/11/22 21:15 | 0 | 48.79326718161194<br/>
NYC_temp.temperature@F | 2013/11/22 21:30 | 0 | 52.30996908829546<br/>
NYC_temp.temperature@F | 2013/11/22 21:45 | 0 | 49.71430245508917<br/>
NYC_temp.temperature@F | 2013/11/22 22:0 | 0 | 47.13891510214991<br/>
NYC_temp.temperature@F | 2013/11/22 22:15 | 0 | 49.95291612235913<br/>
NYC_temp.temperature@F | 2013/11/22 22:30 | 0 | 50.843227855755046<br/>
NYC_temp.temperature@F | 2013/11/22 22:45 | 0 | 48.725790873263136<br/>
NYC_temp.temperature@F | 2013/11/22 23:0 | 0 | 48.93800884422313<br/>
NYC_temp.temperature@F | 2013/11/22 23:15 | 0 | 48.22957610449535<br/>
NYC_temp.temperature@F | 2013/11/22 23:30 | 0 | 45.85070828634284<br/>
NYC_temp.temperature@F | 2013/11/22 23:45 | 0 | 46.338384151928054<br/>
NYC_temp.temperature@F | 2013/11/23 0:0 | 4 | 38.28883561918634<br/>
NYC_temp.temperature@F | 2013/11/23 0:15 | 4 | 39.90878302430756<br/>
NYC_temp.temperature@F | 2013/11/23 0:30 | 4 | 36.16559480778097<br/>
NYC_temp.temperature@F | 2013/11/23 0:45 | 4 | 36.6971144445938<br/>
NYC_temp.temperature@F | 2013/11/23 1:0 | 4 | 37.778384685155004<br/>
NYC_temp.temperature@F | 2013/11/23 1:15 | 4 | 38.735264078073804<br/>
NYC_temp.temperature@F | 2013/11/23 1:30 | 4 | 39.80459284691027<br/>
NYC_temp.temperature@F | 2013/11/23 1:45 | 0 | 40.13595500055241<br/>
NYC_temp.temperature@F | 2013/11/23 2:0 | 0 | 40.512894798522815<br/>
NYC_temp.temperature@F | 2013/11/23 2:15 | 0 | 41.5348439040161<br/>
NYC_temp.temperature@F | 2013/11/23 2:30 | 4 | 37.530082641877144<br/>
NYC_temp.temperature@F | 2013/11/23 2:45 | 4 | 39.09415214607816<br/>
NYC_temp.temperature@F | 2013/11/23 3:0 | 0 | 41.56284193487178<br/>
NYC_temp.temperature@F | 2013/11/23 3:15 | 0 | 43.53825742808295<br/>
NYC_temp.temperature@F | 2013/11/23 3:30 | 0 | 42.22696246960422<br/>
NYC_temp.temperature@F | 2013/11/23 3:45 | 0 | 42.71784055313975<br/>
NYC_temp.temperature@F | 2013/11/23 4:0 | 0 | 45.464429334602144<br/>
NYC_temp.temperature@F | 2013/11/23 4:15 | 0 | 44.866366407492286<br/>
NYC_temp.temperature@F | 2013/11/23 4:30 | 0 | 41.36696570935227<br/>
NYC_temp.temperature@F | 2013/11/23 4:45 | 0 | 47.02346983842182<br/>
NYC_temp.temperature@F | 2013/11/23 5:0 | 0 | 46.933361439379794<br/>
NYC_temp.temperature@F | 2013/11/23 5:15 | 0 | 49.09865866945415<br/>
NYC_temp.temperature@F | 2013/11/23 5:30 | 0 | 45.919611088350656<br/>
NYC_temp.temperature@F | 2013/11/23 5:45 | 0 | 48.94435704242689<br/>
NYC_temp.temperature@F | 2013/11/23 6:0 | 0 | 51.337012632945815<br/>
NYC_temp.temperature@F | 2013/11/23 6:15 | 0 | 50.51085466550742<br/>
NYC_temp.temperature@F | 2013/11/23 6:30 | 0 | 49.98292789827751<br/>
NYC_temp.temperature@F | 2013/11/23 6:45 | 0 | 51.12324205538961<br/>
NYC_temp.temperature@F | 2013/11/23 7:0 | 0 | 52.990730097307555<br/>
NYC_temp.temperature@F | 2013/11/23 7:15 | 0 | 52.360106573269114<br/>
NYC_temp.temperature@F | 2013/11/23 7:30 | 0 | 51.97557441954038<br/>
NYC_temp.temperature@F | 2013/11/23 7:45 | 0 | 57.54647658162918<br/>
NYC_temp.temperature@F | 2013/11/23 8:0 | 0 | 58.68469524142753<br/>
NYC_temp.temperature@F | 2013/11/23 8:15 | 0 | 56.882268949771486<br/>
NYC_temp.temperature@F | 2013/11/23 8:30 | 0 | 55.775777327553016<br/>
NYC_temp.temperature@F | 2013/11/23 8:45 | 0 | 59.72967227732599<br/>
NYC_temp.temperature@F | 2013/11/23 9:0 | 0 | 60.84477048340524<br/>
NYC_temp.temperature@F | 2013/11/23 9:15 | 0 | 59.85803843443443<br/>
NYC_temp.temperature@F | 2013/11/23 9:30 | 0 | 57.89264861914029<br/>
NYC_temp.temperature@F | 2013/11/23 9:45 | 0 | 59.185134859346554<br/>
NYC_temp.temperature@F | 2013/11/23 10:0 | 0 | 59.38407564413357<br/>
NYC_temp.temperature@F | 2013/11/23 10:15 | 0 | 58.703387630356005<br/>
NYC_temp.temperature@F | 2013/11/23 10:30 | 0 | 59.611877049727205<br/>
NYC_temp.temperature@F | 2013/11/23 10:45 | 0 | 64.32824367113838<br/>
NYC_temp.temperature@F | 2013/11/23 11:0 | 0 | 63.61168097329532<br/>
NYC_temp.temperature@F | 2013/11/23 11:15 | 0 | 63.04467450601922<br/>
NYC_temp.temperature@F | 2013/11/23 11:30 | 0 | 61.070562063049024<br/>
NYC_temp.temperature@F | 2013/11/23 11:45 | 0 | 63.813075161421054<br/>
NYC_temp.temperature@F | 2013/11/23 12:0 | 0 | 60.53075803228425<br/>
NYC_temp.temperature@F | 2013/11/23 12:15 | 0 | 60.65007528930808<br/>
NYC_temp.temperature@F | 2013/11/23 12:30 | 0 | 62.38503231963749<br/>
NYC_temp.temperature@F | 2013/11/23 12:45 | 0 | 63.52702044199238<br/>
NYC_temp.temperature@F | 2013/11/23 13:0 | 0 | 63.48078902227522<br/>
NYC_temp.temperature@F | 2013/11/23 13:15 | 0 | 64.40656018703424<br/>
NYC_temp.temperature@F | 2013/11/23 13:30 | 0 | 60.280881502407894<br/>
NYC_temp.temperature@F | 2013/11/23 13:45 | 0 | 61.119884767004436<br/>
NYC_temp.temperature@F | 2013/11/23 14:0 | 0 | 60.35699219079085<br/>
NYC_temp.temperature@F | 2013/11/23 14:15 | 0 | 62.75550218723758<br/>
NYC_temp.temperature@F | 2013/11/23 14:30 | 0 | 62.15613067106441<br/>
NYC_temp.temperature@F | 2013/11/23 14:45 | 0 | 59.994979108956855<br/>
NYC_temp.temperature@F | 2013/11/23 15:0 | 0 | 58.57494858911869<br/>
NYC_temp.temperature@F | 2013/11/23 15:15 | 0 | 56.630146789289256<br/>
NYC_temp.temperature@F | 2013/11/23 15:30 | 0 | 58.14638215523384<br/>
NYC_temp.temperature@F | 2013/11/23 15:45 | 0 | 54.14798700691236<br/>
NYC_temp.temperature@F | 2013/11/23 16:0 | 0 | 54.53950651842607<br/>
NYC_temp.temperature@F | 2013/11/23 16:15 | 0 | 55.47457720214446<br/>
NYC_temp.temperature@F | 2013/11/23 16:30 | 0 | 55.55504565639541<br/>
NYC_temp.temperature@F | 2013/11/23 16:45 | 0 | 51.10391259114387<br/>
NYC_temp.temperature@F | 2013/11/23 17:0 | 0 | 53.62437276835741<br/>
NYC_temp.temperature@F | 2013/11/23 17:15 | 0 | 53.36645222853531<br/>
NYC_temp.temperature@F | 2013/11/23 17:30 | 0 | 53.962173379932196<br/>
NYC_temp.temperature@F | 2013/11/23 17:45 | 0 | 52.47315244244943<br/>
NYC_temp.temperature@F | 2013/11/23 18:0 | 0 | 47.57234834992542<br/>
NYC_temp.temperature@F | 2013/11/23 18:15 | 0 | 48.61465682788362<br/>
NYC_temp.temperature@F | 2013/11/23 18:30 | 0 | 48.43211750957647<br/>
NYC_temp.temperature@F | 2013/11/23 18:45 | 0 | 45.32468743844631<br/>
NYC_temp.temperature@F | 2013/11/23 19:0 | 0 | 44.78593409088903<br/>
NYC_temp.temperature@F | 2013/11/23 19:15 | 0 | 46.45808385992249<br/>
NYC_temp.temperature@F | 2013/11/23 19:30 | 0 | 47.50311237656596<br/>
NYC_temp.temperature@F | 2013/11/23 19:45 | 0 | 42.65752756681862<br/>
NYC_temp.temperature@F | 2013/11/23 20:0 | 0 | 45.193980617761<br/>
NYC_temp.temperature@F | 2013/11/23 20:15 | 0 | 44.65303014778023<br/>
NYC_temp.temperature@F | 2013/11/23 20:30 | 0 | 42.06096124623911<br/>
NYC_temp.temperature@F | 2013/11/23 20:45 | 0 | 42.740875937340114<br/>
NYC_temp.temperature@F | 2013/11/23 21:0 | 0 | 40.252201282727725<br/>
NYC_temp.temperature@F | 2013/11/23 21:15 | 0 | 40.80414193326806<br/>
NYC_temp.temperature@F | 2013/11/23 21:30 | 0 | 40.774274542715986<br/>
NYC_temp.temperature@F | 2013/11/23 21:45 | 4 | 39.98898191101637<br/>
NYC_temp.temperature@F | 2013/11/23 22:0 | 0 | 40.16368495550618<br/>
NYC_temp.temperature@F | 2013/11/23 22:15 | 4 | 38.26748039055655<br/>
NYC_temp.temperature@F | 2013/11/23 22:30 | 4 | 37.50015064982384<br/>
NYC_temp.temperature@F | 2013/11/23 22:45 | 4 | 39.338343702660524<br/>
NYC_temp.temperature@F | 2013/11/23 23:0 | 4 | 39.59004648514917<br/>
NYC_temp.temperature@F | 2013/11/23 23:15 | 4 | 39.33408862366394<br/>
NYC_temp.temperature@F | 2013/11/23 23:30 | 4 | 35.956815292778124<br/>
		</div></td>
	</tr>
</table>
</table>
</table>
</div>    

<br/><br/>
      <hr>
	<div class="container">
      <footer>
        <p>© Team2Awesome 2013</p>
      </footer>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../jumbo_files/jquery-1.10.2.min.js"></script>
    <script src="../jumbo_files/bootstrap.min.js"></script>
</body>
</html>