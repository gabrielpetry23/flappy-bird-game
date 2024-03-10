import java.awt.Image;

public class Bird {
    int eixoX = Tela.larguraTela / 8;
    int eixoY = Tela.alturaTela / 2;
    int largura = 34;
    int altura = 24;
    Image img;

    Bird (Image img) {
        this.img = img;
    }
}
