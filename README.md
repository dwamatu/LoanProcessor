# Loan Processor

## Support and Implementation Document*
----
   
 * Introduction
 * Assumptions
 * Configuration
 * Why Java
 * Requirements
 * Configuration
 * Logs
 * Libraries
 



####  Introduction
---
The Loan Processor Applications is a program designed to iterate through a CSV associating Loans via a tuple of [Network, Product, Month]

#### Assumptions
	1. The  Input file will always be the same size and values will follow the same arrangement in the same format as in the CSV shared.
	2. The Data in the CSV will always be for one year, Other wise there will be need for improving the Solution to factor in years.
	
#### Why Java 
- Java allows a user to implement algorithms in the simplest possible form and at the same time allowing one to implement any 
task efficiently.
- Java is one of the most mature language and has a large following.
- Java offers higher cross - functionality and portability as programs written in one platform can run across desktops, mobiles, embedded systems.


####  Requirements for Running this Solution
---

	* Java Version  > = 1.8  
	

#### Configuration
----
```
LoanProcessor/conf/Jumoconfigs.xml

```
----
Step 
- Modify the path of the logs on configurations above to the path on your machine of your choice
- Modify the path of the Loans CSV input path from the configurations file above. 
- Modify the Path of the output Directory of your choice.
- Run the following command Inside the Project Folder.

```
java -jar dist/LoanProcessor.jar

```

#### Logs
```
INFO:  LoanProcessor/logs/info.log
ERROR: LoanProcessor/logs/error.log

```

#### Libraries Used

```
opencsv-3.3.jar
log4j-1.2.16.jar
```

#### IDE
```
IntelliJ
```
