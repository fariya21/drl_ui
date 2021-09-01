Feature: DRL Functionality Test

@Smoketest1
Scenario: verify title of Fileroom login page.

Given Launch Fileroom url
When  Enters login credential
And   Submit login button
Then  User  able to login successfully in Fileroom

@Smoketest2
Scenario: verify taxcaddy page
#
Given verify taxcaddydropdown
When Hover the dropdown
And  Select Taxcaddy option
Then User  able to navigate to the TC page 

@Smoketest2
Scenario: Add Client 

Given  Verify Add Client PopUP 
When   Click Offline Button
And    Enter Client Detail
Then   Client is Added
