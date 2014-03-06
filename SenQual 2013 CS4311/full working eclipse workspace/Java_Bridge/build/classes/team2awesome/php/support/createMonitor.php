<?php
if (!(empty($_POST["monitorName"]))) 
{
	$con=mysqli_connect("earth.cs.utep.edu","sespring2","cs4311!sespring2","sespring2");

	// Check connection
	if (mysqli_connect_errno($con))
	  {
	  echo "Failed to connect to MySQL: " . mysqli_connect_error();
	  }
	else
	  {
		  $str = "SELECT name FROM monitor WHERE owner_email = \"dummy@us.com\" and name = \"". $_POST["monitorName"] . "\";";
		  $result = mysqli_query($con,$str);
		  $row = mysqli_fetch_array($result);
		  if(empty($row))
		  {
			echo "monitor created!";
			$str = "INSERT INTO monitor VALUES (\"dummy@us.com\", \"". $_POST["monitorName"] . "\");";
			echo $str;
			mysqli_query($con,$str);
			header("Location: monitor.php");
			//login logic
		  }
		  else
		  {
			//fail logic
			echo "monitor name taken";
		  }
	  
	  }
	  mysqli_close($con);
}

?>

<html>
<body>
create a new monitor
<form action="createMonitor.php" method="post">
monitor name: <input type="text" name="monitorName"><br>
<input type="submit" value="Create">
</form> 

</body>
</html>
