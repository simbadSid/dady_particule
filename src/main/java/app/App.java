package main.java.app;

import java.io.FileOutputStream;

import main.java.gui.Gui;


// TODO declare all the functions used to change the variables (by independant threads) as synchronized



public class App implements Runnable
{
// -------------------------------------------------
// Attributes
// -------------------------------------------------
	public final static double	tetaMin			= 0;
	public final static double	tetaMax			= Math.PI / 2.;
	public final static double	xMin			= -1;
	public final static double	xMax			= 1;
	public final static double	zMin			= -.5;
	public final static double	zMax			= .5;
	public final static double	initial_teta	= .5;
	public final static double	initial_x		= 0;
	public final static double	initial_z		= .0001;

	private Gui					gui;

	private Thread				threadWorkHost;												// WorkHost thread parameters
	private boolean				isRunning		= false;
	private double				w, x, y, z;
	private double				alpha, teta, gama, sigma;


// -------------------------------------------------
// Builder
// -------------------------------------------------
	public App()
	{
		this.changeAngle(initial_teta);
	}


// -------------------------------------------------
// Getter/Setter
// -------------------------------------------------
	public boolean	isRunning			()	{return this.isRunning;}
	public double	getTeta				()	{return this.teta;}


	public void setGui				(Gui gui)			{this.gui				= gui;}
	public void init()
	{
// TODO
	}


// -------------------------------------------------
// Public methods
// -------------------------------------------------
	@Override
	public void run()
	{
		while (this.isRunning)
		{
			this.work();
		}
	}


	public synchronized void work()
	{
		this.w = this.x - this.y;
		this.x = this.w - this.y;

		if (1 < this.x * this.x + this.z)
		{
			this.z = this.sigma + this.gama * this.w * this.w - this.z;
			this.y = Math.sqrt(this.z) / this.alpha;
			this.x = - this.w - this.y;
		}

		this.gui.plot(this.x, this.z);
	}


	public synchronized void start()
	{
		assert(!this.isRunning);

		this.isRunning = true;
		this.threadWorkHost = new Thread(this);
		this.threadWorkHost.start();
	}


	public synchronized void pause()
	{
		assert(this.isRunning);

		this.isRunning = false;
	}


	public synchronized void changeInitialPoint(double x, double z)
	{
		assert((x >= xMin) && (x <= xMax));
		assert((z >= zMin) && (z <= zMax));

		this.x = x;
		this.z = z;
		this.y = Math.sqrt(this.z) / this.alpha;
	}


	public synchronized void changeAngle(double teta)
	{
		assert((teta >= tetaMin) && (teta <= tetaMax));

		double cos = Math.cos(teta);
		double sin = Math.sin(teta);

		this.teta	= teta;
		this.alpha	= sin / cos;
		this.sigma	= sin * sin * 2.;
		this.gama	= (cos - sin) * (cos - sin) * this.sigma;
		this.changeInitialPoint(initial_x, initial_z);
	}


	public void save(FileOutputStream outputFile)
	{
// TODO
	}


}