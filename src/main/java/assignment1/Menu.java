// Programmer: Matt Jones S0201735
// File: Menu.java
// Date: 19 Aug 2023
// Purpose: COIT11134 Assignment 1
package assignment1;

import java.util.Scanner;
import java.util.ArrayList;

public class Menu
{

    // Declare an ArrayList object to store member list objects.
    ArrayList<Member> listMember = new ArrayList<>();

    // This method gets a MenuItem
    private int getMenuItem()
    {
        Scanner inputMenuChoice = new Scanner(System.in);

        System.out.printf("\nPlease select from the following\n");
        System.out.println(Values.ENTER_MEMBER + ". Enter member details");
        System.out.println(Values.SEARCH_A_MEMBER + ". Search a member");
        System.out.println(Values.VIEW_ALL_MEMBERS + ". View all members");
        System.out.println(Values.TOTAL_REG_FEE + ". View total registration fee");
        System.out.println(Values.EXIT + ". Exit the application");
        System.out.print("\nEnter choice ==> ");

        String choice = inputMenuChoice.nextLine();

        while (choice.equals("") || !isStringNumeric(choice))
        {
            System.out.printf("Error - Menu selection cannot be blank and must be numeric\n\n");
            System.out.print("Enter choice ==> ");
            choice = inputMenuChoice.nextLine();
        }
        System.out.println();

        //Parse the String to an integer
        return Integer.parseInt(choice);
    }

    // Method to take the menu item selected and call the method related to it or print an error if choice not recognised
    private void processOrders()
    {
        int choice = getMenuItem();

        // While the menu item is not equal to the exit value, call the method selected
        while (choice != Values.EXIT)
        {
            switch (choice)
            {
                case Values.ENTER_MEMBER:
                    enterMemberRecord();
                    break;
                case Values.SEARCH_A_MEMBER:
                    searchMember();
                    break;
                case Values.VIEW_ALL_MEMBERS:
                    viewAllMembers();
                    break;
                case Values.TOTAL_REG_FEE:
                    totalRegFee();
                    break;
                default:
                    System.out.print("ERROR choice not recognised\n\n");
            }

            // If error is made, get menu item selection again
            choice = getMenuItem();
        }
    }

    private void enterMemberRecord()
    {
        // Declare local variables to hold user input
        // Member inputs
        int memberId;
        String memberName;
        String uniName;
        String memberEmail;
        String memberPhone;
        float registerFee;
        // Student and Speaker inputs
        float studentDiscount = 0.0F;
        String speechTopic = "";

        int index;
        String input;

        boolean isStudent = false;
        boolean isSpeaker = false;

        Scanner inText = new Scanner(System.in);

        // Ask user for Member ID and assign to variable
        memberId = inputInteger("Member ID");
        
        //TODO - call findMemberRecord() method to check if the member ID is exist in the current list       
        //TODO -- if the member ID is already exist, display "Member with this ID alreasy exsit"
        //TODO -- return to the main menu
        
        //TODO -- enter other details, full name, university, email, phone etc
        // Ask user for Full Name and assign to variable
        memberName = inputText("full name");
        
        // Ask user for University and assign to variable
        uniName = inputText("University");
        
        // Ask user for Email and assign to variable
        memberEmail = inputText("email");
        
        // Ask user for Phone Number and assign to variable
        memberPhone = inputText("phone number");
        
        // Ask user for Registration Fee and assign to variable
        registerFee = inputFloat("registration fee");
        
        // Ask user for the member type
        do
        {
            System.out.printf("Is this member a Student or a Keynote speaker? ('S' for student, 'K' for keynote speaker, 'N' for None of above): ");

            input = inText.nextLine();

            if (input.equals("") || !isValid(input))

            {
                System.out.printf("Error - please select S, K or N \n");
            }
        }
        while (input.equals("") || !isValid(input));

        // Check if member is a student
        if (input.equalsIgnoreCase("S"))
        {
            isStudent = true;
            // Ask user for Student Discount between 10% and 50% and assign to variable
            do
            {
                studentDiscount = inputFloat("student discount between 10% and 50%");
            }
            while (studentDiscount < 10.0F || studentDiscount > 50.0F);
        }
        
        // Check if member is a keynote speaker
        if (input.equalsIgnoreCase("K"))
        {
            isSpeaker = true;
            // Ask user for Speech Topic and assign to variable
            speechTopic = inputText("speech topic");
        }

        // Construct object depending on the member type and add the new member onto the list
        if (isStudent == true)
        {
            // Create a student member object
            Member student = new Student(memberId, memberName, uniName, memberEmail, memberPhone, registerFee, studentDiscount);
            // Add the object to the member list
            listMember.add(student);
            // Display the member information   
            System.out.println("Details for member entered: ");
            displayHeading();
            System.out.println(student);
        }
        else if (isSpeaker == true)
        {
            // Create a keynote speaker member object
            Member speaker = new Speaker(memberId, memberName, uniName, memberEmail, memberPhone, registerFee, speechTopic);
            // Add the object to the member list
            listMember.add(speaker);
            // Display the member information   
            System.out.println("Details for member entered: ");
            displayHeading();
            System.out.println(speaker);
        }
        else
        {
            // Create a member object
            Member member = new Member(memberId, memberName, uniName, memberEmail, memberPhone, registerFee);
            // Add the object to the member list
            listMember.add(member);
            // Display the member information   
            System.out.println("Details for member entered: ");
            displayHeading();
            System.out.println(member);
        }
    }

