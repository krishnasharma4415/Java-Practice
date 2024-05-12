//Submitted By:Krishna Sharma and Prashant Singh
//Class:11-A
//Submitted To:Preetam Sir(COMPUTER SCIENCE Teacher)
import java.util.Scanner;
public class FlightTicketReservation
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        String name ;       //String to store  User Name
        int start ;         //Integer to store Starting point
        int dd,mm,yyyy;     //Integer to store Departure Date
        int time;           //Integer to store Departure Time
        int flighttype ;    //Integer to store Type Of Flight
        int end ;           //Integer to store Destination point 
        int airline ;       //Integer to store choosen Airline
        int classtype ;     //Integer to store Class preferred by user
        int price = 1 ;     //To store price of 1 Ticket
        int number;         //Integer to store Number of Passsengers Travelling entered by User
        double GST = 1;     //To store GST applies on air travel 
        double total;       //To calculate Total Fare including GST
        int finish ;        //To Confirm Booking of Air Ticket
        System.out.println("                                                   WELCOME TO MY AIRLINES     ");
        System.out.println("                                                   Flight Ticket Booking      ");
        System.out.println();
        System.out.println("                                                   Kindly Enter Your Name     ");
        name = in.nextLine(); //To take Name of User as Input
        System.out.println("                                                    WELCOME "+name             );
        System.out.println();
              
       
        System.out.println();
        System.out.println("                                                Enter your Departure Date:    ");
        System.out.println("                                                   Enter Date(DD)             ");
        dd=in.nextInt();
        System.out.println("                                                   Enter Month Number(MM)     ");
        mm=in.nextInt();
        System.out.println("                                                   Enter Year(YYYY)           ");
        yyyy=in.nextInt();
        System.out.println("                                       Your Departure Date is: "+dd+"/"+mm+"/"+yyyy);
        System.out.println();
        System.out.println("                                       Choose Departure Time(24Hr Clock Time):");
        System.out.println("                                                        1. 1:00");
        System.out.println("                                                        2. 2:00");
        System.out.println("                                                        3. 3:00");
        System.out.println("                                                        4. 4:00");
        System.out.println("                                                        5. 5:00");
        System.out.println("                                                        6. 6:00");
        System.out.println("                                                        7. 7:00");
        System.out.println("                                                        8. 8:00");
        System.out.println("                                                        9. 9:00");
        System.out.println("                                                       10. 10:00");
        System.out.println("                                                       11. 11:00");
        System.out.println("                                                       12. 12:00");
        System.out.println("                                                       13. 13:00");
        System.out.println("                                                       14. 14:00");
        System.out.println("                                                       15. 15:00");
        System.out.println("                                                       16. 16:00");
        System.out.println("                                                       17. 17:00");
        System.out.println("                                                       18. 18:00");
        System.out.println("                                                       19. 19:00");
        System.out.println("                                                       20. 20:00");
        System.out.println("                                                       21. 21:00");
        System.out.println("                                                       22. 22:00");
        System.out.println("                                                       23. 23:00");
        System.out.println("                                                       24. 24:00");
        time=in.nextInt(); //To take Departure Time as Input from User
        System.out.println("");
        System.out.println("                                                 Enter The Type of Flight:       ");
        System.out.println("                                                     1.Domestic Flight           ");
        System.out.println("                                                     2.International Flight      ");
        flighttype = in.nextInt(); //To take Type of Flight as Input from User
        System.out.println();
        if(flighttype==1)
        {
        System.out.println("                                             Enter your Starting Point Number:");
        System.out.println("                                                          1.New Delhi         ");
        System.out.println("                                                          2.Mumbai            ");
        System.out.println("                                                          3.Chennai           ");
        System.out.println("                                                          4.Kolkata           ");
        start = in.nextInt(); //To take Starting point as Input from User
        System.out.println("                                             Enter Your Destination Point Number:");
        System.out.println("                                                          1.New Delhi            ");
        System.out.println("                                                          2.Mumbai               ");
        System.out.println("                                                          3.Chennai              ");
        System.out.println("                                                          4.Kolkata              ");
        end = in.nextInt(); //To take Destination point as Input from User
        System.out.println();
        for(int i=1;i<=4;i++)
        {
        for(int j=1;j<=4;j++)
        {
        String st[] = {"New Delhi","Mumbai","Chennai","Kolkata"};
        if((start==i)&&(end==j))
        System.out.println("                                             Your Route is "+st[i-1]+" To "+st[j-1]+". ");
        }
        }
        System.out.println();
        System.out.println("                                                  Choose any Airline Number:        ");
        System.out.println("                                                      1.Indigo                      ");
        System.out.println("                                                      2.AirIndia                    ");
        System.out.println("                                                      3.SpiceJet                    ");
        System.out.println("                                                      4.Vistara                     ");
        airline = in.nextInt(); //To take choosen airline as Input from User
        System.out.println();
        System.out.println("                                                  Enter class type(1or2)            ");
        System.out.println("                                                    1.Economy Class                 ");
        System.out.println("                                                    2.Buisness Class                ");
        classtype = in.nextInt(); //To take class type as Input from User
        System.out.println();
        System.out.println("                                                 Enter Number of Travellers         ");
        number = in.nextInt(); //To take Number of Travellers as Input from User
        if(classtype==1)
        {
        if((start==1)&&(end==2)&&(airline==1))
        {price = 5500;}
        if((start==1)&&(end==3)&&(airline==1))
        {price = 7000;}
        if((start==1)&&(end==4)&&(airline==1))
        {price = 5500;}
        if((start==2)&&(end==1)&&(airline==1))
        {price = 5500;}
        if((start==2)&&(end==3)&&(airline==1))
        {price = 5000;}
        if((start==2)&&(end==4)&&(airline==1))
        {price = 5500;}
        if((start==3)&&(end==1)&&(airline==1))
        {price = 7000;}
        if((start==3)&&(end==2)&&(airline==1))
        {price = 5000;}
        if((start==3)&&(end==4)&&(airline==1))
        {price = 5000;}
        if((start==4)&&(end==1)&&(airline==1))
        {price = 5500;}
        if((start==4)&&(end==2)&&(airline==1))
        {price = 5500;}
        if((start==4)&&(end==3)&&(airline==1))
        {price = 5000;}
        
        if((start==1)&&(end==2)&&(airline==2))
        {price = 6000;}
        if((start==1)&&(end==3)&&(airline==2))
        {price = 7500;}
        if((start==1)&&(end==4)&&(airline==2))
        {price = 6000;}
        if((start==2)&&(end==1)&&(airline==2))
        {price = 6000;}
        if((start==2)&&(end==3)&&(airline==2))
        {price = 5500;}
        if((start==2)&&(end==4)&&(airline==2))
        {price = 6000;}
        if((start==3)&&(end==1)&&(airline==2))
        {price = 7500;}
        if((start==3)&&(end==2)&&(airline==2))
        {price = 5500;}
        if((start==3)&&(end==4)&&(airline==2))
        {price = 5500;}
        if((start==4)&&(end==1)&&(airline==2))
        {price = 6000;}
        if((start==4)&&(end==2)&&(airline==2))
        {price = 6000;}
        if((start==4)&&(end==3)&&(airline==2))
        {price = 5500;}
        
        if((start==1)&&(end==2)&&(airline==3))
        {price = 6500;}
        if((start==1)&&(end==3)&&(airline==3))
        {price = 8000;}
        if((start==1)&&(end==4)&&(airline==3))
        {price = 6500;}
        if((start==2)&&(end==1)&&(airline==3))
        {price = 6500;}
        if((start==2)&&(end==3)&&(airline==3))
        {price = 6000;}
        if((start==2)&&(end==4)&&(airline==3))
        {price = 6500;}
        if((start==3)&&(end==1)&&(airline==3))
        {price = 8000;}
        if((start==3)&&(end==2)&&(airline==3))
        {price = 6000;}
        if((start==3)&&(end==4)&&(airline==3))
        {price = 6000;}
        if((start==4)&&(end==1)&&(airline==3))
        {price = 6500;}
        if((start==4)&&(end==2)&&(airline==3))
        {price = 6500;}
        if((start==4)&&(end==3)&&(airline==3))
        {price = 6000;}
        
        if((start==1)&&(end==2)&&(airline==4))
        {price = 7000;}
        if((start==1)&&(end==3)&&(airline==4))
        {price = 8500;}
        if((start==1)&&(end==4)&&(airline==4))
        {price = 7000;}
        if((start==2)&&(end==1)&&(airline==4))
        {price = 7000;}
        if((start==2)&&(end==3)&&(airline==4))
        {price = 6500;}
        if((start==2)&&(end==4)&&(airline==4))
        {price = 7000;}
        if((start==3)&&(end==1)&&(airline==4))
        {price = 8500;}
        if((start==3)&&(end==2)&&(airline==4))
        {price = 6500;}
        if((start==3)&&(end==4)&&(airline==4))
        {price = 6500;}
        if((start==4)&&(end==1)&&(airline==4))
        {price = 7000;}
        if((start==4)&&(end==2)&&(airline==4))
        {price = 7000;}
        if((start==4)&&(end==3)&&(airline==4))
        {price = 6500;}
        GST = (price*5)/100;
        }
        
        if(classtype==2)
        {
        if((start==1)&&(end==2)&&(airline==1))
        {price = 11000;}
        if((start==1)&&(end==3)&&(airline==1))
        {price = 14000;}
        if((start==1)&&(end==4)&&(airline==1))
        {price = 11000;}
        if((start==2)&&(end==1)&&(airline==1))
        {price = 11000;}
        if((start==2)&&(end==3)&&(airline==1))
        {price = 10000;}
        if((start==2)&&(end==4)&&(airline==1))
        {price = 11000;}
        if((start==3)&&(end==1)&&(airline==1))
        {price = 14000;}
        if((start==3)&&(end==2)&&(airline==1))
        {price = 10000;}
        if((start==3)&&(end==4)&&(airline==1))
        {price = 10000;}
        if((start==4)&&(end==1)&&(airline==1))
        {price = 11000;}
        if((start==4)&&(end==2)&&(airline==1))
        {price = 11000;}
        if((start==4)&&(end==3)&&(airline==1))
        {price = 10000;}
        
        if((start==1)&&(end==2)&&(airline==2))
        {price = 12000;}
        if((start==1)&&(end==3)&&(airline==2))
        {price = 15000;}
        if((start==1)&&(end==4)&&(airline==2))
        {price = 12000;}
        if((start==2)&&(end==1)&&(airline==2))
        {price = 12000;}
        if((start==2)&&(end==3)&&(airline==2))
        {price = 11000;}
        if((start==2)&&(end==4)&&(airline==2))
        {price = 12000;}
        if((start==3)&&(end==1)&&(airline==2))
        {price = 15000;}
        if((start==3)&&(end==2)&&(airline==2))
        {price = 11000;}
        if((start==3)&&(end==4)&&(airline==2))
        {price = 11000;}
        if((start==4)&&(end==1)&&(airline==2))
        {price = 12000;}
        if((start==4)&&(end==2)&&(airline==2))
        {price = 12000;}
        if((start==4)&&(end==3)&&(airline==2))
        {price = 11000;}
        
        if((start==1)&&(end==2)&&(airline==3))
        {price = 13000;}
        if((start==1)&&(end==3)&&(airline==3))
        {price = 16000;}
        if((start==1)&&(end==4)&&(airline==3))
        {price = 13000;}
        if((start==2)&&(end==1)&&(airline==3))
        {price = 13000;}
        if((start==2)&&(end==3)&&(airline==3))
        {price = 12000;}
        if((start==2)&&(end==4)&&(airline==3))
        {price = 13000;}
        if((start==3)&&(end==1)&&(airline==3))
        {price = 16000;}
        if((start==3)&&(end==2)&&(airline==3))
        {price = 12000;}
        if((start==3)&&(end==4)&&(airline==3))
        {price = 12000;}
        if((start==4)&&(end==1)&&(airline==3))
        {price = 13000;}
        if((start==4)&&(end==2)&&(airline==3))
        {price = 13000;}
        if((start==4)&&(end==3)&&(airline==3))
        {price = 12000;}
        
        if((start==1)&&(end==2)&&(airline==4))
        {price = 14000;}
        if((start==1)&&(end==3)&&(airline==4))
        {price = 17000;}
        if((start==1)&&(end==4)&&(airline==4))
        {price = 14000;}
        if((start==2)&&(end==1)&&(airline==4))
        {price = 14000;}
        if((start==2)&&(end==3)&&(airline==4))
        {price = 13000;}
        if((start==2)&&(end==4)&&(airline==4))
        {price = 14000;}
        if((start==3)&&(end==1)&&(airline==4))
        {price = 17000;}
        if((start==3)&&(end==2)&&(airline==4))
        {price = 13000;}
        if((start==3)&&(end==4)&&(airline==4))
        {price = 13000;}
        if((start==4)&&(end==1)&&(airline==4))
        {price = 14000;}
        if((start==4)&&(end==2)&&(airline==4))
        {price = 14000;}
        if((start==4)&&(end==3)&&(airline==4))
        {price = 13000;}
        GST = (price*12)/100;
        }
        total = ( price + GST)*number;
        System.out.print('\u000C');
        System.out.println("                                             Total Amount Payable: Rs."+total      );
        System.out.println("                                               Press 1 to Finish Booking.         ");
        System.out.println("                                          Press Other Digit to Cancel The Booking.");
        finish = in.nextInt(); //To finish the Booking and show the Final Summary To the User
        System.out.print('\u000C');
        
        if(finish==1)
        {
        System.out.println();
        System.out.println("                                                  *****************             ");
        System.out.println("                                                  *Congratulations*             ");
        System.out.println("                                                  *****************             ");
        System.out.println("                                       Your Domestic Flight Tickets are Booked :)    ");
        System.out.println("                                       ------------------------------------------    ");
        System.out.println("                                              Here's Your Booking Summary       ");
        System.out.println("                                       ------------------------------------------    ");
        System.out.println("                                           Name:                 "+name          );
        for(int i=1;i<=4;i++)
        {
        String st[] = {"New Delhi","Mumbai","Chennai","Kolkata"};
        if(start==i)
        System.out.println("                                           Starting Point:       "+st[i-1]);
        }
        for(int i=1;i<=4;i++)
        {
        String plane[] = {"Indigo","AirIndia","SpiceJet","Vistara"};
        if(airline==i)
        System.out.println("                                           Airline:              "+plane[i-1]);
        }
        for(int i=1;i<=2;i++)
        {
        String seat[] = {"Economy Class","Buisness Class"};
        if(classtype==i)
        System.out.println("                                           Class:                "+seat[i-1]);
        }
        System.out.println("                                           Departure Date:       "+dd+"/"+mm+"/"+yyyy);
        for(int j=1;j<=24;j++)
        {
        String time3[] = {"1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00"};
        if(time==j)
        System.out.println("                                           Departure Time:       "+time3[j-1]);
        }
        System.out.println("                                           Number of Travellers: "+number        );
        System.out.println("                                           Ticket Price:         Rs."+price      );
        System.out.println("                                           GST:                  Rs."+GST        );
        System.out.println("                                           Total Amount Payable: Rs."+total      );
        System.out.println("                                           --------------------------------     ");
        System.out.println("                                           -----------THANK YOU------------     ");
        System.out.println("                                           --------------------------------     ");
        }
        else{ //If User wants to Cancel the Booking
        System.out.println("                                                      THANK YOU                  ");
        System.out.println("                                                    Visit Us Again               ");
        }
        }
        
        if(flighttype==2)
        {
        System.out.println("                                             Enter your Starting Point Number:");
        System.out.println("                                                          1.New Delhi         ");
        System.out.println("                                                          2.Mumbai            ");
        System.out.println("                                                          3.Chennai           ");
        System.out.println("                                                          4.Kolkata           ");
        System.out.println("                                                          5.Dubai             ");
        System.out.println("                                                          6.Bangkok           "); 
        System.out.println("                                                          7.Paris             "); 
        start = in.nextInt(); //To take Starting point as Input from User
        System.out.println("                                             Enter Your Destination Point Number:");
        System.out.println("                                                          1.Dubai                ");
        System.out.println("                                                          2.Bangkok              ");
        System.out.println("                                                          3.Paris                ");
        System.out.println("                                                          4.New Delhi            ");
        System.out.println("                                                          5.Mumbai               ");
        System.out.println("                                                          6.Chennai              ");
        System.out.println("                                                          7.Kolkata              "); 
        end = in.nextInt(); //To take Destination point as Input from User
        System.out.println();
        for(int i=1;i<=7;i++)
        {
        for(int j=1;j<=7;j++)
        {
        String st[] = {"New Delhi","Mumbai","Chennai","Kolkata","Dubai","Bangkok","Paris"};
        String dst[] = {"Dubai","Bangkok","Paris","New Delhi","Mumbai","Chennai","Kolkata"};
        if((start==i)&&(end==j))
        System.out.println("                                             Your Route is "+st[i-1]+" To "+dst[j-1]+". ");
        }
        }
        if(((start==1)||(start==2)||(start==3)||(start==4))&&((end==4)||(end==5)||(end==6)||(end==7)))
        {
        String st[] = {"New Delhi","Mumbai","Chennai","Kolkata","Dubai","Bangkok","Paris"};
        String dst[] = {"Dubai","Bangkok","Paris","New Delhi","Mumbai","Chennai","Kolkata"};    
        System.out.println("                                             Your Entered Route is of Domestic Flight Type ,i.e., "+st[start-1]+" and "+dst[end-1]);
        System.out.println("                                             Please Input International Flight Type Only or Choose Domestic Flight Type. ");
        }
        if(((start==1)||(start==2)||(start==3)||(start==4)||(start==5)||(start==6)||(start==7))&&((end==1)||(end==2)||(end==3)))
        {
        System.out.println();
        System.out.println("                                                  Choose any Airline Number:        ");
        System.out.println("                                                      1.Indigo                      ");
        System.out.println("                                                      2.AirIndia                    ");
        System.out.println("                                                      3.SpiceJet                    ");
        System.out.println("                                                      4.Vistara                     ");
        airline = in.nextInt(); //To take choosen airline as Input from User
        System.out.println();
        System.out.println("                                                  Enter class type(1or2)            ");
        System.out.println("                                                    1.Economy Class                 ");
        System.out.println("                                                    2.Buisness Class                ");
        classtype = in.nextInt(); //To take class type as Input fron User
        System.out.println();
        System.out.println("                                                 Enter Number of Travellers         ");
        number = in.nextInt();
        System.out.println("");
        if(classtype==1)
        {
        if((start==1)&&(end==1)&&(airline==1))
        {price = 10000;}
        if((start==1)&&(end==2)&&(airline==1))
        {price = 10000;}
        if((start==1)&&(end==3)&&(airline==1))
        {price = 40000;}
        if((start==2)&&(end==1)&&(airline==1))
        {price = 10000;}
        if((start==2)&&(end==2)&&(airline==1))
        {price = 10000;}
        if((start==2)&&(end==3)&&(airline==1))
        {price = 40000;}
        if((start==3)&&(end==1)&&(airline==1))
        {price = 10000;}
        if((start==3)&&(end==2)&&(airline==1))
        {price = 10000;}
        if((start==3)&&(end==3)&&(airline==1))
        {price = 40000;}
        if((start==4)&&(end==1)&&(airline==1))
        {price = 10000;}
        if((start==4)&&(end==2)&&(airline==1))
        {price = 10000;}
        if((start==4)&&(end==3)&&(airline==1))
        {price = 40000;}
        if((start==5)&&(end==4)&&(airline==1))
        {price =10000;}
        if((start==5)&&(end==5)&&(airline==1))
        {price =10000;}
        if((start==5)&&(end==6)&&(airline==1))
        {price =10000;}
        if((start==5)&&(end==7)&&(airline==1))
        {price =10000;}
        if((start==6)&&(end==4)&&(airline==1))
        {price =10000;}
        if((start==6)&&(end==5)&&(airline==1))
        {price =10000;}
        if((start==6)&&(end==6)&&(airline==1))
        {price =10000;}
        if((start==6)&&(end==7)&&(airline==1))
        {price =10000;}
        if((start==7)&&(end==4)&&(airline==1))
        {price =40000;}
        if((start==7)&&(end==5)&&(airline==1))
        {price =40000;}
        if((start==7)&&(end==6)&&(airline==1))
        {price =40000;}
        if((start==7)&&(end==7)&&(airline==1))
        {price =40000;}
        if((start==5)&&(end==2)&&(airline==1))
        {price = 30000;}
        if((start==5)&&(end==3)&&(airline==1))
        {price = 30000;}
        if((start==6)&&(end==1)&&(airline==1))
        {price = 30000;}
        if((start==6)&&(end==3)&&(airline==1))
        {price = 30000;}
        if((start==7)&&(end==1)&&(airline==1))
        {price = 40000;}
        if((start==7)&&(end==2)&&(airline==1))
        {price = 50000;}
        
        if((start==1)&&(end==1)&&(airline==2))
        {price = 15000;}
        if((start==1)&&(end==2)&&(airline==2))
        {price = 15000;}
        if((start==1)&&(end==3)&&(airline==2))
        {price = 45000;}
        if((start==2)&&(end==1)&&(airline==2))
        {price = 15000;}
        if((start==2)&&(end==2)&&(airline==2))
        {price = 15000;}
        if((start==2)&&(end==3)&&(airline==2))
        {price = 45000;}
        if((start==3)&&(end==1)&&(airline==2))
        {price = 15000;}
        if((start==3)&&(end==2)&&(airline==2))
        {price = 15000;}
        if((start==3)&&(end==3)&&(airline==2))
        {price = 45000;}
        if((start==4)&&(end==1)&&(airline==2))
        {price = 15000;}
        if((start==4)&&(end==2)&&(airline==2))
        {price = 15000;}
        if((start==4)&&(end==3)&&(airline==2))
        {price = 45000;}
        if((start==5)&&(end==4)&&(airline==2))
        {price =15000;}
        if((start==5)&&(end==5)&&(airline==2))
        {price =15000;}
        if((start==5)&&(end==6)&&(airline==2))
        {price =15000;}
        if((start==5)&&(end==7)&&(airline==2))
        {price =15000;}
        if((start==6)&&(end==4)&&(airline==2))
        {price =15000;}
        if((start==6)&&(end==5)&&(airline==2))
        {price =15000;}
        if((start==6)&&(end==6)&&(airline==2))
        {price =15000;}
        if((start==6)&&(end==7)&&(airline==2))
        {price =15000;}
        if((start==7)&&(end==4)&&(airline==2))
        {price =45000;}
        if((start==7)&&(end==5)&&(airline==2))
        {price =45000;}
        if((start==7)&&(end==6)&&(airline==2))
        {price =45000;}
        if((start==7)&&(end==7)&&(airline==2))
        {price =45000;}
        if((start==5)&&(end==2)&&(airline==2))
        {price = 35000;}
        if((start==5)&&(end==3)&&(airline==2))
        {price = 35000;}
        if((start==6)&&(end==1)&&(airline==2))
        {price = 35000;}
        if((start==6)&&(end==3)&&(airline==2))
        {price = 35000;}
        if((start==7)&&(end==1)&&(airline==2))
        {price = 45000;}
        if((start==7)&&(end==2)&&(airline==2))
        {price = 55000;}
        
        if((start==1)&&(end==1)&&(airline==3))
        {price = 20000;}
        if((start==1)&&(end==2)&&(airline==3))
        {price = 20000;}
        if((start==1)&&(end==3)&&(airline==3))
        {price = 50000;}
        if((start==2)&&(end==1)&&(airline==3))
        {price = 20000;}
        if((start==2)&&(end==2)&&(airline==3))
        {price = 20000;}
        if((start==2)&&(end==3)&&(airline==3))
        {price = 50000;}
        if((start==3)&&(end==1)&&(airline==3))
        {price = 20000;}
        if((start==3)&&(end==2)&&(airline==3))
        {price = 20000;}
        if((start==3)&&(end==3)&&(airline==3))
        {price = 50000;}
        if((start==4)&&(end==1)&&(airline==3))
        {price = 20000;}
        if((start==4)&&(end==2)&&(airline==3))
        {price = 20000;}
        if((start==4)&&(end==3)&&(airline==3))
        {price = 50000;}
        if((start==5)&&(end==4)&&(airline==3))
        {price =20000;}
        if((start==5)&&(end==5)&&(airline==3))
        {price =20000;}
        if((start==5)&&(end==6)&&(airline==3))
        {price =20000;}
        if((start==5)&&(end==7)&&(airline==3))
        {price =20000;}
        if((start==6)&&(end==4)&&(airline==3))
        {price =20000;}
        if((start==6)&&(end==5)&&(airline==3))
        {price =20000;}
        if((start==6)&&(end==6)&&(airline==3))
        {price =20000;}
        if((start==6)&&(end==7)&&(airline==3))
        {price =20000;}
        if((start==7)&&(end==4)&&(airline==3))
        {price =50000;}
        if((start==7)&&(end==5)&&(airline==3))
        {price =50000;}
        if((start==7)&&(end==6)&&(airline==3))
        {price =50000;}
        if((start==7)&&(end==7)&&(airline==3))
        {price =50000;}
        if((start==5)&&(end==2)&&(airline==3))
        {price = 40000;}
        if((start==5)&&(end==3)&&(airline==3))
        {price = 40000;}
        if((start==6)&&(end==1)&&(airline==3))
        {price = 40000;}
        if((start==6)&&(end==3)&&(airline==3))
        {price = 40000;}
        if((start==7)&&(end==1)&&(airline==3))
        {price = 50000;}
        if((start==7)&&(end==2)&&(airline==3))
        {price = 60000;}
        
        if((start==1)&&(end==1)&&(airline==4))
        {price = 25000;}
        if((start==1)&&(end==2)&&(airline==4))
        {price = 25000;}
        if((start==1)&&(end==3)&&(airline==4))
        {price = 55000;}
        if((start==2)&&(end==1)&&(airline==4))
        {price = 25000;}
        if((start==2)&&(end==2)&&(airline==4))
        {price = 25000;}
        if((start==2)&&(end==3)&&(airline==4))
        {price = 55000;}
        if((start==3)&&(end==1)&&(airline==4))
        {price = 25000;}
        if((start==3)&&(end==2)&&(airline==4))
        {price = 25000;}
        if((start==3)&&(end==3)&&(airline==4))
        {price = 55000;}
        if((start==4)&&(end==1)&&(airline==4))
        {price = 25000;}
        if((start==4)&&(end==2)&&(airline==4))
        {price = 25000;}
        if((start==4)&&(end==3)&&(airline==4))
        {price = 55000;}
        if((start==5)&&(end==4)&&(airline==4))
        {price =25000;}
        if((start==5)&&(end==5)&&(airline==4))
        {price =25000;}
        if((start==5)&&(end==6)&&(airline==4))
        {price =15000;}
        if((start==5)&&(end==7)&&(airline==4))
        {price =25000;}
        if((start==6)&&(end==4)&&(airline==4))
        {price =25000;}
        if((start==6)&&(end==5)&&(airline==4))
        {price =25000;}
        if((start==6)&&(end==6)&&(airline==4))
        {price =25000;}
        if((start==6)&&(end==7)&&(airline==4))
        {price =25000;}
        if((start==7)&&(end==4)&&(airline==4))
        {price =55000;}
        if((start==7)&&(end==5)&&(airline==4))
        {price =55000;}
        if((start==7)&&(end==6)&&(airline==4))
        {price =55000;}
        if((start==7)&&(end==7)&&(airline==4))
        {price =55000;}
        if((start==5)&&(end==2)&&(airline==4))
        {price =45000 ;}
        if((start==5)&&(end==3)&&(airline==4))
        {price = 45000;}
        if((start==6)&&(end==1)&&(airline==4))
        {price = 45000;}
        if((start==6)&&(end==3)&&(airline==4))
        {price = 45000;}
        if((start==7)&&(end==1)&&(airline==4))
        {price = 55000;}
        if((start==7)&&(end==2)&&(airline==4))
        {price = 65000;}
        GST = (price*5)/100;
        }
        
        if(classtype==2)
        {
        if((start==1)&&(end==1)&&(airline==1))
        {price = 20000;}
        if((start==1)&&(end==2)&&(airline==1))
        {price = 20000;}
        if((start==1)&&(end==3)&&(airline==1))
        {price = 80000;}
        if((start==2)&&(end==1)&&(airline==1))
        {price = 20000;}
        if((start==2)&&(end==2)&&(airline==1))
        {price = 20000;}
        if((start==2)&&(end==3)&&(airline==1))
        {price = 80000;}
        if((start==3)&&(end==1)&&(airline==1))
        {price = 20000;}
        if((start==3)&&(end==2)&&(airline==1))
        {price = 20000;}
        if((start==3)&&(end==3)&&(airline==1))
        {price = 80000;}
        if((start==4)&&(end==1)&&(airline==1))
        {price = 20000;}
        if((start==4)&&(end==2)&&(airline==1))
        {price = 20000;}
        if((start==4)&&(end==3)&&(airline==1))
        {price = 80000;}
        if((start==5)&&(end==4)&&(airline==1))
        {price =20000;}
        if((start==5)&&(end==5)&&(airline==1))
        {price =20000;}
        if((start==5)&&(end==6)&&(airline==1))
        {price =20000;}
        if((start==5)&&(end==7)&&(airline==1))
        {price =20000;}
        if((start==6)&&(end==4)&&(airline==1))
        {price =20000;}
        if((start==6)&&(end==5)&&(airline==1))
        {price =20000;}
        if((start==6)&&(end==6)&&(airline==1))
        {price =20000;}
        if((start==6)&&(end==7)&&(airline==1))
        {price =20000;}
        if((start==7)&&(end==4)&&(airline==1))
        {price =80000;}
        if((start==7)&&(end==5)&&(airline==1))
        {price =80000;}
        if((start==7)&&(end==6)&&(airline==1))
        {price =80000;}
        if((start==7)&&(end==7)&&(airline==1))
        {price =80000;}
        if((start==5)&&(end==2)&&(airline==1))
        {price = 60000;}
        if((start==5)&&(end==3)&&(airline==1))
        {price = 60000;}
        if((start==6)&&(end==1)&&(airline==1))
        {price = 60000;}
        if((start==6)&&(end==3)&&(airline==1))
        {price = 60000;}
        if((start==7)&&(end==1)&&(airline==1))
        {price = 80000;}
        if((start==7)&&(end==2)&&(airline==1))
        {price = 100000;}
        
        if((start==1)&&(end==1)&&(airline==2))
        {price = 30000;}
        if((start==1)&&(end==2)&&(airline==2))
        {price = 30000;}
        if((start==1)&&(end==3)&&(airline==2))
        {price = 90000;}
        if((start==2)&&(end==1)&&(airline==2))
        {price = 30000;}
        if((start==2)&&(end==2)&&(airline==2))
        {price = 30000;}
        if((start==2)&&(end==3)&&(airline==2))
        {price = 90000;}
        if((start==3)&&(end==1)&&(airline==2))
        {price = 30000;}
        if((start==3)&&(end==2)&&(airline==2))
        {price = 30000;}
        if((start==3)&&(end==3)&&(airline==2))
        {price = 90000;}
        if((start==4)&&(end==1)&&(airline==2))
        {price = 30000;}
        if((start==4)&&(end==2)&&(airline==2))
        {price = 30000;}
        if((start==4)&&(end==3)&&(airline==2))
        {price = 90000;}
        if((start==5)&&(end==4)&&(airline==2))
        {price =30000;}
        if((start==5)&&(end==5)&&(airline==2))
        {price =30000;}
        if((start==5)&&(end==6)&&(airline==2))
        {price =30000;}
        if((start==5)&&(end==7)&&(airline==2))
        {price =30000;}
        if((start==6)&&(end==4)&&(airline==2))
        {price =30000;}
        if((start==6)&&(end==5)&&(airline==2))
        {price =30000;}
        if((start==6)&&(end==6)&&(airline==2))
        {price =30000;}
        if((start==6)&&(end==7)&&(airline==2))
        {price =30000;}
        if((start==7)&&(end==4)&&(airline==2))
        {price =90000;}
        if((start==7)&&(end==5)&&(airline==2))
        {price =90000;}
        if((start==7)&&(end==6)&&(airline==2))
        {price =90000;}
        if((start==7)&&(end==7)&&(airline==2))
        {price =90000;}
        if((start==5)&&(end==2)&&(airline==2))
        {price = 65000;}
        if((start==5)&&(end==3)&&(airline==2))
        {price = 65000;}
        if((start==6)&&(end==1)&&(airline==2))
        {price = 65000;}
        if((start==6)&&(end==3)&&(airline==2))
        {price = 65000;}
        if((start==7)&&(end==1)&&(airline==2))
        {price = 85000;}
        if((start==7)&&(end==2)&&(airline==2))
        {price = 110000;}
        
        if((start==1)&&(end==1)&&(airline==3))
        {price = 40000;}
        if((start==1)&&(end==2)&&(airline==3))
        {price = 40000;}
        if((start==1)&&(end==3)&&(airline==3))
        {price = 100000;}
        if((start==2)&&(end==1)&&(airline==3))
        {price = 40000;}
        if((start==2)&&(end==2)&&(airline==3))
        {price = 40000;}
        if((start==2)&&(end==3)&&(airline==3))
        {price = 100000;}
        if((start==3)&&(end==1)&&(airline==3))
        {price = 40000;}
        if((start==3)&&(end==2)&&(airline==3))
        {price = 40000;}
        if((start==3)&&(end==3)&&(airline==3))
        {price = 100000;}
        if((start==4)&&(end==1)&&(airline==3))
        {price = 40000;}
        if((start==4)&&(end==2)&&(airline==3))
        {price = 40000;}
        if((start==4)&&(end==3)&&(airline==3))
        {price = 100000;}
        if((start==5)&&(end==4)&&(airline==3))
        {price =40000;}
        if((start==5)&&(end==5)&&(airline==3))
        {price =40000;}
        if((start==5)&&(end==6)&&(airline==3))
        {price =40000;}
        if((start==5)&&(end==7)&&(airline==3))
        {price =40000;}
        if((start==6)&&(end==4)&&(airline==3))
        {price =40000;}
        if((start==6)&&(end==5)&&(airline==3))
        {price =40000;}
        if((start==6)&&(end==6)&&(airline==3))
        {price =40000;}
        if((start==6)&&(end==7)&&(airline==3))
        {price =40000;}
        if((start==7)&&(end==4)&&(airline==3))
        {price =100000;}
        if((start==7)&&(end==5)&&(airline==3))
        {price =100000;}
        if((start==7)&&(end==6)&&(airline==3))
        {price =100000;}
        if((start==7)&&(end==7)&&(airline==3))
        {price =100000;}
        if((start==5)&&(end==2)&&(airline==3))
        {price = 70000;}
        if((start==5)&&(end==3)&&(airline==3))
        {price = 70000;}
        if((start==6)&&(end==1)&&(airline==3))
        {price = 70000;}
        if((start==6)&&(end==3)&&(airline==3))
        {price = 70000;}
        if((start==7)&&(end==1)&&(airline==3))
        {price = 90000;}
        if((start==7)&&(end==2)&&(airline==3))
        {price = 120000;}
        
        if((start==1)&&(end==1)&&(airline==4))
        {price = 50000;}
        if((start==1)&&(end==2)&&(airline==4))
        {price = 50000;}
        if((start==1)&&(end==3)&&(airline==4))
        {price = 110000;}
        if((start==2)&&(end==1)&&(airline==4))
        {price = 50000;}
        if((start==2)&&(end==2)&&(airline==4))
        {price = 50000;}
        if((start==2)&&(end==3)&&(airline==4))
        {price = 110000;}
        if((start==3)&&(end==1)&&(airline==4))
        {price = 50000;}
        if((start==3)&&(end==2)&&(airline==4))
        {price = 50000;}
        if((start==3)&&(end==3)&&(airline==4))
        {price = 110000;}
        if((start==4)&&(end==1)&&(airline==4))
        {price = 50000;}
        if((start==4)&&(end==2)&&(airline==4))
        {price = 50000;}
        if((start==4)&&(end==3)&&(airline==4))
        {price = 110000;}
        if((start==5)&&(end==4)&&(airline==4))
        {price =50000;}
        if((start==5)&&(end==5)&&(airline==4))
        {price =50000;}
        if((start==5)&&(end==6)&&(airline==4))
        {price =50000;}
        if((start==5)&&(end==7)&&(airline==4))
        {price =50000;}
        if((start==6)&&(end==4)&&(airline==4))
        {price =50000;}
        if((start==6)&&(end==5)&&(airline==4))
        {price =50000;}
        if((start==6)&&(end==6)&&(airline==4))
        {price =50000;}
        if((start==6)&&(end==7)&&(airline==4))
        {price =50000;}
        if((start==7)&&(end==4)&&(airline==4))
        {price =110000;}
        if((start==7)&&(end==5)&&(airline==4))
        {price =110000;}
        if((start==7)&&(end==6)&&(airline==4))
        {price =110000;}
        if((start==7)&&(end==7)&&(airline==4))
        {price =110000;}
        if((start==5)&&(end==2)&&(airline==4))
        {price = 80000;}
        if((start==5)&&(end==3)&&(airline==4))
        {price = 80000;}
        if((start==6)&&(end==1)&&(airline==4))
        {price = 80000;}
        if((start==6)&&(end==3)&&(airline==4))
        {price = 80000;}
        if((start==7)&&(end==1)&&(airline==4))
        {price = 100000;}
        if((start==7)&&(end==2)&&(airline==4))
        {price = 130000;}
        GST = (price*12)/100;
        }
        total = ( price + GST)*number;
        System.out.print('\u000C');
        System.out.println("                                             Total Amount Payable: Rs."+total      );
        System.out.println("                                               Press 1 to Finish Booking.         ");
        System.out.println("                                          Press Other Digit to Cancel The Booking.");
        finish = in.nextInt(); //To finish the Booking and show the Final Summary To the User
        System.out.print('\u000C');
        if(finish==1)
        {
        System.out.println();
        System.out.println("                                                  *****************             ");
        System.out.println("                                                  *Congratulations*             ");
        System.out.println("                                                  *****************             ");
        System.out.println("                                     Your International Flight Tickets are Booked :)    ");
        System.out.println("                                     -----------------------------------------------    ");
        System.out.println("                                              Here's Your Booking Summary       ");
        System.out.println("                                     -----------------------------------------------    ");
        System.out.println("                                           Name:                 "+name          );
        for(int i=1;i<=4;i++)
        {
        String st[] = {"New Delhi","Mumbai","Chennai","Kolkata"};
        if(start==i)
        System.out.println("                                           Starting Point:       "+st[i-1]);
        }
        for(int i=1;i<=3;i++)
        {
        String dst[] = {"Dubai","Bangkok","Paris"};
        if(end==i)
        System.out.println("                                           Destination Point:    "+dst[i-1]);
        }
        for(int i=1;i<=4;i++)
        {
        String plane[] = {"Indigo","AirIndia","SpiceJet","Vistara"};
        if(airline==i)
        System.out.println("                                           Airline:              "+plane[i-1]);
        }
        for(int i=1;i<=2;i++)
        {
        String seat[] = {"Economy Class","Buisness Class"};
        if(classtype==i)
        System.out.println("                                           Class:                "+seat[i-1]);
        }
        System.out.println("                                           Departure Date:       "+dd+"/"+mm+"/"+yyyy);
        for(int j=1;j<=24;j++)
        {
        String time3[] = {"1:00","2:00","3:00","4:00","5:00","6:00","7:00","8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00","24:00"};
        if(time==j)
        System.out.println("                                           Departure Time:       "+time3[j-1]);
        }
        System.out.println("                                           Number of Travellers: "+number        );
        System.out.println("                                           Ticket Price:         Rs."+price      );
        System.out.println("                                           GST:                  Rs."+GST        );
        System.out.println("                                           Total Amount Payable: Rs."+total      );
        System.out.println("                                           --------------------------------     ");
        System.out.println("                                           -----------THANK YOU------------     ");
        System.out.println("                                           --------------------------------     ");
        }
        else{ //If User wants to Cancel the Booking
        System.out.println("                                                      THANK YOU                  ");
        System.out.println("                                                    Visit Us Again               ");
        }
        }
        }
    }
}