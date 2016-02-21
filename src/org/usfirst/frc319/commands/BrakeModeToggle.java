// Strong Hold BOB 2016 Commands (Shift Toggle)

package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc319.Robot;

/**
 *
 */
public class BrakeModeToggle extends Command {

	public BrakeModeToggle() {

		requires(Robot.driveTrain);

	}

	protected void initialize() {
		// "!" flips the value returned by a boolean
		// if isBrakeModeEnabled is true, !brakeModeEnabled will show false
		if (!Robot.driveTrain.isBrakeModeEnabled) {
			Robot.driveTrain.enableBrakeMode(true);

		} else {
			Robot.driveTrain.enableBrakeMode(false);

		}
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}

}
