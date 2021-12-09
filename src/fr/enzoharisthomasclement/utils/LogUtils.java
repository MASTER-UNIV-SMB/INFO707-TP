package fr.enzoharisthomasclement.utils;

import java.util.Random;

public class LogUtils {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void logOperateur(String message){
        System.out.println(ANSI_RED + "[OPERATEUR] " + ANSI_RESET + " " + message);
    }

    public static void logThread(String message){
        System.out.println(ANSI_YELLOW + "[THREAD] " + ANSI_RESET + " " + message);
    }

    public static void logTrainLine(String message){
        System.out.println(ANSI_BLUE + "[LIGNE] " + ANSI_RESET + " " + message);
    }

    public static void logNode(String message){
        System.out.println(ANSI_CYAN + "[NOEUD] " + ANSI_RESET + " " + message);
    }

    public static void logGarage(String message){
        System.out.println(ANSI_PURPLE + "[GARAGE] " + ANSI_RESET + " " + message);
    }

}
