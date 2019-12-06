package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class GrabberConfig {

    Servo publicSevo;
    double smallValue = 0.025;
    boolean isSmallMoving;
    boolean isConfiguring;
    boolean isHoldingX;
    int configureValue;
    int valuesToConfigure = 2;
    double[] servoConfiguedValues = new double[valuesToConfigure];

    public GrabberConfig(Servo grabber){
        publicSevo = grabber;
        isSmallMoving = false;
        isConfiguring = true;
        isHoldingX = false;
        configureValue = 0;
    }

    public void grabIn(){
//        publicSevo.setPosition(0.4);
        publicSevo.setPosition(servoConfiguedValues[0]);
    }

    public void grabOut(){
//        publicSevo.setPosition(0);
        publicSevo.setPosition(servoConfiguedValues[1]);
    }

    public double position() {
        return publicSevo.getPosition();
    }

    public void moveSlightlyPositive(){
        if(!isSmallMoving) {
            publicSevo.setPosition(publicSevo.getPosition() + smallValue);
            isSmallMoving = true;
        }
    }
    public void moveSlightlyNegative(){
        if(!isSmallMoving) {
            publicSevo.setPosition(publicSevo.getPosition() - smallValue);
            isSmallMoving = true;
        }
    }

    public void configure(){
        if(!isHoldingX) {
            servoConfiguedValues[configureValue] = publicSevo.getPosition();
            configureValue++;
            if (configureValue == valuesToConfigure) {
                isConfiguring = false;
            }
            isHoldingX = true;
        }
    }
}
