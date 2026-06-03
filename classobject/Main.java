package classobject;

public class  Main {
    public static void main(String[] args) {
        var textBox1 = new TextBox();
        textBox1.setText("Hello, World!");
        System.out.println(textBox1.text.toLowerCase());

        var textBox2 = new TextBox();
        textBox2.setText("Hello, Java!");
        System.out.println(textBox2.text.toLowerCase());

        // Memory allocation heap and stack
        var textBox3 = new TextBox();
        var textBox4 = textBox3;
        textBox4.setText("Hello, Spring!");
        System.out.println(textBox3.text.toLowerCase());
    }
}
