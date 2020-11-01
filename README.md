# xShared
Shared module with common utilities to develop webapps.

## Description
This library contains all the shared requirements to develop microservices.


## Getting Started
### Prerequisites
* Java JDK11 
    * [OpenJdk](https://download.java.net/openjdk/jdk11/ri/openjdk-11+28_windows-x64_bin.zip)
    * [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
    
### Installing
This example uses the embedded **MVN** on this repo using **Windows**. Be sure to set the correct **JAVA_HOME** environment variable.

* Clone this repository.
```sh
git clone https://github.dxc.com/gala-lota4/828-fc-shared.git
```

* Compiling the library.
```sh
mvnw.cmd clean install
```
### Testing
* Run all test
```sh
mvnw.cmd test
```


## Contributing
The methodology to contribute on this repository is based on [GitFlow](https://www.atlassian.com/es/git/tutorials/comparing-workflows/gitflow-workflow).

Developers can create 3 types of branches:

* **Feature**:
    * Starts from **develop**
    * Pull request **to develop**
    * Naming under **FT/x-y-z**
    * Example: **FT/create-readme**
* **Bugfix**:
    * Starts from **develop**
    * Pull request to **develop**
    * Naming under **BF/x-y-z**
    * Example: **BF/fix-bus-error-handling**
* **Hotfix**:
    * Starts from **master**
    * Pull request to **master** and **develop**
    * Naming under **HF/x-y-z**
    * Example: **HF/critical-bus-casting**


## Troubleshoot
If you have any problem feel free to open an issue on this repository.


## Authors
Xustyx
[@Xustyx](https://github.com/Xustyx)


## Versioning
This repo uses [SemVer](https://semver.org/) for versioning.
