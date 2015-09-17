<?php
header("Content-Type: text/html;charset=UTF-8");

if (isset($_POST['tag']) && $_POST['tag'] != '') {
    // get tag
    $tag = $_POST['tag'];

    // include db handler
    require_once 'include/DB_Functions.php';
    $db = new DB_Functions();

    // response Array
    $response = array("tag" => $tag, "success" => 0, "error" => 0);

    // check for tag type
    if ($tag == 'login') {
        // Request type is check Login
        $username = $_POST['username'];
        $password = $_POST['password'];
		$result = $db->getUserByUsernameAndPassword($username, $password);
        if ($result != false) {
			while ($row = mysql_fetch_assoc($result)) {
				$user[] = $row;
			}
			echo json_encode($user);
			
        } else {

            $response["error"] = 1;
            $response["error_msg"] = "Incorrect username or password!";
            echo json_encode($response);
			
        }

    } 
	else if ($tag == 'register') {
        // Request type is Register new user
        $username = $_POST['username'];
		$password = $_POST['password'];
        $email = $_POST['email'];
		$name = $_POST['name'];
		$city = $_POST['city'];
		$type = $_POST['type'];
		
        // check if user is already existed
        if ($db->isUserExisted($username)) {
            // user is already existed - error response
            $response["error"] = 1;
            $response["error_msg"] = "User already existed";
            echo json_encode($response);
        } 
		else {
            // store user
            $result = $db->storeUser($username, $password, $email, $name, $city, $type);
            if ($result != false) {
                while ($row = mysql_fetch_assoc($result)) {
					$user[] = $row;
				}
				echo json_encode($user);
			} 
			else {
                // user failed to store
                $response["error"] = 1;
                $response["error_msg"] = "Error occured in Registartion";
                echo json_encode($response);
            }
        }
    } 
	
	else if ($tag == 'change_info_user') {
		$username = $_POST['username'];
		$key = $_POST['key'];
		$value = $_POST['value'];
		$result = $db->changeUserInformation($username, $key, $value);
		echo $result;
	}
	
	else if ($tag == 'load_all_room') {
		$result = $db->loadAllRoom();
		if ($result != false) {
			while ($row = mysql_fetch_assoc($result)) {
					$rooms[] = $row;
				}
				echo json_encode($rooms);
		}
		else {
			$response["error"] = 1;
            $response["error_msg"] = "No room found";
            echo json_encode($response);
		}
		
	}
	
	else {
        echo "Invalid Request";
    }
}
else {
    echo "Access Denied";
}
?>
