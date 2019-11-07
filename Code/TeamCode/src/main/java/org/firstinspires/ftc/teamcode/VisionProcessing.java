package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

public class VisionProcessing {

    //Self explanitory configuration settings on the phones.
    private VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private boolean PHONE_IS_PORTRAIT = false;

    //Need to go to the viewforia website to get a lisense key
    //" -- YOUR NEW VUFORIA KEY GOES HERE  --- ";
    private String VUFORIA_KEY = "AcAoGL//////AAABmeLkNRSnUUO6sf3XrV7ARa8/zi11f8DfKOVhbreQvMXEGPC8BEF1AuiGz9cY98olZHK7/L8dW8zX/TK5XpEUQQhS3icDnuMo9RRv5qp3h91wrWZhWzZOuv7VB7IUeTF5h2rlESJbjHU5nYR51y95M2Gf4oWU83qG30X9/Bke1DxrvZhsULEkSbb/UWSNwd+UpHfTF5qbVk/wBlqTPSJhplv6eKZ54kheUoOs3UH9KLFgwMA45ZEH0R5TWHXsZyeKWAuFmEthq0YBOSt0GvJA+KRkkjsHYrPW7fHSfEPraQDcAGBu+gPdPoY7SF5c/zHR2KvxKCQ4iKmFLUjU4bsoyVXtymxSuEdvCCoYEgYzqius";

    //This is our main Viewforia object
    private VuforiaLocalizer vuforia = null;

    //Predefining values so that they are global
    private VuforiaTrackable stoneTarget;
    private VuforiaLocalizer.Parameters parameters;
    private VuforiaTrackables targetsSkyStone;

    Integer cameraMonitorViewId = null;

    //Overloading this constructor so I can either use the view in the Robot Controller or nor.
    public VisionProcessing(int cameraMonitorViewIdIn) {
        cameraMonitorViewId = cameraMonitorViewIdIn;
        construct();
    }

    public VisionProcessing(){
        construct();
    }

    private void construct(){
        //Gets a reference to the camera Im assuming
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        //parameters is a obejct that will allow us to configure our viewforia.
        //The paremeter "cameraMonirotViewId" will allow the camera display to be seen in the robotController.

        if(cameraMonitorViewId != null){
            parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        }else {
            parameters = new VuforiaLocalizer.Parameters();
        }

        //Configuring the Viewforia withe the paremeters defined earlier
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection   = CAMERA_CHOICE;

        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        //Load the images and
        targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");

        stoneTarget = targetsSkyStone.get(0);
        stoneTarget.setName("Stone Target");

        /*
        VuforiaTrackable blueRearBridge = targetsSkyStone.get(1);
        blueRearBridge.setName("Blue Rear Bridge");
        VuforiaTrackable redRearBridge = targetsSkyStone.get(2);
        redRearBridge.setName("Red Rear Bridge");
        VuforiaTrackable redFrontBridge = targetsSkyStone.get(3);
        redFrontBridge.setName("Red Front Bridge");
        VuforiaTrackable blueFrontBridge = targetsSkyStone.get(4);
        blueFrontBridge.setName("Blue Front Bridge");
        VuforiaTrackable red1 = targetsSkyStone.get(5);
        red1.setName("Red Perimeter 1");
        VuforiaTrackable red2 = targetsSkyStone.get(6);
        red2.setName("Red Perimeter 2");
        VuforiaTrackable front1 = targetsSkyStone.get(7);
        front1.setName("Front Perimeter 1");
        VuforiaTrackable front2 = targetsSkyStone.get(8);
        front2.setName("Front Perimeter 2");
        VuforiaTrackable blue1 = targetsSkyStone.get(9);
        blue1.setName("Blue Perimeter 1");
        VuforiaTrackable blue2 = targetsSkyStone.get(10);
        blue2.setName("Blue Perimeter 2");
        VuforiaTrackable rear1 = targetsSkyStone.get(11);
        rear1.setName("Rear Perimeter 1");
        VuforiaTrackable rear2 = targetsSkyStone.get(12);
        rear2.setName("Rear Perimeter 2");
        //*/

        targetsSkyStone.activate();
    }

    public boolean isSkyStoneInCamera(){
        boolean isVisible = ((VuforiaTrackableDefaultListener)stoneTarget.getListener()).isVisible();
        return isVisible;
    }

    public void cycleReset(){
        targetsSkyStone.deactivate();
        targetsSkyStone.activate();
    }
}
