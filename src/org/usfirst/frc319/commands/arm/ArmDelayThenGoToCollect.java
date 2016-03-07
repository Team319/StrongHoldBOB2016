// Strong Hold BOB 2016 Commands (Arm Stop)
//This does not work. Oops. -Hannah 2/23/16

package org.usfirst.frc319.commands.arm;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.BobConstants;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class ArmDelayThenGoToCollect extends Command {

	private long startTime;

	// public ArmGoToCollect() {

	// requires(Robot.arm);

	// }

	protected void initialize() {
		startTime = System.currentTimeMillis();

		if (System.currentTimeMillis() - startTime < 500) {
			System.out.println("Waiting");
			return;
		}
	}

	protected void execute() {

		System.out.println("Executing");
		Robot.arm.setArmPosition(Robot.constants
				.getConstant(BobConstants.ARM_STORAGE_POS_KEY));

	}

	protected boolean isFinished() {
		return Robot.arm.isOnTarget();

	}

	protected void end() {
	}

	protected void interrupted() {
	}

}
