package poolControllerPD;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class Heating
{
	private LocalDateTime startTime, stopTime, initiate, terminate;
	@SuppressWarnings("unused")
	private Duration runTime, downTime;
	private boolean active, atTargetTemp, OP, DT;
	private float measuredAirTemp, measuredWaterTemp, targetWaterTemp;
	
	// Default Constructor
	public Heating()
	{
		measuredAirTemp = 75.5f;
		measuredWaterTemp = 78.2f;
		targetWaterTemp = 80f;
		initiate = LocalDateTime.now();
		active = false; //whether or not the system is on
		startTime = LocalDateTime.now(); //the time at which the system becomes active
		stopTime = LocalDateTime.now(); //the time at which the system becomes inactive
		runTime = null; //the lenght of time the system has been running
		downTime = null; //the length of time the system has been off
	}
	// Constructor
	public Heating(boolean activeArg)
	{
		measuredAirTemp = 75.5f;
		measuredWaterTemp = 78.2f;
		targetWaterTemp = 80f;
		initiate = LocalDateTime.now();
		terminate = LocalDateTime.now();
		OP = false;
		
		this.active = activeArg;
		if(active == true) //if it's on, start operations now
		{
			startTime = LocalDateTime.now();
			this.startOnClock();
			stopTime = LocalDateTime.now();
		}
		else if(active == false)//if it's off, stop operations now
		{
			stopTime = LocalDateTime.now();
			this.startOffClock();
			startTime = LocalDateTime.now();
		}
		runTime = null;
		downTime = null;
		atTargetTemp = false;
	}
	// Turns the heater on or off depending on whether target temp is above or below measured temp
	public void autoMaintain()
	{
		if(measuredWaterTemp < targetWaterTemp)
		{
			setActive(true);
		}
		else
		{
			setActive(false);
		}
	}
	// Sets whether or not there's an operation scheduled
	public void setOP(boolean OPArg)
	{
		OP = OPArg;
	}
	// Returns whether or not there's an operation scheduled
	public boolean getOP()
	{
		return OP;
	}
	// Sets whether or not there's an operation scheduled
	public void setDT(boolean DTArg)
	{
		DT = DTArg;
	}
	// Returns whether or not there's an operation scheduled
	public boolean getDT()
	{
		return DT;
	}
	// Gets measured air temperature
	public float getMeasuredAirTemp()
	{
		return measuredAirTemp;
	}
	// Sets measured air temperature
	public void setMeasuredAirTemp(float arg)
	{
		measuredAirTemp = arg;
	}
	// Gets the measured water temperature
	public float getMeasuredWaterTemp()
	{
		return measuredWaterTemp;
	}
	// Sets the measured water temperature
	public void setMeasuredWaterTemp(float arg)
	{
		measuredWaterTemp = arg;
	}
	// Gets the target water temperature
	public float getTargetWaterTemp()
	{
		return targetWaterTemp;
	}
	// Sets the target water temperature
	public void setTargetWaterTemp(float arg)
	{
		targetWaterTemp = arg;
	}
	// Returns the initiate
	public LocalDateTime getInitiate()
	{
		return initiate;
	}
	// Sets the initiate value
	public void setInitiate(LocalDateTime init)
	{
		initiate = init;
	}
	// Returns the terminator
	public LocalDateTime getTerminate()
	{
		return terminate;
	}
	// Sets the terminator value
	public void setTerminate(LocalDateTime term)
	{
		terminate = term;
	}
	// Sets the target temp boolean
	public void setAtTargetTemp(boolean atTargetTempArg)
	{
		atTargetTemp = atTargetTempArg;
	}
	// Returns the target temp boolean
	public boolean getAtTargetTemp()
	{
		return atTargetTemp;
	}
	// Determines the active state and sets operations accordingly
	public void setActive(boolean activeArg)
	{
		this.active = activeArg;
		if(this.active == true)
		{
			this.startTime = LocalDateTime.now();
			this.startOnClock();
		}
		else if(this.active == false)
		{
			this.stopTime = LocalDateTime.now();
			this.startOffClock();
		}
	}
	// Returns the active state
	public boolean getActive()
	{
		return this.active;		
	}
	// Returns the length of time the system has been active
	public Duration getRunTime()
	{
		if(this.active == true)
		{
			Duration duration = Duration.between(this.startTime, LocalDateTime.now());
			this.runTime = duration;
			return duration;
		}
		else
		{
			Duration duration = Duration.ZERO;
			return duration;
		}
	}
	// Returns the length of time the system has been inactive
	public Duration getDownTime()
	{
		if(this.active == true)
		{
			Duration duration = Duration.ZERO;
			return duration;
		}
		else
		{
			Duration duration = Duration.between(this.stopTime, LocalDateTime.now());
			this.downTime = duration;
			return duration;
		}
	}
	// Converts a string to a LocalDateTime from form "dd/MM/yyyy HH:mm"
	public LocalDateTime stringToLocalDateTime(String str)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(str, formatter);
        return formatDateTime;
    }	
	// Starts a timer from when the system activates. Used to calculate run time.
	public void startOnClock()
	{
		this.startTime = LocalDateTime.now();
	}
	// Starts a timer from when the system deactivates. Used to calculate down time.
	public void startOffClock()
	{
		this.stopTime = LocalDateTime.now();
	}
	// Schedules a time to run the system with a time and a duration of operations
	public void scheduleRunTime()
	{
		LocalDateTime T1 = LocalDateTime.now();
		LocalDateTime T2 = this.initiate;
		LocalDateTime T3 = this.terminate;
		
		if(dateMatch(T1, T2) && OP == true) //if it's time to start...
		{
			this.setActive(true);
			this.stopTime = LocalDateTime.now();
			this.startOnClock();
		}
		else if(dateMatch(T1, T3) && OP == true) //if it's time to stop again... 
		{
			this.setActive(false);
			this.startTime = LocalDateTime.now();
			this.startOffClock();
			this.OP = false;
		}
	}
	// Schedules a time to shut down the system with a time and a duration of inoperation
	public void scheduleDownTime()
	{
		LocalDateTime T1 = LocalDateTime.now();
		LocalDateTime T2 = this.initiate;
		LocalDateTime T3 = this.terminate;
		
		if(dateMatch(T1, T2) && OP == true) //if it's time to stop...
		{
			this.setActive(false);
			this.stopTime = LocalDateTime.now();
			this.startOffClock();
		}
		if(dateMatch(T1, T3) && OP == true) //if it's time to start again...
		{
			this.setActive(true);
			this.startTime = LocalDateTime.now();
			this.startOnClock();
			this.OP = false;
		}
	}
	// Determines if two dates match down to the minute
	public boolean dateMatch(LocalDateTime T1, LocalDateTime T2)
	{
		boolean match;
		if(T1.getYear() == T2.getYear() && T1.getMonthValue() == T2.getMonthValue() &&
				T1.getDayOfMonth() == T2.getDayOfMonth() && T1.getHour() == T2.getHour() && T1.getMinute() == T2.getMinute())
		{
			match = true;
		}
		else 
			match = false;
		return match;
	}
	// Converts the run time into a string which is usable for the UI.
	public String runTimeToString()
	{
		int c = this.getRunTime().toSecondsPart();
		String str = String.valueOf(c);
		String durationString = new String(str + " minute(s)");
		return durationString;
	}
	// Converts the down time into a string which is usable for the UI.
	public String downTimeToString()
	{
		int c = this.getDownTime().toSecondsPart();
		String str = String.valueOf(c);
		String durationString = new String(str + " minute(s)");
		return durationString;
	}
}

