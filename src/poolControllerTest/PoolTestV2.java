package poolControllerTest;
import poolControllerPD.*;
import poolControllerUI.*;

public class PoolTestV2 
{
	public static void main(String args[])
    {		
		// Instantiate each subsystem
		FilterPump myFilterPump = new FilterPump(true);
		Heating myHeating = new Heating(true);
		FillPump myFillPump = new FillPump(true);
		Lights myLights = new Lights(true);
		Chemical myChemical = new Chemical(7.4f);
		// Instantiate the pool
		Pool myPool = new Pool(myChemical, myFillPump, myFilterPump, myHeating , myLights);
		// Open the GUI
		PoolFrame.open(myPool);
    }
}