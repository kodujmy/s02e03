# Javafaktura s02e03 - Spring MVC - REST in peace

![.images/javafaktura.png](.images/javafaktura.png)

# How to read this repository?

## 01 Let's choose baby name

```
Once Java Programmer was expecting to have baby, actually 2 babies.
He wrote a program to choose names for his newborns.
Unexpectedly triplets were born and the got the names:
Adam, Frank and... ArrayIndexOutOfBoundsException
```

This is simple program solving similar problem.
We are going to draw name for a newborn. Data source is based on real data [https://dane.gov.pl/dataset/219,imiona-nadawane-dzieciom-w-polsce](https://dane.gov.pl/dataset/219,imiona-nadawane-dzieciom-w-polsce)
![.images/chooser.png](.images/chooser.png)
We also present full statistics of most popular names given during first half of 2019.
![.images/all_names.png](.images/all_names.png)

### Features:

#### Spring MVC
1. Draw a random name :white_check_mark:
2. Present all names split by gender :white_check_mark:
3. Draw a random name, taking parents preferences to gender and popularity :white_check_mark:
4. Allow to choose name from existing data - should increase occurrence counter :white_check_mark:
5. Allow to choose new name - should add new name with counter=1 :white_check_mark:
6. Show details of certain name using path variable :white_check_mark:

