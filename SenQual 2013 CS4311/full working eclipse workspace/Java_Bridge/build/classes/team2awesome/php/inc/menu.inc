    <title><?php echo $pageName?> Page</title>

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
         <a class="navbar-brand" href="index.php">SenQual</a>
          <ul class="nav navbar-nav">
            <?php if($pageName=="Home"){echo "<li class=\"active\">";}else{echo "<li>";}?><a href="../main/index.php">Home</a></li>
            <?php if($pageName=="Dashboard"){echo "<li class=\"active\">";}else{echo "<li>";}?><a href="../main/dashboard.php">Dashboard</a></li>
            <?php if($pageName=="Monitor"){echo "<li class=\"active\">";}else{echo "<li>";}?><a href="../main/monitor.php">Monitor</a></li>
            <?php if($pageName=="Rule"){echo "<li class=\"active\">";}else{echo "<li>";}?><a href="../main/rule.php">Rules</a></li>
            <?php if($pageName=="Sensor"){echo "<li class=\"active\">";}else{echo "<li>";}?><a href="../main/sensors.php">Sensors</a></li>
            <?php if($pageName=="Profile"){echo "<li class=\"active\">";}else{echo "<li>";}?><a href="../main/profile.php">Profile</a></li>
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
    