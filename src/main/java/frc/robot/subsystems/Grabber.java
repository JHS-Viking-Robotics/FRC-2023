package frc.robot.subsystems;

import static frc.robot.Constants.Subsystem.Grabber.*;
import static com.revrobotics.PWMSparkMax.IdleMode.*;
import static com.revrobotics.PWMSparkMaxLowLevel.MotorType.*;

import com.revrobotics.PWMSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Grabber extends SubsystemBase {

    private final PWMSparkMax left;  
    private final PWMSparkMax right;

    private final PWMSparkMax grabber;

    public  Grabber() {
        
    }   

    public void squeeze() {
      grabber.set(0.5);
    }
        
    public void release() {
        grabber.set(-0.5);
    }
}