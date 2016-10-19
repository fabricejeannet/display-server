package persistence.memory;

import persistence.Repositories;
import persistence.RepositoryMessages;

/**
 * Created by fabricejeannet on 13/10/2016.
 */
public class MemoryRepositories extends Repositories {

    protected RepositoryMessages repositoryMessages() {
        return repositoryMessages;
    }


    private MemoryRepositoryMessages repositoryMessages = new MemoryRepositoryMessages();
}
