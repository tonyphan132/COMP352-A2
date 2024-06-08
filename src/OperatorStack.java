public class OperatorStack {
    private String[] operatorStack;
    private int currentIndex = 0;

    public OperatorStack(){
        operatorStack = new String[1];
    }
    public OperatorStack(int size){
        operatorStack = new String[size];
    }

    public void push(String s1){
        operatorStack[currentIndex] = s1;
        currentIndex++;
        if (currentIndex == operatorStack.length){
            resize();
        }
    }



    public String pop(){
        if (currentIndex == 0){
            return null;
        }
        String returnChar = operatorStack[--currentIndex];
        operatorStack[currentIndex] = null;
        return returnChar;
    }

    private void resize(){
        String[] tempStack = new String[operatorStack.length * 2];
        for (int i = 0; i < currentIndex; i++){
            tempStack[i] = operatorStack[i];
        }
        operatorStack = tempStack;
    }

    public String top(){
        if (currentIndex == 0){
            return null;
        }
        return operatorStack[currentIndex - 1];
    }

}
