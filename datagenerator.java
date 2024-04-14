public class datagenerator {   
    final static String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}; // List of months
    final static int[] DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // List of month lengths
    final static String[] DELS = {" ", "-", "/"};
    final static int MAX_YEAR = 3000;
    final static int MIN_YEAR = 1753;
    final static int MAX_MONTH = 12;
    final static int MIN_MONTH = 1;
    final static int MIN_DAY = 1;
    
    public static void main(String[] args){
        GenerateDates();
    }

    public static void GenerateDates(){
        IterateYear();
    }

    public static boolean IsLeapYear(int year){
        return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
    }

    public static void PrintDate(String d, int m, String y){
        String month = MONTHS[m-1];
        String monthCaps = month.toUpperCase();
        String monthLower = month.toLowerCase();
        String[] monthArr = {Integer.toString(m), month, monthCaps, monthLower,""};
        if(m < 10){
            monthArr[4] = "0"+m;
        }
        if(y.length() < 2){
            y = "0"+y;
        }
        for(String del : DELS){
            for(String mo : monthArr){
                if(!mo.equals("")){
                    System.out.println(d + del + mo + del + y);
                }
            }
        }
    }

    public static void IterateMonth(int year){
        for(int m = MIN_MONTH; m <= MAX_MONTH; m++){
            IterateDay(year, m);
        }
    }

    public static void IterateYear(){
        for(int y = MIN_YEAR; y <= MAX_YEAR; y++){
            IterateMonth(y);
        }
        for(int y = 0; y < 100; y++){
            IterateMonth(y);
        }
    }

    public static void IterateDay(int year, int month){
        int max_day = DAYS[month-1];
        if(IsLeapYear(year) && month==2){
            max_day++;
        }
        for(int d = MIN_DAY; d <= max_day; d++){
            if (d == 32) {
                System.out.println("oh no!");
            }
            PrintDate(Integer.toString(d),month,Integer.toString(year));
            if(d < 10){
                PrintDate("0"+d,month,Integer.toString(year));
            }
        }
    }


}
