package org.usfirst.frc319;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class BobConstants {

	private int _constantsIndex;
	private double defaultValue = 0.0;
		
		/*** ARM CONSTANTS ***/
	
	public static final String ARM_STORAGE_POS_KEY = "armStoragePosition";
	public static final String ARM_VERTICAL_POS_KEY = "armVerticalPosition";
	public static final String ARM_AUTO_SHOOT_LOW_POS_KEY = "armAutoShootLowPosition";
	public static final String ARM_AUTO_SEARCH_POS_KEY = "armAutoSearchPosition";
	public static final String ARM_SHOOT_FROM_CLEAT_POS_KEY = "armShootFromCleatPosition";
	public static final String ARM_AUTO_SHOOT_HIGH_POS_KEY = "armAutoShootHighPosition";
	public static final String ARM_LOW_GOAL_POS_KEY = "armLowGoalPosition";
	public static final String ARM_SHOOT_FROM_TOWER_POS_KEY = "armShootFromTowerPosition";
	public static final String ARM_COLLECT_POS_KEY = "armCollectPosition";
	public static final String ARM_P_UP_KEY = "armP_up";
	public static final String ARM_I_UP_KEY = "armI_up";
	public static final String ARM_D_UP_KEY = "armD_up";
	public static final String ARM_F_UP_KEY = "armF_up";
	public static final String ARM_P_DOWN_KEY = "armP_down";
	public static final String ARM_I_DOWN_KEY = "armI_down";
	public static final String ARM_D_DOWN_KEY = "armD_down";
	public static final String ARM_F_DOWN_KEY = "armF_down";
	public static final String ARM_IZONE_KEY = "armIZone";

		/*** SHOOTER CONSTANTS ***/
	
	public static final String SHOOTER_LEFT_P_KEY = "leftShooterP";
	public static final String SHOOTER_LEFT_I_KEY = "leftShooterI";
	public static final String SHOOTER_LEFT_D_KEY = "leftShooterD";
	public static final String SHOOTER_LEFT_F_KEY = "leftShooterF";
	public static final String SHOOTER_LEFT_IZONE_KEY = "leftShooterIZone";
	public static final String SHOOTER_RIGHT_P_KEY = "rightShooterP";
	public static final String SHOOTER_RIGHT_I_KEY = "rightShooterI";
	public static final String SHOOTER_RIGHT_D_KEY = "rightShooterD";
	public static final String SHOOTER_RIGHT_F_KEY = "rightShooterF";
	public static final String SHOOTER_RIGHT_IZONE_KEY = "rightShooterIZone";
	public static final String SHOOTER_HIGH_SPEED_KEY = "ShooterHighSpeed";
	public static final String SHOOTER_LOW_SPEED_KEY = "ShooterLowSpeed";
	public static final String SHOOTER_COLLECT_LOW_SPEED_KEY = "ShooterCollectLowSpeed";
	public static final String SHOOTER_COLLECT_HIGH_SPEED_KEY = "ShooterCollectHighSpeed";
	
		/*** Collector Constants ***/
	
	public static final String COLLECTOR_COLLECT_LIMIT_KEY = "collectorCollectLimit";
	public static final String COLLECTOR_LOW_SPEED_THRESHOLD_KEY = "collectorLowSpeedThreshold";
	public static final String COLLECTOR_LOAD_LIMIT_KEY = "collectorLoadLimit";
	public static final String COLLECTOR_LOW_SPEED_KEY = "collectorLowSpeed";
	public static final String COLLECTOR_HIGH_SPEED_KEY = "collectorHighSpeed";

	
	public BobConstants(int constantsIndex) {
		this._constantsIndex = constantsIndex;
	}

	private HashMap<String, Double> blueConstants = new HashMap<String, Double>() {

		{
			/*** ARM CONSTANTS ***/
			put(ARM_STORAGE_POS_KEY, 0.0);
			put(ARM_VERTICAL_POS_KEY, -190.0);
			put(ARM_AUTO_SHOOT_LOW_POS_KEY, -230.0);
			put(ARM_AUTO_SEARCH_POS_KEY, -253.0);
			put(ARM_SHOOT_FROM_CLEAT_POS_KEY, -315.0);
			put(ARM_AUTO_SHOOT_HIGH_POS_KEY, -344.0);
			put(ARM_LOW_GOAL_POS_KEY, -374.0);
			put(ARM_SHOOT_FROM_TOWER_POS_KEY, -350.0);
			put(ARM_COLLECT_POS_KEY, -407.0);
			put(ARM_P_UP_KEY, 22.0);
			put(ARM_I_UP_KEY, .05);
			put(ARM_D_UP_KEY, .25);
			put(ARM_F_UP_KEY, 0.0);
			put(ARM_P_DOWN_KEY, 11.0);
			put(ARM_I_DOWN_KEY, .05);
			put(ARM_D_DOWN_KEY, .25);
			put(ARM_F_DOWN_KEY, 0.0);
			put(ARM_IZONE_KEY, 0.0);

			/*** SHOOTER CONSTANTS ***///doubles need to have 0.0 not just 0
			put(SHOOTER_LEFT_P_KEY, 0.11);
			put(SHOOTER_LEFT_I_KEY, 0.0011);
			put(SHOOTER_LEFT_D_KEY, 1.1);
			put(SHOOTER_LEFT_F_KEY, 0.0288179);
			put(SHOOTER_LEFT_IZONE_KEY, 50.0);
			put(SHOOTER_RIGHT_P_KEY, .11);
			put(SHOOTER_RIGHT_I_KEY, 0.0011);
			put(SHOOTER_RIGHT_D_KEY, 1.1);
			put(SHOOTER_RIGHT_F_KEY, 0.02997);
			put(SHOOTER_RIGHT_IZONE_KEY, 50.0);
			put(SHOOTER_HIGH_SPEED_KEY,4000.0);
			put(SHOOTER_LOW_SPEED_KEY,3000.0);
			put(SHOOTER_COLLECT_HIGH_SPEED_KEY, 800.0);
			put(SHOOTER_COLLECT_LOW_SPEED_KEY, 300.0);
			
			/*** COLLECTOR CONSTANTS ***/
			
			put(COLLECTOR_COLLECT_LIMIT_KEY, 1.5);
			put(COLLECTOR_HIGH_SPEED_KEY, 0.85);
			put(COLLECTOR_LOAD_LIMIT_KEY, 1.75);
			put(COLLECTOR_LOW_SPEED_KEY, 0.3);
			put(COLLECTOR_LOW_SPEED_THRESHOLD_KEY, 0.6);
		}
	};

	private HashMap<String, Double> orangeConstants = new HashMap<String, Double>() {
		{
			/*** ARM CONSTANTS ***/
			put(ARM_STORAGE_POS_KEY, 0.0);
			put(ARM_VERTICAL_POS_KEY, -190.0);
			put(ARM_AUTO_SHOOT_LOW_POS_KEY, -230.0);
			put(ARM_AUTO_SEARCH_POS_KEY, -253.0);
			put(ARM_SHOOT_FROM_CLEAT_POS_KEY, -317.0);
			put(ARM_AUTO_SHOOT_HIGH_POS_KEY, -344.0);
			put(ARM_LOW_GOAL_POS_KEY, -366.0);
			put(ARM_SHOOT_FROM_TOWER_POS_KEY, -355.0);
			put(ARM_COLLECT_POS_KEY, -420.0);
			put(ARM_P_UP_KEY, 22.0);
			put(ARM_I_UP_KEY, .05);
			put(ARM_D_UP_KEY, .25);
			put(ARM_F_UP_KEY, 0.0);
			put(ARM_P_DOWN_KEY, 11.0);
			put(ARM_I_DOWN_KEY, .05);
			put(ARM_D_DOWN_KEY, .25);
			put(ARM_F_DOWN_KEY, 0.0);
			put(ARM_IZONE_KEY, 0.0);

			/*** SHOOTER CONSTANTS ***/
			put(SHOOTER_LEFT_P_KEY, 0.11);
			put(SHOOTER_LEFT_I_KEY, 0.0011);
			put(SHOOTER_LEFT_D_KEY, 1.1);
			put(SHOOTER_LEFT_F_KEY, 0.0288179);
			put(SHOOTER_LEFT_IZONE_KEY, 50.0);
			put(SHOOTER_RIGHT_P_KEY, 0.11);
			put(SHOOTER_RIGHT_I_KEY, 0.0011);
			put(SHOOTER_RIGHT_D_KEY, 1.1);
			put(SHOOTER_RIGHT_F_KEY, 0.02997);
			put(SHOOTER_RIGHT_IZONE_KEY, 50.0);
			put(SHOOTER_HIGH_SPEED_KEY,4000.0);
			put(SHOOTER_LOW_SPEED_KEY,3000.0);
			put(SHOOTER_COLLECT_HIGH_SPEED_KEY, 800.0);
			put(SHOOTER_COLLECT_LOW_SPEED_KEY, 300.0);

			/*** COLLECTOR CONSTANTS ***/
			
			put(COLLECTOR_COLLECT_LIMIT_KEY, 1.75);
			put(COLLECTOR_HIGH_SPEED_KEY, 0.85);
			put(COLLECTOR_LOAD_LIMIT_KEY, 1.75);
			put(COLLECTOR_LOW_SPEED_KEY, 0.5);
			put(COLLECTOR_LOW_SPEED_THRESHOLD_KEY, 0.6);
		}
	};

	// if key exists, return constant for currently configured robot
	// if key doesn't exist, return a default value.
	public double getConstant(String constantKey) {
		if (_constantsIndex == 0) {
			if (orangeConstants.containsKey(constantKey)) {
				return orangeConstants.get(constantKey);
			} else {
				System.err.println("CONSTANT NOT FOUND: Using default"
						+ defaultValue + " for " + constantKey);
				return defaultValue;
			}
		} else {
			if (blueConstants.containsKey(constantKey)) {
				return blueConstants.get(constantKey);
			} else {
				System.err.println("CONSTANT NOT FOUND: Using default"
						+ defaultValue + " for " + constantKey);
				return defaultValue;
			}
		}
	}
}
