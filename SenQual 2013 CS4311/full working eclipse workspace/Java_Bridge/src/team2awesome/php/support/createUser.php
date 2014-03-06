<?php

if (!(empty($_POST["name"]))) 
{

	$con=mysqli_connect("earth.cs.utep.edu","sespring2","cs4311!sespring2","sespring2");

	// Check connection
	if (mysqli_connect_errno($con))
	  {
	  echo "Failed to connect to MySQL: " . mysqli_connect_error();
	  }
	else
	  {
	  
		  $result = mysqli_query($con,"SELECT email FROM profile WHERE email = \"" . $_POST["email"] . "\";");
		  $row = mysqli_fetch_array($result);
		  if(!empty($row))
		  {
			//email already in database
			//do not create account
			echo "email already registered, enter another email address";
		  }
		  else
		  {
		      $notificationValue = 0;
			  if(!empty($_POST['notification']))
			  {
				foreach ($_POST['notification'] as $notification)
				{
					$notificationValue += $notification;
				}
			  }
			  else
				$notificationValue = 4;
				
			  $alertValue = 0;
			  if(!empty($_POST['alert']))
			  {
				foreach ($_POST['alert'] as $alert)
				{
					$alertValue += $alert;
				}
			  }
			  else
				$alertValue = 4;
				
				
			//email not found
			//create account
			$sql = "INSERT INTO profile VALUES (\"" . $_POST["name"] . "\", \"" . $_POST["title"] . "\", \"" . $_POST["affiliation"] ."\", \"" . $_POST["email"] ."\", \"" . $_POST["password"] . "\", \"" . $_POST["phonenumber"] ."\", " . $notificationValue .", " . $alertValue . ");";
			if (!mysqli_query($con,$sql))
				{
					die('Error: ' . mysqli_error($con));
				}
			echo "account has been created";
			header("Location: userCreated.php");
		  }
	  
	  }
	  mysqli_close($con);
	  
	  

}
	/*begin JAVA_BRIDGE*/
		define("JAVA_HOSTS", "localhost:8080");
		define("JAVA_SERVLET", "/Java_Bridge/GUI");
		require_once("Java.inc");
		echo java_context()->getServlet()->header("Create User", $_SESSION['EMAIL'], "FALSE");
	/*end JAVA_BRIDGE*/
?>

</head>
	<body>

		<form action="createUser.php" method="post">
			name: <input type="text" name="name"/><br/>
			title: <input type="text" name="title"/><br/>
			affiliation: <input type="text" name="affiliation"/><br/>
			email: <input type="text" name="email"/><br/>
			password: <input type="text" name="password"/><br/>
			phone number: <input type="text" name="phonenumber"/><br/>
			<br/>
			Select how you would like to be notified when an anomaly is detected:<br/>
			<input type="checkbox" name="notification[]" value="1"/>text<br/>
			<input type="checkbox" name="notification[]" value="2"/>email<br/>
			<input type="checkbox" name="notification[]" value="4"/>no notification<br/>
			<br/>
			Select the frequency of notification<br/>
			<input type="checkbox" name="alert[]" value="1"/>each anomaly detected<br/>
			<input type="checkbox" name="alert[]" value="2"/>once for each rule violated<br/>
			<input type="checkbox" name="alert[]" value="4"/>no once<br/>
			<input type="submit" value="Create User"/>
		</form>
	</body>
</html>

