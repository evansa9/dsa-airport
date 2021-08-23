import java.io.*;

/**
 * Purpose: Data Structure and Algorithms Project 
 * Status: Complete and thoroughly tested
 * Last update: 12/04/19
 * Submitted:  12/05/19
 * Comment: test suite and sample run attached
 * @author: Andrew Evans and Gavin Coulthurst 
 * @version: 2019.09.06
 */

//Driver frame - Andrew
public abstract class Driver {

	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
	
	private static int currentRunway = 0;
	
	/**
	 * Main method, which runs the menu application.
	 *
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		//ADTs
		ListSLS<Runway> runways = new ListSLS<Runway>();
		ListSLS<Plane> planesWaiting = new ListSLS<Plane>();

		int totalTakeOffs = 0;

		System.out.println("Welcome to the airport program.");
		
		//Initialize runways
		System.out.print("Enter number of runways: ");
		int numberOfRunways = Integer.parseInt(stdin.readLine());
	        System.out.println(numberOfRunways);
		
		for (int i=0; i<numberOfRunways; i++) {
			System.out.print("Enter the name of runway number " + (i+1) + ": ");
			String runwayName = stdin.readLine();
			System.out.println(runwayName);
			//add runway to list
			runways.add(i, new Runway(runwayName));
		}	

		//start loop logic
		boolean run = true;
		int caseIn;

		System.out.println("Select from the following menu:");
		System.out.println("\t0. Exit the program.");
		System.out.println("\t1. Plane enters the system.");
		System.out.println("\t2. Plane takes off.");
		System.out.println("\t3. Plane is allowed to re-enter a runway.");
		System.out.println("\t4. Runway opens.");
		System.out.println("\t5. Runway closes.");
		System.out.println("\t6. Display info about planes waiting to take off.");
		System.out.println("\t7. Display info about planes waiting to be allowed to re-enter a runway.");
		System.out.println("\t8. Display number of planes who have taken off.");

		while (run) {
			System.out.print("\nMake you menu selection now: ");
			caseIn = Integer.parseInt(stdin.readLine());
			System.out.println(caseIn);

			switch (caseIn) {
			case 0: //exit
				System.out.println("Leaving airport.");
				run = false;
				break;
			case 1: //plane enters
				enterPlane(runways);
				break;
			case 2: //planes take off
				totalTakeOffs += takeOff(runways, planesWaiting);
				break;
			case 3: //plane re-enters
				reenterPlane(runways, planesWaiting);
				break;
			case 4: //runway opens
				openRunway(runways);
				break;
			case 5: //runway closes
				closeRunway(runways, planesWaiting);
				break;
			case 6: //display waiting to take off planes
				System.out.print(runways.toString());
				break;
			case 7: //display waiting to re-enter planes
				displayPlanes(planesWaiting);
				break;
			case 8: //display number taken off planes
				System.out.println(totalTakeOffs + " planes have taken off from the airport.");
				break;
			default: 
				System.out.println("Please enter a a valid integer.");
			}
		}
	}	

	//worked on by Andrew
	/**
	 * Method which takes user input to add a new Plane to a Runway.
	 *
	 * @param runways The list of Runways
	 * @throws IOException 
	 */
	public static void enterPlane(ListSLS<Runway> runways) throws IOException
	{
		//get new plane specifications
		System.out.print("Enter a flight number: ");
		String flightNumber = stdin.readLine();
		System.out.println(flightNumber);
		System.out.print("Enter destination: ");
		String destination = stdin.readLine();
		System.out.println(destination);
		System.out.print("Enter runway: ");
		String runway = stdin.readLine();
		System.out.println(runway);

		//check flight number for uniqueness
		boolean unique = true;
		for (int i=0; i < runways.size() && unique; i++) {
			Runway current = runways.get(i);
			for (int j =0; j < current.getSize(); j++) {
				Plane p = current.takeoff();
				if (p.getFlightNum().equals(flightNumber)) {
					unique = false;
				}
				current.enterRunway(p);
			}
		}

		//uniqueness logic
		if (!unique) {
			System.out.println("Plane not added, flight number is not unique.");
			return;
		}

		//add to correct runway queue
		boolean added = false;
		while (!added) {
			for (int i=0; i<runways.size() && (!added); i++) {
				Runway current = runways.get(i);
				if (current.getName().equals(runway)) {
					Plane newPlane = new Plane(flightNumber, destination, current);
					current.enterRunway(newPlane);
					added = true;
				}
			}
			if (!added) {
				System.out.println("No such runway.\nEnter runway: ");
				runway = stdin.readLine();
			}
		}

	}

