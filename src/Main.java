import java.lang.Math;
public class Main {
    public static void main(String[] args) {
        System.out.println(evaluateExpression("2+2*4+4"));
        //2*4 = 8
        //8+2 + 4 = 14
    }

    public static String evaluateExpression(String expression){
        OperatorStack opStack = new OperatorStack();
        NumberStack valueStack = new NumberStack();
        String[] arr = expression.split("");

        for (String token: arr){
            if (isNumber(token)){
                System.out.println("Number token: " + token);
                valueStack.push(token);
            }
            else{
                System.out.println("Operation token: " + token);
                repeatOperations(token, valueStack, opStack);
                opStack.push(token);
            }
        }
        repeatOperations("$", valueStack, opStack);
        return valueStack.pop();
    }

    private static void repeatOperations(String operation, NumberStack valueStack, OperatorStack operatorStack){
        System.out.println(valueStack.getSize() > 1);
        System.out.println(precedence(operation) >= precedence(operatorStack.top()));
        while (valueStack.getSize() > 1 && precedence(operation) >= precedence(operatorStack.top())){
            doOperation(valueStack, operatorStack);
        }
    }
    // 1 +

    private static void doOperation(NumberStack valueStack, OperatorStack operatorStack){
        String x = valueStack.pop();
        String y = valueStack.pop();
        double x1 = Double.parseDouble(x);
        double y1 = Double.parseDouble(y);
        String operator = operatorStack.pop();
        double answer = 0;
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
        }
        valueStack.push("" + answer);
    }

    private static boolean isNumber(String s1){
        try{
            int number = Integer.parseInt(s1);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static int precedence(String op){
        if (op == null){
            return -1;
        }
        if (op.equals("(") || op.equals(")")){
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

    //If closing parenthesis encountered push (value.pop OP value.POP)
    


}

