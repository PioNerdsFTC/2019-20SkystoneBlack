package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleopTesting extends LinearOpMode {

    //Wheels
    private DcMotor[] wheels = new DcMotor[4];

    //Joystick Values
    private double joyY;
    private double joyX;
    private double joyZ;

    private RobotDrive robot;

    BNO055IMU imu;

    @Override
    public void runOpMode() {
        //Connect the wheels int the configuration to the wheels in the code

        /*
        wheels[0] = hardwareMap.get(DcMotor.class,"FLeft");
        wheels[1] = hardwareMap.get(DcMotor.class,"FRight");
        wheels[2] = hardwareMap.get(DcMotor.class,"BLeft");
        wheels[3] = hardwareMap.get(DcMotor.class,"BRight");
        //*/

        //Make an instance of the RobotDrive Class
        RobotDrive robot = new RobotDrive();

        //Make an instance of the Gyro Class
//        Gyro gyro = new Gyro();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        Gyro gyro = new Gyro(imu);

        Grabber grab = new Grabber(hardwareMap.get(Servo.class,"grabber"));

        //Wait for the human driver to press the play button
        waitForStart();

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
//            telemetry.addData("isSmallMoving: ", grab.isSmallMoving);
//            telemetry.addData("isHoldingX: ", grab.isHoldingX);
//            telemetry.addData("configureValue: ", grab.configureValue);
//            telemetry.addData("leftBump: ", gamepad1.left_bumper);
//            telemetry.addData("rightBump: ", gamepad1.right_bumper);

            //Drive Mecanum wheels with the wheels defined.
//            robot.mecanumDrive(joyX, joyY, joyZ,false, wheels);
//            robot.gyroDriveTowardsDriection(gyro, 0,true, 1.0, wheels);

            //Self explanitory.
            showGyrosData(gyro);

//            showWheelSpeeds(robot);

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