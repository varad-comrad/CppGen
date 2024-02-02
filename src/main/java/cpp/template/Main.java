package cpp.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.cli.*;

/**
 * Main
 * Class that will be compiled into a jar file
 * This class will be used to create the template for the C++ project
 *
 * @version 0.1
 *
 * The custom args of the executable are:
 * -h, --help: Show the help message (not required)
 * -v, --version: Show the version of the executable (not required)
 * -n, --name: Name of the project (required)
 * -p, --path: Path of the project (default: '.')
 * -b, --builder: Builder to use (premake or cmake for now) (default: cmake)
 * -i --interactive: Interactive mode (not required)
 * -t --type: Type of the project (binary, library) (default: binary)
 * -g --git: Initialize a git repository (not required)
 *
 */
public class Main {

    public  static String version = "0.1";


    public static void main(String[] args){
        Options options = getOptions();

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("h")) {
                formatter.printHelp("This C++ project generator has the following arguments ", options);
            } else if (cmd.hasOption("v")) {
                System.out.println("Version: " + version);
            } else if(cmd.hasOption("i")){
                interactiveCreationMode();
            } else {
                System.out.println("Building project...");
                Thread.sleep(150);
                String name = cmd.getOptionValue("n"); // String or null
                String path = cmd.getOptionValue("p"); // String or null
                String builder = cmd.getOptionValue("b"); // String or null
                String type = cmd.getOptionValue("t"); // String or null
                boolean git = cmd.hasOption("g"); // boolean

                if(path == null) path = ".";

                if(type == null) type = "bin";
                if(type.equals("lib") || type.equals("library")) type = "lib";
                else type = "bin";

                if(builder == null) builder = "cmake";

                System.out.println("Name: " + name);
                System.out.println("Path: " + path);
                System.out.println("Builder: " + builder);
                System.out.println("Type: " + type);
                buildProject(name, path, builder, type, git);
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void buildProject(String name, String path, String builder, String type, boolean git){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cd " + path + " && mkdir " + name + " && cd " + name + " && touch README.md" + "mkdir src docs includes scripts test" + (git ? " && git init && touch .gitignore .gitmodules" : ""));
            runCommand(processBuilder);

            if(builder.equals("premake")){
                buildPremakeProject(path, name, type);
            } else if(builder.equals("cmake")){
                buildCmakeProject(path, name, type);
            } else {
                throw new RuntimeException("Builder not supported");
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static void buildCmakeProject(String path, String name, String type) throws IOException{
        ProcessBuilder processBuilder = new ProcessBuilder("cd " + path + " && cd " + name + " && touch CMakeLists.txt && mkdir build cmake");
        runCommand(processBuilder);

        if (type.equals("lib")) {
            processBuilder = new ProcessBuilder("cd " + path + " && cd " + name + " && cd src && touch lib.cpp");
        } else {
            processBuilder = new ProcessBuilder("cd " + path + " && cd " + name + " && cd src && touch main.cpp");
        }

        runCommand(processBuilder);
    }

    private static void buildPremakeProject(String path, String name, String type) throws IOException{
        ProcessBuilder processBuilder = new ProcessBuilder("cd " + path + " && cd " + name + " && touch Build.lua && mkdir premake");
        runCommand(processBuilder);

        if (type.equals("lib")) {
            processBuilder = new ProcessBuilder("cd " + path + " && cd " + name + " && cd src && touch lib.cpp");
        } else {
            processBuilder = new ProcessBuilder("cd " + path + " && cd " + name + " && cd src && touch main.cpp");
        }
        runCommand(processBuilder);
    }

    private static Options getOptions() {
        Options options = new Options();
        Option option0 = new Option("h", "help", false, "Show the help message");
        option0.setRequired(false);
        Option option1 = new Option("v", "version", false, "Show the version of the executable");
        option1.setRequired(false);
        Option option2 = new Option("n", "name", true, "Name of the project (Required argument)");
        option2.setRequired(true);
        Option option3 = new Option("p", "path", true, "Path of the project");
        option3.setRequired(false);
        Option option4 = new Option("b", "builder", true, "Builder to use (premake or cmake for now)");
        option4.setRequired(false);
        Option option5 = new Option("i", "interactive", false, "Interactive mode");
        option5.setRequired(false);
        Option option6 = new Option("t", "type", true, "Type of the project (executable, library, test for now)");
        option6.setRequired(false);
        Option option7 = new Option("g", "git", false, "Initialize a git repository");
        option7.setRequired(false);


        options.addOption(option0);
        options.addOption(option1);
        options.addOption(option2);
        options.addOption(option3);
        options.addOption(option4);
        options.addOption(option5);
        options.addOption(option6);
        options.addOption(option7);
        return options;
    }

    private static void runCommand(ProcessBuilder processBuilder) throws IOException {
        Process process = processBuilder.start();
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    private static void interactiveCreationMode() {
    }
}