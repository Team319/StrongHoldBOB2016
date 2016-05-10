package org.usfirst.frc319.commands.auto;



import java.util.HashMap;
import java.util.Map;

import org.usfirst.frc319.commands.drivetrain.AutoDriveStraightDistanceUsingRobotDrive;

import com.team319.auto.SelectedAuto;

import edu.wpi.first.wpilibj.command.Command;

public class AutoMapFactory {
	private Map<SelectedAuto, Command> map;

	private static AutoMapFactory instance;

	public static AutoMapFactory getInstance(){
		if(instance == null){
			instance = new AutoMapFactory();
		}
		return instance;
	}

	private AutoMapFactory(){
		map = new HashMap<SelectedAuto, Command>();
	}
	
	public Command buildAuto(SelectedAuto auto){
		if(auto.getSelectedMode().equalsIgnoreCase(AutoDictionary.AUTO_POSITION_1)){
			return new LowBarHighGoalAuto();
		}else if(auto.getSelectedMode().equalsIgnoreCase(AutoDictionary.AUTO_POSITION_2)){
			return new Position2VariousAuto();
		}else if (auto.getSelectedMode().equalsIgnoreCase(AutoDictionary.AUTO_POSITION_3)){
			return new Position3VariousAuto();
		}else if(auto.getSelectedMode().equalsIgnoreCase(AutoDictionary.AUTO_POSITION_4)){
			return new Position4VariousAuto();
		}else if (auto.getSelectedMode().equalsIgnoreCase(AutoDictionary.AUTO_POSITION_5)){
			return new Position5VariousAuto();
		}else if(auto.getSelectedMode().equalsIgnoreCase(AutoDictionary.AUTO_DO_NOTHING)){
			return new DoNothing();
		}else if(auto.getSelectedMode().equalsIgnoreCase(AutoDictionary.AUTO_DRIVE_STAIGHT)){
			return new AutoDriveStraightDistanceUsingRobotDrive();
		}
		return null;
	}
	//-------removed to make auto chooser simpler--------
	/*if(auto.getSelectedMode().equalsIgnoreCase(AutoDictionary.MODE_DRIVE_AND_SHOOT_HIGH)){
		return new AutoDriveStraightThenSplineThenShoot(auto.getSelectedDefense(), auto.getSelectedPosition());
	}else if(auto.getSelectedMode().equalsIgnoreCase(AutoDictionary.MODE_DRIVE_STRAIGHT)){
		return new AutoDriveStraightThenSplineThenShoot(auto.getSelectedDefense(), auto.getSelectedPosition());
	}*/
	
	
	/**
	private void addAuto(String position, String defense, String mode, Command auto){
		this.map.put(new SelectedAuto(position, defense, mode), auto);
	}

	public Map<SelectedAuto, Command> getMap() {
		return map;
	}

	public boolean hasAuto(SelectedAuto auto){
		return map.containsKey(auto);
	}

	public Command getAuto(SelectedAuto auto){
		return map.get(auto);
	}
	**/
}
