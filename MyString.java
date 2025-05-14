public class MyString 
{
    private String str;

    public MyString(String str) {
        this.str = str;
    }

    public int length() {
        int count = 0;
        for (char c : str.toCharArray()) {
            count++;
        }
        return count;
    }

    public String reverse() {
        char[] chars = new char[length()];
        for (int i = 0; i < length(); i++) {
            chars[i] = str.charAt(length() - 1 - i);
        }
        return new String(chars);
    }

    public String replace(char oldChar, char newChar) {
        char[] chars = new char[length()];
        for (int i = 0; i < length(); i++) {
            if (str.charAt(i) == oldChar) {
                chars[i] = newChar;
            } else {
                chars[i] = str.charAt(i);
            }
        }
        return new String(chars);
    }

    public String toUpperCase() {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                chars[i] = (char) (chars[i] - ('a' - 'A'));
            }
        }
        return new String(chars);
    }

    public String toLowerCase() {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                chars[i] = (char) (chars[i] + ('a' - 'A'));
            }
        }
        return new String(chars);
    }

    public String[] split(String delimiter) {
        int count = 1;
        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i, i + delimiter.length()).equals(delimiter)) {
                count++;
            }
        }
        String[] result = new String[count];
        int index = 0;
        String temp = "";
        for (int i = 0; i < str.length(); i++) {
            if (i <= str.length() - delimiter.length() && str.substring(i, i + delimiter.length()).equals(delimiter)) {
                result[index++] = temp;
                temp = "";
                i += delimiter.length() - 1;
            } else {
                temp += str.charAt(i);
            }
        }
        result[index] = temp;
        return result;
    }

    public String getString() {
        return str;
    }

    public static void main(String[] args) {
        MyString myStr = new MyString("My name is Krishna Sharma and my reg no. is AP22110010128");
        
        System.out.println("Original String: " + myStr.getString());
        System.out.println("Length: " + myStr.length());
        System.out.println("Reversed: " + myStr.reverse());
        System.out.println("Replaced 'o' with 'x': " + myStr.replace('a', 'x'));
        System.out.println("Uppercase: " + myStr.toUpperCase());
        System.out.println("Lowercase: " + myStr.toLowerCase());
        
        String[] parts = myStr.split(" ");
        System.out.println("Split by space:");
        for (String part : parts) {
            System.out.println(part);
        }
    }    
}