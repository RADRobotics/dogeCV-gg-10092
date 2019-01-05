package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "CompetitionRobotTeleOp", group = "Competition")
public class CompetitionRobotTeleOp extends OpMode {
    private DcMotor leftWheelFront;
    //front top left wheel
    private DcMotor leftWheelBack;
    //back bottom left wheel
    private DcMotor rightWheelFront;
    //front top right wheel
    private DcMotor rightWheelBack;
    //back bottom right wheel
    private DcMotor rightArm;
    private DcMotor leftArm;
    private DcMotor armExtendRight;
    //arm extend right
    private DcMotor armExtendLeft;
    //arm extend left
    private double nitro;
    private double nitro2;

    private Servo rightLock;
    private Servo leftLock;

    private double setpoint = 0;


    @Override
    public void init() {
        leftWheelFront = hardwareMap.dcMotor.get("leftWheel");
        leftWheelBack = hardwareMap.dcMotor.get("leftWheel2");
        rightWheelFront = hardwareMap.dcMotor.get("rightWheel");
        rightWheelBack = hardwareMap.dcMotor.get("rightWheel2");
        rightArm = hardwareMap.dcMotor.get("rightArm");
        leftArm = hardwareMap.dcMotor.get("leftArm");
        armExtendRight = hardwareMap.dcMotor.get("armExtend");
        armExtendLeft = hardwareMap.dcMotor.get("armExtend2");
        rightLock = hardwareMap.servo.get("rightLock");
        leftLock = hardwareMap.servo.get("leftLock");

        nitro  = 0;
        nitro2 = 0;
        rightLock.setPosition(.8);
        leftLock.setPosition(.2);

        leftWheelFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftWheelBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightWheelFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightWheelBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftWheelFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftWheelBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightWheelFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightWheelBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);


    }

    @Override
    public void loop() {
        nitro=  1 - (gamepad1.right_trigger *.8);
        nitro2=.1 + (gamepad2.right_trigger *.8);



        //drive motors
        rightWheelFront.setPower((-gamepad1.left_stick_y - gamepad1.right_stick_x) * nitro);
        rightWheelBack.setPower((-gamepad1.left_stick_y - gamepad1.right_stick_x) * nitro);
        leftWheelBack.setPower((gamepad1.left_stick_y - gamepad1.right_stick_x) * nitro);
        leftWheelFront.setPower((gamepad1.left_stick_y - gamepad1.right_stick_x) * nitro);

        //arm extension motors
        armExtendRight.setPower((-gamepad2.right_stick_y) /* * nitro2*/);
        armExtendLeft.setPower((gamepad2.right_stick_y)/* * nitro2*/);
        //arm motors/lock servos
//        setpoint = /*rightArm.getCurrentPosition()+*/ gamepad2.left_stick_y*125*(nitro2);
        if(gamepad2.a) {
            rightArm.setPower(0);
            leftArm.setPower(0);
            leftLock.setPosition(.2);
            rightLock.setPosition(.8);
        }
            else{
    //            rightArm.setPower(.6 + nitro*.6);
    //            leftArm.setPower(.6 + nitro*.6);
    //            rightArm.setTargetPosition((int) setpoint);
    //            leftArm.setTargetPosition(-(int) setpoint);
                rightArm.setPower(-gamepad2.left_stick_y);
                leftArm.setPower(gamepad2.left_stick_y);
                //what is the point of setting arm power and changing it right after?
                leftLock.setPosition(0.8);
                rightLock.setPosition(.2);
            }





       // leftLock.setPosition(gamepad1.right_stick_y);
        //rightLock.setPosition(gamepad1.left_stick_x);
    }
}


