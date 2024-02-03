# CppGen

CppGen is a C++ project generator CLI tool. 

## Requirements

- Java
- Maven
- Git
- Bash (for Linux users)

## Installation

For Linux users, you can use the following commands to install the tool:

```bash
git clone https://github.com/varad-comrad/CppTemplateGen
cd CppTemplateGen
chmod +x setup.sh
./setup.sh
```

For Windows users, it's the same, but you have to run the `setup.bat` file:

```bash
git clone https://github.com/varad-comrad/CppTemplateGen
cd CppTemplateGen
setup.bat
```

## Usage

To use the tool, you can use the following command:

```bash
cppgen -n <project-name>
```

This will create a new directory with the project name and generate the project files in it.

You can also use other flags, explained when executed:
```bash
cppgen -h
```

The flags are:
```
-b,--builder <arg>   Builder to use (premake or cmake for now)
-g,--git             Initialize a git repository
-h,--help            Show the help message
-i,--interactive     Interactive mode
-n,--name <arg>      Name of the project (Required argument)
-p,--path <arg>      Path of the project
-t,--type <arg>      Type of the project (binary, library) (default: binary)
-v,--version         Show the version of the executable
```                                             