	//worked on by Andrew
	/**
	 * Method which allows a Plane to take off from its runway, if it is given clearance.
	 *
	 * @param runways The list of Runways
	 * @param planesWaiting The list of planes waiting to re-enter a Runway.
	 * @throws IOException
	 */
	public static int takeOff(ListSLS<Runway> runways, ListSLS<Plane> planesWaiting) throws IOException
	{
		//select runway
		boolean selected = false;
		Runway current = null;
		for (int i =0; i < runways.size() && (!selected); i++) {
			current = runways.get((currentRunway)%runways.size());
			if (current.isEmpty()) {
				currentRunway++;
			}
			else {
				selected = true;
			}
		}

		//return if all runways are empty
		if (selected == false) {
			System.out.println("No plane on any runway.");
			return 0;
		}

		//get clearance confimration
		Plane waiting = current.takeoff();
		System.out.print("Is flight " +waiting.getFlightNum()+ " cleared for takeoff? (Y/N): ");
		char cleared = stdin.readLine().charAt(0);
		System.out.println(cleared);

		currentRunway = (currentRunway +1)%runways.size();

		//clearance logic
		if(cleared == 'Y' || cleared == 'y') {
			System.out.println("Flight " +waiting.getFlightNum()+ " has taken off from runway " +current.getName()+ ".");
			return 1;
		}
		else {
			System.out.println("Flight " +waiting.getFlightNum()+ " is now waiting to be allowed to re-enter a runway.");
			planesWaiting.add(planesWaiting.size(),waiting);
			return 0;
		}
	}

	//worked on by Andrew
	/**
	 * Allows a plane to re-enter its desired Runway.
	 *
	 * @param runways The list of Runways.
	 * @param planesWaiting The list of Planes waiting to re-enter a Runway.
	 * @throws IOException
	 */
	public static void reenterPlane(ListSLS<Runway> runways, ListSLS<Plane> planesWaiting) throws IOException
	{
		if (planesWaiting.isEmpty()) {
			System.out.println("There are no planes waiting for clearance.");
		}
		else {
			System.out.print("Enter flight number: ");
			String flightNumber = stdin.readLine();
			System.out.println(flightNumber);

			boolean flightMatch = false;
			Plane plane = null;
			while (!flightMatch) {

				int i =0;
				for (i=0; i < planesWaiting.size() && (!flightMatch); i++) {
					plane = planesWaiting.get(i);
					if (plane.getFlightNum().equals(flightNumber)) {
						flightMatch = true;
					}
				}

				if (!flightMatch) {
					System.out.print("Flight " +flightNumber+ " is not waiting for clearance.\nEnter flight number: ");
					flightNumber = stdin.readLine();
					System.out.println(flightNumber);
				}
				else {
					System.out.println("Flight " +plane.getFlightNum()+ " is now waiting for takeoff on runway " +plane.getRunway().getName() + ".");
					planesWaiting.remove(i-1);
					plane.getRunway().enterRunway(plane);
				}
			}

		}	
	}

