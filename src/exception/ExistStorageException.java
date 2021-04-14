package exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("ERROR: Резюме " + uuid + " уже внесено в базу", uuid);
    }
}
