import java.awt.Image;

public class Tubo {
    int eixoX = Tela.larguraTela;
    int eixoY = 0;
    int largura = 64;
    int altura = 512;
    Image img;
    boolean passou = false;

    Tubo (Image img) {
        this.img = img;
    }
}
