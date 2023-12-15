package wtf.sikelio.javafxchat.server;

import wtf.sikelio.javafxchat.common.Message;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ConnectedClient implements Runnable {
    private static Integer _idCounter = 0;
    private Integer _id;
    private Server _server;
    private Socket _socket;
    private ObjectOutputStream _out;
    private ObjectInputStream _in;

    public ConnectedClient(Server server, Socket socket) {
        this._server = server;
        this._socket = socket;
        this._id = _idCounter;

        ConnectedClient._idCounter++;

        try {
            this._out = new ObjectOutputStream(this._socket.getOutputStream());

            System.out.println("New connection, id: " + this._id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            this._in = new ObjectInputStream(this._socket.getInputStream());

            boolean isActive = true;

            while (true) {
                Message message = (Message) this._in.readObject();
                message.setSender(String.valueOf(this._id));
                this._server.broadcastMessage(message, this._id);
            }
        } catch (EOFException | SocketException e) {
            System.out.println("Client " + this._id + " disconnected.");
            this._server.disconnectedClient(this);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeClient();
        }
    }

    public void sendMessage(Message message) {
        try {
            this._out.writeObject(message);
            this._out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer getId() {
        return this._id;
    }

    public void setId(Integer id) {
        this._id = id;
    }

    public void closeClient() {
        try {
            if (this._in != null) this._in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (this._out != null) this._out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (this._socket != null) this._socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
