package stringbalancing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


class Stack {
        
    private List<Character> elements;

    public Stack() {
        this.elements = new ArrayList<Character>();
    }
        
    public Stack(List<Character> elements) {
        this.elements = new ArrayList<>(elements);
    }
    
    public int size() {
        return this.elements.size();
    }
        
    public void push(Character element) {
        this.elements.add(element);
    }
        
    public Character pop() {
        if (isEmpty())
            return null;
 
        var element = elements.get(size() - 1);
        elements.remove(size() - 1);
        return element;
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }

}

public class Balanced {

    public static void main(String[] args) {
        
        var matches = Map.of('{', '}', '[', ']', '(', ')');
        var openElems = matches.keySet();
        
        try (var scanner = new Scanner(System.in)) {
            
            while(scanner.hasNext()) {
                
                var line = scanner.nextLine();
                
                var closeStack = new Stack();
                
                if (line.length() % 2 != 0) {
                    System.out.println(false);
                    continue;
                }
                
                var result = true;
                var index = line.length() - 1;
                while (index >= 0) {
                    var c = line.charAt(index);
                    if (openElems.contains(c)) {
                        var closeElem = closeStack.pop();
                        if (matches.get(c) != closeElem) {
                            result = false;
                            break;
                        } 
                    }
                    else
                        closeStack.push(c);
                        
                    index--;
                }                 

                if (closeStack.size() > 0)
                    result = false;

                System.out.println(result);
            }
        }
    }
}
