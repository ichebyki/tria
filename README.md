# tria

The project is a sample project for triangle task

# Description
The program determines the type of a triangle.
It should take the lengths of the triangle's three sides as input,
and return whether the triangle is equilateral, isosceles or scalene.

# Platform used to develop
The project is developed under Intellij IDEA 2017.1 community edition
The project is a Gradle specific project
Used Java platform is JDK 1.8.0_102

# How to build and run (Windows)
1) clone Github repository git@github.com:ichebyki/tria.git to some dir
     % git clone git@github.com:ichebyki/tria.git tria
2) change current dir to root of the cloned repo
3) build the project
     % gradlew build
4) run the built application
     % java -cp build\libs\tria-1.0-SNAPSHOT.jar com.tri.app.TriMain
5) run all tests
     % gradlew :cleanTest :test --tests "com.tri.app.*"
6) see testing results in
     build\reports\tests\test\index.html

# Implementation notes
The application prompts user to enter three triangle sides from console.
Then, it tries to create Triangle object and print the kind of triangle.

As mentioned, the format of the sides values must be Java double format, but
with three restriction:
  - only radix 10 number format is allowed now (no hex, binary and etc.),
    but can be easy extended in the future by editing TriFactory class
  - Nan and Infinity are prohibited
  - only positive values are allowed
  - the values must be greater than zero

The other geometry restriction is:
  if a, b, c are triangle sides values, the following must be true:
     (a + b) > c && (b + c) > a && (a + c) > b

The Triangle object may be created directly but we use TriFactory class object.
The TriFactory is intended for:
  1) divide user input from Triangle class
  2) possible fine tune restrictions adding in the future (for example creating special triangle only)
  3) setup Locale specific input (for example Russian decimal symbol is ',')
  4) try/catch exception while creating Triangle object with bad sides values,
     so factory returns null/non-null object for new Triangle object
  5) load properties file TriError.properties for errors diag


# Testsuite
The are 3 testsuite with 20 Junit tests
