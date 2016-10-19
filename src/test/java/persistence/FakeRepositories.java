package persistence;

/**
 * Created by fabricejeannet on 05/04/15.
 */
public class FakeRepositories extends Repositories {


    protected RepositoryMessages repositoryMessages() {
        return repositoryMessages;
    }


    private FakeRepositoryMessages repositoryMessages = new FakeRepositoryMessages();
}