	//worked on by Gavin
	/**
	 * Opens a new Runway.
	 *
	 * @param runways The list of Runways
	 * @throws IOException
	 */
	public static void openRunway(ListSLS<Runway> runways) throws IOException
	{
		boolean validName = false;
		String name = "";
		while (!validName)
		{
			validName = true;
			System.out.print("Enter the name of the new runway : ");
			name = stdin.readLine();
			System.out.println(name);

			for (int i = 0; i < runways.size(); i++)
			{
				if (runways.get(i).getName().equals(name))
				{
					validName = false;
					System.out.println("Runway " + name + " already exists, please choose another name.");
				}
			}
		}

		runways.add(runways.size(), new Runway(name));
		System.out.println("Runway " + name + " has opened.");
	}

	//worked on by Gavin
	/**
	 * Closes a Runway, and reassigns all of the planes waiting on it to another Runway.
	 *
	 * @param runways The list of Runways.
	 * @param planesWaiting The list of planes waiting to re-enter a Runway.
	 * @throws IOException
	 */
	public static void closeRunway(ListSLS<Runway> runways, ListSLS<Plane> planesWaiting) throws IOException
	{
		// Finds runway to close.
		Runway runwayToClose = null;
		while (runwayToClose == null)
		{
			System.out.print("Enter runway: ");
			String name = stdin.readLine();
			System.out.println(name);

			for (int i = 0; i < runways.size(); i++)
			{
				if (runways.get(i).getName().equals(name))
				{
					runwayToClose = runways.get(i);
					runways.remove(i);
				}
			}
			if (runwayToClose == null)
			{
				System.out.println("No such runway!");
			}
		}

		// Loops through each plane waiting to takeoff on the runway, assigning it a new runway.
		while (!runwayToClose.isEmpty())
		{
			Plane nextPlane = runwayToClose.takeoff();
			Runway nextRunway = null;

			while (nextRunway == null)
			{
				System.out.print("Enter new runway for plane " + nextPlane.getFlightNum() + ": ");
				String name = stdin.readLine();
				System.out.println(name);

				for (int i = 0; i < runways.size(); i++)
				{
					if (runways.get(i).getName().equals(name))
					{
						nextRunway = runways.get(i);
					}
				}
				if (nextRunway == null)
				{
					System.out.println("No such runway!");
				}
			}

			nextPlane.setRunway(nextRunway);
			nextRunway.enterRunway(nextPlane);
			System.out.println("Flight " + nextPlane.getFlightNum() + " is now waiting for takeoff on runway " + nextRunway.getName() + ".");
		}

		// Loops through the planes waiting to re-enter a runway, seeing if any need to find a new runway.
		for (int i = 0; i < planesWaiting.size(); i++)
		{
			if (planesWaiting.get(i).getRunway().getName().equals(runwayToClose.getName()))
			{
				Runway nextRunway = null;
				
				while (nextRunway == null)
				{
					System.out.print("Enter new runway for plane " + planesWaiting.get(i).getFlightNum() + ": ");
					String name = stdin.readLine();
					System.out.println(name);

					for (int j = 0; j < runways.size(); j++)
					{
						if (runways.get(j).getName().equals(name))
						{
							nextRunway = runways.get(j);
						}
					}
					if (nextRunway == null)
					{
						System.out.println("No such runway!");
					}
				}

				planesWaiting.get(i).setRunway(nextRunway);
				System.out.println("Flight " + planesWaiting.get(i).getFlightNum() + " is now waiting for takeoff on runway " + nextRunway.getName() + ".");
			}
		}

		System.out.println("Runway " + runwayToClose.getName() + " has been closed.");
	}

	//worked on by Gavin
	/**
	 * Displays all the planes waiting to re-enter a Runway.
	 * 
	 * @param planesWaiting The list of planes waiting to re-enter a Runway.
	 */
	public static void displayPlanes(ListSLS<Plane> planesWaiting)
	{
		if (planesWaiting.isEmpty())
		{
			System.out.println("There are no planes waiting to be cleared to re-enter a runway!");
		}
		else
		{
			System.out.println("These planes are waiting to be cleared to re-enter a runway:");
			System.out.println(planesWaiting.toString());
		}
	}


}
