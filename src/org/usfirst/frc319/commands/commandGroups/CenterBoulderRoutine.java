// Strong Hold BOB 2016 Commands (Collect and Load)

package org.usfirst.frc319.commands.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc319.commands.collector.*;
import org.usfirst.frc319.subsystems.*;

/**
 *
 */
public class CenterBoulderRoutine extends CommandGroup {

    public CenterBoulderRoutine() {
    	
    	addSequential(new CollectAndStop());
    	//addSequential(new LoadBoulder());
    	addSequential(new CenterBoulderAfterCollected(.35));
    	addSequential(new LoadBoulder());
    	addSequential(new CenterBoulderAfterCollected(.2));
    	addSequential(new LoadBoulder());
    	addSequential(new CenterBoulderAfterCollected(.2));
    	addSequential(new LoadBoulder());
    	addSequential(new CenterBoulderAfterCollected(.2));
    	
    	//addSequential(new CollectAndStop());
    	/*
    	addSequential(new LoadBoulder());
    	addSequential(new CenterBoulder());
    	addSequential(new LoadBoulder());
    	addSequential(new CenterBoulder());
    	addSequential(new LoadBoulder());
    	addSequential(new CenterBoulder());
    	addSequential(new LoadBoulder());
    	addSequential(new CollectAndStop());
    	*/
    } 
    
}
