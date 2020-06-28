package lesson2.server;

public interface AuthService {
    String getNicknameByLoginAndPassword(String login, String password);
}
