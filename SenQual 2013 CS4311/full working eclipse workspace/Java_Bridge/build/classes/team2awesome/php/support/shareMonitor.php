<?php




if ((!(empty($_POST["monitor"]))) and (!(empty($_POST["email"]))))
{	

	$con=mysqli_connect("earth.cs.utep.edu","sespring2","cs4311!sespring2","sespring2");

	// Check connection
	if (mysqli_connect_errno($con))
	  {
	  echo "Failed to connect to MySQL: " . mysqli_connect_error();
	  }
	else
	  {
		  //checks to make sure monitor name hasnt been created yet
		  $str = "SELECT email FROM profile WHERE email = \"". $_POST["email"] . "\";";
		  $result = mysqli_query($con,$str);
		  $row = mysqli_fetch_array($result);
		  if(!empty($row))
		  {
			$str = "INSERT INTO monitor VALUES (\"".$_POST["email"]. "\", \"". $_POST["monitor"] . "\");";
			echo $str;
			mysqli_query($con,$str);

			header("Location: monitor.php");
			//login logic
		  }
		  else
		  {
			//fail logic
			echo "email not found<br>";
		  }
	  
	  }
	  mysqli_close($con); 
}

?>

<html>
<body>
share a monitor

select a monitor to share
<form action="shareMonitor.php" method="post">
<select name = "monitor" id = "monitor" multiple = "multiple" size = "20">
<?php

//loads all monitors

$con=mysqli_connect("earth.cs.utep.edu","sespring2","cs4311!sespring2","sespring2");

// Check connection
if (mysqli_connect_errno($con))
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
else
  {
  
	  $str = "SELECT name FROM monitor WHERE owner_email = \"dummy@us.com\";";
	  $result = mysqli_query($con,$str);
	  for($row = mysqli_fetch_array($result); !empty($row); $row = mysqli_fetch_array($result))
	  {
		$monitorName = $row["name"];

		
		echo "<option value = \"" . $monitorName . "\" title = \"monitortitle\" > " . $monitorName . "</option>\n";
	  }  
  }
  mysqli_close($con);

?>
</select>
<br>
enter email address<br>
email address: <input type="text" name="email"><br>
<input type="submit" value="Share">
</form> 

</body>
</html>
