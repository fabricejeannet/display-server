package persistence;

/**
 * Created by fabricejeannet on 05/04/15.
 */
public abstract class Repositories {

    public static void initialise(Repositories instance) {
        Repositories.instance = instance;
    }

    public static RepositoryMessages messages() {
        return instance.repositoryMessages();
    }


    protected abstract RepositoryMessages repositoryMessages();



    private static Repositories instance;
}
