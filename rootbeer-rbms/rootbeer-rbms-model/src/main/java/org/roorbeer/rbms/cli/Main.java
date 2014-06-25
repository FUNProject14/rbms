/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.roorbeer.rbms.cli;

/**
 *
 * @author b1012120
 */
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
