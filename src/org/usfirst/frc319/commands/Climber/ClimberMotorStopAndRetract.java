// Strong Hold BOB 2016 Commands (Collect and Load)

package org.usfirst.frc319.commands.Climber;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc319.commands.collector.*;
import org.usfirst.frc319.subsystems.*;

/**
 *
 */
public class ClimberMotorStopAndRetract extends CommandGroup {

    public ClimberMotorStopAndRetract() {
    	
    	addParallel(new ClimberStop());
    	addSequential(new RetractClimber());
    } 
    
}
