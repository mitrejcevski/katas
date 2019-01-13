# Kata's

## Description
A project containing Kata`s for practicing TDD using Kotlin. Each kata has its own package, and each exercise goals are described below.

#### 1. String Calculator
##### package name: `string`
1. An empty string returns zero
2. A single number returns the value
3. Two numbers, comma delimited, returns the sum
4. Two numbers, new line delimited, returns the sum
5. Three numbers, delimited either way, returns the sum
6. Negative numbers throw an exception
7. Numbers greater then 1000 are ignored

#### 2. Password Verifier
##### package name: `password`
1. Password should be larger than 8 chars
2. Password should have one uppercase letter at least
3. Password should have one lowercase letter at least
4. Password should have one number at least
5. Each one of these should throw an exception with a different message of your choosing
6. Password is OK if the previous conditions are satisfied

#### 3. Greeter
##### package name `greet`
1. Should return greeting for given name:  
 input ```"name"```  
 output ```Hello, name.```  
2. Should return generic greeting when no or empty name provided:   
 input ```"" or null```  
 output ```Hello, my fiend```  
3. Should return shouting greeting when shouting name provided:  
 input ```"NAME"```  
 output ```HELLO NAME!```  
4. Should return greeting for both when two names provided:  
 input ```"name1", "name2"```  
 output ```Hello, name1 and name2.```  
5. Should return greeting for all when more than two names provided:  
 input ```"name1", "name2", "name3"```  
 output ```Hello, name1, name2 and name3.```  
6. Should return greeting when mixing shouting names with normal names:  
 input ```"name1", "NAME2", "name3"```  
 output ```Hello, name1 and name3. AND HELLO NAME2!```
7. Should return greeting when coma separated names provided as input:  
 input ```"name1", "name2, name3"```  
 output ```Hello, name1, name2, and name3.```
 
#### 4. FizzBuzz
##### package name `fizzbuzz`
1. Should print numbers from 1 to 100
2. For the multiples of 3 print `Fizz` instead of the number
3. For the multiples of 5 print `Buzz` instead of the number
4. For the multiples of both 3 and 5 print `FizzBuzz` instead of the number
5. A number is `Fizz` if it is dividable by 3 or if it contains 3 inside
6. A number is `Buzz` if it is dividable by 5 or if it contains 5 inside

#### 5. Roman to decimal numbers
##### package name `roman`
Converter to resolve input from Roman numerals into decimal. The values of the roman numerals are shown in the following table.

| Symbol | Value |
|:------:|:-----:|
| I      | 1     |
| V      | 5     |
| X      | 10    |
| L      | 50    |
| C      | 100   |
| D      | 500   |
| M      | 100   |  

Numbers are formed by combining symbols together and adding the values. Generally, symbols are placed in order of value, starting with the largest values. When smaller values precede larger values, the smaller values are subtracted from the larger values, and the result is added to the total:

| Roman Number | Computation                               | Value | Comment                  |
|:------------:|:-----------------------------------------:|:-----:|:-------:                 |
|IV            | 5 - 1                                     | 4     | only subtraction         |
|VI            | 5 + 1                                     | 6     | only addition            |
|MMVI          | 1000 + 1000 + 5 + 1                       | 2006  | only addition            |
|MCMXLIV       | 1000 + (1000 - 100) + (50 - 10) + (5 - 1) | 1944  | addition and subtraction |

#### 6. Banking Kata
##### package name `banking`
Simple bank application with the following features

    - Deposit into Account
    - Withdraw from an Account
    - Print the Account statement to the console

Statement should have transactions in the following format:

    DATE       | AMOUNT  | BALANCE
    10/04/2014 | 500.00  | 1400.00
    02/04/2014 | -100.00 | 900.00
    01/04/2014 | 1000.00 | 1000.00

Constraints:
 1. Start with a class with the following structure

    ```
    public class Account {

      public void deposit(int amount)

      public void withdraw(int amount)

      public void printStatement()
    }
    ```
 2. You are not allowed to add any other public methods in this class
 3. Use Strings and Integers for dates and amounts (keep it simple)
 4. Don't worry about the spacing in the statement printed in the console
