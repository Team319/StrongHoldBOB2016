// Strong Hold BOB 2016 Commands (Compressor Run)


package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc319.Robot;

/**
 *
 */
public class CompressorRun extends Command {

    public CompressorRun() {

        requires(Robot.compressor);

    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.compressor.CompressorRun();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
