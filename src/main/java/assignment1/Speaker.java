// Programmer: Matt Jones S0201735
// File: Speaker.java
// Date: 19 Aug 2023
// Purpose: COIT11134 Assignment 1

package assignment1;

public class Speaker extends Member
{
    // Instance variables
    String speechTopic;         // Title to presented at the conference
    
    // Constructor
    public Speaker(int memberId, String memberName, String uniName, String memberEmail, int memberPhone, float registerFee, String speechTopic)
    {
        super(memberId, memberName, uniName, memberEmail, memberPhone, registerFee);
        this.speechTopic = speechTopic;
    }
    
    // Speech Topic Getter and Setter
    public String getSpeechTopic()
    {
        return speechTopic;
    }

    public void setSpeechTopic(String speechTopic)
    {
        this.speechTopic = speechTopic;
    }
    
    // toString method for Speaker class
    @Override
    public String toString()
    {
        return "Speaker{" + "speechTopic=" + speechTopic + '}';
    }
}
