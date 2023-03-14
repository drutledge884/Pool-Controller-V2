package poolControllerPD;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Chemical
{
	private LocalDateTime initiate, dateChecked;
	@SuppressWarnings("unused")
	private boolean active, atTargetTemp, OP, DT;
	private float pH, storedPH;
	
	// Default Constructor
	public Chemical()
	{
		initiate = LocalDateTime.now();
		active = false; //whether or not the system is on
	}
	// Constructor
	public Chemical(float arg)
	{
		pH = arg;
		storedPH = 0f;
		//dateChecked = LocalDateTime.now();
		initiate = LocalDateTime.now();
		dateChecked = LocalDateTime.now().minusHours(24);
		OP = false;
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
	// Gets measured pH
	public float getpH()
	{
		return pH;
	}
	// Sets pH
	public void setpH(float arg)
	{
		pH = arg;
	}
	// Gets the stored pH
	public float getStoredPH()
	{
		return storedPH;
	}
	// Sets the stored pH
	public void setStoredPH(float arg)
	{
		storedPH = arg;
	}
	// Gets the stored date
	public LocalDateTime getStoredDate()
	{
		return dateChecked;
	}
	// Sets the stored date
	public void setStoredDate(LocalDateTime arg)
	{
		dateChecked = arg;
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
	// Converts a string to a LocalDateTime from form "dd/MM/yyyy HH:mm"
	public LocalDateTime stringToLocalDateTime(String str)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime formatDateTime = LocalDateTime.parse(str, formatter);
        return formatDateTime;
    }	
	// Schedules a time to run the system with a time and a duration of operations
	public void scheduleCheck()
	{
		LocalDateTime T1 = LocalDateTime.now();
		LocalDateTime T2 = this.initiate;
		
		if(dateMatch(T1, T2) && OP == true) //if it's time to check...
		{
			OP = false;
			storedPH = this.getpH();
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
}


