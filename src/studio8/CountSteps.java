package studio8;

import java.io.*;
import java.util.*;

// TODO: Develop an algorithm to count steps in accelerometer data
//    Major steeps:
//       1. Create a class and main method.
//       2. Using a Scanner and File object, read data from your .csv file.
//       3. Develop and test algorithms to count the "peaks" in the data.

public class CountSteps
{
	public static void main(String[] args)
	{
		try
		{
			float[] pastZ = {1, 1, 1};
			int count = 0;
			boolean cooldown = false;
			int cooldownCounter = 0;
			int line = 0;
			
			File dataInput = new File("data/steps2.csv");
			Scanner dataScanner = new Scanner(dataInput);
			
			while(dataScanner.hasNextLine())
			{
				line++;
				String currLine = dataScanner.nextLine();
				
				if(cooldownCounter > 25)
				{
					cooldown = false;
					cooldownCounter = 0;
				}
				
				if(cooldown)
				{
					cooldownCounter++;
					continue;
				}
				
				String[] currData = currLine.split(",");
				
				float x = Float.parseFloat(currData[0]);
				float y = Float.parseFloat(currData[1]);
				float z = Float.parseFloat(currData[2]);
				
				pastZ[0] = pastZ[1];
				pastZ[1] = pastZ[2];
				pastZ[2] = z;
				
				// if(pastZ[1] < pastZ[0] && pastZ[1] < pastZ[2])
				
				// if(pastZ[1] < 0.75)
				// {
				//	System.out.println("FOUND! at " + line);
				//	cooldown = true;
				//	++count;
				// }
				
				if(z < 0.65 && !cooldown)
				{
					while(z < 0.65)
					{
						currLine = dataScanner.nextLine();
						currData = currLine.split(",");
						
						z = Float.parseFloat(currData[2]);
					}
					
					cooldown = true;
					count++;
				}

			}
			
			System.out.println("COUNT is \t" + count);
			
			dataScanner.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}