# MasterAppiumFramework
With further updates - This project is designed and developed with the help of -
1. Udemy course (OmPrakash Chavan - Appium Mobile Automation - Android &amp; iOS + Frameworks + CICD)
2. YouTube playlist (Testing Mini Bytes)
------------------------------------------------------------
![image](https://user-images.githubusercontent.com/26399692/138761285-c49b6879-47cf-4a52-a877-ae20cdf4ef94.png)
![image](https://user-images.githubusercontent.com/26399692/138761338-472a1491-556d-4342-8c15-341a58faba70.png)
![image](https://user-images.githubusercontent.com/26399692/139634693-24396f05-f9b6-45b9-a9ac-ee2f82fef986.png)
------------------------------------------------------------
Updations and Implementations done:
------------------------------------------------------------
1. Parallel testing (Multiple devices - Android and iOS) (Real devices and Emulator/Simulator)
2. Application used: https://github.com/saucelabs/sample-app-mobile/releases (V2.7.1)
3. ExtentReports V5.0.9
4. User has options for customization
5. Email to User(s) using Java mail API
6. Retry failed test cases
7. Custom Enums, Exceptions, Annotations 
8. Zip the ExtentReports directory into Project path (you can send this Zip file as well as an Attachment in Email)
9. Automatically open the report after tests execution.
10. Start and Stop Appium Server programmatically
11. Videos for Failed test cases
12. Pass Test Data from JSON file
13. Pass Expected Data from XML file
14. Send EMail using Java mail API to User(s) with attachment(s).  
15. Jenkins job setup
16. Send EMail using Jenkins to User(s) with attachment(s).  
------------------------------------------------------------
**ExtentReports V5.0.9** 
1. User can apply the Filters - 
- DeviceType - Pixel 3, Pixel 3A, Pixel 4
- Author - Rajat, Nishant, Gautam, Pankaj 
- TestType - Smoke, Sanity, Regression, BVT

2. Screenshots are attached in the ExtentReport as Base64 format.
![image](https://user-images.githubusercontent.com/26399692/139594083-4e48aa67-1fc3-4fbb-a719-e20c9a5e5427.png)
![image](https://user-images.githubusercontent.com/26399692/139594098-06f176e1-a318-4af2-bfbc-6e5970002d02.png)

------------------------------------------------------------
**User has options for customization**
![image](https://user-images.githubusercontent.com/26399692/138759427-8dd20f34-8400-4e2b-9c8a-70e9d28bf231.png)
------------------------------------------------------------
**Email to User(s) using Java mail API**
![image](https://user-images.githubusercontent.com/26399692/139594288-8de6e123-0154-46df-a9e9-26fc1c8fa161.png)
------------------------------------------------------------
**Send EMail using Java mail API to User(s) with attachment(s)**
 - https://mvnrepository.com/artifact/javax.mail/mail/1.4.7
 - https://www.tutorialspoint.com/java/java_sending_email.htm
 - Gmail -> Manage your Google account: 
        ![image](https://user-images.githubusercontent.com/26399692/137579937-12c01d4d-1f62-4867-8c40-c056391d3b7e.png)
 - Security -> Turn on : Less Secure App access:
        ![image](https://user-images.githubusercontent.com/26399692/137579959-e1554f06-5583-4ad1-ad28-ed69ed27b922.png)
------------------------------------------------------------
**How to run the Project from Local machine**
1. Pull the code into your machine and import in IDE (Eclipse/intelliJ).
2. Go to testng.xml -> Run this file as TestNG suite (you should have done the setup for Appium)
  It should start the execution -> Parallel Testing.
------------------------------------------------------------
**NOTE: **
1. Make sure devices (Either Emulator or Real-Device) are ready 
2. This is for Appium Sever start and stop programatically -> com.appium.utils.AppiumServerUtils -> You need to give the path of Node installation
       
![image](https://user-images.githubusercontent.com/26399692/138760824-639b8609-e5db-48aa-8323-1241551e427c.png) 
![image](https://user-images.githubusercontent.com/26399692/138760107-8fb49a24-7b81-4236-82a8-9eabbed8203b.png)
------------------------------------------------------------
**How to run the Project from Jenkins**
[__Final-OC_Appium-Github.pdf](https://github.com/rajatt95/MasterAppiumFramework_TDD/files/7450866/__Final-OC_Appium-Github.pdf)
![image](https://user-images.githubusercontent.com/26399692/139624776-cded4554-c2af-4e52-aed9-b1ac715572a5.png)
------------------------------------------------------------
Email to User(s) using Jenkins:
![image](https://user-images.githubusercontent.com/26399692/139624661-d2652a4c-798b-40c7-804f-cbb18892dd4c.png)

------------------------------------------------------------

