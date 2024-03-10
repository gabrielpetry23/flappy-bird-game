import java.awt.Image;

public class Bird {
    private int birdX = Tela.larguraTela / 8;
    private int birdY = Tela.alturaTela / 2;
    private int larguraBird = 34;
    private int alturaBird = 24;
    private Image img;

    Bird (Image img) {
        this.img = img;
    }

    public int getBirdX () {
        return this.birdX;
    }

    public int getBirdY () {
        return this.birdY;
    }

    public int getLarguraBird () {
        return this.larguraBird;
    }

    public Image getImg () {
        return this.img;
    }

    public int getAlturaBird () {
        return this.alturaBird;
    }
}
