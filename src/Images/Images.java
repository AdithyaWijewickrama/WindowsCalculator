package Images;

import com.codes.Commons;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 *
 * @author adith
 */
public enum Images {
    SHOWPSW("src\\Images\\ShowPassword-" + getColor() + ".png"),
    HIDEPSW("src\\Images\\HidePassword-" + getColor() + ".png"),;

    public final ImageIcon IMG;

    private Images(String IMG) {
        this.IMG = new ImageIcon(IMG);
    }

    public ImageIcon getImage(int w, int h) {
        return Commons.getImage(IMG, w, h);
    }

    public static String getColor() {
        return UIManager.getLookAndFeel().getName().contains("dark") ? "white" : "black";
    }
}
