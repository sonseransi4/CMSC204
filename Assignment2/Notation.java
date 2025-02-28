public class Notation {
	//@author Sonse Ransibrahmanakul
    //Get operator precedence: '^' -> 3, '*'/'/' -> 2, '+'/'-' -> 1, others -> -1.
    private static int getPrecedence(char op) 
    {
        switch (op) 
        {
            case '^': 
                return 3;
            case '*': 
            case '/': 
                return 2;
            case '+': 
            case '-': 
                return 1;
            default:
                return -1;
        }
    }

    //Check if a character is an operator
    private static boolean isOperator(char ch) 
    {
        return getPrecedence(ch) != -1;
    }
    //Default
    public Notation()
    {
        
    }
    //Convert infixexpression to postfix
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
        MyQueue<String> outQ = new MyQueue<>(infix.length());
        MyStack<Character> opStack = new MyStack<>(infix.length());
        
        for (int i = 0; i < infix.length(); i++)
        {
            char token = infix.charAt(i);
            if (token == ' ') 
            {
                continue;
            }
            if (Character.isDigit(token)) {
                try 
                {
                    outQ.enqueue(String.valueOf(token));
                } catch (QueueOverflowException e) {
                    throw new InvalidNotationFormatException("Invalid Format");
                }
            } else if (token == '(') {
                try 
                {
                    opStack.push(token);
                } catch (StackOverflowException e) {
                    throw new InvalidNotationFormatException("Invalid Format");
                }
            } else if (isOperator(token)) 
            {
                try 
                {
                    while (!opStack.isEmpty() && getPrecedence(opStack.top()) >= getPrecedence(token))
                    {
                        outQ.enqueue(String.valueOf(opStack.pop()));
                    }
                    opStack.push(token);
                } catch (StackUnderflowException | StackOverflowException | QueueOverflowException e) 
                {
                    throw new InvalidNotationFormatException("Invalid Format");
                }
            } 
            else if (token == ')') 
            {
                try 
                {
                    while (!opStack.isEmpty() && opStack.top() != '(') 
                    {
                        outQ.enqueue(String.valueOf(opStack.pop()));
                    }
                    if (opStack.isEmpty()) {
                        throw new InvalidNotationFormatException("Invalid Format");
                    }
                    opStack.pop();
                } catch (StackUnderflowException | QueueOverflowException e) 
                {
                    throw new InvalidNotationFormatException("Invalid Format");
                }
            } 
            else
            {
                throw new InvalidNotationFormatException("Invalid Format");
            }
        }
        
        try {
            while (!opStack.isEmpty()) 
            {
                char op = opStack.pop();
                if (op == '(' || op == ')') {
                    throw new InvalidNotationFormatException("Invalid Format");
                }
                outQ.enqueue(String.valueOf(op));
            }
        } 
        catch (StackUnderflowException | QueueOverflowException e)
        {
            throw new InvalidNotationFormatException("Invalid Format");
        }
        
        return outQ.toString();
    }

    //Convert postfix expression to infix
    public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
        MyStack<String> exStk = new MyStack<>(postfix.length());
        
        for (int i = 0; i < postfix.length(); i++)
        {
            char token = postfix.charAt(i);
            if (token == ' ') {
                continue;
            }
            if (Character.isDigit(token)) {
                try {
                    exStk.push(String.valueOf(token));
                } 
                catch (StackOverflowException e) 
                {
                    throw new InvalidNotationFormatException("Invalid Format");
                }
            } 
            else if (isOperator(token)) 
            {
                try {
                    String operand1 = exStk.pop();
                    String operand2 = exStk.pop();
                    String combined = "(" + operand2 + token + operand1 + ")";
                    exStk.push(combined);
                } catch (StackUnderflowException | StackOverflowException e) {
                    throw new InvalidNotationFormatException("Invalid Format");
                }
            } 
            else
            {
                throw new InvalidNotationFormatException("Invalid Format");
            }
        }
        
        try {
            if (exStk.size() != 1)
            {
                throw new InvalidNotationFormatException("Invalid Format");
            }
            return exStk.top();
        } 
        catch (StackUnderflowException e) 
        {
            throw new InvalidNotationFormatException("Invalid Format");
        }
    }

    //Evaluate a postfix expression and return the result
    public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
        MyStack<String> evStk = new MyStack<>(postfixExpr.length());
        
        for (int i = 0; i < postfixExpr.length(); i++) 
        {
            char token = postfixExpr.charAt(i);
            if (token == ' ') 
            {
                continue;
            }
            if (Character.isDigit(token) || token == '(') 
            {
                try 
                {
                    evStk.push(String.valueOf(token));
                } 
                catch (StackOverflowException e) 
                {
                    throw new InvalidNotationFormatException("Invalid Format");
                }
            } else if (isOperator(token)) {
                try
                {
                    double operand1 = Double.parseDouble(evStk.pop());
                    double operand2 = Double.parseDouble(evStk.pop());
                    double result;
                    switch (token) {
                        case '+': result = operand2 + operand1; break;
                        case '-': result = operand2 - operand1; break;
                        case '*': result = operand2 * operand1; break;
                        case '/': result = operand2 / operand1; break;
                        case '^': result = Math.pow(operand2, operand1); break;
                        default: throw new InvalidNotationFormatException("Invalid Format");
                    }
                    evStk.push(String.valueOf(result));
                } catch (StackUnderflowException | StackOverflowException e) {
                    throw new InvalidNotationFormatException("Invalid Format");
                }
            } 
            else
            {
                throw new InvalidNotationFormatException("Invalid Format");
            }
        }
        
        try {
            if (evStk.size() != 1) {
                throw new InvalidNotationFormatException("Invalid Format");
            }
            return Double.parseDouble(evStk.top());
        } 
        catch (StackUnderflowException e)
        {
            throw new InvalidNotationFormatException("Invalid Format");
        }
    }
}

//Exception for the invalid notation format
class InvalidNotationFormatException extends Exception 
{
    public InvalidNotationFormatException(String errormessage) {
        super(errormessage);
    }
}
