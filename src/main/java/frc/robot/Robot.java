// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax; 
import com.revrobotics.CANSparkMax; 
import com.revrobotics.CANSparkMaxLowLevel.MotorType; //Low level type is needed to refer to which type of motor it is (brushed/brushless)

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot 
{
  private DifferentialDrive m_myRobot;

  private Joystick m_leftStick;
  private Joystick m_rightStick; 

  private final MotorController m_leftMotor = new PWMSparkMax(0);
  private final MotorController m_rightMotor = new PWMSparkMax(1); //Motor Ids for PWM are O and 1
  //private final MotorController m_leftMotor = new CANSparkMax(1, MotorType.kBrushed);
  //private final MotorController m_rightMotor = new CANSparkMax(2, MotorType.kBrushed);


  @Override
  public void robotInit() 
  {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
     m_leftMotor.setInverted(true);


    m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);
    
    m_leftStick = new Joystick(0);
    
    m_rightStick = new Joystick(1); //This would require the other left joystick to move the robot. I'd like to figure out a system in which we can get drive on two controller joysticks.
  }

  @Override
  public void teleopPeriodic() 
  {
    m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY()); //by altering this code, it became arcade drive rather than tank (initially value was m_rightStick.getY())
    SmartDashboard.putNumber("Joystick x value", m_leftStick.getX()); //Test code for 01/20/2022. We're attempting to figure out how to 
    SmartDashboard.putNumber("Joystick y value", m_leftStick.getY()); //get values to display on Shuffleboard
  }
}
