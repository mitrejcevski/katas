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