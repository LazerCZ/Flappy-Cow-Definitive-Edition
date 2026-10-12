import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Cow extends Sprite {
    private int lives;
    private double speed;
    private double jumpPower;
    private double gravity;

    public Cow(Texture asset, double baseSpeed, double JP){
        super(asset);
        this.speed = baseSpeed;
        this.jumpPower = JP;
    }

    public double getSpeed(){
        return speed;
    }

    public void changeSpeed(double value){
        speed += value;
    }
    public void jump(){
        gravity = jumpPower;
    }
    public int getHP(){
        return lives;
    }
    public void damageCow(int value){
        lives -= value;
    }
    public void skin(int value){
        Texture s1 = new Texture("gameAssets/Untitled Drawing.png");
        Texture s2 = new Texture("gameAssets/ChocoCow.png");
        Texture s3 = new Texture("gameAssets/StrawCow.png");
        switch (value){
            case 1:
                this.setTexture(s2);
                break;
            case 2:
                this.setTexture(s3);
                break;
            default:
                this.setTexture(s1);
                break;
        }
    }
    public void makeSound(){
        Sound moo = Gdx.audio.newSound(Gdx.files.internal("gameAssets/cow3.mp3"));
        moo.play();
    }
}
