<?php

	include_once "db_config.inc";
	session_start();
	if (session_status()==PHP_SESSION_ACTIVE) {
		session_destroy();
	}
	$con=new mysqli($server, $username, $password, $database);
		if($con->connect_errno>0)
		{
			die('Unable to connect to database['.$con->connect_error.']');
		}

    if (mysqli_connect_errno())
    {
       echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }

	// $temperature = $_GET['temperature'];

	$sql= "SELECT alarm FROM house WHERE hid=1";

	$result = mysqli_query($con ,$sql);
		
	if ($result->num_rows > 0) {	
		// output data of each row
		while($row = $result->fetch_assoc()) {
		// echo "state: " . $row["state"];
		// echo "<br>";
		$state = $row["alarm"];
		echo $state;
		}
	} else {
		echo "No result";
	}

    mysqli_close($con);

?>