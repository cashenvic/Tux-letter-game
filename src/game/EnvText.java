/*
gestion d'affichage de texte à l'écran
 */
package game;

import env3d.Env;

public class EnvText {
    
    private int x;
    private int y;
    private String text;
    private double size;
    private double r;
    private double g;
    private double b;
    private double a;
    private final Env env;
    
    
    private int adjustXValue(int x){
        if (x<0) return 0;
        return x;
    }

    private int adjustYValue(int y){
        if (y<0) return 0;
        return y;
    }
    
    private double adjustColorValue(double v){
        if (v<0.0) return 0.0;
        if (v>1.0) return 1.0;
        return v;
    }
    
    private double adjustSizeValue(double v){
        if (v<0.1) return 0.1;
        return v;
    }
    
    
    public EnvText(Env env, String text, int x, int y){
        this.x = adjustXValue(x);
        this.y = adjustXValue(y);
        this.text = text;    
        a = 1.0;
        size = 1.0;
        r = 1.0;
        g = 1.0;
        b = 1.0;
        this.env = env;
    }

    
    public EnvText(Env env, String text, int x, int y,double size, double r, double g, double b, double a){
        this.x = x;
        if (x<0) this.x=0;
        this.y = y;
        if (y<0) this.y=0;
        this.text = text;    
        this.a = adjustColorValue(a);
        this.size = adjustSizeValue(size);
        this.r = adjustColorValue(r);
        this.g = adjustColorValue(g);
        this.b = adjustColorValue(b);
        this.env=env;
    }
    
    public void display(){
        env.setDisplayStr(text, x, y, size, r, g, b, a);
    }
    
    public void display(double size, double r, double g, double b, double a){
        this.a = adjustColorValue(a);
        this.size = adjustSizeValue(size);
        this.r = adjustColorValue(r);
        this.g = adjustColorValue(g);
        this.b = adjustColorValue(b);        
        env.setDisplayStr(text, x, y, this.size, this.r, this.g, this.b, this.a);        
    }
    
    void move(int x, int y){
        erase();
        this.x = adjustXValue(x);
        this.y = adjustXValue(y);
        env.setDisplayStr(text, x, y, size, r, g, b, a);        
    }
    
    void modify(String text){
        erase();
        this.text = text;
        display();        
    }
    
    void erase(){
        env.setDisplayStr("", x, y, size, r, g, b, a);        
    }
}
