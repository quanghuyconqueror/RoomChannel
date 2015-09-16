<?php

class DB_Functions {

    private $db;

    //put your code here
    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $this->db = new DB_Connect();
        $this->db->connect();
    }

    // destructor
    function __destruct() {
        
    }

    /**
     * Storing new user
     * returns user details
     */
    public function storeUser($username, $password, $email, $name, $city, $type) {
		mysql_query("set names 'utf8'");
        $result = mysql_query("INSERT INTO Users(Username, Password, Email, Name, City, Type) VALUES('$username', '$password', '$email', '$name', '$city', '$type')");
		
        if ($result) {
            $user = mysql_query("SELECT * FROM Users WHERE Username = '$username'");  
            return $user;
			
        } else {
            return false;
        }
    }
	
	
    public function isUserExisted($username) {
		mysql_query("set names 'utf8'");
        $result = mysql_query("SELECT Username from Users WHERE Username = '$username'");
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            // user existed 
            return true;
        } else {
            // user not existed
            return false;
        }
    }
	
	public function getUserByUsernameAndPassword($username, $password) {
		mysql_query("set names 'utf8'");
        $result = mysql_query("SELECT * FROM Users WHERE Username = '$username' AND Password = '$password'");
        // check for result 
        $no_of_rows = mysql_num_rows($result);
        if ($no_of_rows > 0) {
            return $result;
            
        } else {
            // user not found
            return false;
        }
    }
	
	public function changeUserInformation($username, $key, $value) {
		mysql_query("set names 'utf8'");
		$result = mysql_query("UPDATE Users SET $key = '".$value."' WHERE Username = '".$username."'");
		return $result;
	}
	
}

?>
