package game;

public class Chronometre {
    private long begin;
    private long end;
    private long current;
    private int limite;

    /**
     * Constructeur d'un chronomètre dont le temps limie est passé en paramètre
     *
     * @param limite
     */
    public Chronometre(int limite) {
        //intialisation
        this.limite = limite;
    }

    /**
     * Demarre le chronomètre
     *
     */
    public void start(){
        begin = System.currentTimeMillis();
        //System.out.println("Temps begin : " + begin + " ms");
        this.end = this.begin + limite;
    }

    /**
     * Arrete le chronomètre
     *
     */
    public void stop(){
        end = this.current;
        //System.out.println("Temps begin : " + end + " ms");
    }

    /**
     * Donne le temps du chronomètre
     *
     * @return long
     */
    public long getTime() {
        current = System.currentTimeMillis();
        return (current - begin) / 1000;
    }

    /**
     * Donne le temps du chronomètre en millisecondes
     *
     * @return long
     *
     */
    public long getMilliseconds() {
        return end-begin;
    }

    /**
     * Donne le temps du chronomètre en secondes
     *
     * @return int
     *
     */
    public int getSeconds() {
        return (int) ((end - begin) / 1000.0);
    }

    /**
     * Donne le temps du chronomètre en minutes
     *
     * @return double
     */
    public double getMinutes() {
        return (end - begin) / 60000.0;
    }

    /**
     * Donne le temps du chronomètre en heures
     *
     * @return double
     */
    public double getHours() {
        return (end - begin) / 3600000.0;
    }
    
    /**
    * Méthode pour savoir s'il reste du temps pour la partie
     *
     * @return boolean
     */
    public boolean remainsTime() {
        current = System.currentTimeMillis();
        int timeSpent;
        timeSpent = (int) ((current - begin));
        return (limite >= timeSpent);
    }
     
}