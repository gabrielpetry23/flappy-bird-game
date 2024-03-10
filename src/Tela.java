import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Tela extends JPanel implements ActionListener {
    static final int larguraTela = 360;
    static final int alturaTela = 640;

    private Image background;
    private Image birdImg;
    private Image tuboCimaImg;
    private Image tuboBaixoImg;

    private Bird bird;
    private int velocityY = -6;
    private int gravity = 1;

    private Timer timer;

    Tela () {
        setPreferredSize(new Dimension(larguraTela, alturaTela));

        background = new ImageIcon(getClass().getResource("/flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("/flappybird.png")).getImage();
        tuboCimaImg = new ImageIcon(getClass().getResource("/toppipe.png")).getImage();
        tuboBaixoImg = new ImageIcon(getClass().getResource("/bottompipe.png")).getImage();
        bird = new Bird(birdImg);
        timer = new Timer(1000 / 60, this); //60 frames por seg
        timer.start();
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        desenhar(g);
    }

    public void desenhar (Graphics g) {
        //ele vai printando isso no tempo botei no timer (60f/s)
        g.drawImage(background, 0, 0, larguraTela, alturaTela, null);
        g.drawImage(bird.img, bird.eixoX, bird.eixoY, bird.largura, bird.altura, null);
    }

    public void pular () {
        velocityY += gravity;
        bird.eixoY += velocityY; 
        bird.eixoY = Math.max(bird.eixoY, 0); //pra não ultrapassar topo da tela
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pular();
        repaint();
    }
}
