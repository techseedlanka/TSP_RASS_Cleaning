/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

/**
 *
 * @author Sapu
 */
public class table_data {

    String AllowanceCode;
    String AllowanceName;
    String AllowanceAmount;

    String DeductionCode;
    String DeductionName;
    String DeductionAmount;

    //Admin Staff Attendance Entry
    String EMPID;
    String Date;
    int Worked;
    int Leave;
  

    //EMP_Attendance Entry - Guards
    int night;
    int day;
    int fullleave;
     int halfleave;
    String totalShifts;
    String month;
    String year;
    String EffectRank;
    String EffectRate;
    String DefCompany;
    String EmployeeNo;
    String Rank;
    String ShiftRate;
    String Location;
    String totalAmount;
    String ot;
    String otRate;

    //Location Carder
    String LocCarder_CarderType;
    String LocCarder_Rank;
    String LocCarder_NoOfGuards;

    //sunday shift rates
    String Loc;
    String rank;
    String Amount;

    //poya day shift rates
    String poya_Loc;
    String poya_rank;
    String poya_Amount;

    //Invoice Shift Rates
    String Invoice_Shift_Loc;
    String Invoice_Shift_Rank;
    String Invoice_Shift_Rate;

}
