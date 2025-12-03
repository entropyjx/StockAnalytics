# stock-analytics

Notes:
This application is a fully functioning rest api that calculates data from quandl.

I used HashMaps for O(1) lookups.
This allows the runtime to be O(N).

I made the api key, api url (quandl) and the threshold for busy day volume configurable under application.properties.

I used BigDecimal since it is more precise and it is recommended to use this Data Type when dealing with currencies, especially when dividing (averages).
	Averages are returned, rounded to 2 decimal places.

Setup:
1. Install Microsoft VS Code
2. Install Java, I use Amazon Coretta's Open JDK: https://docs.aws.amazon.com/corretto/latest/corretto-25-ug/downloads-list.html
3. Install Maven, I use brew: `brew install maven`
4. Set and confirm `JAVA_HOME`: 
For example, I set correta in JAVA_HOME and JAVA_PATH in my `~/.zshrc` mac os zsh profile, add the following lines:
```
    export JAVA_HOME=/Library/Java/JavaVirtualMachines/amazon-corretto-25.jdk/Contents/Home
    export PATH=$JAVA_HOME/bin:$PATH
```


In VS Code, make sure you have the following Extensions installed (some extensions might install others)

1. Spring Boot Extension Pack
2. Spring Boot Tools
3. Spring Boot Dashboard
4. Spring Initializr Java Support
5. Java Extension Pack
6. Lombok Annotations Support 
7. Language Support for Java(TM) by Red Hat

Open `\stock-analytics\pricing` folder with VS Code.

In terminal in VS Code, run: `mvn install`

Under `SRING-BOOT DASHBOARD` section, right click `pricing` and click `start`
Or if in the terminal, run: `mvn spring-boot:run`

Open up browser.
Type in: http://localhost:5000/coding-exercise

Or http://localhost:5000/monthly-average?stocks=COF,GOOG,MSFT&month-start=2017-01&month-end=2017-06

Both do the same thing.

## Trouble shooting Mac OS X

### Setting Maven's Java Home
1. Find Java Home on Mac:
```
> /usr/libexec/java_home
/Library/Java/JavaVirtualMachines/[ rest of path ]
```
2. Find where mvn's home is
```
mvn -version
Apache Maven 3.9.4 (dfbb324ad4a7c8fb0bf182e6d91b0ae20e3d2dd9)
Maven home: /usr/local/.../maven/3.9.4/libexec
```
3. Open mvn's config
```
vim /usr/local/.../maven/3.9.4/bin/mvn
```
4. Change java home to maven config:
```
#!/bin/bash
JAVA_HOME="${JAVA_HOME:-/Library/Java/JavaVirtualMachines/[ rest of path ]}" exec "/usr/local/Cellar/maven/3.9.4/libexec/bin/mvn"  "$@"
```

### Deleting JDKs that don't work with Maven

1. Delete old SDKs
```
cd /Library/Java/JavaVirtualMachines
sudo rm -rf <jdk version>
```

2. Install Amazon Correto SDK:
Download and install the Mac OS dmg file:
https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html

3. Follow above steps to set maven's java home path.