// Strong Hold BOB 2016 Commands (Collect and Load)

package org.usfirst.frc319.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc319.commands.arm.ArmStop;
import org.usfirst.frc319.commands.collector.*;
import org.usfirst.frc319.commands.shooter.ShooterStop;
import org.usfirst.frc319.subsystems.*;

/**
 *
 */
public class StopAllMechanisms extends CommandGroup {

    public StopAllMechanisms() {
    	
    	addParallel(new CollectorStop());
    	addParallel(new ArmStop());
    	addParallel(new ShooterStop());
 
    } 
    
}
