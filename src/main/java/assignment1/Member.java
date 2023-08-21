// Programmer: Matt Jones S0201735
// File: Member.java
// Date: 19 Aug 2023
// Purpose: COIT11134 Assignment 1

package assignment1;

public class Member 
{
    // Instance Variables
    private int memberId;           // Member ID
    private String memberName;      // Full name
    private String uniName;         // University
    private String memberEmail;     // Email
    private String memberPhone;        // Phone Number
    private float registerFee;      // Registration Fee
    
    // Constructor
    public Member(int memberId, String memberName, String uniName, String memberEmail, String memberPhone, float registerFee)
    {
        this.memberId = memberId;
        this.memberName = memberName;
        this.uniName = uniName;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.registerFee = registerFee;
    }
    

    // Member ID Getter and Setter
    public int getMemberId()
    {
        return memberId;
    }

    public void setMemberId(int memberId)
    {
        this.memberId = memberId;
    }

    // Member Name Getter and Setter
    public String getMemberName()
    {
        return memberName;
    }

    public void setMemberName(String memberName)
    {
        this.memberName = memberName;
    }

    // University Getter and Setter
    public String getUniName()
    {
        return uniName;
    }

    public void setUniName(String uniName)
    {
        this.uniName = uniName;
    }

    // Email Getter and Setter
    public String getMemberEmail()
    {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail)
    {
        this.memberEmail = memberEmail;
    }

    // Phone Number Getter and Setter
    public String getMemberPhone()
    {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone)
    {
        this.memberPhone = memberPhone;
    }

    // Registration Fee Getter and Setter
    public float getRegisterFee()
    {
        return registerFee;
    }

    public void setRegisterFee(float registerFee)
    {
        this.registerFee = registerFee;
    }
    
    // toString method for Member class
    @Override
    public String toString()
    {
        return String.format("%-12.12s%-20.20s%-20.20s%-20.20s%-14.14s$%-13.2fNA", this.memberId, this.memberName, this.uniName, this.memberEmail, this.memberPhone, this.registerFee);
    }
}
