package com.example.utacredittracker.calculators;

import com.example.utacredittracker.degreehandling.Degree_Class;

import java.util.ArrayList;

public class Manual_Calculator {

    public Manual_Calculator() {
        //no-parameter empty constructor
    }

    public void addClass(Degree_Class course) {
        this.classesTaken.add(course);
    }

    public void removeClass(Degree_Class course) {
        this.classesTaken.remove(course);
    }

    public double calculate() {
        double pointSum = 0.0;
        double hourSum = 0.0;

        for(Degree_Class course : this.classesTaken) {
            char currGrade = course.getLetterGrade();
            double currHours = course.getCreditHours();
            double gradePoints = 0.0;

            if(currGrade == 'A') {
                gradePoints = 4.0 * currHours;
                pointSum = pointSum + gradePoints;
                hourSum = hourSum + currHours;
            }
            else if(currGrade == 'B') {
                gradePoints = 3.0 * currHours;
                pointSum = pointSum + gradePoints;
                hourSum = hourSum + currHours;
            }
            else if(currGrade == 'C') {
                gradePoints = 2.0 * currHours;
                pointSum = pointSum + gradePoints;
                hourSum = hourSum + currHours;
            }
            else if(currGrade == 'D') {
                gradePoints = currHours; //1.0 * currHours
                pointSum = pointSum + gradePoints;
                hourSum = hourSum + currHours;
            }
            else {
                hourSum = hourSum + currHours;
            }
        }
        return pointSum / hourSum;
    }

    public ArrayList<Degree_Class> getClassesTaken()
    {
        return this.classesTaken;
        /* old code from Swing implementation
        int len = this.classesTaken.size();
        Object objClassesTaken[] = new Object[len];
        for(int i = 0; i < len; i++)
        {
            objClassesTaken[i] = this.classesTaken.get(i);
        }
        return objClassesTaken;
        */
    }

    private ArrayList<Degree_Class> classesTaken = new ArrayList<Degree_Class>();
}
