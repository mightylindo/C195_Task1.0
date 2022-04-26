C195-Task1: Appointment Scheduling Application
Author: Jordan Lindorff
email: jlindo6@wgu.edu phone: 801-231-4259
Student Application Version 1.0
Submission Date: 4/20/22

IDE:IntelliJ IDEA 2021.3 (Community Edition)
    Build #IC-213.5744.223, built on November 26, 2021
    Runtime version: 11.0.13+7-b1751.19 amd64
    VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
    Kotlin: 213-1.5.10-release-949-IJ5744.223
    JDK:11.0.11
    JavaFX: javafx-base:11.0.2

Directions:
Step1: Run the program.
Step2: Login with (username: test, password: test) or with (username:admin, password: admin).
Step3: After login prompt will generate if there is an appointment for the loggin in user within 15 minutes. Select ok.
Step4: Choose your desired section: Customers, Appointments, or Reports by clicking on their button.
    Customer Screen Add instructions:
    Step 1: Enter Customer Name, Address, Postal Code, Phone number, and select the country from the first combobox, then select the first-level divsion from the second combobox.
    Step 2: Click the add button.
    Customer Screen Modify instructions:
    Step 1: Select a customer from the customersTable.
    Step 2: Modify any of the fields or comboboxes.
    Step 3: Click modify to commit the changes.
    Customer Screen Delete instructions:
    Step 1: Select the customer you wish to delete from the customersTable.
    Step 2: Click the delete button, and the program will check if that customer has any appointments.
    Step 3: If appointments exist for that customer a prompt will display asking if you want to delete all appointments for that customer.
        click OK and the program will then delete the appointments and once they are deleted it will delete the customer as well.
    Customer Screen return instructions:
    Step 1: If you wish to return to the main screen, click on the return button.

    Appointment Screen Add instructions:
    Step 1: Select Customer from the customer combobox, enter the appointment description, title, location,, select the contact from the contact combobox, enter the type, start and end, and select
        the user from the user combobox.
        Step 1a: The start and end dates need to be entered in YYYY-MM-DDTHH:MM format ex(2022-04-23T08:00)
    Step 2: Click the add button.
    Appointment Screen Modify Instructions:
    Step 1: Select an appointment from the appointmentsTable.
    Step 2: Modify the desired information in the comboboxes and textfields.
    Step 3: Click the modify button to commit the changes.
    Appointment Screen Delete instructions:
    Step 1: Select the appointment you wish to delete.
    Step 2: Click the delete button and you will be prompted asking if you really want to delete the appointment.
    Step 3: Click OK on the prompt and the appointment will be deleted.
    Appointment Screen Return instructions:
    Step 1: If you wish to return to the main screen, click on the return button.

    Reports Screen Instructions:
    Step 1: Click on the button for the desired report.
        Report 1 instructions:
        Step 1: Click on return button to return to the reports screen.
        Report 2 instructions:
        Step 1: Click on the desired contact and the tableview will update to show the contacts schedule
        Step 2: Click on the return button to return to the reports screen.
        Report 3 instructions:
        Step 1: Click on the country you wish to select, and the tableview will update to show all customers from that country.
        Step 2: Click on the return button to return to the reports screen.
    Step 2: Click on the return button to return to the main screen.
Step5: When finished with the program click the red X in the upper right corner to close the program.

About Report 3:
Report 3 is a report of all customers in a given country. Select the radio button for the country you'd like to view and the
tableview will update accordingly and display the customer and their address information.

MySQL Driver:
mysql-connector-java-8.0.25
