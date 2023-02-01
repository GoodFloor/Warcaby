package pl.tp.server;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

public class MovesHistory {
    private int gameId;
    private List<MovesHistoryEntry> history;
    public MovesHistory(int gameID) {
        this.gameId = gameID;
        history = new ArrayList<MovesHistoryEntry>();
    }
    public void addMove(String source, String destination) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        MovesHistoryEntry newMove = new MovesHistoryEntry(history.size(), source, destination, this);
        history.add(newMove);
        session.persist(this);
        session.getTransaction().commit();
        session.close();
    }
    public int getId() {
        return gameId;
    }
}
