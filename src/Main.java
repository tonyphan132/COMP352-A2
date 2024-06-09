import java.io.*;
import java.lang.Math;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner readExpression = null;
        try{
            readExpression = new Scanner(new FileInputStream("input.txt"));
            String s1 = readExpression.nextLine();
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }


    }

    /**
     * Method that will evaluate a mathematical expression given as a String, the String will be split up as tokens separated by a space.
     * Method will separate each token into a value stack or an operator stack.
     * If it is an operator, will also call repeatOperations function UNLESS it is an opening parenthesis.
     *
     * @param expression represents the mathematical expression to be evaluated
     * @return the answer of the mathematical expression as a string.
     */
    public static String evaluateExpression(String expression){
        OperatorStack opStack = new OperatorStack();
        NumberStack valueStack = new NumberStack();
        String[] arr = expression.split(" ");

        for (String token: arr){
            if (isNumber(token)){
                valueStack.push(token);
            }
            else if (token.equals("(")){
                opStack.push(token);
            }
            else if (token.equals(")")){
                repeatOperations(")", valueStack, opStack);
            }
            else{
                repeatOperations(token, valueStack, opStack);
                opStack.push(token);
            }
        }
        repeatOperations("$", valueStack, opStack);
        return valueStack.pop();
    }

    /**
     * Method will call doOperation method if the given operator has a smaller precedence than the top operator in the operator stack OR
     * if the given operator is a closing parenthesis ) , in which case all operation will be performed until an opening parenthesis ( is met.
     * Will only call doOperation if there are more than to operands in the valueStack
     *
     * @param operator is the operator given as a String value
     * @param valueStack Stack containing the operands
     * @param operatorStack Stack containing the operators
     */
    private static void repeatOperations(String operator, NumberStack valueStack, OperatorStack operatorStack){
        if (operator.equals(")")){
            while(valueStack.getSize() > 1 && !operatorStack.top().equals("(")){
                doOperation(valueStack, operatorStack);
            } // (5 * 2
            operatorStack.pop();
        }
        else{
            while (valueStack.getSize() > 1 && precedence(operator) >= precedence(operatorStack.top()) && !operatorStack.top().equals("(")){
                doOperation(valueStack, operatorStack);
            }
        }
    }

    /**
     * Method will perform the operation specified at the top of operator Stack with the two up-most number values of the value stack
     * and will append the value of the operation back into the value stack.
     *
     * @param valueStack Stack containing the operands
     * @param operatorStack Stack containing the operators
     */
    private static void doOperation(NumberStack valueStack, OperatorStack operatorStack){
        String x = valueStack.pop();
        String y = valueStack.pop();
        double x1 = Double.parseDouble(x);
        double y1 = Double.parseDouble(y);
        String operator = operatorStack.pop();
        double answer = 0;
        boolean booleanAnswer = false;
        boolean booleanOrNumber = false;
        switch(operator){
            case "^":
                answer = Math.pow(y1,x1);
                break;
            case "+":
                answer = y1 + x1;
                break;
            case "-":
                answer = y1 - x1;
                break;
            case "*":
                answer = y1 * x1;
                break;
            case "/":
                answer = y1 / x1;
                break;
            case "==":
                booleanAnswer = y1 == x1;
                booleanOrNumber = true;
                break;
            case "!=":
                booleanAnswer = y1 != x1;
                booleanOrNumber = true;
                break;
            case ">":
                booleanAnswer = y1 > x1;
                booleanOrNumber = true;
                break;
            case "<":
                booleanAnswer = y1 < x1;
                booleanOrNumber = true;
                break;
            case "≥":
                booleanAnswer = y1 >= x1;
                booleanOrNumber = true;
                break;
            case "≤":
                booleanAnswer = y1 <= x1;
                booleanOrNumber = true;
                break;
            }
            if (booleanOrNumber){
                valueStack.push("" + booleanAnswer);
            }
            else{
                valueStack.push("" + answer);
            }
    }

    /**
     * Method that verifies if a String is a valid integer or not.
     *
     * @param s1 String sequence that needs to be checked.
     * @return boolean value whether argument is a number or not.
     */
    private static boolean isNumber(String s1){
        try{
            Integer.parseInt(s1);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Method that returns the precedence of the operator
     *
     * @param op represents the operator that we want to get the precedence of.
     * @return Integer value representing precedence of operator.
     */
    public static int precedence(String op){
        if (op == null){
            return -1;
        }
        else if (op.equals("(") || op.equals(")")){
            return 1;
        }
        else if(op.equals("^")) {
            return 2;
        }
        else if(op.equals("*") || op.equals("/")) {
            return 3;
        }
        else if(op.equals("+") || op.equals("-")) {
            return 4;
        }
        else if(op.equals(">") || op.equals("<") || op.equals("≥") || op.equals("≤")) {
            return 5;
        }
        else if(op.equals("==") || op.equals("!=")){
            return 6;
        }
        else{
            return 7;
        }
    }
}

