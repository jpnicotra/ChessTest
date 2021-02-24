# ChessTest
#### _By JPN_
ChessTest requires last version of [Maven](https://maven.apache.org/) to run.

## Installation

Download code from [this repository](https://github.com/jpnicotra/ChessTest.git) and start command line in this project directory


Install WhiteHatGaming library as a maven dependency
```sh
mvn install:install-file -Dfile=docs\libs\userinput.jar -DgroupId=com.whitehatgaming -DartifactId=user-input -Dversion=1.0.0.0 -Dpackaging=jar -DgeneratePom=true
```
Install maven dependencies
```sh
mvn clean install
```

Generate Javadoc
```sh
mvn javadoc:javadoc
```

## Execution

```sh
mvn compile exec:java
```

## Features

- At app starts, it opens a Swing interface
- User choose between chess movements files (located in data directory)
- App executes all moves and alert user if some invalid movements where involved
- App verifies if opponent king it's on check status

## Chess Movements Example

```sh
e2e4
e7e5
f1c4
b8c6
d1f3
d7d6
f3f7
```

## Tech

ChessTest uses a number of open source projects to work properly:

- [Spring-Context] - version 5.3.4
- [Spring-Test] - version 5.3.4
- [slf4j-api] - version 1.7.30
- [jUnit] - version 4.13.2
- [JDK] - version 1.8

## Plugins

ChessTest is currently extended with the following plugins.
Instructions on how to use them in your own application are linked below.

| Plugin | README |
| ------ | ------ |
| GitHub | [plugins/github/README.md][PlGh] |



## License

MIT
