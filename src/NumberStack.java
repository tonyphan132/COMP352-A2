public class NumberStack {
    private String[] values;
    private int currentIndex;

    public NumberStack(){
        values = new String[1];
    }
    public NumberStack(int size){
        values = new String[size];
    }

    public void push(String value){
        values[currentIndex] = value;
        currentIndex++;
        if (currentIndex == values.length){
            resize();
        }
    }

    public String pop(){
        if (currentIndex == 0){
            return null;
        }
        String returnStr = values[--currentIndex];
        values[currentIndex] = null;
        return returnStr;
    }

    private void resize(){
        String[] tempStack = new String[values.length * 2];
        for (int i = 0; i < currentIndex; i++){
            tempStack[i] = values[i];
        }
        values = tempStack;
    }

    public int getSize(){
        return currentIndex;
    }

}
