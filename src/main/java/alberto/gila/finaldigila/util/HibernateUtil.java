package alberto.gila.finaldigila.util;

import alberto.gila.finaldigila.model.Videojuego;
import alberto.gila.finaldigila.model.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // 1. Crea el registro de servicios (lee hibernate.cfg.xml)
                registry = new StandardServiceRegistryBuilder()
                        .configure()
                        .build();

                // 2. Crea los metadatos
                MetadataSources sources = new MetadataSources(registry);

                // AÑADIMOS LAS CLASES MANUALMENTE PARA ASEGURAR
                sources.addAnnotatedClass(Usuario.class);
                sources.addAnnotatedClass(Videojuego.class);
                // --------------------------------------------------------------------

                Metadata metadata = sources.getMetadataBuilder().build();

                // 3. Crea la SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

                System.out.println("✅ Hibernate iniciado correctamente. Tablas revisadas/creadas. ✅");

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}