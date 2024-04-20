package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A utility class for handling object streams.
 */
public class ObjectStreamHandler {
    private final ObjectOutputStream output;
    private final ObjectInputStream input;

    private final Logger logger = LogManager.getLogger(ObjectStreamHandler.class);

    public ObjectStreamHandler(Socket socket) throws IOException {
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    public void writeObject(Object obj) {
        try {
            output.writeObject(obj);
            output.flush();
        } catch (IOException e) {
            logger.error("Error closing streams {}", e.getMessage(), e);
        }
    }

    public Object readObject() throws IOException {
        try {
            return input.readObject();
        } catch (ClassNotFoundException e) {
            logger.error("Error closing streams {}", e.getMessage(), e);
            return null;
        }
    }

    public void close() throws IOException {
        output.close();
        input.close();
    }
}