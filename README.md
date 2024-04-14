# 01 Dates



## Files

### dates.java

The source code.

### in.txt

A list of valid and invalid inputs.

### output.txt

The list of results after testing the contents of a custom input file.

### validity.txt

The list of results after testing the contents of **in.txt**.

## Instructions

### Accepted Date Formats

- **Day:** Either **d** or **dd**, including any preceding 0s (if applicable). Must be an integer greater than 0 and less than or equal to the limit for a given month

- **Month:** Either **m** or **mm**, including any preceding 0s (if applicable). Must be an integer greater than 0 and less than 13.

- **Year:** Either **yy** or **yyyy**, including any preceding 0s (if applicable). Two-digit years must be greater than or equal to 0 and less than 100. Two-digit years below 50 will be filed under the 21st century (2000s), and all else will be filed under the 20th century (1900s). Four-digit years must be greater than or equal to 1753 and less than or equal to 3000.

- **Separators:** Either "-", "/", or " ". A date may only use one type of separator and may only use two instances of them, in the format **D|M|Y** where | is a placeholder for a given separator.

### 1: Manual Usage

Compile and run **dates.java** with the following commands:

    javac dates.java

    java dates

Type any date into the command line and press ENTER, and the result will either confirm or deny your date's validity according to the rules above. Type Ctrl+C to end the program.

### 2: Automatic Usage

Compile **dates.java**:

    javac dates.java

Run with the following command:

    java dates < [filename].txt > output.txt

for a custom testing file. To use the original testing data, replace **[filename].txt** with **in.txt**.

<<<<<<< HEAD
## Dev Diary

### Creating test data

My test data is grouped into two sections: valid and invalid dates. Valid dates are dates which perfectly conform to the criteria detailed above, and invalid dates are everything else.

When writing my test data I tried to 
=======
## Dev Log

When first beginning to write the test data, I had trouble thinking of examples of errors in incorrect dates, because I was too focused on the mindset of proving dates were **correct** instead. I had initially only covered the basic errors, such as numbers being out of the valid range for that value. However, with some helpful advice from Reuben and my peers I eventually began to expand my thinking to strictly test for different formatting errors, starting with the realization that my code allowed leading and trailing whitespaces and other separators. Eventually I managed to code all of these restrictions, and it was then just a matter of testing them.

To write the test data, I tried to create examples of incorrect dates using each boundary for valid values. For instance, when testing the checkers for the day value I would write date examples that featured days numbered -1, 0, 1, 28, 29, 30, 31, 32. This enabled me to test the boundaries in either direction, so that a number too high or too low would be caught and rejected, while retaining the border values as legitimate. I then did the same with dates featuring months -1, 0, 1, 12, 13 and years 1752, 1753, 1754, 2999, 3000, 3001.

To check for separators I initially considered using a form of REGEX to match the date format, but I quickly decided that this would be far above my capabilities, so instead I would take the date as a string input and split it three times into three unique string arrays. A valid date must have three sections, so any arrays with length not equal to three would constitute as illegitimate. I also had to make sure to check that each of the three indexes of any legal arrays were not empty, as a date like "-02-02" or "02-02-" would create an array of length 3 after splitting by the hyphens.

For leap years, I had to check whether the year was either a multiple of 4 but NOT 100, or a multiple of 400. Whenever either condition was met, I would increase the allowance for days in February by 1. I was concerned with whether any possible bugs in the code would make this increase permanent, so in my test data I made sure to consecutively test for 28-02, 29-02 in a non-leap year, 29-02 in a leap year, and then 29-02 in a non-leap year, in order to ensure that the occurrence of a leap year did not have an effect on the accuracy of future non-leap years.
>>>>>>> 009c2c9d22873a3b89f78156f2b8dc20f8601b11
