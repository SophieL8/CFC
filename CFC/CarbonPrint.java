public class CarbonPrint {
  private String commute;
  private String typeOfCar;
  private double flyHours;
  private String diet;
  private String energy;

  /** Default constructor */
  public CarbonPrint() {
    this("car", "electric", 3, "meat", "100% renewable");
  }

  /** Construct a loan with specified annual interest rate,
      number of years, and loan amount
    */
  public CarbonPrint(String commute, String typeOfCar, double myflyHours,
                     String diet, String energy)  {
                     
    this.commute = commute;
    this.typeOfCar = typeOfCar;
    flyHours = myflyHours;
    this.diet = diet;
    this.energy = energy;
  }

  /** Return monthlyElectricBill */
  public String getCommute() {
    return commute;
  }

  /** Set a new monthlyElectricBill */
  public void setCommute(String commute) {
    this.commute = commute;
  }

  /** Return monthlyGasBill */
  public String getTypeOfCar() {
    return typeOfCar;
  }

  /** Set a new monthlyGasBill */
  public void setTypeOfCar(String typeOfCar) {
    this.typeOfCar = typeOfCar;
  }

  /** Return monthlyOilBill */
  public double getFlyHours() {
    return flyHours;
  }

  /** Set a monthlyOilBill */
  public void setFlyHours(double newflyHours) {
    flyHours = newflyHours;
  }
  
  /** Return totalYearlyMileage */
  public String getDiet() {
    return diet;
  }

  /** Set a totalYearlyMileage */
  public void setDiet(String diet) {
    this.diet = diet;
  }
  
  /** Return numFlightsLess */
  public String getEnergy() {
    return energy;
  }

  /** Set a numFlightsLess */
  public void setEnergy(String energy) {
    this.energy = energy;
  }

  /** Find total carbon footprint  **/  
  public double getTotalFootprint() {
  
    double total = 0;
    
    int commuteScore = 0;
    int typeOfCarScore = 0;
    int dietScore = 0;
    int energyScore = 0;

    if (getCommute().equals("Walking or Biking")) {
      commuteScore = 0;
    }
    
    else if (getCommute().equals("Public Transporation/Other methods")) {
      commuteScore = 1;
    }
    
    else if (getCommute().equals("Car")) {
      commuteScore = 2;
    }
    
    if (getTypeOfCar().equals("Don't own a car")) {
      typeOfCarScore = 0;
    }
    
    else if (getTypeOfCar().equals("Electric")) {
      commuteScore = 1;
    }
    
    else if (getTypeOfCar().equals("Other")) {
      commuteScore = 2;
    }
    
    if (getDiet().equals("Plants")) {
      dietScore = 0;
    }
    
    else if (getDiet().equals("Equal amounts of veggies and meat")) {
      dietScore = 1;
    }
    
    else if (getDiet().equals("Meat")) {
      dietScore = 2;
    }
    
    if (getEnergy().equals("100% renewable")) {
      energyScore = 0;
    }
    
    else if (getEnergy().equals("Some are renewable")) {
      energyScore = 1;
    }
    
    else if (getEnergy().equals("100% non renewable")) {
      energyScore = 2;
    }
    
    double finalCommute = 0.19*16*commuteScore;
    double finalTypeOfCar = 0.19*16*commuteScore;
    double finalFlyHours = getFlyHours()*0.25;
    double finalDiet = 0.2*16*dietScore;
    double finalEnergy = 0.19*16*energyScore;
    
    total = finalCommute + finalTypeOfCar + finalFlyHours + finalDiet + finalEnergy;
    return total;  
    
  }
}