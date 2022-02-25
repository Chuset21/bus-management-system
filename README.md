# Bus Management System

A bus management system built for a college assignment.

## Requirements
- Java 17

## Running
Make sure to download the [jar file](jar/bus-management-system-1.0-SNAPSHOT.jar) in the jar directory.  
Open your preferred terminal.
cd into the directory where this jar is stored (it doesn't have to be stored anywhere specific) `cd some-directory`  
In the command line run: `java -jar bus-management-system-1.0-SNAPSHOT.jar`  
If you know that your terminal supports ANSI coloring and the program isn't detecting it, try running it like so:  
`java -jar bus-management-system-1.0-SNAPSHOT.jar force-ansi`

## Using
Pass arguments separated by spaces/tabs.  
If you would like to pass an argument containing white space please make sure to surround the argument with quotation  
marks, like so __"\<argument>"__.

## Colors
I suggest using a console with a black background (since the colors are bright) that supports __ANSI__ coloring,  
if you don't know if your console supports it, don't worry, the program should take care of that, if it doesn't try  
running it with `java -jar bus-management-system-1.0-SNAPSHOT.jar force-ansi`.   
Known consoles to not support ANSI:
 - Command prompt  
 - Windows Powershell  

Known consoles to support ANSI:
- Most Unix consoles  
- IntelliJ console  
- [Windows Terminal](https://aka.ms/terminal)  

This is what it looks like on my IntelliJ console which supports ANSI coloring, the best coloring I've seen on Windows.  

![Something Went Wrong](images/showcase_colors.png)