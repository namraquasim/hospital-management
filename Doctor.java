// Description: creates a doctor with an ID at a hospital and has methods to check if there is a patient, 
//		assign a patient, and release a patient and


public class Doctor
 {
  private int doctorID;
  private Patient currentPatient;

  //Constructor to initialize member variables
  //Initially no patient is assigned
  public Doctor(int id)
   {
    this.doctorID = id;
    currentPatient = null;
   }

  //toString method returns a string containing
  //the information of a doctor 
  public String toString()
   {
    String result = "Doctor id " + doctorID;

    if (currentPatient == null)
      result += " is not seeing any patient";
    else
      result += " is seeing a patient with id " + currentPatient.getPatientID();

    return result;
   }
  
  public boolean hasPatient() //checks if the doctor has a patient and returns true if the doctor does and false if the doctor doesn't not
  {
	  if (currentPatient != null)
		  return true;
	  return false;
  }
  
  public boolean assignPatient(Patient pat) //assigns a patient object to a current patient and returns true if successful and false if not
  {
	  if(hasPatient() == false)
	  {
		  currentPatient = pat;
		  return true;
	  }
	  else
		  return false;
  }
  
  public Patient releasePatient() //releases a patient from a doctor 
  {
	if(hasPatient()== true)
	{
		Patient patient = currentPatient;
		currentPatient = null;
		return patient;
	}
	else
		return null;
  }
     
  //More Methods need to be added here
     
 }
