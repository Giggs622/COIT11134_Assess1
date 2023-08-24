// Programmer: Matt Jones S0201735
// File: Menu.java
// Date: 19 Aug 2023
// Purpose: COIT11134 Assignment 1 Menu main class

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

        //Parse the String to an integer
        return Integer.parseInt(choice);
    }

    // Method to take the menu item selected and call the method related to it or print an error if choice not recognised
    private void processOrders()
    {
        // Declare variable to hold menu choice
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
                    System.out.print("\nERROR choice not recognised\n");
            }

            // If error is made, get menu item selection again
            choice = getMenuItem();
        }
    }

    private void enterMemberRecord()
    {
        // Declare local variables to hold user input
        // Student and Speaker inputs
        float studentDiscount = 0.0F;
        String speechTopic = "";
        // String to hold user input
        String input;
        // Booleans to hold type checks
        boolean isStudent = false;
        boolean isSpeaker = false;
        // Scanner to take user input
        Scanner inText = new Scanner(System.in);
        
        System.out.println();

        // Ask user for Member ID and assign to variable
        int memberId = inputInteger("member ID");
        
        // Call findMemberRecord() method to check if the member ID is exist in the current list    
        int index = findMemberRecord(memberId);
        
        // If the member ID is already exist, display "Member with this ID alreasy exist"
        if (index >= 0)
        {
            System.out.println("\nMember with this ID already exists");
            // Return to the main menu
            return;
        }

        // Ask user for Full Name and assign to variable
        String memberName = inputText("full name");
        
        // Ask user for University and assign to variable
        String uniName = inputText("University");
        
        // Ask user for Email and assign to variable
        String memberEmail = inputText("email");
        
        // Ask user for Phone Number and assign to variable
        String memberPhone = inputText("phone number");
        
        // Ask user for Registration Fee and assign to variable
        float registerFee = inputFloat("registration fee");
        
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
            while (studentDiscount < Values.STUDENT_DISCOUNT_MIN || studentDiscount > Values.STUDENT_DISCOUNT_MAX);
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
            System.out.println("\nDetails for member entered: ");
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
            System.out.println("\nDetails for member entered: ");
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
            System.out.println("\nDetails for member entered: ");
            displayHeading();
            System.out.println(member);
        }
    }

    // Method to search for a specific member
    private void searchMember()
    {
        System.out.println();

        // Ask user for member ID and assign to variable
        int memberID = inputInteger("member ID");
        // Check if member ID has already been stored
        int index = findMemberRecord(memberID);
        // If member ID found, print member information
        if (index > -1)
        {
            Member m = listMember.get(index);
            // Display "Member found" message
            System.out.println("\nMember found");
            // Display related member information
            displayHeading();
            System.out.println(m);
        }
        else
        // Display "No record found" message
        {
            System.out.println("\nNo record found");
        }
    }
    
    // Method to view all of the information on members currently stored
    private void viewAllMembers()
    {
        // If list is empty display error, otherwise print out all members in the list
        if (!listMember.isEmpty())
        {
            displayHeading();
            for (Member m : listMember)
            {
                System.out.println(m);
            }
        }
        else
        {
            System.out.println("\nNo members found");
        } 
    }
    
    
    private void totalRegFee()
    {
        // Variable to hold total
        float sumTotal = 0.0F;
        
        // Loop to get the registration fee of each member and add to total
        for (Member m : listMember)
        {
            sumTotal += m.getRegisterFee();
        }
        
        // Print the total registration fee
        System.out.printf("\nThe total registration fee for all members is: $%.2f\n", sumTotal);
    }

    private int findMemberRecord(int memberID)
    {
        // Declare variables
        int index = -1;                 // Store array index number if found or pass number < 0
        int arrayCounter = 0;           // Count to which array index is found in for loop
        
        // Check every element in ListArray for matching member ID
        for (Member e : listMember)
        {
            if (memberID == e.getMemberId())
            {
                index = arrayCounter;
            }
            arrayCounter++;
        }
        
        // Return the index number
        return index;
    }

    // Check if a string is numeric
    private boolean isStringNumeric(String str)
    {
        // Check that every character in the string is a number
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
        // Check that every character in the string is either a number or a decimal point
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
        // Check that string is either an s, k or n, not case sensitive
        return input.equalsIgnoreCase("S") || input.equalsIgnoreCase("K") || input.equalsIgnoreCase("N");
    }

    // Method to ask user for numerical input and check that it is a number and isn't blank
    // Method input is a String that is the detail to ask the user for
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
    // Method input is a String that is the detail to ask the user for
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
    // Method input is a String that is the detail to ask the user for
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
    
    // Method to display the heading used when displaying member details
    private void displayHeading()
    {
        System.out.printf("\n%-12s%-20s%-20s%-20s%-14s%-14s%-20s\n", "Member ID", "Full Name", "University", "Email", "Phone Number", "Register Fee", "Speech Topic");
    }
    
    // Main method to run the menu
    public static void main(String[] args)
    {
        // Declare a new object from Menu class
        Menu myApp = new Menu();

        // Print welcome message
        System.out.printf("\nWelcome to the Conference Registration System\n\n");
        
        // Call the menu method
        myApp.processOrders();

        // Print an exit message on exit
        System.out.printf("%n");
        System.out.println("Thank you for using the Conference Registration System");
        System.out.println("Program written by S0201735");
    }
}
