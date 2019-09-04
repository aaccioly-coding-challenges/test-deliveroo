# Cron Expression Parser

Idiomatic Cron expression parsing in Scala.

## Documentation

Cron Expression Parser is an utility tool meant to read a [*nix/ crontab][1] compatible expression and output expected 
running times.   

### Distribution

`cron-expression-parser` is packaged as a ZIP file. A binary distribution includes the main Application JAR file, the 
[Scala 2.13.0][3] runtime and all required underlying libraries. It also includes start scripts for Linux, OSX and 
Windows.

### Running

To run Cron Expression Parser navigate to the `bin` folder and run the script compatible with your platform:

```bash
cron-expression-parser */15 0 1,15 * 1-5 /usr/bin/find
```

Please, take a look at the [Technical Task Specification][2] for further instructions on how to use this application.

The remaining documentation will be focused on information for other developers.

### Building

`cron-expression-parser` is built using [SBT](https://www.scala-sbt.org):

To build from source and run tests, execute the following task in the project root directory: 

```bash
sbt test
```

#### Staging

Cron Expression Parser makes use of [SBT Native Packager][4] to generate start scripts and native packages.
 
In order to stage `cron-expression-parser` so that you can run it locally use the following command:

```bash
sbt stage
```

Scripts and libraries are created in the `stage` directory. `/bin` contains fully functional scripts:

```bash 
./target/universal/stage/bin/cron-expression-parser
```

#### Creating a package

To generate a end user distribution run the following task:

```bash 
sbt universal:packageBin
```

A ZIP file with everything required to run the application will be generated `/target/universal`.

While this is beyond the scope of the exercise, for information on how to build other types of packages (tar.gz, 
deb, rpm, dmg, msi, docker images, etc), please refer to [SBT Native Packager Documentation][5].  

[1]: http://man7.org/linux/man-pages/man5/crontab.5.html
[2]: Technical_Task_-_Write_a_Cron_Parser.pdf
[3]: https://www.scala-lang.org/
[4]: https://github.com/sbt/sbt-native-packager
[5]: https://www.scala-sbt.org/sbt-native-packager/index.html