import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Tela extends JPanel implements ActionListener, KeyListener {
    static final int larguraTela = 360;
    static final int alturaTela = 640;

    private Image background;
    private Image birdImg;
    private Image tuboCimaImg;
    private Image tuboBaixoImg;

    private Bird bird;
    private int velocityX = -4; //movimentação tubo, dando a impressão que bird ta andando para direita
    private int velocityY = 0; //movimentação bird
    private int gravity = 1;

    ArrayList<Tubo> tubos;

    private Timer timerJogo;
    private Timer timerTubos;

    Tela () {
        setPreferredSize(new Dimension(larguraTela, alturaTela));
        setFocusable(true);
        addKeyListener(this);

        background = new ImageIcon(getClass().getResource("/flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("/flappybird.png")).getImage();
        tuboCimaImg = new ImageIcon(getClass().getResource("/toppipe.png")).getImage();
        tuboBaixoImg = new ImageIcon(getClass().getResource("/bottompipe.png")).getImage();
        bird = new Bird(birdImg);
        tubos = new ArrayList<Tubo>();
        timerJogo = new Timer(1000 / 60, this); //60 frames por seg
        timerJogo.start();
        timerTubos = new Timer(1500, new ActionListener() { //vai printar os tubos a cada 1,5 seg
            @Override
            public void actionPerformed(ActionEvent e) {
                addTubos();
            }
        });
        timerTubos.start();
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        desenhar(g);
    }

    public void desenhar  (Graphics g) {
        //ele vai printando isso no tempo botei no timer (60f/s)
        g.drawImage(background, 0, 0, larguraTela, alturaTela, null);
        g.drawImage(bird.img, bird.eixoX, bird.eixoY, bird.largura, bird.altura, null);
        //desenhar tubos
        for (int i = 0 ; i < tubos.size() ; i++) {
            Tubo tubo = tubos.get(i);
            g.drawImage(tubo.img, tubo.eixoX, tubo.eixoY, tubo.largura, tubo.altura, null);
        }

    }

    public void pular () {
        //bird 
        velocityY += gravity; //gravidade aplicada
        bird.eixoY += velocityY; //movendo para baixo
        bird.eixoY = Math.max(bird.eixoY, 0); //pra não ultrapassar topo da tela
        //tubos
        for (int i = 0 ; i < tubos.size() ; i++) {
            Tubo tubo = tubos.get(i);
            tubo.eixoX += velocityX; //movendo para esquerda
        }
    }

    public void addTubos () {
        Tubo tuboCima = new Tubo(tuboCimaImg);
        tubos.add(tuboCima);
        //Tubo tuboBaixo = new Tubo(tuboBaixoImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pular();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -8;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