    private void totalRegFee()
    {

        //TODO -- get size from the member list
        //TODO -- using for loop to get each member's registration fee and sum them up
        //TODO -- note, ArrlyList has a get(i) method to get each element in the ArrlyList
        //TODO -- output the total registration fee   
    }

    private void viewAllMembers()
    {

        //TODO -- get size from the member list
        //If memeber size is 0, output "No member found!"
        //If member size is > 0
        //using a for loop to get each element from the member list
        //output the member details by calling its toString method.  
    }

    private void searchMember()
    {
        Scanner inText = new Scanner(System.in);
        String input;
        int memberID;

        do
        {
            System.out.printf("Pease enter a Member ID: ");

            input = inText.nextLine();

            if (input.equals("") || !isStringNumeric(input))
            {
                System.out.printf("Error - Member ID cannot be blank and must be numeric\n");
            }

        }
        while (input.equals("") || !isStringNumeric(input));

        memberID = Integer.parseInt(input);

        int index = findMemberRecord(memberID);

        if (index > -1)
        {
            //TODO -- member found, get the related memeber by the index
            //TODO  -- display "Member found" message
            //TODO -- display related member information

        }
        else
        //TODO - display "No record found" message
        {
            System.out.println();
        }

    }

    private int findMemberRecord(int memberID)
    {
        int index = -1;

        //TODO -- get the size from the member list
        //TODO -- using for loop to get each element of the list
        //TODO -- if the element's memberID  == memberID to be found
        //TODO -- update the index value to the curent element's index
        return index;
    }

    // Check if a string is numeric
    private boolean isStringNumeric(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            if (!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }

        return true;
    }

    // Check if a string is float
    private boolean isStringFloat(String str)
    {
        for (int i = 0; i < str.length(); i++)
        {
            if (!Character.isDigit(str.charAt(i)) && (str.charAt(i) != '.'))
            {
                return false;
            }
        }

        return true;
    }
    
    // Check if a member type String is valid
    private boolean isValid(String input)
    {
        return input.equalsIgnoreCase("S") || input.equalsIgnoreCase("K") || input.equalsIgnoreCase("N");
    }

    // Method to ask user for numerical input and check that it is a number and isn't blank
    private int inputInteger(String queryName)
    {
        // Declare variable to hold input
        String input;
        
        // Declare scanner object to take user input
        Scanner inText = new Scanner(System.in);
        
        // Take user input and check that input isn't blank and is numeric
        do
        {
            System.out.printf("Please enter %s (must be an integer): ", queryName);

            input = inText.nextLine();

            if (input.equals("") || !isStringNumeric(input))
            {
                System.out.printf("Error - %s must be a number and cannot be blank\n", queryName);
            }
        }
        while (input.equals("") || !isStringNumeric(input));

        return Integer.parseInt(input);
    }

    // Method to ask user for numerical input and check that it is a float and isn't blank
    private float inputFloat(String queryName)
    {
        // Declare variable to hold input
        String input;
        
        // Declare scanner object to take user input
        Scanner inText = new Scanner(System.in);
        
        // Take user input and check that input isn't blank and is a float
        do
        {
            System.out.printf("Please enter %s (must be a number): ", queryName);

            input = inText.nextLine();

            if (input.equals("") || !isStringFloat(input))
            {
                System.out.printf("Error - %s must be a number and cannot be blank\n", queryName);
            }
        }
        while (input.equals("") || !isStringFloat(input));

        return Float.parseFloat(input);
    }
    
    // Method to ask user for text input and check that text isn't blank
    private String inputText(String queryName)
    {
        // Declare variable to hold input
        String input;
        
        // Declare scanner object to take user input
        Scanner inText = new Scanner(System.in);
        
        // Take user input and check that input isn't blank and is numeric
        do
        {
            System.out.printf("Please enter %s: ", queryName);

            input = inText.nextLine();

            if (input.isEmpty() || input.startsWith(" "))
            {
                System.out.printf("Error - %s must cannot be blank\n", queryName);
            }
        }
        while (input.isEmpty() || input.startsWith(" "));

        return input;
    }
    
    private void displayHeading()
    {
        System.out.printf("\n%-12s%-20s%-20s%-20s%-14s%-14s%-20s\n", "Member ID", "Full Name", "University", "Email", "Phone Number", "Register Fee", "Speech Topic");
    }
    
    public static void main(String[] args)
    {
        // Delcare a new object from Menu class
        Menu myApp = new Menu();

        // Print welcome message
        System.out.printf("\nWelcome to the Conference Registration System\n\n");
        
        myApp.displayHeading();
        
        // Call the menu method
        myApp.processOrders();

        // Print an exit message on exit
        System.out.printf("%n");
        System.out.println("Thank you for using the Conference Registration System");
        System.out.println("Program written by S0201735");
    }

    // Print welcome message
    // Display menu options
    // Get menu selection from user
    // Determine registration type from user and collect registration details
    // Collect full name
    // Collect university name
    // Collect email
    // Collect phone number
    // Collect registration fee
    // Collect speech topic
    // String validation (No blanks
    // Positive float validation
}
