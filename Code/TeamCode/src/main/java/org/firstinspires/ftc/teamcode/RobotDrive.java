package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class RobotDrive {

    double[] publicWheelSpeeds = new double[4];

    public RobotDrive(){
    }

    public void mecanumDrive(double joyX, double joyY, double joyZ, boolean enableWheels, double scale, DcMotor[] wheels) {
        double[] wheelValues = new double[4];

        //This is the Calculations for mecanum drive

        //This was found by seeing which directions the wheels
        //needed to rotate to get a net positive movement in each axis.

        //If you have any questions, ask Ryan, he has a sheet that can explain it.
        wheelValues[0] =  joyX + joyY +  joyZ; //FL
        wheelValues[1] = -joyX + joyY + -joyZ; //FR
        wheelValues[2] = -joyX + joyY +  joyZ; //BL
        wheelValues[3] =  joyX + joyY + -joyZ; //BR

        //Make sure the values for each wheels are withing the range [-1,1]
        //Also make sure they are all scaled appropriately
        wheelValues = normalize(wheelValues);

        setWheelSpeeds(wheelValues, wheels, scale, enableWheels);
    }

    public void gyroDriveTowardsDriection(Gyro gyro, double gryoStraightValue, boolean enableWheels, double scale, DcMotor[] wheels){
        double[] wheelValues = new double[4];

        double[] leftNRightValues = driveTowards(gyro,gryoStraightValue);

        /*
        wheelValues [0] = leftNRightValues[0]; //fL
        wheelValues [1] = leftNRightValues[1]; //fR
        wheelValues [2] = leftNRightValues[0]; //bL
        wheelValues [3] = leftNRightValues[1]; //bR
        //*/

//        setWheelSpeeds(wheelValues,wheels);
        mecanumDrive(0,1,leftNRightValues[0],enableWheels,scale, wheels);
    }

    private double[] driveTowards(Gyro gyro, double gyroStraightValue){
        double[] leftNRightValues = new double[2];

        //0 is the left, 1 is the right;

        double gyroError = gyro.getZ();

        leftNRightValues[0] = gyroError /  90.0;
        leftNRightValues[1] = gyroError / -90.0;

        return leftNRightValues;
    }


    private double[] normalize(double[] values){
        double[] newValues = new double[values.length];
        double max = max(values);
        for(int i = 0; i < values.length; i++){
            //For each value, scale it based of the max
            newValues[i] = values[i]/max;
        }
        return newValues;
    }

    private double max(double[] values){
        double max = Math.abs(values[0]);
        //I use abs() because this will get the correct largest value
        //and allow for the scaling division to preserve the sign of the value
        for(int i = 1; i < values.length; i++){
            if(Math.abs(values[i]) > max){
                max = Math.abs(values[i]);
            }
        }
        return max;
    }

    public void setWheelSpeeds(double[] wheelSpeeds, DcMotor[] wheels, double scale, boolean enableWheels){
            publicWheelSpeeds = wheelSpeeds;
        for(int i = 0; i < 4; i++){
            //Set the speed of the Wheels
            if(enableWheels) {
                if (i % 2 == 0) {
                    //This inverts the Left side wheels because when they spin clockwise that is backwards while the right side clockwise is forwards
                    wheels[i].setPower(-wheelSpeeds[i] * scale);
                } else {
                    wheels[i].setPower(wheelSpeeds[i] * scale);
                }
            }
        }
    }

 /*  public void driveToColor(double power, String targetColor, DcMotor[] wheels) {
        if (targetColor.equals("blue")) {
            // color == color reading from sensor
            while (!(color.equals("blue"))) {
                // robot move forward
                wheels[0].setPower(power);
                wheels[1].setPower(power);
                wheels[2].setPower(power);
                wheels[3].setPower(power);
            }
        }

        else if (targetColor.equals("red")) {
            while (!(color.equals("red"))) {
                wheels[0].setPower(power);
                wheels[1].setPower(power);
                wheels[2].setPower(power);
                wheels[3].setPower(power);
            }
        }
        else{
        wheels[0].setPower(0);
            wheels[1].setPower(0);
            wheels[2].setPower(0);
            wheels[3].setPower(0);
          }
    }
*/
}
