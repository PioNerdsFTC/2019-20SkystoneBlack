package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class TeleopTesting extends LinearOpMode {

    //Wheels
    private DcMotor[] wheels = new DcMotor[4];

    //Joystick Values
    private double joyY;
    private double joyX;
    private double joyZ;

    //Predefining the classes so they are global
    RobotDrive robot = new RobotDrive();
    Gyro gyro;
    Grabber grab;
    VisionProcessing viz;

    BNO055IMU imu;

    public ElapsedTime time = new ElapsedTime();
    public int resetTime = 5;

    double startingStraight;

    @Override
    public void runOpMode() {
        //Connect the wheels int the configuration to the wheels in the code

        ///*
        wheels[0] = hardwareMap.get(DcMotor.class,"FLeft");
        wheels[1] = hardwareMap.get(DcMotor.class,"FRight");
        wheels[2] = hardwareMap.get(DcMotor.class,"BLeft");
        wheels[3] = hardwareMap.get(DcMotor.class,"BRight");
        //*/


        ///*
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        gyro = new Gyro(imu);
        //*/

        /*
        grab = new Grabber(hardwareMap.get(Servo.class,"grabber"));
        //*/

        //Defining the vision processing to display the camera on the robot Controller.
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        viz = new VisionProcessing(cameraMonitorViewId);

        //Wait for the human driver to press the play button
        waitForStart();

        startingStraight =  Double.parseDouble(gyro.getZ());

        telemetry.addData("MyStatus", "Started");
        telemetry.update();

            while (opModeIsActive()) {

                //Define Variables for joystick values

                //A negative is placed before those two values because
                //pushing a stick_y upward yields the uncommon negative value
                joyY = -gamepad1.left_stick_y;
                joyX =  gamepad1.left_stick_x;
    //            joyZ = -gamepad1.right_stick_y; //Maybe...
                joyZ = gamepad1.right_trigger + -gamepad1.left_trigger;

                /*
                if(grab.isConfiguring){
                    if(gamepad1.left_bumper){
                        grab.moveSlightlyNegative();
                    }else if(gamepad1.right_bumper){
                        grab.moveSlightlyPositive();
                    }else{
                        grab.isSmallMoving = false;
                    }
                    if(gamepad1.x){
                        grab.configure();
                    }else{
                        grab.isHoldingX = false;
                    }
                }else{
                    if(gamepad1.a){
                        grab.grabIn();
                    }else{
                        grab.grabOut();
                    }
                    if(gamepad1.y){
                        grab.isConfiguring = true;
                        grab.configureValue = 0;
                    }
                }

                telemetry.addData("servo: ", grab.position());
                telemetry.addData("isConfiguring: ", grab.isConfiguring);
                //*/



                //Drive Mecanum wheels with the wheels defined.
                robot.mecanumDrive(joyX, joyY, joyZ, true, 1.0, wheels);

//                robot.gyroDriveTowardsDriection(gyro, startingStraight,true, 1.0, wheels);
                telemetry.addData("StartingGyro: ", startingStraight);

                //Self explanitory.
                showGyrosData(gyro);

                showWheelSpeeds(robot);

                //Prints a boolean if skystone in camera
                if(viz.isSkyStoneInCamera()){
                    telemetry.addData("Seeing_Skystone", true);
                    if(time.time() > resetTime){
                        viz.cycleReset();
                        time.reset();
                    }
                }else{
                    telemetry.addData("Seeing_Skystone", false);
                    time.reset();
                }


            telemetry.addData("MyStatus", "Running");
            telemetry.update();
        }
    }

    /*
    public TeleopTesting(){
    }

    public void showTelementaryData(String label, String data){
        telemetry.addData(label,data);
        telemetry.update();
    }
    public void showTelementaryData(String label, Float data){
        telemetry.addData(label,data);
        telemetry.update();
    }
    //*/

    public void showGyrosData(Gyro gyro){
        telemetry.addData("X: ", gyro.getX());
        telemetry.addData("Y: ", gyro.getY());
        telemetry.addData("Z: ", gyro.getZ());
    }

    public void showWheelSpeeds(RobotDrive robot){
        for(int i = 0; i < robot.publicWheelSpeeds.length; i++){
            telemetry.addData("Motor Number: " + i, robot.publicWheelSpeeds[i]);
        }
    }
}