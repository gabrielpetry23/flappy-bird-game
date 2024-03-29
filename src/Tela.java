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

    private int tuboX = larguraTela;
    private int tuboY = 0;
    private int larguraTubo = 64;
    private int alturaTubo = 512;

    private int birdX = larguraTela / 8;
    private int birdY = alturaTela / 2;
    private int larguraBird = 34;
    private int alturaBird = 24;

    private Bird bird;

    private int velocityX = -4; //movimentação tubo, dando a impressão que bird ta andando para direita
    private int velocityY = 0; //movimentação bird
    private int gravity = 1;

    ArrayList<Tubo> tubos;
    Random random = new Random();

    private Timer timerJogo;
    private Timer timerTubos;
    private boolean gameOver = false;
    double score = 0;

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
        if (!gameOver) {
            g.drawImage(bird.img, bird.eixoX, bird.eixoY, bird.largura, bird.altura, null);
            //Tubos
            for (int i = 0 ; i < tubos.size() ; i++) {
                Tubo tubo = tubos.get(i);
                g.drawImage(tubo.img, tubo.eixoX, tubo.eixoY, tubo.largura, tubo.altura, null);
            }
        }

        //Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " +String.valueOf((int) score), larguraTela / 2/2, alturaTela/2);
        } else {
            g.drawString(String.valueOf((int) score), 10, 35);
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

            if (!tubo.passou && bird.eixoX > tubo.eixoX + tubo.largura) {
                tubo.passou = true;
                score += 0.5; //0.5 pois tem 2 tubos
            }

            if (encostouTubo(bird, tubo)) {
                gameOver = true;
            }
        }
        limiteCair();
    }

    public void limiteCair() {
        if (bird.eixoY > alturaTela) {
            gameOver = true;
        }
    }

    public boolean encostouTubo (Bird b, Tubo t) {
        return b.eixoX < t.eixoX + t.largura && 
        b.eixoX + b.largura > t.eixoX && 
        b.eixoY < t.eixoY + t.altura &&
        b.eixoY + b.altura > t.eixoY;
    }

    public void addTubos () {
        int randomTuboY = (int)  ((tuboY - (alturaTubo/4)) - (Math.random()*(alturaTubo/2))); //1/4 3/4 alturaTubo
        int espacoEntreTubos = alturaTela/4;
        Tubo tuboCima = new Tubo(tuboCimaImg);
        tuboCima.eixoY = randomTuboY;
        tubos.add(tuboCima);

        Tubo tuboBaixo = new Tubo(tuboBaixoImg);
        tuboBaixo.eixoY = tuboCima.eixoY + alturaTubo + espacoEntreTubos;
        tubos.add(tuboBaixo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pular();
        repaint();
        if (gameOver) {
            timerTubos.stop();
            timerJogo.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -8;
            if (gameOver) {
                bird.eixoY = birdY;
                velocityY = 0;
                tubos.clear();
                score = 0;
                gameOver = false;
                timerJogo.start();
                timerTubos.start();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
