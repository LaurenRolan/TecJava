package bean;

import java.sql.Date;

public class Inscription {
    Tournoi tournoi;
    Date date;

    public Inscription(Date date) {
        this.date = date;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
