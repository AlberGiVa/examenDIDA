package alberto.gila.finaldigila.DAO;


import alberto.gila.finaldigila.model.Videojuego;
import alberto.gila.finaldigila.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class VideojuegoDAO {

    private static VideojuegoDAO i;
    public static VideojuegoDAO getInstance() { return i == null ? i = new VideojuegoDAO() : i; }

    public List<Videojuego> obtenerTodos() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("FROM Equipo", Videojuego.class).list();
        } catch (Exception e) { return null; }
    }

    public void guardar(Videojuego e) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.merge(e);
            t.commit();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public void borrar(Videojuego e) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.remove(e);
            t.commit();
        } catch (Exception ex) { ex.printStackTrace(); }
    }
}