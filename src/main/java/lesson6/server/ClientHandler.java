package lesson6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private String nickname;
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public String getNickname() {
        return nickname;
    }

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            socket.setSoTimeout(10000);
        } catch (IOException e) {
            e.printStackTrace();
            Server.LOGGER.error(e);
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
            Server.LOGGER.error(e);
        }
    }

    public void disconnect() {
        server.unsubscribe(this);
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            Server.LOGGER.error(e);
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            Server.LOGGER.error(e);
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            Server.LOGGER.error(e);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = in.readUTF();
                if (msg.startsWith("/auth")) {
                    String[] tokens = msg.split("\\s");
                    String nick = DbAuthService.getNicknameByLoginAndPassword(tokens[1], tokens[2]);
                    if (nick != null && !server.isNickBusy(nick)) {
                        ClientHandler.this.sendMsg("/authOK " + nick);
                        nickname = nick;
                        server.subscribe(ClientHandler.this);
                        socket.setSoTimeout(0);
                        Server.LOGGER.info("Client is connected - " + nickname);
                        break;
                    }
                }
            }
            while (true) {
                String msg = in.readUTF();
                if (msg.startsWith("/")) {
                    if (msg.equals("/end")) {
                        ClientHandler.this.sendMsg("/end");
                        Server.LOGGER.info("Client " + ClientHandler.this.nickname + " disconnected");
                        break;
                    }
                    if (msg.startsWith("/w")) {
                        String[] tokens = msg.split("\\s", 3);
                        server.privateMsg(ClientHandler.this, tokens[1], tokens[2]);
                        Server.LOGGER.info("Client " + ClientHandler.this.nickname + " is send PM to " + tokens[1] + " : " + tokens[2]);
                    }
                    if (msg.startsWith("/c")) {
                        String[] tokens = msg.split("\\s", 2);
                        DbAuthService.changeNickName(tokens[1], ClientHandler.this.getNickname());
                        server.unsubscribe(ClientHandler.this);
                        nickname = tokens[1];
                        server.subscribe(ClientHandler.this);
                    }
                } else {
                    server.broadcastMsg(nickname + ": " + msg);
                    Server.LOGGER.info("User " + nickname + " send message: " + msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Server.LOGGER.error(e);
        } finally {
            ClientHandler.this.disconnect();
        }
    }
}