<?php
	session_start();
	session_destroy();
	/*begin JAVA_BRIDGE*/
		require_once("../inc/JavaConnect.inc");
		setBridge("/Java_Bridge/GUIProfile", "Expired Session", "", false);
	/*end JAVA_BRIDGE*/
	$result=java_context()->getServlet()->logout($_SESSION['ID']);
	//ignore result
	
?>
	</head>
	<body>
		<div class="jumbotron">
		<div class="container">
		<?php 
			if(!empty($_SESSION['EMAIL']))		
				echo "<b>".$_SESSION['EMAIL'].": You have been logged out due to inactivity.</b><br/>";
			else
				echo "<b>You weren't even logged in..</b><br/><i>Why are your even here..?</i>";
		?>
		</div>
		</div>
		<br/><br/>
		<hr>
	<div class="container">
<?php include("../inc/footer.inc")?>