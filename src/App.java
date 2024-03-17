import javax.swing.*;

public class App extends JFrame  {
    public static void main(String[] args) {
        new App();
    }

    App () {
        Tela telaJogo = new Tela();
        add(telaJogo);
        setTitle("FlappyBird");
        //setLocationRelativeTo(null); // Centralizar o JFrame
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Ajustar o tamanho do JFrame
        telaJogo.requestFocus();
        setVisible(true);
    }
}