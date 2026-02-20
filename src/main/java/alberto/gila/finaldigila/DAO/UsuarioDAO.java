package alberto.gila.finaldigila.DAO;

import alberto.gila.finaldigila.model.Usuario;
import alberto.gila.finaldigila.util.HibernateUtil;
import alberto.gila.finaldigila.util.SecurityUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioDAO {

    private static UsuarioDAO i;
    public static UsuarioDAO getInstance() { return i == null ? i = new UsuarioDAO() : i; }

    public boolean guardar(Usuario u) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = s.beginTransaction();
            s.merge(u);
            t.commit();
            return true;
        } catch (Exception e) { return false; }
    }
    public Usuario validarLogin(String user, String pass) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {

            Usuario u = s.createQuery("FROM Usuario WHERE username = :u", Usuario.class)
                    .setParameter("u", user).uniqueResult();

            if (u != null && SecurityUtil.checkPassword(pass, u.getPassword())) {
                return u;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}