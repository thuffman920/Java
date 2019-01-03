/**
 * 
 */
package tshuffma.cd_directory.util;

import java.util.Calendar;

/**
 * @author Tyler
 *
 */
public class Date {

  private static final int THIRTY_ONE_DAYS = 31;
  
  private static final int THIRTY_DAYS = 30;
  
  private static final int TWENTY_EIGHT_DAYS = 28;
  
  private static final int LEAP_YEAR = 29;
  
  private static final int[] thirtyOneDays = {1, 3, 5, 7, 8, 10, 12};
  
  private static final int[] thirtyDays = {4, 6, 9, 11};
  
  private int year;
  
  private int month;
  
  private int day;
  
  public Date(int month, int day, int year) {
    boolean correct = false;
    if (month == 2 && this.yearIsLeap() && day > LEAP_YEAR)
      throw new IllegalArgumentException("Exception: Wrong day");
    else if (month == 2 && day > TWENTY_EIGHT_DAYS)
      throw new IllegalArgumentException("Exception: Wrong day");
    else if (month > 12 || month <= 0)
      throw new IllegalArgumentException("Exception: Wrong day");
    for (int i = 0; i < thirtyDays.length; i++) {
      if (month == thirtyDays[i]) {
        if (day > THIRTY_DAYS)
          throw new IllegalArgumentException("Exception: Wrong day");
        else {
          correct = true;
          this.month = month;
          this.day = day;
          this.year = year;
          break;
        }
      }
    }
    if (!correct) {
      for (int i = 0; i < thirtyOneDays.length; i++) {
        if (month == thirtyOneDays[i]) {
          if (day > THIRTY_ONE_DAYS) 
            throw new IllegalArgumentException("Exception: Wrong day");
          else {
            correct = true;
            this.month = month;
            this.day = day;
            this.year = year;
            break;
          }
        }
      }
    }
    if (!correct) {
      correct = true;
      this.month = month;
      this.day = day;
      this.year = year;
    }
  }
  
  public int getYear() {
    return this.year;
  }
  
  public int getMonth() {
    return this.month;
  }
  
  public int getDay() {
    return this.day;
  }
  
  public int compareTo(Date obj) {
    int totalDays = this.calculateTotalDay();
    int totalDaysOther = obj.calculateTotalDay();
    if (this.year < obj.year - 1 || this.year > obj.year + 1) 
      return (this.year - obj.year) * 365;
    else if (this.year == obj.year - 1 || this.year == obj.year + 1) {
      if (obj.month == 1 && this.month == 12)
        return -30;
      else if (obj.month == 12 && this.month == 1)
        return 30;
      else if (Math.max(obj.month, this.month) == obj.month)
        return ((12 - obj.month + this.month)) * 30; 
      else 
        return (this.month - obj.month - 12) * 30; 
    } else {
      if (this.month < obj.month - 1 || this.month > obj.month + 1)
        return (this.month - obj.month) * 30;
      else if (this.month == obj.month - 1 || this.month == obj.month + 1) {
          if (obj.day == 1 && this.day == totalDays && 
              Math.max(obj.month, this.month) == obj.month)
            return -1;
          else if (obj.day == totalDaysOther && this.day == 1 &&
              Math.max(obj.month, this.month) == this.month)
            return 1;
          else if (Math.max(obj.day, this.day) == obj.day && 
              Math.max(obj.month, this.month) == this.month)
            return (totalDaysOther - obj.day) + this.day; 
          else if (Math.max(obj.day, this.day) == obj.day && 
              Math.max(obj.month, this.month) == obj.month)
            return (this.day - totalDays) - obj.day;
          else if (Math.max(obj.day, this.day) == this.day && 
              Math.max(obj.month, this.month) == this.month)
            return (totalDaysOther - obj.day) + this.day;
          else 
            return (this.day - totalDays) - obj.day;
      } else
        return this.day - obj.day;
    }
  }

  public int calculateTotalDay() {
    for (int i = 0; i < thirtyOneDays.length; i++) {
      if (this.month == thirtyOneDays[i])
        return 31;
    }
    for (int i = 0; i < thirtyDays.length; i++) {
      if (this.month == thirtyDays[i])
        return 30;
    }
    if (this.yearIsLeap())
      return 29;
    return 28;
  }

  public boolean yearIsLeap() {
    boolean leapYear = false;
    if (this.year % 4 == 0)
      leapYear = true;
    if (this.year % 100 == 0) {
      if (this.year % 400 == 0)
        leapYear = true;
      else
        leapYear = false;
    }
    return leapYear;
  }
  
  public boolean isWednesday() {
    Calendar date = Calendar.getInstance();
    date.set(this.year, this.month - 1, this.day);
    if (date.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY)
      return true;
    return false;
  }
  
  public boolean isSunday() {
    Calendar date = Calendar.getInstance();
    date.set(this.year, this.month - 1, this.day);
    if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
      return true;
    return false;
  }
}
