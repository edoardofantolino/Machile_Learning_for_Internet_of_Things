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


	$sql= "UPDATE house
		   SET alarm = 0
		   WHERE hid = 1;";

	//localhost/forWifi/turn_on.php
	
	mysqli_query($con ,$sql);

    mysqli_close($con);

?>