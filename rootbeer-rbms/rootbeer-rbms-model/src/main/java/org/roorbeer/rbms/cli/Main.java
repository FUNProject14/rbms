package org.roorbeer.rbms.cli;


public class Main {
    public static void main(String[] args){
        if(args.length == 0){
            System.out.printf("引数を指定して下さい");
            System.exit(0);
        }
        
        switch (args[0]){
            case "adduser":  
            case "buy":
            case "drink":
            case "getposts":
        }
    }
}
