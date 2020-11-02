package common;

import java.net.URI;
import java.net.URISyntaxException;

public class TestApp {

    public static void main(String[] args) {
        try {
            // open websocket
            final WebsocketClient clientEndPoint = new WebsocketClient(new URI("wss://app.qa.ivh.local:30011/bb8fbc95-b3ff-4c43-afd4-2c403a6b30a1"));

            // add listener
            clientEndPoint.addMessageHandler(new WebsocketClient.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            // send message to websocket
            clientEndPoint.sendMessage("OPTIONS * RTSP/1.0\r\nCSeq: 1\r\nUser-Agent: Performance Test Python RTSP client\r\n\r\n");

            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    	
    }
}
