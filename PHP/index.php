<?php
/**
 * @package index.php
 * @author  Faisal<faisal@nascenia.com>
 * @created 2014 - 03 - 06
 * @version 2014 - 03 - 06
 */

if(isset($_REQUEST['action'])) {
    if($_REQUEST['action'] == "login") {
        $username = isset($_REQUEST['username']) ? $_REQUEST['username'] : null;
        if(!$username) {
            echo json_encode(array(
                'code'      => 403,
                'message'   => "Invalid request"
            ));
            die;
        }
        //search username
        if($username == "Faisal") {
            echo json_encode(array(
                'code'      => 200,
                'message'   => "Username found"
            ));
        } else {
            echo json_encode(array(
                'code'      => 401,
                'message'   => "Username/Password invalid"
            ));
        }
    }
} else {
    echo json_encode(array(
        'code'      => 404,
        'message'   => "Request not available"
    ));
}
