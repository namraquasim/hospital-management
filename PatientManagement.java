// Description: This program manages patient queues, assigns patients to doctors,
//              release them to check out, etc.

import java.util.LinkedList;

public class PatientManagement
 {
    private LinkedList<Patient> highPriorityQueue; //Requires immediate life-saving intervention
    private LinkedList<Patient> intermediateQueue; //Requires significant intervention within two to four hours.
    private LinkedList<Patient> delayedCareQueue; //Needs medical treatment, but this can safely be delayed.

    private LinkedList<Patient> checkOutQueue; //queue for patients that need to check out

    private Doctor[] doctorList; //an array of doctors
     
  //Constructor to initialize member variables
  public PatientManagement(int numOfDoctors)
    {
        highPriorityQueue = new LinkedList<Patient>();
        intermediateQueue = new LinkedList<Patient>();
        delayedCareQueue = new LinkedList<Patient>();
        checkOutQueue = new LinkedList<Patient>();

        //creating doctor objects
        doctorList = new Doctor[numOfDoctors];
        for (int i=0; i<doctorList.length; i++)
        {
            doctorList[i] = new Doctor(i);
        }
    }
     
   //The printQueue prints out the content
   //of the queues and the array of doctors
   public void printPatientQueues()
     {
         System.out.print("\nHigh Priority Queue:\n" + highPriorityQueue.toString() + "\n");
         System.out.print("\nIntermediate Queue:\n" + intermediateQueue.toString() + "\n");
         System.out.print("\nDelayed Care Queue:\n" + delayedCareQueue.toString() + "\n\n");
         for (int i = 0; i < doctorList.length; i++)
         {
             System.out.println(doctorList[i].toString());
         }
         System.out.print("\nCheck Out Queue:\n" + checkOutQueue.toString() + "\n\n");
     }
     
   //More Methods need to be added here
     
   public boolean addPatient(int patientID, String conditionLevel) // adds a patient to the list and decides to add it to three different queues
   {
	   Patient pat = new Patient(patientID, conditionLevel);
	   
	   if(pat.getConditionLevel().equalsIgnoreCase("High Priority")) //checks the status/string if it is High priority
	   {
		   highPriorityQueue.add(pat);
		   return true;
	   }
	   
	   if (pat.getConditionLevel().equalsIgnoreCase("Intermediate")) // checks the status/string if it is intermediate 
	   {
		   intermediateQueue.add(pat);
		   return true;
	   }
	   
	   if (pat.getConditionLevel().equalsIgnoreCase("Delayed Care")) //checks the status/string if it is delayed care 
	   {
		   delayedCareQueue.add(pat);
		   return true;
	   }
	   
	   else
		   return false;
   }
   
   public Patient assignPatientToDoctor() //assigns a patient to a doctor for a doctor to work on 
   {
	   int count = 0;
	   for(int x = 0; x < doctorList.length;x++) //loops through doctors and keeps track of each one that has a patient
	   {
		   if(doctorList[x].hasPatient())
		   {
			   count ++;
		   }
	   }
	   if (count == doctorList.length) //if the count is the same as number of doctors it returns null
	   {
		   return null;
	   }
	   
	   Patient pat = null;
       if (!(highPriorityQueue.isEmpty())) //checks the high priority queue if it is empty
       {
           pat = highPriorityQueue.remove();          
       }
       
       else if (!(intermediateQueue.isEmpty())) //checks the intermediate queue if it is empty 
       {
           pat = intermediateQueue.remove();
       }
       
       else if (!(delayedCareQueue.isEmpty())) //checks the delayed care queue if it is empty
       {
           pat = delayedCareQueue.remove();
       }
       
       if (pat!=null)
       {
           for(int i = 0; i < doctorList.length; i++)  //loops through all the doctors to assign the patient to an empty doctor 
           {
               if (!(doctorList[i].hasPatient()))
               {
                   doctorList[i].assignPatient(pat);
                   return pat;
               }
           }
       }
       return null;
   }
   
   public Patient releasePatientFromDoctorToCheckOutQueue(int doctorID) //removes the patient from the doctor to the check out queue
   {
	   if (doctorID < 0 || doctorID >= doctorList.length)
	   {
		   return null;
	   }
	   
	   if (doctorList[doctorID].hasPatient())
	   {
		   Patient pat = doctorList[doctorID].releasePatient();
		   checkOutQueue.add(pat);
		   return pat;
	   }
	   
	   return null;
   }
   
   public Patient checkOutPatient() //checks out patient from the check out queue
   {
	   if(!(checkOutQueue.isEmpty()))
	   {
		   Patient pat = checkOutQueue.remove();
		   return pat;
	   }
	   
	   return null;
   }
     
 }