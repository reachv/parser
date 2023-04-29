import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Map<String, List<String>> parser = new HashMap<>();
        List<String> pa = new ArrayList<>();

        parser.put("E", Arrays.asList("TQ"));
        parser.put("Q", Arrays.asList("+TQ", "-TQ", "Z"));
        parser.put("T", Arrays.asList("FR"));
        parser.put("R", Arrays.asList("*FR", "/FR", "Z"));
        parser.put("F", Arrays.asList("(E)", "a"));

        List<StringBuilder> testCases = new ArrayList<>();
        StringBuilder t1 = new StringBuilder("(a+a)*a$");
        StringBuilder t2 = new StringBuilder("a*(a+a)$");
        StringBuilder t3 = new StringBuilder("a(a+a)$");
        testCases.add(t1);
        testCases.add(t2);
        testCases.add(t3);

        for(StringBuilder input : testCases){
            System.out.println("Stack Vs Input");
            StringBuilder stack = new StringBuilder("E$");
            System.out.println(stack + " vs " + input);
            boolean res = true;
            while((stack.charAt(0)!=input.charAt(0))){
                if(parser.get(String.valueOf(stack.charAt(0))) == null){
                    res = false;
                    break;
                }
                if(parser.get(String.valueOf(stack.charAt(0))).size() <= 1){
                    stack.replace(0, 1, parser.get(String.valueOf(stack.charAt(0))).get(0));
                    System.out.println(stack + " vs " + input);
                    continue;
                }
                List<String> temp = parser.get(String.valueOf(stack.charAt(0)));
                Boolean x = false;
                for(int i = 0; i < temp.size(); i++){
                    StringBuilder tem = new StringBuilder(temp.get(i));
                    if(tem.charAt(0) == input.charAt(0)){
                        if(tem.charAt(0) == '('){
                            input.replace(input.indexOf("("), input.indexOf("(") + 1, "");
                            input.replace(input.indexOf(")"), input.indexOf(")") + 1, "");
                            stack.replace(0, 1, "E");
                            x = true;
                            break;
                        }
                        tem.replace(0, 1, "");
                        input.replace(0 , 1, "");
                        stack.replace(0, 1, tem.toString());
                        x = true;
                        break;
                    }
                }
                if(!x) {
                    if (parser.get(String.valueOf(stack.charAt(0))).contains("Z")) {
                        stack.replace(0, 1, "");
                    } else {
                        System.out.println(input);
                        res = false;
                        break;
                    }
                }
                System.out.println(stack + " vs " + input);
            }
            if(res){
                System.out.println("The string is valid\n");
                continue;
            }
            System.out.println("The string is not valid\n");
        }

    }
}