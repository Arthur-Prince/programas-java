package ep2;
/*
    Implementado por: Mauricio Mori Dantas Santana 7991170
*/

public class Registradores{

    private int ax, bx, cx, dx;

    Registradores(){
        this.ax = 0x0000;
        this.bx = 0x0000;
        this.cx = 0x0000;
        this.dx = 0x0000;
    }

    // getters e setters
    public int getAx(){
        return this.ax;
    }

    public void setAx(int ax){
        this.ax = ax;
    }

    public int getBx(){
        return this.bx;
    }

    public void setBx(int bx){
        this.bx = bx;
    }

    public int getCx(){
        return this.cx;
    }

    public void setCx(int cx){
        this.cx = cx;
    }

    public int getDx(){
        return this.dx;
    }

    public void setDx(int dx){
        this.dx = dx;
    }
}
