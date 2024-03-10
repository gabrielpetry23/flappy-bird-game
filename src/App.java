import javax.swing.*;

public class App extends JFrame  {
    public static void main(String[] args) {
        new App();
    }

    App () {
        Tela telaJogo = new Tela();
        add(telaJogo);
        setTitle("FlappyBird");
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        telaJogo.requestFocus();
    }
}