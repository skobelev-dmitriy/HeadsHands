package rf.digworld.headhands.util;

import java.io.IOException;

public class NoConnectivityException extends IOException {

    @Override
    public String getMessage() {
        return "Отсутствует подключение к интернету";
    }

}