// Strong Hold BOB 2016 Commands (Arm Stop)

package org.usfirst.frc319.commands.arm;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc319.Robot;
import org.usfirst.frc319.commands.camera.TargetManager;

/**
 *
 */
public class ArmAutoAdjust extends Command {

    public ArmAutoAdjust() {

        requires(Robot.arm);
    }

    protected void initialize() {
    	//ticks to degrees
    	//y = -0.3642 * x - 68.759;

    	double degreeChange = TargetManager.getInstance().getTarget().getVerticalOffset();

    	double distance = TargetManager.getInstance().getTarget().getDistance();

    	//degree offset from distance
    	//y = -1.8106x + 5.3007

    	double distanceOffsetDegrees= distance*-1.8106 + 8.3007; //5.3007;

    	//degreeChange -= distanceOffsetDegrees;

    	double tickChange = degreeChange / 0.3642;

    	double position = Math.round(Robot.arm.getArmPosition() - tickChange);

    	SmartDashboard.putNumber("Move Arm to Position", position);

    	Robot.arm.setArmPosition(position);
    }

    protected void execute() {


    }

    protected boolean isFinished() {
        return Robot.arm.isOnTarget();


    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
