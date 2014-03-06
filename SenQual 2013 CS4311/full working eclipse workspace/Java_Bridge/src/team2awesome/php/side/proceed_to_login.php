<?php session_start();
	/*begin JAVA_BRIDGE*/
		require_once("../inc/JavaConnect.inc");
		setBridge("/Java_Bridge/GUIRule", "Profile Created Confirmation", $_SESSION['ID'], false);
	/*end JAVA_BRIDGE*/
?>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<h3>Account created!</h3>
			<p>Check your email for a link to activate your accout.<br/></p>
		</div>
	</div>

		<br/><br/>
		<hr>
	<div class="container">
<?php include("../inc/footer.inc")?>
