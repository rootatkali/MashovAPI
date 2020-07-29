# MashovAPI [![Build Status](https://travis-ci.org/rootatkali/MashovAPI.svg?branch=master)](https://travis-ci.org/rootatkali/MashovAPI)
An open-source API for the Israeli Mashov school management system, written in Java.
This API is WIP.

## Installation
Installations for gradle:
```gradle
// ...
repositories {
  // ...
  jcenter() // Use the main jcenter repository.
}

// ...

dependencies {
  implementation 'de.faceco:MashovAPI:1.0.9-android' // For the latest version, also works in JDK environments
}
```

For more platforms, see [the Bintray repo](https://bintray.com/rootatkali/facecode/MashovAPI/).

## Usage
This is how to start using the API:
```java
API api = API.getInstance();
```
The first step is to fetch a single school, or all of the schools:

```java
// Single school
int schoolId = 580019;
api.fetchSchool(schoolId);

// All the schools
School[] all = api.getAllSchools();
```

After selecting a school, you need to log in:

```java
LoginInfo loginInfo = api.login(2020, "username", "password");
```

After logging in, you can either read the login info or request more data, such as the grades of the student:
```java
Grade[] grades = api.getGrades();
int gradeSum = 0;
for (Grade g : grades) {
  gradeSum += g.getGrade();
}
System.out.println(gradeSum / grades.length);
```
