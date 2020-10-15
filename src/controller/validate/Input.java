package controller.validate;

import java.util.Scanner;

public class Input {
    private static Input input;

    private Input(){

    }

    public static Input getInput(){
        if(input == null){
            input = new Input();
        }
        return input;
    }

    public String inputString(String message){
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int inputInt(String message){
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        int value = 0;
        boolean check;
        do {
            try {
                value = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (NumberFormatException e) {
                System.err.println("Input error, please try again!!!");
                check = true;
            }
        } while (check);
        return value;
    }

    public boolean inputGender(String message){
        ValidateGender validateGender = new ValidateGender();
        boolean isValid;
        String genderValidate;
        do {
            genderValidate = inputString(message);
            isValid = validateGender.validate(genderValidate);
            if(!isValid){
                System.err.println("Input error, please try again!!!");
            }
        } while (!isValid);
        if(genderValidate.equals("Y")||genderValidate.equals("y")){
            return true;
        }
        return false;
    }
}
