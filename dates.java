import java.util.Scanner;

public class dates {
   final static String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}; // List of months
   final static int[] DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // List of month lengths

   final static int MAX_YEAR = 3000;
   final static int MIN_YEAR = 1753;
   final static int MAX_YEAR_LENGTH = 4;
   final static int MIN_YEAR_LENGTH = 2;
   final static int MAX_MONTH = 12;
   final static int MIN_MONTH = 1;
   final static int MIN_DAY = 1;
   final static int MAX_MONTH_STR = 3;
   final static int MAX_MONTH_INT = 2;
   final static int TWOTHOUSAND = 2000;
   final static int NINETEENHUNDRED = 1900;
   static boolean IsLeapYear = false;

   public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      while(sc.hasNext()){
         String input = sc.nextLine();
         DateValidator(input);
      }
      sc.close();
   }

   public static void DateValidator(String input){
      try{
         IsLeapYear = false;
         String newDate;

         SeparatorValidator(input);
         
         String[] date = DateSplitter(input);

         newDate = DateConstructor(date);

         System.out.println(newDate);

      } catch(Exception e){
         System.out.println(input + " - INVALID");
         System.err.println(e.getMessage() + " " + input);
      }
   }

   public static String DateConstructor(String[] date) throws Exception{
      try{
         String newDate = "";
         String month = MonthFormat(MonthValidator(date[1]));   // Reformats the month for the output date
         int monthNum = FindMonthIndex(month);
         String year = FormatYear(date[2]);
         int yearNum = StringToInt(year);
         String day = FormatDay(date[0], monthNum, yearNum);

         newDate = day + " " + month + " " + year;
         return newDate;
      } catch(Exception e){
         throw new Exception(e.getMessage());
      }
   }

   public static void SeparatorValidator(String in) throws Exception{
         String[] dels = {" ", "/", "-"}; // List of separators
         String temp = in;
         int x = 0;
         for(String del : dels){
            String temp2 = temp.replace(del, "");
            x += (temp.length() - temp2.length());
         }
         if(x != 2){
            throw new Exception("Invalid date format: Separators");
         }
   }

   public static String[] DateSplitter(String input) throws Exception{
         // Splits the string using each of the three separators
         String[] date1 = input.split(" ");
         String[] date2 = input.split("-");
         String[] date3 = input.split("/");
         
         String[] date = null;
         
         // Checks which version of the array is the correct length, if any
         if(date1.length == 3){
            date = date1;
         } else if(date2.length == 3){
            date = date2;
         } else if(date3.length == 3){
            date = date3;
         } else { // if none match, then it's invalid
            throw new Exception("Invalid date format: Length");
         }

         return date;
   }
   
   public static void MonthLengthChecker(String month, boolean isInteger) throws Exception{
      int maxLength;
      if(isInteger){
         maxLength = MAX_MONTH_INT;
      } else {
         maxLength = MAX_MONTH_STR;
      }
      int monthLength = month.length();
      if(monthLength > maxLength || monthLength < 1){
         throw new Exception("Invalid month: Incorrect length");
      }
   }

   public static String MonthValidatorInt(String in) throws Exception{
      MonthLengthChecker(in, true);
      int n = StringToInt(in);
      String month = "";
      if(n <= MAX_MONTH && n >= MIN_MONTH){
         month = MONTHS[n - 1];
      } else {
         throw new Exception("Invalid month: Must be 1-12");
      }
      return month;
   }

   public static String MonthValidatorString(String in) throws Exception{
      // In this instance, the month is probably in a string format instead
      MonthLengthChecker(in,false);
      if(in.length() == 3){ // Checks if the input month is a 3-letter string
         boolean temp2 = false;
         for(String m : MONTHS){
            if(m.toLowerCase().equals(in) || m.toUpperCase().equals(in) || MonthFormat(m).equals(in)){ // iterates through the list of months to find a match
               temp2 = true;
            }
         }
         if(temp2 == false){ // If no match has been found for the month
            throw new Exception("Invalid month: Does not exist");
         }
      } else { // If it's not 3 letters, it can't be a valid month
         throw new Exception("Invalid month: Must be 3 letters");
      }
      return in;
   }

   public static String MonthValidator(String in) throws Exception{
      String month = "";
      if(IsInteger(in)){
         month = MonthValidatorInt(in);
      } else {
         month = MonthValidatorString(in);
      }

      return month;
   }

   public static boolean IsInteger(String in){
      try{
         int num = Integer.parseInt(in);
         return true;
      } catch(Exception e){
         return false;
      }
   }

   public static int FindMonthIndex(String month){
      int num = 0;
      for(int i = 0; i < MAX_MONTH; i++){
         if(month.equals(MONTHS[i])){
            num = i+1;
         }
      }
      return num;
   }

   public static boolean IsLeapYear(int year){
      boolean LeapYear = false;
      if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){
         LeapYear = true;
      }
      return LeapYear;
   }

   public static int StringToInt(String in) throws Exception{
      int out = Integer.parseInt(in);
      return out;
   }

   public static String FormatDay(String in, int month, int year) throws Exception{
      String newDate = "";
      if(in.length() > 2 || in.length() < 1){ // Checks that the day is not more than 2 digits
         throw new Exception("Invalid day: Incorrect length");
      }
      int day = 0;

      try{
         day = StringToInt(in);
      } catch (Exception e){
         throw new Exception("Invalid day: Not an integer");
      }

      newDate = day + "";

      if(day < 10){ // If the day is less than 10, add a leading zero to the new date
         newDate = "0" + newDate;
      }
      DayValidator(day, month, year);
      
      return newDate;
   }

   public static void DayValidator(int day, int month, int year) throws Exception{
      int daysInMonth = DaysInMonth(month, year);
      if(day > daysInMonth || day < MIN_DAY){ // Check that the day is greater than 0 and less than the maximum for that month
         throw new Exception("Invalid day: Does not exist");
      }
   }

   public static int DaysInMonth(int month, int year){
      int days = DAYS[month-1];
      if(IsLeapYear(year) && month==2){
         days++;
      }
      return days;
   }

   public static String FormatYear(String in) throws Exception{
      String year = "";

      if(YearLengthValidator(in)){
         year = in;
      } else {
         year = AppendYear(in);
      }
      int y = Integer.parseInt(year);

      try{
         y = StringToInt(year);
      } catch(Exception e){
         throw new Exception("Invalid year: Not an integer");
      }

      YearValidator(y);
      
      return year;
   }

   public static void YearValidator(int in) throws Exception{
      if(in > MAX_YEAR || in < MIN_YEAR){ // check if year is in acceptable range
         throw new Exception("Invalid year: Out of range");
      }
   }

   public static boolean YearLengthValidator(String in) throws Exception{
      if(in.length() != MAX_YEAR_LENGTH){
         if(in.length() == MIN_YEAR_LENGTH){
            return false;
         } else {
            throw new Exception("Invalid year: Wrong number of digits");
         }
      } else {
         return true;
      }
   }

   public static String AppendYear(String in) {
      int y = Integer.parseInt(in);
      if(y < 50) {
         in = 20 + in;
      } else {
         in = 19 + in;
      }
      return in;
   }

   public static String MonthFormat(String month){
      return month.substring(0,1).toUpperCase() + month.substring(1).toLowerCase(); // Formats a three-letter string to an "Xxx" format (First letter caps)
   }
}