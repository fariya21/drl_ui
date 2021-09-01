Feature: DRL Functionality Test

@Smoketest1
Scenario: Verify title of Fileroom login page.

Given Launch Fileroom url
When  Enters login credential
And   Submit login button
Then  User  able to login successfully in Fileroom

@Smoketest2
Scenario: Verify title of TaxCaddy Page.

Given User mouseover on dropdown list option
When Clicking on TaxCaddy option
Then Allow user to navigate on TaxCaddy page

@Smoketest3
Scenario: Allow user to Add Client in TaxCaddy.

Given User clicking on Administrative button from TaxCaddy page
And Verify allow user to move on Administrative page
When Clicking on Add Client button
Then Allow user to open Add client Pop-up Window

@Smoketest4
Scenario: Add Client 

Given  Verify Add Client PopUP 
When   Click Offline Button
And    Enter Client Detail
Then   Client is Added

@Smoketest5
Scenario: Verify Status of DRL Request ID. 

Given Enter Client ID in searchbox
And Select record for DRL request
When Click Create Custom DRL
Then Verify Status changed to Completed or In Error

