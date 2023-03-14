package poolControllerPD;
import poolControllerPD.*;
@SuppressWarnings("unused")

public class Pool 
{
	private Chemical chemical;
	private FillPump fillPump;
	private FilterPump filterPump;
	private Heating heating;
	private Lights lights;
	private int panelIndex;
	
	// Constructor
	public Pool(Chemical chemicalParam, FillPump fillPumpParam, FilterPump filterPumpParam, Heating heatingParam, Lights lightsParam)
	{
		//populate pool elements
		this.chemical = chemicalParam;
		this.fillPump = fillPumpParam;
		this.filterPump = filterPumpParam;
		this.heating = heatingParam;
		this.lights = lightsParam;
	}
	// Default constructor
	public Pool()
	{
		// Populate pool elements
		this.chemical = new Chemical();
		this.fillPump = new FillPump();
		this.filterPump = new FilterPump();
		this.heating = new Heating();
		this.lights = new Lights();
	}
	// Sets the panel index
	public void setPanelIndex(int i)
	{
		panelIndex = i;
	}
	// Returns the panel index
	public int getPanelIndex()
	{
		return panelIndex;
	}
	// Return filter pump condition
	public FilterPump getFilterPump()
	{
		return this.filterPump;
	}
	// Return heater condition
	public Heating getHeating()
	{
		return this.heating;
	}
	// Return fill pump condition
	public FillPump getFillPump()
	{
		return this.fillPump;
	}
	// Return lights condition
	public Lights getLights()
	{
		return this.lights;
	}
	// Return chemical condition
	public Chemical getChemical()
	{
		return this.chemical;
	}
	// Set chemical condition
	public void setChemical(Chemical chemicalParam)
	{
		this.chemical = chemicalParam;
	}
	// Set filter pump condition
	public void setFilterPump(FilterPump filterPumpParam)
	{
		this.filterPump = filterPumpParam;
	}
	// Set heater condition
	public void setHeater(Heating heatingParam)
	{
		this.heating = heatingParam;
	}
	// Set fill pump condition
	public void setFillPump(FillPump fillPumpParam)
	{
		this.fillPump = fillPumpParam;
	}
	// Set light condition
	public void setLights(Lights lightsParam)
	{
		this.lights = lightsParam;
	}
}
