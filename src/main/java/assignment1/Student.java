// Programmer: Matt Jones S0201735
// File: Student.java
// Date: 19 Aug 2023
// Purpose: COIT11134 Assignment 1

package assignment1;

public class Student extends Member
{
    // Constructor
    public Student(int memberId, String memberName, String uniName, String memberEmail, int memberPhone, float registerFee)
    {
        super(memberId, memberName, uniName, memberEmail, memberPhone, registerFee);
    }
    
    // Register Fee Getter for Student class
    @Override
    public float getRegisterFee()
    {
        float baseRegisterFee = super.getRegisterFee();
        return baseRegisterFee - (baseRegisterFee * (Values.STUDENT_DISCOUNT / 100));
    }

    // toString method for Student class
    @Override
    public String toString()
    {
        return "Student{" + '}';
    }
}
