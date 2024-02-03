#!/usr/bin/bash

# Creating the environment variable

echo "export CPP_TEMPLATE_1J=$(pwd)" >> ~/.bashrc
export CPP_TEMPLATE_1J=$(pwd)

# Compiling the project into a .jar

mvn clean package


# Creating the alias for the executable

echo "alias cppgen='java -jar $CPP_TEMPLATE_1J/target/CppTemplate-1.0-SNAPSHOT-jar-with-dependencies.jar'" >> ~/.bashrc