<?php
/**
 * @package upload.php
 * @author  Faisal<faisal@nascenia.com>
 * @created 2014 - 03 - 07
 * @version 2014 - 03 - 07
 */

function saveImage($data) {
    $imageString = $data['imageString'];
    $imageString = str_replace("data:image/png;base64,",'',$imageString);
    $imageString = str_replace(" ",'+',$imageString);
    $image = base64_decode($imageString);
    $flag = file_put_contents("images/".time().".png",$image);
    return $flag ? true : false;
}

if(isset($_POST['action'])) {

    switch($_POST['action']) {
        case 'uploadSnap':
            $boolFlag = saveImage($_POST);
            if($boolFlag) {
                echo json_encode(array(
                    'code'  => 200,
                    'message' => 'image saved successfully'
                ));
            } else {
                echo json_encode(array(
                    'code'  => 401,
                    'message' => 'image not saved successfully'
                ));
            }
            break;
        default:
            echo json_encode(array(
                'code'  => 404,
                'message' => 'request not valid'
            ));
            break;
    }

}

