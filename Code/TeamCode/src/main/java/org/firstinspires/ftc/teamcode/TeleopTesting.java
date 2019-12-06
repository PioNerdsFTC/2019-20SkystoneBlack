package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

    RobotDrive robot = new RobotDrive();
    Gyro gyro;
    GrabberConfig grab;
    VisionProcessing viz;
    Grabber grabber;

    BNO055IMU imu;

    public ElapsedTime time = new ElapsedTime();
    public int resetTime = 5;

    private Servo elbow;
    double[] elbowScale = {0.15,0.8};
    private Servo wrist;
    double[] wristScale = {0.05,0.6};//{0.07,0.65};
    private DcMotorSimple armTilter;
    private double armSpeed;
    private double armScale = 1.0;
    private DcMotorSimple elevator;
    private double joyEle;
    private double elevatorSpeedScale = 0.5;
    private Servo holder;
    double[] holderScale = {0.45,0.85};

    int count = 0;

    int aCount = 0;
    private boolean isA = false;


//    private double servoSpeed;

    @Override
    public void runOpMode() {
        //Connect the wheels int the configuration to the wheels in the code


        ///*
        //Wheels
        wheels[0] = hardwareMap.get(DcMotor.class,"FLeft");
        wheels[1] = hardwareMap.get(DcMotor.class,"FRight");
        wheels[2] = hardwareMap.get(DcMotor.class,"BLeft");
        //needs to be changed because I need the encoder values from the arm tilter, so It needs to be the DC Motor Class
        wheels[3] = hardwareMap.get(DcMotor.class,"BRight");
        //*/


        ///*
        //Gyro
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        gyro = new Gyro(imu);
        //*/

//

        //Defining the vision processing to display the camera on the robot Controller.
        //Vision Processing
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        viz = new VisionProcessing(cameraMonitorViewId);


        /*
        //Grabber Config
        //1
        holder = hardwareMap.get(Servo.class, "Holder");
        grab = new GrabberConfig(holder);
        //*/

        /*
        //Grabber
        //2
        elbow = hardwareMap.get(Servo.class, "Elbow");
        elbow.scaleRange(elbowScale[0],elbowScale[1]);
        wrist = hardwareMap.get(Servo.class, "Wrist");
        wrist.scaleRange(wristScale[0],wristScale[1]);
        armTilter = hardwareMap.get(DcMotorSimple.class, "armTilter");
        elevator  = hardwareMap.get(DcMotorSimple.class, "Elevator");
        holder = hardwareMap.get(Servo.class, "Holder");
        holder.scaleRange(holderScale[0],holderScale[1]);
        Grabber grabber = new Grabber(elbow, wrist, armTilter, wheels[0], elevator, wheels[1], holder);
        //*/


        //Wait for the human driver to press the play button
        waitForStart();

        telemetry.addData("MyStatus", "Started");
        telemetry.update();

            while (opModeIsActive()) {


                /*
                //1
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
                for(int i = 0; i < grab.valuesToConfigure; i++){
                    telemetry.addData(i + ": ", grab.servoConfiguedValues[i]);
                }
                //*/

//                if(gamepad2.a){
//                    grabber.setElbow(1.0);
//                }else{
//                    grabber.setElbow(0.0);
//                }


                //count++;
                //telemetry.addData("counte", count);


                /*
                //3
                if(gamepad2.dpad_up){
                    telemetry.addData("Current: ", grabber.moveElbowUp());
                    telemetry.addData("Button: ", "UP");
                }else if(gamepad2.dpad_down){
                    telemetry.addData("Current: ", grabber.moveElbowDown());
                    telemetry.addData("Button: ", "Down");
                }
                telemetry.addData("Current: ", grabber.current);
                telemetry.addData("Button: ", "none");
                //*/

                /*
                //6
                if(gamepad2.a){
                    if(!isA){
                        aCount++;
                    }
                    isA = true;
                }else{
                    isA = false;
                }
                if(aCount % 2 == 0){
                    grabber.openHolder();
                }else{
                    grabber.closeHolder();
                }
                //*/

                /*
                if(gamepad2.b){
                    grabber.setWrist(1.0);
                }else {
                    grabber.setWrist(0.0);
                }
                //*/

                /*
                //7
                joyEle = gamepad2.right_trigger + -gamepad2.left_trigger;
                grabber.setElevatorSpeed(joyEle * elevatorSpeedScale);
                telemetry.addData("JoyEle: ", joyEle);
                telemetry.addData("vlaeu: ", joyEle*elevatorSpeedScale);
                telemetry.addData("Elevator: ", grabber.getElevator());
                /*
                //IF ENCODER RESETS EVERY TIME
                //PRESS D PAD RIGHT BEFOR SHUT DOWN
                //8
                //THIS NEEDS TO BE CHANGED BECAUSE OF REWIRING.
                if(gamepad2.dpad_right){
                    grabber.driveMotorToEncoder(grabber.elevator, 0, elevatorSpeedScale);
                }
                //*/

                /*
                //9
                if(gamepad2.left_bumper){
                    grabber.setTilter(0.1);
                }else if(gamepad1.right_bumper){
                    grabber.setTilter(-0.1);
                }else{
                    grabber.stopTilter();
                }
                telemetry.addData("Tilter: ", grabber.getTilter());
                //TELL THEM TO HOLD IT DOWN AT THE END OF THE MATCH???
                //*/

                /*
                //10
                if(gamepad2.left_bumper && gamepad1.right_bumper){
                    grabber.tiltUnderBridge();
                }else{
                    grabber.tiltBack();
                }
                //*/



                //Define Variables for joystick values

                //A negative is placed before those two values because
                //pushing a stick_y upward yields the uncommon negative value
                joyY = -gamepad1.left_stick_y;
                joyX =  gamepad1.left_stick_x;
                joyZ = gamepad1.right_trigger + -gamepad1.left_trigger;
                //joyZ = -gamepad1.right_stick_y; //Maybe...






//                if(gamepad2.right_trigger == 1){
//                    grabber.sendElevatorUp();
//                }else if(gamepad2.left_trigger == 1){
//                    grabber.sendElevatorDown();
//                }else{
//                    grabber.stopElevator();
//                }
//
//                if(gamepad2.right_bumper){
//                    grabber.setTilterSpeed(grabber.defaultTilterSpeed);
//                }else if(gamepad2.left_bumper){
//                    grabber.setTilterSpeed(-grabber.defaultTilterSpeed);
//                }else{
//                    grabber.setTilterSpeed(0);
//                }

                /*

                if(gamepad2.right_bumper && gamepad2.left_bumper){

                }else{

                }
                 */


                /*
                servoSpeed = -gamepad2.left_stick_y;

//                armSpeed = gamepad1.right_trigger + -gamepad1.left_trigger;

                if(gamepad1.a) {
                    if(!isA){
//                        grabber.moveElbow(grabber.getElbow() - 0.1);
                        //                    grabber.tiltUnderBridge();
                    }
                    isA = true;
                }else {
                    if(isA){
                        //grabber.tiltBack();
                    }
                    isA = false;
                }

                grabber.moveElbow(servoSpeed);

                telemetry.addData("Elbow", grabber.getElbow());
                telemetry.addData("Wrist", grabber.getWrist());

                //*/





                //Drive Mecanum wheels with the wheels defined.
//               robot.mecanumDrive(joyX, joyY, joyZ, true, 1.0, wheels);

//               armTilter.setPower(armSpeed * armScale);

//                robot.gyroDriveTowardsDriection(gyro, gyro.startValue + 90, true, 0.5, wheels);
//                telemetry.addData("StartingGyro: ", gyro.startValue);

                //Self explanitory.
                showGyrosData(gyro);

//                showWheelSpeeds(robot);

                //Prints a boolean if skystone in camera
                /*
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
                //*/


//                telemetry.addData("gyro", gyro.getZ());

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