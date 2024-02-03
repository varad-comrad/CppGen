#!/usr/bin/bash

# Creating the environment variable

export CPP_TEMPLATE_1J=$(pwd) >> ~/.bashrc


# Compiling the project into a .jar

mvn clean package


# Creating the alias for the executable

echo "alias cppgen='java -jar $CPP_TEMPLATE_1J/target/cpp-template-1.0-SNAPSHOT-jar-with-dependencies.jar'" >> ~/.bashrc